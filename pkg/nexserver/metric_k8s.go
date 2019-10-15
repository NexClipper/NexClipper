package nexserver

import (
	"fmt"
	pb "github.com/NexClipper/NexClipper/api"
	"k8s.io/klog"
	"time"
)

func (s *NexServer) mustValidK8sCluster(k8sClusterName string, clusterId uint) (*K8sCluster, error) {
	var k8sCluster K8sCluster

	result := s.db.Where("name=? AND agent_cluster_id=?", k8sClusterName, clusterId).First(&k8sCluster)
	if result.Error != nil {
		k8sCluster = K8sCluster{
			Name:           k8sClusterName,
			AgentClusterID: clusterId,
		}
		s.db.Create(&k8sCluster)
	}

	return &k8sCluster, nil
}

func (s *NexServer) findK8sCluster(k8sClusterName string, clusterId uint) *K8sCluster {
	var k8sCluster K8sCluster

	result := s.db.Where("name=? AND agent_cluster_id=?", k8sClusterName, clusterId).First(&k8sCluster)
	if result.Error != nil {
		return nil
	}

	return &k8sCluster
}

func (s *NexServer) findK8sNode(nodeName string, k8sClusterId uint) *K8sNode {
	var k8sNode K8sNode

	result := s.db.Where("name=? AND k8s_cluster_id=?", nodeName, k8sClusterId).First(&k8sNode)
	if result.Error != nil {
		return nil
	}

	return &k8sNode
}

func (s *NexServer) newK8sObject(obj *pb.K8SObject, k8sClusterId uint) (*K8sObject, error) {
	k8sObject := &K8sObject{
		K8sClusterID: k8sClusterId,
		ApiVersion:   obj.ApiVersion,
		Kind:         obj.Kind,
		Name:         obj.Name,
	}

	result := s.db.Create(k8sObject)
	if result.Error != nil {
		return nil, fmt.Errorf("Failed to create K8S Object: %v\n", result.Error)
	}

	return k8sObject, nil
}

func (s *NexServer) newK8sNode(node *pb.K8SObject, k8sCluster *K8sCluster) (*K8sNode, error) {
	k8sObject, err := s.newK8sObject(node, k8sCluster.ID)
	if err != nil {
		return nil, fmt.Errorf("Failed to create K8S Node: %v @ %v\n", node.Name, k8sCluster.Name)
	}

	k8sNode := &K8sNode{
		Name:         node.Name,
		K8sClusterID: k8sCluster.ID,
		K8sObjectID:  k8sObject.ID,
	}

	result := s.db.Create(k8sNode)
	if result.Error != nil {
		return nil, fmt.Errorf("Failed to create K8S node: %v @ %v\n", result.Error, k8sCluster.Name)
	}

	return k8sNode, nil
}

func (s *NexServer) addK8sNodes(nodes []*pb.K8SObject, k8sCluster *K8sCluster) error {
	for _, node := range nodes {
		k8sNode := s.getK8sNode(node.Name, k8sCluster.ID)
		if k8sNode == nil {
			newNode, err := s.newK8sNode(node, k8sCluster)
			if err != nil {
				return err
			}

			klog.Infof("Add new K8S node: %v @ %v\n", newNode.Name, k8sCluster.Name)
		}
	}

	return nil
}

func (s *NexServer) findK8sNamespace(nsName string, k8sClusterId uint) *K8sNamespace {
	var k8sNamespace K8sNamespace

	result := s.db.Where("name=? AND k8s_cluster_id=?", nsName, k8sClusterId).First(&k8sNamespace)
	if result.Error != nil {
		return nil
	}

	return &k8sNamespace
}

func (s *NexServer) newK8sNamespace(ns *pb.K8SNamespace, k8sCluster *K8sCluster) (*K8sNamespace, error) {
	k8sObject, err := s.newK8sObject(ns.Object, k8sCluster.ID)
	if err != nil {
		return nil, err
	}

	k8sNamespace := K8sNamespace{
		Name:         ns.Object.Name,
		K8sClusterID: k8sCluster.ID,
		K8sObjectID:  k8sObject.ID,
	}

	result := s.db.Create(&k8sNamespace)
	if result.Error != nil {
		return nil, fmt.Errorf("Failed to create K8S namespace: %v @ %v\n", err, k8sCluster.Name)
	}

	return &k8sNamespace, nil
}

