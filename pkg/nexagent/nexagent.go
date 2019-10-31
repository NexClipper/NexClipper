package nexagent

import (
	"context"
	"fmt"
	pb "github.com/NexClipper/NexClipper/api"
	"github.com/denisbrodbeck/machineid"
	"github.com/shirou/gopsutil/cpu"
	"github.com/shirou/gopsutil/host"
	_ "github.com/shirou/gopsutil/net"
	"github.com/shirou/gopsutil/process"
	"google.golang.org/grpc"
	"google.golang.org/grpc/keepalive"
	"google.golang.org/grpc/metadata"
	"io"
	"io/ioutil"
	netutil "k8s.io/apimachinery/pkg/util/net"
	"k8s.io/client-go/kubernetes"
	"k8s.io/client-go/rest"
	"k8s.io/client-go/tools/clientcmd"
	"k8s.io/client-go/util/homedir"
	"log"
	"os"
	"path/filepath"
	"sigs.k8s.io/yaml"
	"time"
)

const (
	AppName          = "NexAgent"
	AppDescription   = "NexAgent for NexClipper Monitoring System"
	NexAgentVersion  = "0.3.0"
	K8sLeaseLockName = "nexagent-lease-lock"
)

var kacp = keepalive.ClientParameters{
	Time:                10 * time.Second,
	Timeout:             time.Second,
	PermitWithoutStream: true,
}

type NexAgent struct {
	connected bool
	hostName  string
	config    *Config

	ctx  context.Context
	conn *grpc.ClientConn

	uuid      string
	nodeId    string
	machineId string

	collectorClient pb.CollectorClient

	reportInterval       time.Duration
	updateStatusInterval time.Duration

	useK8sMetric bool
	k8sClientSet *kubernetes.Clientset

	processInfoMap map[int32]*ProcessInfo
	lastCheckTS    time.Time

	k8sConfig *rest.Config
	hostInfo  *host.InfoStat
}

type AgentConfig struct {
	Cluster        string
	ServerAddress  string
	ReportInterval int
	ApiPort        int
}

type TLSConfig struct {
	Use      bool
	CertFile string
	KeyFile  string
}

type KubernetesConfig struct {
	ClusterName string
	Namespace   string
}

type Config struct {
	Agent      AgentConfig
	TLS        TLSConfig
	Kubernetes KubernetesConfig
}

type ProcessInfo struct {
	updated bool
	ps      *process.Process
	mem     *process.MemoryInfoStat
	cpu     *cpu.TimesStat
}

type BasicMetric struct {
	Name  string
	Label string
	Type  string
	Value float64
}

type BasicMetrics []*BasicMetric

func (s *NexAgent) connectServer() (*grpc.ClientConn, error) {
	conn, err := grpc.Dial(
		s.config.Agent.ServerAddress,
		grpc.WithInsecure(),
		grpc.WithBlock(),
		grpc.WithTimeout(10*time.Second),
		grpc.WithKeepaliveParams(kacp))
	if err != nil {
		return nil, err
	}

	s.conn = conn

	return s.conn, nil
}

func (s *NexAgent) saveContext(agentUuid string) {
	md := metadata.Pairs("UUID", agentUuid)
	ctx := metadata.NewOutgoingContext(context.Background(), md)

	s.ctx = ctx
}

func (s *NexAgent) resetContext() {
	s.ctx = context.Background()
}

func (s *NexAgent) updateAgent() {
	defer func() {
		if r := recover(); r != nil {
			log.Printf("updateAgent: %v\n", r)
		}
	}()

	if s.connected == false {
		return
	}

	hostInfo, err := host.Info()
	if err != nil {
		log.Fatalf("Failed to get host information: %v", err)
	}
	s.hostName = hostInfo.Hostname
	s.hostInfo = hostInfo

	ip, err := netutil.ChooseHostInterface()
	if err != nil {
		log.Fatalf("Failed to get IP address: %v", err)
	}

	system, role, _ := host.Virtualization()

	nodeInfo := &pb.Node{
		Host:                 hostInfo.Hostname,
		Os:                   hostInfo.OS,
		Platform:             hostInfo.Platform,
		PlatformFamily:       hostInfo.PlatformFamily,
		PlatformVersion:      hostInfo.PlatformVersion,
		VirtualizationSystem: system,
		VirtualizationRole:   role,
		Uptime:               hostInfo.Uptime,
		Ipv4:                 ip.String(),
		Ipv6:                 "",
	}

	agentInfo := &pb.Agent{
		Version:   NexAgentVersion,
		Cluster:   s.config.Agent.Cluster,
		Node:      nodeInfo,
		MachineId: s.machineId,
	}

	ctx := context.Background()
	resp, err := s.collectorClient.UpdateAgent(ctx, agentInfo)
	if err != nil {
		log.Printf("Failed updateAgent: %v\n", err)
	}

	if resp.Success {
		s.uuid = resp.DataString[0]
		s.nodeId = resp.DataString[1]

		s.saveContext(s.uuid)
	} else {
		log.Printf("updateAgent: failed to update: %v\n", resp.DataString[0])
	}
}

