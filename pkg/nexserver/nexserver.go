package nexserver

import (
	"context"
	"fmt"
	pb "github.com/NexClipper/NexClipper/api"
	"github.com/dgraph-io/ristretto"
	"github.com/google/uuid"
	"github.com/jinzhu/gorm"
	_ "github.com/jinzhu/gorm/dialects/postgres"
	"google.golang.org/grpc"
	"google.golang.org/grpc/codes"
	"google.golang.org/grpc/keepalive"
	"google.golang.org/grpc/metadata"
	"google.golang.org/grpc/peer"
	"google.golang.org/grpc/status"
	"io"
	"io/ioutil"
	"log"
	"net"
	"os"
	"path/filepath"
	"sigs.k8s.io/yaml"
	"strings"
	"sync"
	"time"
)

const (
	AppName          = "NexServer"
	AppDescription   = "NexServer for NexClipper Monitoring System"
	NexServerVersion = "0.2.0"
	ConfigFilename   = "nexserver.yaml"
)

var kaep = keepalive.EnforcementPolicy{
	MinTime:             5 * time.Second,
	PermitWithoutStream: true,
}

var kasp = keepalive.ServerParameters{
	MaxConnectionIdle: 15 * time.Second,
	Time:              5 * time.Second,
	Timeout:           1 * time.Second,
}

type ServerConfig struct {
	BindAddress     string
	AgentListenPort int
	ApiPort         int
}

type DatabaseConfig struct {
	Host     string
	Port     int
	User     string
	Password string
	DbName   string
	SslMode  string
}

type TLSConfig struct {
	Use      bool
	CertFile string
	KeyFile  string
}

type Config struct {
	Server   ServerConfig
	Database DatabaseConfig
	TLS      TLSConfig
}

type NexServer struct {
	sync.RWMutex

	config *Config
	db     *gorm.DB
	dbLock map[string]*sync.RWMutex

	agentMap map[string]*Agent
	nodeMap  map[string]*Node

	cache *ristretto.Cache
}

func (s *NexServer) newAgent(in *pb.Agent, publicIpv4 string, cluster *Cluster) *Agent {
	agentUuid, _ := uuid.NewUUID()

	agent := &Agent{
		Ipv4:        in.Node.Ipv4,
		Ipv6:        "",
		PublicIpv4:  publicIpv4,
		PublicIpv6:  "",
		Version:     in.Version,
		LastContact: time.Now(),
		Uuid:        agentUuid.String(),
		Description: "",
		ClusterID:   cluster.ID,
		MachineID:   in.MachineId,
		Online:      true,
	}

	return agent
}

func (s *NexServer) addAgent(agentUuid string, agent *Agent) {
	s.Lock()
	defer s.Unlock()

	s.agentMap[agentUuid] = agent

	agent.Online = true

	result := s.db.Model(&agent).Update("online", true)
	if result.Error != nil {
		log.Printf("failed to update agent: %v\n", result.Error)
	}
}

func (s *NexServer) deleteAgent(agentUuid string) {
	s.Lock()
	defer s.Unlock()

	delete(s.agentMap, agentUuid)

	var agent Agent

	result := s.db.Where("uuid=?", agentUuid).First(&agent)
	if result.Error == nil {
		agent.Online = false

		result := s.db.Model(&agent).Update("online", false)
		if result.Error != nil {
			log.Printf("failed to update agent: %v\n", result.Error)
		}
	}
}

func (s *NexServer) findAgent(agentUuid string) *Agent {
	s.RLock()
	defer s.RUnlock()

	agent, found := s.agentMap[agentUuid]
	if !found {
		return nil
	}

	return agent
}

func (s *NexServer) updateLastContact(agentUuid string) bool {
	s.Lock()
	defer s.Unlock()

	agent, found := s.agentMap[agentUuid]
	if !found {
		return false
	}
	agent.LastContact = time.Now()

	s.db.Save(agent)

	return true
}