func (s *NexServer) findK8sDeployment(name string, nsId uint, k8sClusterId uint) *K8sDeployment {
	var k8sDeployment K8sDeployment

	result := s.db.Where("name=? AND k8s_namespace_id=? AND k8s_cluster_id=?",
		name, nsId, k8sClusterId).First(&k8sDeployment)
	if result.Error != nil {
		return nil
	}

	return &k8sDeployment
}

func (s *NexServer) findK8sStatefulSet(name string, nsId uint, k8sClusterId uint) *K8sStatefulSet {
	var k8sStatefulSet K8sStatefulSet

	result := s.db.Where("name=? AND k8s_namespace_id=? AND k8s_cluster_id=?",
		name, nsId, k8sClusterId).First(&k8sStatefulSet)
	if result.Error != nil {
		return nil
	}

	return &k8sStatefulSet
}

func (s *NexServer) findK8sReplicaSet(name string, nsId uint, k8sClusterId uint) *K8sReplicaSet {
	var k8sReplicaSet K8sReplicaSet

	result := s.db.Where("name=? AND k8s_namespace_id=? AND k8s_cluster_id=?",
		name, nsId, k8sClusterId).First(&k8sReplicaSet)
	if result.Error != nil {
		return nil
	}

	return &k8sReplicaSet
}

func (s *NexServer) findK8sDaemonSet(name string, nsId uint, k8sClusterId uint) *K8sDaemonSet {
	var k8sDaemonSet K8sDaemonSet

	result := s.db.Where("name=? AND k8s_namespace_id=? AND k8s_cluster_id=?",
		name, nsId, k8sClusterId).First(&k8sDaemonSet)
	if result.Error != nil {
		return nil
	}

	return &k8sDaemonSet
}

func (s *NexServer) addK8sObjectLabel(k8sObject *K8sObject, labels map[string]string) error {
	var k8sLabel K8sLabel
	var k8sObjectTag K8sObjectTag

	for k, v := range labels {
		label := fmt.Sprintf("%s=%s", k, v)

		result := s.db.Where("label=? AND k8s_object_id=?", label, k8sObject.ID).First(&k8sLabel)
		if result.Error != nil {
			k8sLabel = K8sLabel{
				Label:       label,
				K8sObjectID: k8sObject.ID,
			}
			result := s.db.Create(&k8sLabel)
			if result.Error != nil {
				return fmt.Errorf("Failed to create K8S Object Label: %v\n", result.Error)
			}
		}

		result = s.db.Where("k8s_label_id=? AND k8s_object_id=?", k8sLabel.ID, k8sObject.ID).First(&k8sObjectTag)
		if result.Error != nil {
			k8sObjectTag := &K8sObjectTag{
				K8sObjectID: k8sObject.ID,
				K8sLabelID:  k8sLabel.ID,
			}
			result = s.db.Create(&k8sObjectTag)
			if result.Error != nil {
				return fmt.Errorf("Failed to create K8S Object Tag: %v\n", result.Error)
			}
		}
	}

	return nil
}

func (s *NexServer) findK8sPod(podName string, namespaceId uint, k8sClusterId uint) *K8sPod {
	var k8sPod K8sPod

	result := s.db.Where("name=? AND k8s_namespace_id=? AND k8s_cluster_id=?",
		podName, namespaceId, k8sClusterId).First(&k8sPod)
	if result.Error != nil {
		return nil
	}

	return &k8sPod
}

