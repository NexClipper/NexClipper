package nexagent

import (
	"context"
	"encoding/json"
	pb "github.com/NexClipper/NexClipper/api"
	"google.golang.org/grpc/codes"
	"k8s.io/apimachinery/pkg/api/resource"
	v1 "k8s.io/apimachinery/pkg/apis/meta/v1"
	"k8s.io/client-go/tools/leaderelection"
	"k8s.io/client-go/tools/leaderelection/resourcelock"
	"k8s.io/client-go/transport"
	"log"
	"os"
	"os/signal"
	"strings"
	"syscall"

	"fmt"
	"time"

	metav1 "k8s.io/apimachinery/pkg/apis/meta/v1"
)

type PodMetricsList struct {
	Kind       string `json:"kind"`
	APIVersion string `json:"apiVersion"`
	Metadata   struct {
		SelfLink string `json:"selfLink"`
	} `json:"metadata"`
	Items []struct {
		Metadata struct {
			Name              string    `json:"name"`
			Namespace         string    `json:"namespace"`
			SelfLink          string    `json:"selfLink"`
			CreationTimestamp time.Time `json:"creationTimestamp"`
		} `json:"metadata"`
		Timestamp  time.Time `json:"timestamp"`
		Window     string    `json:"window"`
		Containers []struct {
			Name  string `json:"name"`
			Usage struct {
				CPU    string `json:"cpu"`
				Memory string `json:"memory"`
			} `json:"usage"`
		} `json:"containers"`
	} `json:"items"`
}

type NodeMetricsList struct {
	Kind       string `json:"kind"`
	APIVersion string `json:"apiVersion"`
	Metadata   struct {
		SelfLink string `json:"selfLink"`
	} `json:"metadata"`
	Items []struct {
		Metadata struct {
			Name              string    `json:"name"`
			SelfLink          string    `json:"selfLink"`
			CreationTimestamp time.Time `json:"creationTimestamp"`
		} `json:"metadata"`
		Timestamp time.Time `json:"timestamp"`
		Window    string    `json:"window"`
		Usage     struct {
			CPU    string `json:"cpu"`
			Memory string `json:"memory"`
		} `json:"usage"`
	} `json:"items"`
}

func (s *NexAgent) getPodMetrics() (*PodMetricsList, error) {
	var pods PodMetricsList

	data, err := s.k8sClientSet.RESTClient().Get().AbsPath("apis/metrics.k8s.io/v1beta1/pods").DoRaw()
	if err != nil {
		return nil, err
	}
	err = json.Unmarshal(data, &pods)
	if err != nil {
		return nil, err
	}

	return &pods, nil
}

func (s *NexAgent) getNodeMetrics() (*NodeMetricsList, error) {
	var nodes NodeMetricsList

	data, err := s.k8sClientSet.RESTClient().Get().AbsPath("apis/metrics.k8s.io/v1beta1/nodes").DoRaw()
	if err != nil {
		return nil, err
	}
	err = json.Unmarshal(data, &nodes)
	if err != nil {
		return nil, err
	}

	return &nodes, nil
}

