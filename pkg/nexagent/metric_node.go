package nexagent

import (
	"fmt"
	pb "github.com/NexClipper/nexclipper-engine/api"
	"github.com/shirou/gopsutil/cpu"
	"github.com/shirou/gopsutil/load"
	"github.com/shirou/gopsutil/mem"
	"log"
	"time"
)

func (s *NexAgent) addNodeLoadMetric(metrics *pb.Metrics, ts *time.Time) *pb.Metrics {
	avgStat, err := load.Avg()
	if err != nil {
		log.Printf("Failed get load average stat: %v\n", err)
		return nil
	}

	loadMetrics := BasicMetrics{
		&BasicMetric{
			Name:  "node_cpu_load_avg",
			Label: fmt.Sprintf("host=%s,min=1", s.hostName),
			Type:  "gauge",
			Value: avgStat.Load1,
		},
		&BasicMetric{
			Name:  "node_cpu_load_avg",
			Label: fmt.Sprintf("host=%s,min=5", s.hostName),
			Type:  "gauge",
			Value: avgStat.Load5,
		},
		&BasicMetric{
			Name:  "node_cpu_load_avg",
			Label: fmt.Sprintf("host=%s,min=15", s.hostName),
			Type:  "gauge",
			Value: avgStat.Load15,
		},
	}

	s.appendMetrics(metrics, &loadMetrics, "/node/metrics", pb.Metric_NODE, s.hostName, 0, ts)

	log.Printf("Load Avg. load1: %v, load5: %v, load15: %v\n",
		avgStat.Load1, avgStat.Load5, avgStat.Load15)

	return metrics
}

func (s *NexAgent) addNodeCpuMetric(metrics *pb.Metrics, ts *time.Time) *pb.Metrics {
	cpuStats, err := cpu.Times(true)
	if err != nil {
		return metrics
	}

	for _, cpuStat := range cpuStats {
		cpuMetrics := BasicMetrics{
			&BasicMetric{
				Name:  "node_cpu_user",
				Label: fmt.Sprintf("host=%s,cpu=%s", s.hostName, cpuStat.CPU),
				Type:  "gauge",
				Value: cpuStat.User,
			},
			&BasicMetric{
				Name:  "node_cpu_system",
				Label: fmt.Sprintf("host=%s,cpu=%s", s.hostName, cpuStat.CPU),
				Type:  "gauge",
				Value: cpuStat.System,
			},
			&BasicMetric{
				Name:  "node_cpu_idle",
				Label: fmt.Sprintf("host=%s,cpu=%s", s.hostName, cpuStat.CPU),
				Type:  "gauge",
				Value: cpuStat.Idle,
			},
		}

		s.appendMetrics(metrics, &cpuMetrics, "/node/metrics", pb.Metric_NODE, s.hostName, 0, ts)
	}

	return metrics
}

func (s *NexAgent) addNodeMemoryMetric(metrics *pb.Metrics, ts *time.Time) *pb.Metrics {
	vMemStat, err := mem.VirtualMemory()
	if err != nil {
		return metrics
	}

	memoryMetrics := BasicMetrics{
		&BasicMetric{
			Name:  "node_memory_total",
			Label: fmt.Sprintf("host=%s", s.hostName),
			Type:  "gauge",
			Value: float64(vMemStat.Total),
		},
		&BasicMetric{
			Name:  "node_memory_available",
			Label: fmt.Sprintf("host=%s", s.hostName),
			Type:  "gauge",
			Value: float64(vMemStat.Available),
		},
		&BasicMetric{
			Name:  "node_memory_used",
			Label: fmt.Sprintf("host=%s", s.hostName),
			Type:  "gauge",
			Value: float64(vMemStat.Used),
		},
		&BasicMetric{
			Name:  "node_memory_used_percent",
			Label: fmt.Sprintf("host=%s", s.hostName),
			Type:  "gauge",
			Value: vMemStat.UsedPercent,
		},
		&BasicMetric{
			Name:  "node_memory_free",
			Label: fmt.Sprintf("host=%s", s.hostName),
			Type:  "gauge",
			Value: float64(vMemStat.Free),
		},
	}

	if s.hostInfo.OS == "linux" {
		memoryMetrics = append(memoryMetrics, &BasicMetric{
			Name:  "node_memory_buffers",
			Label: fmt.Sprintf("host=%s,os=linux", s.hostName),
			Type:  "gauge",
			Value: float64(vMemStat.Buffers),
		})

		memoryMetrics = append(memoryMetrics, &BasicMetric{
			Name:  "node_memory_cached",
			Label: fmt.Sprintf("host=%s,os=linux", s.hostName),
			Type:  "gauge",
			Value: float64(vMemStat.Cached),
		})
	}

	s.appendMetrics(metrics, &memoryMetrics, "/node/metrics", pb.Metric_NODE, s.hostName, 0, ts)

	return metrics
}

func (s *NexAgent) sendNodeMetrics(ts *time.Time) {
	defer func() {
		if r := recover(); r != nil {
			log.Printf("Network error: %v\n", r)
		}
	}()

	metrics := &pb.Metrics{
		Metrics: make([]*pb.Metric, 0, 10),
	}

	s.addNodeLoadMetric(metrics, ts)
	s.addNodeCpuMetric(metrics, ts)
	s.addNodeMemoryMetric(metrics, ts)

	_, err := s.collectorClient.ReportMetrics(s.ctx, metrics)
	if err != nil {
		log.Printf("Failed sendMetrics(): %v\n", err)
	}
}