func (s *NexServer) addK8sContainer(containers []*pb.Container, pod *K8sPod, ns *K8sNamespace, k8sCluster *K8sCluster) error {
	for _, containerInfo := range containers {
		container := s.findK8sContainer(containerInfo.Name, pod.ID, ns.ID, k8sCluster.ID)
		if container == nil {
			newContainer := &K8sContainer{
				Name:           containerInfo.Name,
				Image:          containerInfo.Image,
				ContainerType:  containerInfo.Type,
				ContainerId:    containerInfo.ContainerId,
				K8sClusterID:   k8sCluster.ID,
				K8sNamespaceID: ns.ID,
				K8sPodID:       pod.ID,
			}
			result := s.db.Create(newContainer)
			if result.Error != nil {
				klog.Errorf("failed to create container %v\n", result.Error)
				continue
			}
		}
	}

	return nil
}

func (s *NexServer) addPods(pods []*pb.K8SPod, ns *K8sNamespace, k8sCluster *K8sCluster) error {
	var k8sObject *K8sObject
	var err error

	for _, pod := range pods {
		currentPod := s.getK8sPod(pod.Object.Name, ns.ID, k8sCluster.ID)
		if currentPod != nil {
			k8sObject = s.getK8sObjectById(currentPod.K8sObjectID)
		} else {
			k8sObject, err = s.newK8sObject(pod.Object, k8sCluster.ID)
			if err != nil {
				klog.Errorf("failed to create K8S object %s: %v\n", pod.Object.Name, err)
				continue
			}

			newPod := &K8sPod{
				Name:           pod.Object.Name,
				K8sClusterID:   k8sCluster.ID,
				K8sNamespaceID: ns.ID,
				K8sObjectID:    k8sObject.ID,
				Qos:            pod.Qos,
			}
			result := s.db.Create(newPod)
			if result.Error != nil {
				klog.Errorf("failed to create Pod %s: %v\n", pod.Object.Name, err)
				continue
			}
			currentPod = newPod
		}

		err = s.addK8sContainer(pod.Containers, currentPod, ns, k8sCluster)
		if err != nil {
			klog.Errorf("failed to create container: %v\n", err)
			continue
		}

		err = s.addK8sObjectLabel(k8sObject, pod.Object.Labels)
		if err != nil {
			klog.Errorf("Failed to create label for %s: %v\n", k8sObject.Name, err)
		}
	}

	return nil
}

func (s *NexServer) findK8sObjectById(k8sObjectID uint) *K8sObject {
	var k8sObject K8sObject

	result := s.db.Where("id=?", k8sObjectID).First(&k8sObject)
	if result.Error != nil {
		return nil
	}

	return &k8sObject
}

func (s *NexServer) findK8sContainer(containerName string, podId, namespaceId, clusterId uint) *K8sContainer {
	var k8sContainer K8sContainer

	result := s.db.Where(
		"name=? AND k8s_pod_id=? AND k8s_namespace_id=? AND k8s_cluster_id=?",
		containerName, podId, namespaceId, clusterId).First(&k8sContainer)
	if result.Error != nil {
		return nil
	}

	return &k8sContainer
}

func (s *NexServer) mustValidK8sContainer(containerName string, podId, namespaceId, clusterId uint) (*K8sContainer, error) {
	var k8sContainer K8sContainer

	result := s.db.Where(
		"name=? AND k8s_pod_id=? AND k8s_namespace_id=? AND k8s_cluster_id=?",
		containerName, podId, namespaceId, clusterId).First(&k8sContainer)
	if result.Error != nil {
		k8sContainer = K8sContainer{
			Name:           containerName,
			K8sClusterID:   clusterId,
			K8sNamespaceID: namespaceId,
			K8sPodID:       podId,
		}

		result = s.db.Create(&k8sContainer)
		if result.Error != nil {
			return nil, fmt.Errorf("Failed to create K8S Container: %v\n", result.Error)
		}
	}

	return &k8sContainer, nil
}