func (s *NexAgent) getK8sPodMetrics(ts *time.Time) ([]*pb.K8SPodMetric, error) {
	podMetrics, err := s.getPodMetrics()
	if err != nil {
		return nil, fmt.Errorf("getK8sPodMetrics: failed to get PodMetrics: %v\n", err)
	}

	tsUnix := ts.Unix()
	k8sPodMetrics := make([]*pb.K8SPodMetric, 0, len(podMetrics.Items))

	for _, podMetric := range podMetrics.Items {
		k8sPodMetric := &pb.K8SPodMetric{
			PodName:             podMetric.Metadata.Name,
			Namespace:           podMetric.Metadata.Namespace,
			K8SContainerMetrics: make([]*pb.K8SContainerMetric, 0, len(podMetric.Containers)),
		}
		for _, container := range podMetric.Containers {
			cpuUsage := resource.MustParse(container.Usage.CPU)
			memoryUsage := resource.MustParse(container.Usage.Memory)
			label := fmt.Sprintf("pod=%s,namespace=%s,cluster=%s",
				podMetric.Metadata.Name, podMetric.Metadata.Namespace,
				s.config.Kubernetes.ClusterName)

			k8sContainerMetric := &pb.K8SContainerMetric{
				Container: container.Name,
				Metrics: []*pb.Metric{
					{
						Value:      float64(cpuUsage.MilliValue()),
						Ts:         tsUnix,
						SourceType: pb.Metric_K8S_CONTAINER,
						Source:     container.Name,
						Endpoint:   "/k8s/pod/container",
						Name:       "k8s_pod_cpu_usage",
						Label:      label,
						Type:       "gauge",
						Cluster:    s.config.Kubernetes.ClusterName,
					},
					{
						Value:      float64(memoryUsage.Value()),
						Ts:         tsUnix,
						SourceType: pb.Metric_K8S_CONTAINER,
						Source:     container.Name,
						Endpoint:   "/k8s/pod/container",
						Name:       "k8s_pod_memory_usage",
						Label:      label,
						Type:       "gauge",
						Cluster:    s.config.Kubernetes.ClusterName,
					},
				},
			}

			k8sPodMetric.K8SContainerMetrics = append(k8sPodMetric.K8SContainerMetrics, k8sContainerMetric)
		}
		k8sPodMetrics = append(k8sPodMetrics, k8sPodMetric)
	}

	return k8sPodMetrics, nil
}

func (s *NexAgent) getK8sNodeMetrics(ts *time.Time) ([]*pb.K8SNodeMetric, error) {
	nodeMetrics, err := s.getNodeMetrics()
	if err != nil {
		return nil, fmt.Errorf("getK8sNodeMetrics: failed to get NodeMetrics: %v\n", err)
	}

	tsUnix := ts.Unix()
	k8sNodeMetrics := make([]*pb.K8SNodeMetric, 0, len(nodeMetrics.Items))

	for _, nodeMetric := range nodeMetrics.Items {
		cpuUsage := resource.MustParse(nodeMetric.Usage.CPU)
		memoryUsage := resource.MustParse(nodeMetric.Usage.Memory)
		label := fmt.Sprintf("node=%s,cluster=%s",
			nodeMetric.Metadata.Name, s.config.Kubernetes.ClusterName)

		k8sNodeMetric := &pb.K8SNodeMetric{
			NodeName: nodeMetric.Metadata.Name,
			Metrics: []*pb.Metric{
				{
					Value:      float64(cpuUsage.MilliValue()),
					Ts:         tsUnix,
					SourceType: pb.Metric_K8S_NODE,
					Source:     nodeMetric.Metadata.Name,
					Endpoint:   "/k8s/nodes",
					Name:       "k8s_node_cpu_usage",
					Label:      label,
					Type:       "gauge",
					Cluster:    s.config.Kubernetes.ClusterName,
				},
				{
					Value:      float64(memoryUsage.Value()),
					Ts:         tsUnix,
					SourceType: pb.Metric_K8S_NODE,
					Source:     nodeMetric.Metadata.Name,
					Endpoint:   "/k8s/nodes",
					Name:       "k8s_node_memory_usage",
					Label:      label,
					Type:       "gauge",
					Cluster:    s.config.Kubernetes.ClusterName,
				},
			},
		}
		k8sNodeMetrics = append(k8sNodeMetrics, k8sNodeMetric)
	}

	return k8sNodeMetrics, nil
}

