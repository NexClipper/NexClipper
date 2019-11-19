/*
Copyright 2019 NexClipper.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package nexserver

import (
	"fmt"
	"github.com/dgraph-io/ristretto"
)

func (s *NexServer) initCache() (*ristretto.Cache, error) {
	cache, err := ristretto.NewCache(&ristretto.Config{
		NumCounters: 1e7,
		MaxCost:     1 << 30,
		BufferItems: 64,
	})
	if err != nil {
		return nil, fmt.Errorf("failed to initialize cache: %v\n", err)
	}
	s.cache = cache

	return cache, nil
}

func (s *NexServer) getMetricEndpoint(endpoint string) *MetricEndpoint {
	value, found := s.cache.Get(fmt.Sprintf("ME_%s", endpoint))
	if !found {
		metricEndpoint := s.findMetricEndpoint(endpoint)
		s.cache.Set(fmt.Sprintf("ME_%s", endpoint), *metricEndpoint, 1)

		return metricEndpoint
	}
	metricEndpoint := value.(MetricEndpoint)

	return &metricEndpoint
}

func (s *NexServer) getMetricType(typeName string) *MetricType {
	value, found := s.cache.Get(fmt.Sprintf("MT_%s", typeName))
	if !found {
		metricType := s.findMetricType(typeName)
		s.cache.Set(fmt.Sprintf("MT_%s", typeName), *metricType, 1)

		return metricType
	}
	metricType := value.(MetricType)

	return &metricType
}

func (s *NexServer) getMetricName(name string, metricType *MetricType) *MetricName {
	value, found := s.cache.Get(fmt.Sprintf("MN_%d_%s", metricType.ID, name))
	if !found {
		metricName := s.findMetricName(name, metricType)
		s.cache.Set(fmt.Sprintf("MN_%d_%s", metricType.ID, name), *metricName, 1)

		return metricName
	}
	metricName := value.(MetricName)

	return &metricName
}

func (s *NexServer) getMetricLabel(label string) *MetricLabel {
	value, found := s.cache.Get(fmt.Sprintf("ML_%s", label))
	if !found {
		metricLabel := s.findMetricLabel(label)
		s.cache.Set(fmt.Sprintf("ML_%s", label), *metricLabel, 1)

		return metricLabel
	}
	metricLabel := value.(MetricLabel)

	return &metricLabel
}

func (s *NexServer) getNode(hostName string, clusterId uint) *Node {
	key := fmt.Sprintf("NODE_%d_%s", clusterId, hostName)

	value, found := s.cache.Get(key)
	if !found {
		node := s.findNode(hostName, clusterId)
		if node == nil {
			return nil
		}

		s.cache.Set(key, *node, 1)
		return node
	}

	node := value.(Node)

	return &node
}

func (s *NexServer) getNodeById(id, clusterId uint) *Node {
	key := fmt.Sprintf("NODEBYID_%d_%d", clusterId, id)

	value, found := s.cache.Get(key)
	if !found {
		node := s.findNodeById(id, clusterId)
		if node == nil {
			return nil
		}

		s.cache.Set(key, *node, 1)
		return node
	}

	node := value.(Node)

	return &node
}

func (s *NexServer) getNodeByAgent(agent *Agent) *Node {
	key := fmt.Sprintf("NODE_%d", agent.ID)

	value, found := s.cache.Get(key)
	if !found {
		node := s.findNodeByAgent(agent)
		if node == nil {
			return nil
		}

		s.cache.Set(key, *node, 1)
		return node
	}

	node := value.(Node)

	return &node
}

func (s *NexServer) getContainer(containerName string, nodeId, clusterId uint) *Container {
	key := fmt.Sprintf("CONT_%d_%d_%s", clusterId, nodeId, containerName)

	value, found := s.cache.Get(key)
	if !found {
		container := s.findContainer(containerName, nodeId, clusterId)
		if container == nil {
			return nil
		}

		s.cache.Set(key, *container, 1)
		return container
	}

	container := value.(Container)
	return &container
}

func (s *NexServer) getProcess(processName string, pid int32, nodeId, clusterId uint) *Process {
	key := fmt.Sprintf("PROC_%d_%d_%d_%s", clusterId, nodeId, pid, processName)

	value, found := s.cache.Get(key)
	if !found {
		process := s.findProcess(processName, pid, nodeId, clusterId)
		if process == nil {
			return nil
		}

		s.cache.Set(key, *process, 1)
		return process
	}

	process := value.(Process)
	return &process
}

func (s *NexServer) getRemoteAgent(machineId string) *Agent {
	key := fmt.Sprintf("AGENT_%s", machineId)

	value, found := s.cache.Get(key)
	if !found {
		agent := s.findRemoteAgent(machineId)
		if agent == nil {
			return nil
		}

		s.cache.Set(key, *agent, 1)
		return agent
	}

	agent := value.(Agent)
	return &agent
}

func (s *NexServer) getK8sNode(nodeName string, k8sClusterId uint) *K8sNode {
	key := fmt.Sprintf("K8S_NODE_%d_%s", k8sClusterId, nodeName)

	value, found := s.cache.Get(key)
	if !found {
		node := s.findK8sNode(nodeName, k8sClusterId)
		if node == nil {
			return nil
		}

		s.cache.Set(key, *node, 1)
		return node
	}

	node := value.(K8sNode)
	return &node
}

func (s *NexServer) getK8sNamespace(nsName string, k8sClusterId uint) *K8sNamespace {
	key := fmt.Sprintf("K8S_NS_%d_%s", k8sClusterId, nsName)

	value, found := s.cache.Get(key)
	if !found {
		ns := s.findK8sNamespace(nsName, k8sClusterId)
		if ns == nil {
			return nil
		}

		s.cache.Set(key, *ns, 1)
		return ns
	}

	ns := value.(K8sNamespace)
	return &ns
}

func (s *NexServer) getK8sDeployment(name string, nsId uint, k8sClusterId uint) *K8sDeployment {
	key := fmt.Sprintf("K8S_DP_%d_%d_%s", k8sClusterId, nsId, name)

	value, found := s.cache.Get(key)
	if !found {
		dp := s.findK8sDeployment(name, nsId, k8sClusterId)
		if dp == nil {
			return nil
		}

		s.cache.Set(key, *dp, 1)
		return dp
	}

	dp := value.(K8sDeployment)
	return &dp
}

func (s *NexServer) getK8sStatefulSet(name string, nsId uint, k8sClusterId uint) *K8sStatefulSet {
	key := fmt.Sprintf("K8S_SS_%d_%d_%s", k8sClusterId, nsId, name)

	value, found := s.cache.Get(key)
	if !found {
		ss := s.findK8sStatefulSet(name, nsId, k8sClusterId)
		if ss == nil {
			return nil
		}

		s.cache.Set(key, *ss, 1)
		return ss
	}

	ss := value.(K8sStatefulSet)
	return &ss
}

func (s *NexServer) getK8sReplicaSet(name string, nsId uint, k8sClusterId uint) *K8sReplicaSet {
	key := fmt.Sprintf("K8S_RS_%d_%d_%s", k8sClusterId, nsId, name)

	value, found := s.cache.Get(key)
	if !found {
		rs := s.findK8sReplicaSet(name, nsId, k8sClusterId)
		if rs == nil {
			return nil
		}

		s.cache.Set(key, *rs, 1)
		return rs
	}

	rs := value.(K8sReplicaSet)
	return &rs
}

func (s *NexServer) getK8sDaemonSet(name string, nsId uint, k8sClusterId uint) *K8sDaemonSet {
	key := fmt.Sprintf("K8S_DS_%d_%d_%s", k8sClusterId, nsId, name)

	value, found := s.cache.Get(key)
	if !found {
		ds := s.findK8sDaemonSet(name, nsId, k8sClusterId)
		if ds == nil {
			return nil
		}

		s.cache.Set(key, *ds, 1)
		return ds
	}

	ds := value.(K8sDaemonSet)
	return &ds
}

func (s *NexServer) getK8sObjectById(k8sObjectId uint) *K8sObject {
	key := fmt.Sprintf("K8S_OBJ_%d", k8sObjectId)

	value, found := s.cache.Get(key)
	if !found {
		obj := s.findK8sObjectById(k8sObjectId)
		if obj == nil {
			return nil
		}

		s.cache.Set(key, *obj, 1)
		return obj
	}

	obj := value.(K8sObject)
	return &obj
}

func (s *NexServer) getK8sPod(podName string, namespaceId uint, k8sClusterId uint) *K8sPod {
	key := fmt.Sprintf("K8S_POD_%d_%d_%s", namespaceId, k8sClusterId, podName)

	value, found := s.cache.Get(key)
	if !found {
		pod := s.findK8sPod(podName, namespaceId, k8sClusterId)
		if pod == nil {
			return nil
		}

		s.cache.Set(key, *pod, 1)
		return pod
	}

	pod := value.(K8sPod)
	return &pod
}

func (s *NexServer) purgeAll() {
	if s.cache != nil {
		s.cache.Clear()
	}
}