func (s *NexServer) addWorkloads(workloads []*pb.K8SObject, ns *K8sNamespace, k8sCluster *K8sCluster) error {
	var k8sObject *K8sObject
	var err error

	defer func() {
		if r := recover(); r != nil {
			klog.Errorf("Failed to addWorkloads: %v\n", r)
		}
	}()

	for _, workload := range workloads {
		switch workload.Kind {
		case "Deployment":
			if deployment := s.getK8sDeployment(workload.Name, ns.ID, k8sCluster.ID); deployment != nil {
				k8sObject = s.getK8sObjectById(deployment.K8sObjectID)
				break
			}
			k8sObject, err = s.newK8sObject(workload, k8sCluster.ID)
			if err != nil {
				klog.Errorf("Failed to create K8S Object %s: %v\n", workload.Name, err)
				continue
			}
			deployment := &K8sDeployment{
				Name:           workload.Name,
				K8sClusterID:   ns.K8sClusterID,
				K8sNamespaceID: ns.ID,
				K8sObjectID:    k8sObject.ID,
			}
			result := s.db.Create(deployment)
			if result.Error != nil {
				klog.Errorf("Failed to create Deployment: %v\n", result.Error)
				continue
			}
		case "StatefulSet":
			if ss := s.getK8sStatefulSet(workload.Name, ns.ID, k8sCluster.ID); ss != nil {
				k8sObject = s.getK8sObjectById(ss.K8sObjectID)
				break
			}
			k8sObject, err = s.newK8sObject(workload, k8sCluster.ID)
			if err != nil {
				klog.Errorf("Failed to create K8S Object %s: %v\n", workload.Name, err)
				continue
			}
			statefulSet := &K8sStatefulSet{
				Name:           workload.Name,
				K8sClusterID:   ns.K8sClusterID,
				K8sNamespaceID: ns.ID,
				K8sObjectID:    k8sObject.ID,
			}
			result := s.db.Create(statefulSet)
			if result.Error != nil {
				klog.Errorf("Failed to create StatefulSet: %v\n", result.Error)
				continue
			}
		case "ReplicaSet":
			if rs := s.getK8sReplicaSet(workload.Name, ns.ID, k8sCluster.ID); rs != nil {
				k8sObject = s.getK8sObjectById(rs.K8sObjectID)
				break
			}
			k8sObject, err = s.newK8sObject(workload, k8sCluster.ID)
			if err != nil {
				klog.Errorf("Failed to create K8S Object %s: %v\n", workload.Name, err)
				continue
			}
			replicaSet := &K8sReplicaSet{
				Name:           workload.Name,
				K8sClusterID:   ns.K8sClusterID,
				K8sNamespaceID: ns.ID,
				K8sObjectID:    k8sObject.ID,
			}
			result := s.db.Create(replicaSet)
			if result.Error != nil {
				klog.Errorf("Failed to create ReplicaSet: %v\n", result.Error)
				continue
			}
		case "DaemonSet":
			if ds := s.getK8sDaemonSet(workload.Name, ns.ID, k8sCluster.ID); ds != nil {
				k8sObject = s.getK8sObjectById(ds.K8sObjectID)
				break
			}
			k8sObject, err = s.newK8sObject(workload, k8sCluster.ID)
			if err != nil {
				klog.Errorf("Failed to create K8S Object %s: %v\n", workload.Name, err)
				continue
			}
			daemonSet := &K8sDaemonSet{
				Name:           workload.Name,
				K8sClusterID:   ns.K8sClusterID,
				K8sNamespaceID: ns.ID,
				K8sObjectID:    k8sObject.ID,
			}
			result := s.db.Create(daemonSet)
			if result.Error != nil {
				klog.Errorf("Failed to create DaemonSet: %v\n", result.Error)
				continue
			}
		}

		err = s.addK8sObjectLabel(k8sObject, workload.Labels)
		if err != nil {
			klog.Errorf("Failed to create K8S Object Label: %v\n", err)
			continue
		}
	}

	return nil
}