func (s *NexAgent) addK8sNodes(cluster *pb.K8SCluster) []*pb.K8SObject {
	nodes, err := s.k8sClientSet.CoreV1().Nodes().List(metav1.ListOptions{})
	if err != nil || nodes == nil || nodes.Items == nil {
		log.Printf("addK8sNodes: failed to get node resources: %v\n", err)
		return nil
	}

	k8sNodes := make([]*pb.K8SObject, 0, len(nodes.Items))
	for _, node := range nodes.Items {
		apiVersion := node.APIVersion
		if apiVersion == "" {
			apiVersion = "v1"
		}
		kind := node.Kind
		if kind == "" {
			kind = "Node"
		}
		k8sNode := &pb.K8SObject{
			ApiVersion: apiVersion,
			Spec:       node.Spec.String(),
			Status:     node.Status.String(),
			Kind:       kind,
			Name:       node.Name,
			Labels:     node.Labels,
			K8SCluster: cluster.Object.Name,
		}

		k8sNodes = append(k8sNodes, k8sNode)
	}

	cluster.K8SNodes = k8sNodes
	return k8sNodes
}

func (s *NexAgent) addK8sNamespaces(cluster *pb.K8SCluster) []*pb.K8SNamespace {
	namespaces, err := s.k8sClientSet.CoreV1().Namespaces().List(metav1.ListOptions{})
	if err != nil || namespaces == nil || namespaces.Items == nil {
		log.Printf("addK8sNamespaces: failed to get namespace resources: %v\n", err)
		return nil
	}

	k8sNamespaces := make([]*pb.K8SNamespace, 0, len(namespaces.Items))
	for _, ns := range namespaces.Items {
		apiVersion := ns.APIVersion
		if apiVersion == "" {
			apiVersion = "v1"
		}
		kind := ns.Kind
		if kind == "" {
			kind = "Namespace"
		}

		k8sNamespace := &pb.K8SNamespace{
			Object: &pb.K8SObject{
				ApiVersion:   apiVersion,
				Kind:         kind,
				Name:         ns.Name,
				Labels:       ns.Labels,
				K8SCluster:   cluster.Object.Name,
				K8SNamespace: ns.Name,
			},
			Workloads: nil,
			Items:     nil,
		}
		k8sNamespaces = append(k8sNamespaces, k8sNamespace)
	}

	cluster.K8SNamespaces = k8sNamespaces
	return k8sNamespaces
}

