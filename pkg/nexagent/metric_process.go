package nexagent

import (
	"fmt"
	pb "github.com/NexClipper/NexClipper/api"
	"github.com/shirou/gopsutil/cpu"
	"log"
	"strings"

	"github.com/shirou/gopsutil/process"
	"time"
)

func (s *NexAgent) isScrapeProcess(
	ps *process.Process, mem *process.MemoryInfoStat,
	cpuPercent float64, memPercent float32, cpuTimes *cpu.TimesStat) bool {

	name, err := ps.Name()
	if err != nil {
		return false
	}
	if savedInfo, found := s.processInfoMap[ps.Pid]; found {
		savedName, err := savedInfo.ps.Name()
		if err != nil {
			delete(s.processInfoMap, ps.Pid)
		} else {
			if name == savedName {
				savedInfo.updated = true
				return true
			}
		}
	}

	if cpuPercent < 0.1 {
		return false
	}
	if memPercent < 0.1 {
		return false
	}

	if mem.RSS == 0 || mem.VMS == 0 {
		return false
	}

	s.processInfoMap[ps.Pid] = &ProcessInfo{
		updated: true,
		ps:      ps,
		mem:     mem,
		cpu:     cpuTimes,
	}
	return true
}

func (s *NexAgent) calculateProcessLoad(ps *process.Process, now *time.Time) (float64, float64) {
	prevProcessInfo, found := s.processInfoMap[ps.Pid]
	if found == false {
		return 0, 0
	}

	cpuTimes, err := ps.Times()
	if err != nil {
		return 0, 0
	}
	elapsedSeconds := now.Sub(s.lastCheckTS).Seconds()
	cpuUserDiff := cpuTimes.User - prevProcessInfo.cpu.User
	cpuSystemDiff := cpuTimes.System - prevProcessInfo.cpu.System

	if elapsedSeconds == 0 {
		return 0, 0
	}
	cpuUserPerSec := cpuUserDiff / elapsedSeconds
	cpuSystemPerSec := cpuSystemDiff / elapsedSeconds

	return cpuUserPerSec, cpuSystemPerSec
}

func (s *NexAgent) sendProcessMetrics(ts *time.Time) {
	defer func() {
		if r := recover(); r != nil {
			log.Printf("sendProcessMetrics: %v\n", r)
		}
	}()
	s.clearProcessUpdateFlag()

	psInfoAll, err := process.Processes()
	if err != nil {
		log.Printf("Failed to get process information: %v\n", err)
		return
	}

	processes := make([]*pb.Process, 0, len(psInfoAll))
	for _, psInfo := range psInfoAll {
		name, err := psInfo.Name()
		if err != nil {
			continue
		}
		memInfo, err := psInfo.MemoryInfo()
		if err != nil {
			continue
		}

		cpuPercent, err := psInfo.CPUPercent()
		if err != nil {
			continue
		}
		memPercent, err := psInfo.MemoryPercent()
		if err != nil {
			continue
		}
		cpuTimes, err := psInfo.Times()
		if err != nil {
			continue
		}

		if !s.isScrapeProcess(psInfo, memInfo, cpuPercent, memPercent, cpuTimes) {
			continue
		}

		cpuUserLoad, cpuSystemLoad := s.calculateProcessLoad(psInfo, ts)

		cmd, err := psInfo.Cmdline()
		if err != nil {
			continue
		}
		if strings.Contains(cmd, "docker ps -a") {
			continue
		}

		processMetrics := &pb.Metrics{
			Metrics: make([]*pb.Metric, 0, 16),
		}

		label := fmt.Sprintf("host=%s,pid=%d,process=%s", s.hostName, psInfo.Pid, name)
		metrics := &BasicMetrics{
			&BasicMetric{
				Name:  "process_cpu_user_load",
				Label: label,
				Type:  "gauge",
				Value: cpuUserLoad,
			},
			&BasicMetric{
				Name:  "process_cpu_system_load",
				Label: label,
				Type:  "gauge",
				Value: cpuSystemLoad,
			},
			&BasicMetric{
				Name:  "process_cpu_percent",
				Label: label,
				Type:  "gauge",
				Value: cpuPercent,
			},
			&BasicMetric{
				Name:  "process_memory_percent",
				Label: label,
				Type:  "gauge",
				Value: float64(memPercent),
			},
			&BasicMetric{
				Name:  "process_memory_rss",
				Label: label,
				Type:  "gauge",
				Value: float64(memInfo.RSS),
			},
			&BasicMetric{
				Name:  "process_memory_vms",
				Label: label,
				Type:  "gauge",
				Value: float64(memInfo.VMS),
			},
			&BasicMetric{
				Name:  "process_memory_data",
				Label: label,
				Type:  "gauge",
				Value: float64(memInfo.Data),
			},
			&BasicMetric{
				Name:  "process_memory_stack",
				Label: label,
				Type:  "gauge",
				Value: float64(memInfo.Stack),
			},
			&BasicMetric{
				Name:  "process_memory_locked",
				Label: label,
				Type:  "gauge",
				Value: float64(memInfo.Locked),
			},
			&BasicMetric{
				Name:  "process_memory_swap",
				Label: label,
				Type:  "gauge",
				Value: float64(memInfo.Swap),
			},
		}

		s.appendMetrics(processMetrics, metrics, "/process/metrics", pb.Metric_PROCESS, name, psInfo.Pid, ts)

		processes = append(processes, &pb.Process{
			Pid:     psInfo.Pid,
			Name:    name,
			Metrics: processMetrics,
		})
	}

	processAll := &pb.ProcessAll{
		Cluster:   s.config.Agent.Cluster,
		Host:      s.hostName,
		Processes: processes,
	}

	s.removeTerminatedProcess()

	resp, err := s.collectorClient.UpdateProcess(s.ctx, processAll)
	if err != nil {
		log.Printf("sendProcessMetrics: failed to send: %v\n", err)
	}
	if !resp.Success {
		log.Printf("sendProcessMetrics: response: %v\n", err)
	}
}