func (s *NexServer) getPublicIP(ctx context.Context) (string, error) {
	p, ok := peer.FromContext(ctx)
	if !ok {
		return "", fmt.Errorf("failed to get peer information: %v\n", ctx)
	}

	publicIpv4 := strings.Split(p.Addr.String(), ":")[0]

	return publicIpv4, nil
}

func (s *NexServer) UpdateAgent(ctx context.Context, in *pb.Agent) (*pb.Response, error) {
	cluster := s.findCluster(in.Cluster)

	publicIpv4, err := s.getPublicIP(ctx)
	if err != nil {
		return nil, status.Error(codes.Unknown, "failed to get public IP address")
	}

	remoteAgent := s.getRemoteAgent(in.MachineId)
	if remoteAgent == nil {
		remoteAgent = s.newAgent(in, publicIpv4, cluster)
		result := s.db.Create(remoteAgent)
		if result.Error != nil {
			log.Printf("failed to create a new agent: %s\n", result.Error)
		}
	}

	agent := s.findAgent(remoteAgent.Uuid)
	if agent == nil {
		s.addAgent(remoteAgent.Uuid, remoteAgent)
	}

	s.updateLastContact(remoteAgent.Uuid)

	node := s.findNodeByAgent(remoteAgent)
	if node == nil {
		node = s.newNode(remoteAgent, publicIpv4, in.Node)

		s.db.Create(node)
	}

	return &pb.Response{
		Success:    true,
		Code:       0,
		Error:      "",
		DataString: []string{remoteAgent.Uuid, node.Uuid},
	}, nil
}

func (s *NexServer) findAgentFromContext(ctx context.Context) *Agent {
	md, ok := metadata.FromIncomingContext(ctx)
	if !ok {
		return nil
	}

	agentUuid := md.Get("UUID")
	if len(agentUuid) == 0 {
		return nil
	}
	agent := s.findAgent(agentUuid[0])
	if agent == nil {
		return nil
	}

	return agent
}

func (s *NexServer) Ping(stream pb.Collector_PingServer) error {
	agent := s.findAgentFromContext(stream.Context())
	if agent == nil {
		log.Printf("invalid agent")
		return status.Error(codes.PermissionDenied, "invalid agent")
	}

	go func() {
		for {
			in, err := stream.Recv()
			if err == io.EOF {
				log.Printf("Agent: error: %v\n", err)
			}
			if err != nil {
				log.Printf("Agent: %s disconnected: %v\n", agent.Uuid, err)
				s.deleteAgent(agent.Uuid)

				return
			}

			log.Printf("Agent: received UUID: %s, Timestamp: %v\n", agent.Uuid, in.Timestamp)
		}
	}()

	for range time.Tick(time.Second * 5) {
		agentStatus := &pb.Status{
			Uuid:      agent.Uuid,
			Timestamp: time.Now().Unix(),
		}

		err := stream.Send(agentStatus)
		if err != nil {
			log.Printf("Agent: failed to send ping: %v\n", err)
			break

		}
	}

	return nil
}

func (s *NexServer) ReportMetrics(ctx context.Context, in *pb.Metrics) (*pb.Response, error) {
	agent := s.findAgentFromContext(ctx)
	if agent == nil {
		return nil, status.Error(codes.PermissionDenied, "invalid agent")
	}

	s.addMetrics(in, agent.ClusterID, nil)

	return s.response(true, 0, ""), nil
}

func (s *NexServer) mustValidAgent(ctx context.Context) (*Agent, error) {
	agent := s.findAgentFromContext(ctx)
	if agent == nil {
		log.Printf("invalid agent")
		return nil, status.Error(codes.PermissionDenied, "invalid agent")
	}

	return agent, nil
}

func (s *NexServer) mustValidCluster(clusterName string) (*Cluster, error) {
	cluster := s.findCluster(clusterName)
	if cluster == nil {
		log.Printf("invalid cluster")
		return nil, status.Error(codes.InvalidArgument, "invalid cluster")
	}

	return cluster, nil
}

func (s *NexServer) mustValidNode(nodeName string, clusterId uint) (*Node, error) {
	node := s.findNode(nodeName, clusterId)
	if node == nil {
		log.Printf("invalid node")
		return nil, status.Error(codes.InvalidArgument, "invalid node")
	}

	return node, nil
}