func (s *NexAgent) addK8sWorkloads(ns *pb.K8SNamespace) ([]*pb.K8SObject, []*pb.K8SPod) {
	deployments, err := s.k8sClientSet.AppsV1().Deployments(ns.Object.Name).List(metav1.ListOptions{})
	if err != nil || deployments == nil || deployments.Items == nil {
		log.Printf("addK8sWorkloads: failed to get deployment resources: %v\n", err)
		return nil, nil
	}

	rs, err := s.k8sClientSet.AppsV1().ReplicaSets(ns.Object.Name).List(metav1.ListOptions{})
	if err != nil || rs == nil || rs.Items == nil {
		log.Printf("addK8sWorkloads: failed to get replicaset resources: %v\n", err)
		return nil, nil
	}

	sfs, err := s.k8sClientSet.AppsV1().StatefulSets(ns.Object.Name).List(metav1.ListOptions{})
	if err != nil || sfs == nil || sfs.Items == nil {
		log.Printf("addK8sWorkloads: failed to get statefulset resources: %v\n", err)
		return nil, nil
	}

	ds, err := s.k8sClientSet.AppsV1().DaemonSets(ns.Object.Name).List(metav1.ListOptions{})
	if err != nil || ds == nil || ds.Items == nil {
		log.Printf("addK8sWorkloads: failed to get daemonset resources: %v\n", err)
		return nil, nil
	}

	pods, err := s.k8sClientSet.CoreV1().Pods(ns.Object.Name).List(metav1.ListOptions{})
	if err != nil || pods == nil || pods.Items == nil {
		log.Printf("addK8sWorkloads: failed to get pod resources: %v\n", err)
		return nil, nil
	}

	totalCount := len(deployments.Items) + len(rs.Items) + len(sfs.Items) + len(ds.Items)
	ns.Workloads = make([]*pb.K8SObject, 0, totalCount)
	ns.Pods = make([]*pb.K8SPod, 0, len(pods.Items))

	for _, workload := range deployments.Items {
		kind := workload.Kind
		if kind == "" {
			kind = "Deployment"
		}
		apiVersion := workload.APIVersion
		if apiVersion == "" {
			apiVersion = "apps/v1"
		}

		k8sObject := &pb.K8SObject{
			ApiVersion:   apiVersion,
			Kind:         kind,
			Name:         workload.Name,
			Labels:       workload.Labels,
			K8SCluster:   ns.Object.K8SCluster,
			K8SNamespace: ns.Object.Name,
		}
		ns.Workloads = append(ns.Workloads, k8sObject)
	}
	for _, workload := range rs.Items {
		kind := workload.Kind
		if kind == "" {
			kind = "ReplicaSet"
		}
		apiVersion := workload.APIVersion
		if apiVersion == "" {
			apiVersion = "apps/v1"
		}

		k8sObject := &pb.K8SObject{
			ApiVersion:   apiVersion,
			Kind:         kind,
			Name:         workload.Name,
			Labels:       workload.Labels,
			K8SCluster:   ns.Object.K8SCluster,
			K8SNamespace: ns.Object.Name,
		}
		ns.Workloads = append(ns.Workloads, k8sObject)
	}
	for _, workload := range sfs.Items {
		kind := workload.Kind
		if kind == "" {
			kind = "StatefulSet"
		}
		apiVersion := workload.APIVersion
		if apiVersion == "" {
			apiVersion = "apps/v1"
		}

		k8sObject := &pb.K8SObject{
			ApiVersion:   apiVersion,
			Kind:         kind,
			Name:         workload.Name,
			Labels:       workload.Labels,
			K8SCluster:   ns.Object.K8SCluster,
			K8SNamespace: ns.Object.Name,
		}
		ns.Workloads = append(ns.Workloads, k8sObject)
	}
	for _, workload := range ds.Items {
		kind := workload.Kind
		if kind == "" {
			kind = "DaemonSet"
		}
		apiVersion := workload.APIVersion
		if apiVersion == "" {
			apiVersion = "apps/v1"
		}

		k8sObject := &pb.K8SObject{
			ApiVersion:   apiVersion,
			Kind:         kind,
			Name:         workload.Name,
			Labels:       workload.Labels,
			K8SCluster:   ns.Object.K8SCluster,
			K8SNamespace: ns.Object.Name,
		}
		ns.Workloads = append(ns.Workloads, k8sObject)
	}

	for _, pod := range pods.Items {
		kind := pod.Kind
		if kind == "" {
			kind = "Pod"
		}
		apiVersion := pod.APIVersion
		if apiVersion == "" {
			apiVersion = "v1"
		}

		k8sPod := &pb.K8SPod{
			Object: &pb.K8SObject{
				ApiVersion:   apiVersion,
				Kind:         kind,
				Name:         pod.Name,
				Labels:       pod.Labels,
				K8SCluster:   ns.Object.K8SCluster,
				K8SNamespace: ns.Object.Name,
			},
			Qos: string(pod.Status.QOSClass),
		}

		if pod.Status.ContainerStatuses != nil && len(pod.Status.ContainerStatuses) > 0 {
			containers := make([]*pb.Container, 0, len(pod.Status.ContainerStatuses))

			for _, container := range pod.Status.ContainerStatuses {
				var containerType string
				var containerId string

				containerType = "docker"
				if strings.Contains(container.ContainerID, "://") {
					cIDs := strings.Split(container.ContainerID, "://")

					if len(cIDs) >= 2 {
						containerType = cIDs[0]
						containerId = cIDs[1]
					} else {
						log.Printf("addK8sWorkloads: unknown container id: %v\n", container.ContainerID)
						continue
					}
				} else {
					containerId = container.ContainerID
				}

				containers = append(containers, &pb.Container{
					Type:        containerType,
					Name:        container.Name,
					Image:       container.Image,
					ContainerId: containerId,
				})
			}
			k8sPod.Containers = containers
		}

		ns.Pods = append(ns.Pods, k8sPod)
	}

	return ns.Workloads, ns.Pods
}

