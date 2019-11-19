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
	"time"
)

type IncidentItem struct {
	ClusterId   uint
	NodeId      uint
	ProcessId   uint
	ContainerId uint
	PodId       uint
	TargetType  string
	Target      string
	Value       float64
	Condition   float64
	EventName   string
	ReportedTs  time.Time
	DetectedTs  time.Time
}

type Incident struct {
	incidentMap map[string][]*IncidentItem
}

func (s *NexServer) InitBasicRuleChecker() {
	s.CheckNodeBasicIncident(s.metricChannel)
}

func (s *NexServer) FireAgentDisconnected(clusterId, nodeId uint, hostName string) {
	item := &IncidentItem{
		ClusterId:   clusterId,
		NodeId:      nodeId,
		ProcessId:   0,
		ContainerId: 0,
		PodId:       0,
		TargetType:  "AGENT",
		Target:      hostName,
		EventName:   "agent_disconnected",
		ReportedTs:  time.Now(),
		DetectedTs:  time.Now(),
	}

	s.AddIncident("agent_disconnected", item)
}

func (s *NexServer) ClearAgentConnected(clusterId, nodeId uint, hostName string) {
	item := &IncidentItem{
		ClusterId:   clusterId,
		NodeId:      nodeId,
		ProcessId:   0,
		ContainerId: 0,
		PodId:       0,
		TargetType:  "AGENT",
		Target:      hostName,
		EventName:   "agent_disconnected",
		ReportedTs:  time.Now(),
		DetectedTs:  time.Now(),
	}

	if s.IsExistIncident("agent_disconnected", item) == true {
		s.ClearIncident("agent_disconnected", item)

		item.EventName = "agent_connected"
		s.AddIncident("agent_connected", item)
	}
}

func (s *NexServer) CheckNodeBasicIncident(nodeMetricChan chan Metric) {
	gaugeType := s.getMetricType("gauge")
	nodeCpuLoad1 := s.getMetricName("node_cpu_load_avg_1", gaugeType)
	nodeDiskFree := s.getMetricName("node_disk_free", gaugeType)
	nodeMemoryUsedPercent := s.getMetricName("node_memory_used_percent", gaugeType)

	for metric := range nodeMetricChan {
		if metric.NameID == nodeCpuLoad1.ID {
			if metric.Value >= s.config.BasicRule.NodeCpuLoad1 {
				node := s.getNodeById(metric.NodeID, metric.ClusterID)

				incidentItem := &IncidentItem{
					ClusterId:  metric.ClusterID,
					NodeId:     metric.NodeID,
					TargetType: "NODE",
					Target:     node.Host,
					Value:      metric.Value,
					Condition:  s.config.BasicRule.NodeCpuLoad1,
					EventName:  "node_cpu_load_avg_1",
					ReportedTs: metric.Ts,
					DetectedTs: time.Now(),
				}
				s.AddIncident("node_cpu_load_avg_1", incidentItem)
			}
		} else if metric.NameID == nodeDiskFree.ID {
			if metric.Value < s.config.BasicRule.NodeDiskFree {
				node := s.getNodeById(metric.NodeID, metric.ClusterID)

				incidentItem := &IncidentItem{
					ClusterId:  metric.ClusterID,
					NodeId:     metric.NodeID,
					TargetType: "NODE",
					Target:     node.Host,
					Value:      metric.Value,
					Condition:  s.config.BasicRule.NodeDiskFree,
					EventName:  "node_disk_free",
					ReportedTs: metric.Ts,
					DetectedTs: time.Now(),
				}
				s.AddIncident("node_disk_free", incidentItem)
			}
		} else if metric.NameID == nodeMemoryUsedPercent.ID {
			if 100.0-metric.Value < s.config.BasicRule.NodeMemoryFree {
				node := s.getNodeById(metric.NodeID, metric.ClusterID)

				incidentItem := &IncidentItem{
					ClusterId:  metric.ClusterID,
					NodeId:     metric.NodeID,
					TargetType: "NODE",
					Target:     node.Host,
					Value:      100.0 - metric.Value,
					Condition:  s.config.BasicRule.NodeMemoryFree,
					EventName:  "node_memory_free",
					ReportedTs: metric.Ts,
					DetectedTs: time.Now(),
				}
				s.AddIncident("node_memory_free", incidentItem)
			}
		}
	}
}

func (s *NexServer) IsSameIncident(left, right *IncidentItem) bool {
	if left.EventName != right.EventName {
		return false
	}
	if left.ClusterId != right.ClusterId || left.NodeId != right.NodeId {
		return false
	}
	if left.Target != right.Target || left.TargetType != right.TargetType {
		return false
	}
	if left.ProcessId != right.ProcessId || left.ContainerId != right.ContainerId || left.PodId != right.PodId {
		return false
	}

	return true
}

func (s *NexServer) AddIncident(eventName string, item *IncidentItem) bool {
	itemList, found := s.incidentMap[eventName]
	if found == false {
		itemList = make([]*IncidentItem, 0, 10)
	}

	itemList = append(itemList, item)
	if len(itemList) > 10 {
		itemList = itemList[len(itemList)-10:]
	}

	s.incidentMap[eventName] = itemList

	return true
}

func (s *NexServer) ClearIncident(eventName string, item *IncidentItem) bool {
	itemList, found := s.incidentMap[eventName]
	if found == false {
		return false
	}

	for idx, it := range itemList {
		if s.IsSameIncident(it, item) {
			itemList = append(itemList[:idx], itemList[idx+1:]...)
			break
		}
	}

	return true
}

func (s *NexServer) IsExistIncident(eventName string, item *IncidentItem) bool {
	itemList, found := s.incidentMap[eventName]
	if found == false {
		return false
	}

	for _, it := range itemList {
		if s.IsSameIncident(it, item) {
			return true
		}
	}

	return false
}