func (s *NexServer) response(success bool, code uint32, errMsg string) *pb.Response {
	return &pb.Response{
		Success: true,
		Code:    code,
		Error:   errMsg,
	}
}

func (s *NexServer) ReportNodeMetrics(ctx context.Context, in *pb.NodeMetrics) (*pb.Response, error) {
	return nil, status.Error(codes.Unimplemented, "unimplemented")
}

func (s *NexServer) ReportProcessMetrics(ctx context.Context, in *pb.ProcessMetrics) (*pb.Response, error) {
	return nil, status.Error(codes.Unimplemented, "unimplemented")
}

func (s *NexServer) ReportContainerMetrics(ctx context.Context, in *pb.ContainerMetrics) (*pb.Response, error) {
	return nil, status.Error(codes.Unimplemented, "unimplemented")
}

func (s *NexServer) UpdateK8SCluster(ctx context.Context, in *pb.K8SCluster) (*pb.Response, error) {
	_, err := s.mustValidAgent(ctx)
	if err != nil {
		return nil, status.Error(codes.PermissionDenied, "invalid agent")
	}
	cluster, err := s.mustValidCluster(in.AgentCluster)
	if err != nil {
		return nil, status.Error(codes.InvalidArgument, "invalid cluster")
	}
	k8sCluster, err := s.mustValidK8sCluster(in.Object.Name, cluster.ID)
	if err != nil {
		return nil, status.Error(codes.InvalidArgument, "invalid kubernetes cluster")
	}

	if in.K8SNodes == nil || in.K8SNamespaces == nil {
		return nil, status.Error(codes.InvalidArgument, "invalid kubernetes cluster")
	}

	err = s.addK8sNodes(in.K8SNodes, k8sCluster)
	if err != nil {
		return nil, status.Error(codes.InvalidArgument, "invalid arguments")
	}
	err = s.addNamespaces(in.K8SNamespaces, k8sCluster)
	if err != nil {
		return nil, status.Error(codes.InvalidArgument, "invalid arguments")
	}

	return s.response(true, 0, ""), nil
}

func (s *NexServer) ReportK8SMetrics(ctx context.Context, in *pb.K8SMetrics) (*pb.Response, error) {
	_, err := s.mustValidAgent(ctx)
	if err != nil {
		return nil, status.Error(codes.PermissionDenied, "invalid agent")
	}
	cluster, err := s.mustValidCluster(in.AgentCluster)
	if err != nil {
		return nil, status.Error(codes.InvalidArgument, "invalid cluster")
	}
	k8sCluster, err := s.mustValidK8sCluster(in.K8SCluster, cluster.ID)
	if err != nil {
		return nil, status.Error(codes.InvalidArgument, "invalid kubernetes cluster")
	}

	s.addK8sMetrics(in, k8sCluster.ID)

	return s.response(true, uint32(codes.OK), ""), nil
}

func (s *NexServer) UpdateProcess(ctx context.Context, in *pb.ProcessAll) (*pb.Response, error) {
	_, err := s.mustValidAgent(ctx)
	if err != nil {
		return nil, err
	}

	cluster, err := s.mustValidCluster(in.Cluster)
	if err != nil {
		return nil, err
	}

	node, err := s.mustValidNode(in.Host, cluster.ID)
	if err != nil {
		return nil, err
	}

	var processPtr *Process
	for _, psInfo := range in.Processes {
		var processItem Process

		processPtr = s.getProcess(psInfo.Name, psInfo.Pid, node.ID, cluster.ID)
		if processPtr == nil {
			processItem = Process{
				Name:        psInfo.Name,
				PID:         psInfo.Pid,
				ClusterID:   cluster.ID,
				NodeID:      node.ID,
				ContainerID: 0,
			}

			result := s.db.Create(&processItem)
			if result.Error != nil {
				log.Printf("failed to create process record: %v\n", psInfo.Name)
				continue
			}
			processPtr = &processItem
		}

		s.addMetrics(psInfo.Metrics, cluster.ID, *processPtr)
	}

	return s.response(true, 0, ""), nil
}