func (s *NexAgent) clearProcessUpdateFlag() {
	for pid := range s.processInfoMap {
		s.processInfoMap[pid].updated = false
	}
}

func (s *NexAgent) removeTerminatedProcess() {
	removePidList := make([]int32, 0, len(s.processInfoMap))

	for pid := range s.processInfoMap {
		if s.processInfoMap[pid].updated == false {
			removePidList = append(removePidList, pid)
		}
	}

	for _, pid := range removePidList {
		delete(s.processInfoMap, pid)
	}
}

func (s *NexAgent) appendMetrics(
	metrics *pb.Metrics, values *BasicMetrics,
	endpoint string, sourceType pb.Metric_SourceType,
	source string, sourceInt int32, ts *time.Time) *pb.Metrics {

	tsUnix := ts.Unix()

	for _, metric := range *values {
		metrics.Metrics = append(metrics.Metrics, &pb.Metric{
			Cluster:    s.config.Agent.Cluster,
			Node:       s.hostName,
			SourceType: sourceType,
			Source:     source,
			SourceInt:  sourceInt,
			Endpoint:   endpoint,
			Name:       metric.Name,
			Label:      metric.Label,
			Type:       metric.Type,
			Value:      metric.Value,
			Ts:         tsUnix,
		})
	}

	return metrics
}

func (s *NexAgent) appendMetric(
	metrics *pb.Metrics, metric *BasicMetric,
	endpoint string, sourceType pb.Metric_SourceType,
	source string, sourceInt int32, ts *time.Time) *pb.Metrics {

	tsUnix := ts.Unix()

	metrics.Metrics = append(metrics.Metrics, &pb.Metric{
		Cluster:    s.config.Agent.Cluster,
		Node:       s.hostName,
		SourceType: sourceType,
		Source:     source,
		SourceInt:  sourceInt,
		Endpoint:   endpoint,
		Name:       metric.Name,
		Label:      metric.Label,
		Type:       metric.Type,
		Value:      metric.Value,
		Ts:         tsUnix,
	})

	return metrics
}

func (s *NexAgent) sendMetrics(ts *time.Time) {
	defer func() {
		if r := recover(); r != nil {
			log.Printf("sendMetrics: %v\n", r)
		}
	}()

	if s.connected == false {
		return
	}

	go s.sendNodeMetrics(ts)
	go s.sendDockerMetrics(ts)
	//go func() {
	//	if s.useK8sMetric {
	//		if err := s.sendK8sMetrics(ts); err != nil {
	//			fmt.Printf("%v\n", err)
	//		}
	//	}
	//}()
	s.sendProcessMetrics(ts)
}

func (s *NexAgent) runPing(client pb.CollectorClient) {
	stream, err := client.Ping(s.ctx)
	if err != nil {
		log.Printf("Ping: %v\n", err)
	}

	waitc := make(chan struct{})
	go func() {
		defer func() {
			if r := recover(); r != nil {
				log.Printf("ping: %v\n", r)
				s.connected = false
			}
		}()

		for range time.Tick(time.Second * 5) {
			if s.connected == false {
				break
			}

			status := &pb.Status{
				Uuid:      s.uuid,
				Timestamp: time.Now().Unix(),
			}

			err := stream.Send(status)
			if err != nil {
				log.Printf("Ping: failed to send ping: %v\n", err)
				s.connected = false
				close(waitc)
				return
			}
		}
	}()

	go func() {
		defer func() {
			if r := recover(); r != nil {
				log.Printf("Ping: failed to receive ping: %v\n", r)
				s.connected = false
			}
		}()

		for {
			if s.connected == false {
				break
			}

			in, err := stream.Recv()
			if err == io.EOF {
				log.Printf("Ping EOF: %v\n", err)
				s.connected = false
				break
			}
			if in != nil {
				log.Printf("Ping received: %v\n", in.Timestamp)
			}
		}
	}()

	<-waitc

	if err = stream.CloseSend(); err != nil {
		log.Printf("Failed close stream: %v\n", err)
	}
}