func (s *NexServer) addNamespaces(namespaces []*pb.K8SNamespace, k8sCluster *K8sCluster) error {
	var k8sNS *K8sNamespace
	var err error

	for _, namespace := range namespaces {
		k8sNS = s.findK8sNamespace(namespace.Object.Name, k8sCluster.ID)
		if k8sNS == nil {
			k8sNS, err = s.newK8sNamespace(namespace, k8sCluster)
			if err != nil {
				return err
			}

			klog.Infof("Add new K8S namespace: %v @ %v\n", k8sNS.Name, k8sCluster.Name)
		}

		if err = s.addWorkloads(namespace.Workloads, k8sNS, k8sCluster); err != nil {
			klog.Errorf("Failed to add namespace %s workloads: %v\n", k8sNS.Name, err)
			continue
		}
		if err = s.addPods(namespace.Pods, k8sNS, k8sCluster); err != nil {
			klog.Errorf("Failed to add namespace %s workloads: %v\n", k8sNS.Name, err)
			continue
		}
	}

	return nil
}

func (s *NexServer) addK8sMetrics(in *pb.K8SMetrics, clusterId uint) (int, int) {
	var metricEndpoint *MetricEndpoint
	var metricType *MetricType
	var metricName *MetricName
	var metricLabel *MetricLabel
	var k8sMetric K8sMetric

	savedCount := 0
	skippedCount := 0

	for _, k8sNodeMetric := range in.K8SNodeMetrics {
		for _, reportMetric := range k8sNodeMetric.Metrics {
			k8sNode := s.getK8sNode(k8sNodeMetric.NodeName, clusterId)
			if k8sNode == nil {
				skippedCount += 1
				continue
			}

			k8sMetric.K8sClusterID = clusterId
			k8sMetric.K8sNodeID = k8sNode.ID

			metricEndpoint = s.getMetricEndpoint(reportMetric.Endpoint)
			metricType = s.getMetricType(reportMetric.Type)
			metricName = s.getMetricName(reportMetric.Name, metricType)
			metricLabel = s.getMetricLabel(reportMetric.Label)

			k8sMetric.EndpointID = metricEndpoint.ID
			k8sMetric.TypeID = metricType.ID
			k8sMetric.NameID = metricName.ID
			k8sMetric.LabelID = metricLabel.ID
			k8sMetric.Ts = time.Unix(reportMetric.Ts, 0)
			k8sMetric.Value = reportMetric.Value

			s.db.Create(&k8sMetric)
			savedCount += 1
		}
	}

	for _, k8sPodMetric := range in.K8SPodMetrics {
		for _, k8sContainerMetric := range k8sPodMetric.K8SContainerMetrics {
			k8sNS := s.getK8sNamespace(k8sPodMetric.Namespace, clusterId)
			if k8sNS == nil {
				skippedCount += 1
				continue
			}
			k8sPod := s.getK8sPod(k8sPodMetric.PodName, k8sNS.ID, clusterId)
			if k8sPod == nil {
				skippedCount += 1
				continue
			}

			k8sContainer, err := s.mustValidK8sContainer(
				k8sContainerMetric.Container, k8sPod.ID, k8sNS.ID, clusterId)
			if err != nil {
				skippedCount += 1
				continue
			}

			k8sMetric.K8sClusterID = clusterId
			k8sMetric.K8sNamespaceID = k8sNS.ID
			k8sMetric.K8sPodID = k8sPod.ID
			k8sMetric.K8sContainerID = k8sContainer.ID
			k8sMetric.K8sNodeID = 0

			for _, reportMetric := range k8sContainerMetric.Metrics {
				metricEndpoint = s.getMetricEndpoint(reportMetric.Endpoint)
				metricType = s.getMetricType(reportMetric.Type)
				metricName = s.getMetricName(reportMetric.Name, metricType)
				metricLabel = s.getMetricLabel(reportMetric.Label)

				k8sMetric.EndpointID = metricEndpoint.ID
				k8sMetric.TypeID = metricType.ID
				k8sMetric.NameID = metricName.ID
				k8sMetric.LabelID = metricLabel.ID
				k8sMetric.Ts = time.Unix(reportMetric.Ts, 0)
				k8sMetric.Value = reportMetric.Value

				s.db.Create(&k8sMetric)
				savedCount += 1
			}
		}
	}

	return savedCount, skippedCount
}