func (s *NexServer) UpdateContainer(ctx context.Context, in *pb.ContainerAll) (*pb.Response, error) {
	_, err := s.mustValidAgent(ctx)
	if err != nil {
		log.Printf("UpdateContainer: invalid agent: %s\n", in.Host)
		return nil, err
	}

	cluster, err := s.mustValidCluster(in.Cluster)
	if err != nil {
		log.Printf("UpdateContainer: invalid cluster: %s\n", in.Host)
		return nil, err
	}

	node, err := s.mustValidNode(in.Host, cluster.ID)
	if err != nil {
		log.Printf("UpdateContainer: invalid node: %s\n", in.Host)
		return nil, err
	}

	var containerPtr *Container
	for _, containerInfo := range in.Containers {
		var containerItem Container

		containerPtr = s.getContainer(containerInfo.Name, node.ID, cluster.ID)
		if containerPtr == nil {
			containerItem = Container{
				Name:        containerInfo.Name,
				ContainerID: containerInfo.ContainerId,
				Type:        containerInfo.Type,
				ClusterID:   cluster.ID,
				NodeID:      node.ID,
				Image:       containerInfo.Image,
			}

			result := s.db.Create(&containerItem)
			if result.Error != nil {
				log.Printf("Failed create new container: %v\n", containerItem.Name)
				continue
			}
			containerPtr = &containerItem
		}

		s.addMetrics(containerInfo.Metrics, cluster.ID, *containerPtr)
	}

	return s.response(true, 0, ""), nil
}

func (s *NexServer) Start() error {
	_, err := s.initCache()
	if err != nil {
		log.Fatalf("Server: failed to start: %v\n", err)
	}

	listenPort := fmt.Sprintf("%s:%d",
		s.config.Server.BindAddress, s.config.Server.AgentListenPort)
	listen, err := net.Listen("tcp", listenPort)
	if err != nil {
		return err
	}
	log.Println("Server: listen at", listenPort)

	s.SetupApiHandler()

	srv := grpc.NewServer(
		grpc.KeepaliveEnforcementPolicy(kaep),
		grpc.KeepaliveParams(kasp))

	pb.RegisterCollectorServer(srv, s)

	if err := srv.Serve(listen); err != nil {
		return err
	}

	return nil
}

func (s *NexServer) LoadConfig(configPath string) error {
	var dirPath string
	var err error

	if configPath == "" {
		dirPath, err = filepath.Abs(filepath.Dir(os.Args[0]))
		if err != nil {
			return fmt.Errorf("failed to get configuration from file: %v\n", err)
		}
		configPath = filepath.Join(dirPath, "conf", ConfigFilename)
	}

	yamlFile, err := ioutil.ReadFile(configPath)
	if err != nil {
		return fmt.Errorf("failed to read configuration file: %v\n", err)
	}

	config := &Config{}
	err = yaml.Unmarshal(yamlFile, config)
	if err != nil {
		return fmt.Errorf("failed to unmarshal configuration: %v\n", err)
	}

	s.config = config

	return nil
}

func NewNexServer() *NexServer {
	server := &NexServer{
		agentMap: make(map[string]*Agent),
		nodeMap:  make(map[string]*Node),
		dbLock:   make(map[string]*sync.RWMutex),
		config:   &Config{},
	}

	return server
}

func (s *NexServer) SetServerConfig(bindAddress string, agentPort, apiPort int) {
	s.config.Server.BindAddress = bindAddress
	s.config.Server.AgentListenPort = agentPort
	s.config.Server.ApiPort = apiPort
}

func (s *NexServer) SetDatabaseConfig(dbHost string, dbPort int, dbUser, dbPass, dbName, dbSslMode string) {
	dbConfig := DatabaseConfig{
		Host:     dbHost,
		Port:     dbPort,
		User:     dbUser,
		Password: dbPass,
		DbName:   dbName,
		SslMode:  dbSslMode,
	}

	s.config.Database = dbConfig
}
