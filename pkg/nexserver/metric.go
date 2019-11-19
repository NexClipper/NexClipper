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
	pb "github.com/NexClipper/NexClipper/api"
	"time"
)

func (s *NexServer) addMetrics(in *pb.Metrics, clusterId uint, nodeId uint, source interface{}) (int, int) {
	var metricEndpoint *MetricEndpoint
	var metricType *MetricType
	var metricName *MetricName
	var metricLabel *MetricLabel
	var sourceType pb.Metric_SourceType

	var metric Metric
	var container *Container
	var process *Process

	savedCount := 0
	skippedCount := 0

	for _, reportMetric := range in.Metrics {
		sourceType = reportMetric.SourceType
		metricEndpoint = s.getMetricEndpoint(reportMetric.Endpoint)
		metricType = s.getMetricType(reportMetric.Type)
		metricName = s.getMetricName(reportMetric.Name, metricType)
		metricLabel = s.getMetricLabel(reportMetric.Label)

		switch sourceType {
		case pb.Metric_CONTAINER:
			if source != nil {
				srcContainer := source.(Container)
				container = &srcContainer
			} else {
				node := s.getNode(reportMetric.Node, clusterId)
				if node == nil {
					skippedCount += 1
					continue
				}
				container = s.getContainer(reportMetric.Source, node.ID, clusterId)
				if container == nil {
					skippedCount += 1
					continue
				}
			}
			metric.ContainerID = container.ID
		case pb.Metric_PROCESS:
			if source != nil {
				srcProcess := source.(Process)
				process = &srcProcess
			} else {
				node := s.getNode(reportMetric.Node, clusterId)
				if node == nil {
					skippedCount += 1
					continue
				}
				process = s.getProcess(reportMetric.Source, reportMetric.SourceInt, node.ID, clusterId)
				if process == nil {
					skippedCount += 1
					continue
				}
			}
			metric.ProcessID = process.ID
		}

		metric.ClusterID = clusterId
		metric.NodeID = nodeId
		metric.EndpointID = metricEndpoint.ID
		metric.TypeID = metricType.ID
		metric.NameID = metricName.ID
		metric.LabelID = metricLabel.ID
		metric.Ts = time.Unix(reportMetric.Ts, 0)
		metric.Value = reportMetric.Value

		s.db.Create(&metric)
		savedCount += 1

		s.metricChannel <- metric
	}

	s.metricSaveCounterLock.Lock()
	s.metricSaveCounter += uint64(savedCount)
	s.metricSaveCounterLock.Unlock()

	return savedCount, skippedCount
}