func (s *NexAgent) Start() error {
	defer func() {
		if r := recover(); r != nil {
			log.Printf("Start: %v\n", r)
			time.Sleep(15 * time.Second)
			s.resetContext()
		}
	}()

	s.SetupApiHandler()

	for {
		s.resetContext()
		conn, err := s.connectServer()
		if err != nil {
			log.Printf("Failed connect to server: %v\n", err)
			time.Sleep(15 * time.Second)

			continue
		}
		log.Println("Server connected")

		s.connected = true
		s.collectorClient = pb.NewCollectorClient(conn)

		now := time.Now()
		s.lastCheckTS = now

		s.updateAgent()
		s.sendMetrics(&now)

		if s.useK8sMetric {
			s.updateK8sCluster()
			//if err := s.sendK8sMetrics(&now); err != nil {
			//	fmt.Printf("%v\n", err)
			//}
		}

		go s.runPing(s.collectorClient)
		go func() {
			for now := range time.Tick(time.Second * s.reportInterval) {
				if s.connected == false {
					break
				}
				s.sendMetrics(&now)
				s.lastCheckTS = now
			}
		}()

		for range time.Tick(time.Second * s.updateStatusInterval) {
			if s.connected == false {
				break
			}
			s.updateAgent()
			if s.useK8sMetric {
				s.updateK8sCluster()
			}
		}
	}
}

func (s *NexAgent) LoadConfig(configPath string) error {
	var dirPath string
	var err error

	if configPath == "" {
		dirPath, err = filepath.Abs(filepath.Dir(os.Args[0]))
		if err != nil {
			return fmt.Errorf("Faled to get configuration from file: %v\n", err)
		}

		configPath = filepath.Join(dirPath, "conf/nexagent.yaml")
	}

	yamlFile, err := ioutil.ReadFile(configPath)
	if err != nil {
		return fmt.Errorf("Failed to read configuration file: %v\n", err)
	}

	config := &Config{}
	err = yaml.Unmarshal(yamlFile, config)
	if err != nil {
		return fmt.Errorf("Failed to unmarshal configuration: %v\n", err)
	}

	s.config = config
	s.reportInterval = time.Duration(config.Agent.ReportInterval)
	s.updateStatusInterval = time.Duration(config.Agent.ReportInterval)

	s.initK8s()

	return nil
}

func (s *NexAgent) InitWithDefault() {
	s.config.Agent.Cluster = "default"
	s.reportInterval = 5
	s.updateStatusInterval = 15

	s.initK8s()
}

func (s *NexAgent) initK8s() bool {
	var kubeConfig string
	var config *rest.Config
	var err error

	log.Printf("Trying to get Kubernetes configuration in cluster")
	config, err = rest.InClusterConfig()
	if err != nil {
		log.Printf("Trying to use KUBERCONFIG environment variable")
		kubePath := os.Getenv("KUBECONFIG")
		if kubePath == "" {
			home := homedir.HomeDir()
			if home != "" {
				kubeConfig = filepath.Join(home, ".kube", "config")
			} else {
				s.useK8sMetric = false
				return false
			}
		}

		config, err = clientcmd.BuildConfigFromFlags("", kubeConfig)
		if err != nil {
			s.useK8sMetric = false
			return false
		}
		log.Printf("Kubernetes API Server: %s\n", config.Host)
	} else {
		log.Printf("Use in cluster configuration")
	}

	clientSet, err := kubernetes.NewForConfig(config)
	if err != nil {
		s.useK8sMetric = false
		return false
	}

	s.k8sConfig = config
	s.k8sClientSet = clientSet
	s.useK8sMetric = false

	go s.setupLeaseLock()

	return s.useK8sMetric
}

func (s *NexAgent) SetK8sNamespace(k8sNamespace string) {
	s.config.Kubernetes.Namespace = k8sNamespace
}

func (s *NexAgent) SetK8sCluster(k8sCluster string) {
	s.config.Kubernetes.ClusterName = k8sCluster
}

func (s *NexAgent) SetServerAddress(serverAddress string) {
	s.config.Agent.ServerAddress = serverAddress
}

func (s *NexAgent) SetAgentCluster(agentCluster string) {
	s.config.Agent.Cluster = agentCluster
}

func (s *NexAgent) SetApiPort(restApiPort int) {
	s.config.Agent.ApiPort = restApiPort
}

func NewNexAgent() *NexAgent {
	machineId, err := machineid.ProtectedID(AppName)
	if err != nil {
		log.Fatalf("Failed to start application: %s\n", err)
	}

	return &NexAgent{
		machineId:      machineId,
		processInfoMap: make(map[int32]*ProcessInfo),
		config:         &Config{},
	}
}