func (s *NexAgent) updateK8sCluster() {
	if s.connected == false {
		return
	}

	k8sCluster := &pb.K8SCluster{
		Object: &pb.K8SObject{
			Name: s.config.Kubernetes.ClusterName,
		},
		AgentCluster: s.config.Agent.Cluster,
	}

	nodes := s.addK8sNodes(k8sCluster)
	if nodes == nil {
		return
	}
	s.addK8sNamespaces(k8sCluster)
	for _, ns := range k8sCluster.K8SNamespaces {
		s.addK8sWorkloads(ns)
	}

	resp, err := s.collectorClient.UpdateK8SCluster(s.ctx, k8sCluster)
	if err != nil {
		log.Printf("updateK8sCluster: failed to update kubernetes information: %v\n", err)
	} else if resp.Code != uint32(codes.OK) {
		log.Printf("updateK8sCluster: failed to update kubernetes information: %v\n", resp.Error)
	}
}

func (s *NexAgent) sendK8sMetrics(ts *time.Time) error {
	k8sNodeMetrics, err := s.getK8sNodeMetrics(ts)
	if err != nil {
		return err
	}

	k8sPodMetrics, err := s.getK8sPodMetrics(ts)
	if err != nil {
		return err
	}

	k8sMetrics := &pb.K8SMetrics{
		AgentCluster:   s.config.Agent.Cluster,
		K8SCluster:     s.config.Kubernetes.ClusterName,
		K8SNodeMetrics: k8sNodeMetrics,
		K8SPodMetrics:  k8sPodMetrics,
	}

	resp, err := s.collectorClient.ReportK8SMetrics(s.ctx, k8sMetrics)
	if err != nil {
		return fmt.Errorf("sendK8sMetrics: failed to report K8S metrics: %v\n", err)
	}

	if !resp.Success {
		return fmt.Errorf("sendK8sMetrics: failed to report K8S metrics: %v\n", resp.Error)
	}

	return nil
}

func (s *NexAgent) setupLeaseLock() {
	lock := &resourcelock.LeaseLock{
		LeaseMeta: v1.ObjectMeta{
			Name:      K8sLeaseLockName,
			Namespace: s.config.Kubernetes.Namespace,
		},
		Client: s.k8sClientSet.CoordinationV1(),
		LockConfig: resourcelock.ResourceLockConfig{
			Identity: s.machineId,
		},
	}

	ctx, cancel := context.WithCancel(context.Background())
	defer cancel()

	s.k8sConfig.Wrap(
		transport.ContextCanceller(
			ctx, fmt.Errorf("setupLeaseLock: the leader of agent is shutting down")))

	ch := make(chan os.Signal, 1)
	signal.Notify(ch, os.Interrupt, syscall.SIGTERM)
	go func() {
		<-ch

		log.Printf("setupLeaseLock: received termination, signalling shutdown")
		cancel()
	}()

	leaderelection.RunOrDie(ctx, leaderelection.LeaderElectionConfig{
		Lock:          lock,
		LeaseDuration: 60 * time.Second,
		RenewDeadline: 15 * time.Second,
		RetryPeriod:   5 * time.Second,
		Callbacks: leaderelection.LeaderCallbacks{
			OnStartedLeading: func(ctx context.Context) {
				log.Printf("%s: leading\n", s.machineId)
				s.useK8sMetric = true
			},
			OnStoppedLeading: func() {
				log.Printf("%s: lost\n", s.machineId)
				s.useK8sMetric = false
			},
			OnNewLeader: func(identity string) {
				if identity == s.machineId {
					return
				}
				log.Printf("setupLeaseLock: new leader elected: %v\n", identity)
			},
		},
		ReleaseOnCancel: true,
	})

	coordV1 := s.k8sClientSet.CoordinationV1()
	_, err := coordV1.Leases(s.config.Kubernetes.Namespace).Get(K8sLeaseLockName, metav1.GetOptions{})
	if err == nil || !strings.Contains(err.Error(), "the leader of agent is shutting down") {
		log.Fatalf("%s: expected to get an error when trying to make a client call: %v", s.machineId, err)
	}

	log.Printf("%s: done", s.machineId)
}
