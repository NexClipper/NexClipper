package nexagent

import (
	"fmt"
	pb "github.com/NexClipper/NexClipper/api"
	"github.com/shirou/gopsutil/cpu"
	"io/ioutil"
	"log"
	"os"
	"path"
	"strings"

	"github.com/shirou/gopsutil/docker"
	"time"
)

type ContainerInfo struct {
	dockerStat *docker.CgroupDockerStat
	cpuStat    *cpu.TimesStat
	memStat    *docker.CgroupMemStat
	qos        string
}

func (s *NexAgent) findPodContainers(qos string, cpuBasePaths []string, memBasePaths []string,
	isGuaranteed bool, containerInfoMap map[string]*ContainerInfo) map[string]*ContainerInfo {

	for _, pathByType := range [][]string{cpuBasePaths, memBasePaths} {
		for _, basePath := range pathByType {
			_, err := os.Stat(basePath)
			if err != nil {
				continue
			}

			pods, err := ioutil.ReadDir(basePath)
			if err != nil {
				continue
			}

			for _, pod := range pods {
				if isGuaranteed {
					if strings.Contains(pod.Name(), "burstable") ||
						strings.Contains(pod.Name(), "besteffort") {
						continue
					}
				}
				fileInfo, err := os.Stat(path.Join(basePath, pod.Name()))
				if err != nil {
					continue
				}
				if !fileInfo.IsDir() {
					continue
				}

				containers, err := ioutil.ReadDir(path.Join(basePath, pod.Name()))
				if err != nil {
					continue
				}

				basePath := path.Join(basePath, pod.Name())
				for _, container := range containers {
					cName := container.Name()
					cID := container.Name()
					if strings.HasPrefix(cID, "docker-") {
						cID = cID[7:]
					}
					if strings.HasSuffix(cID, ".scope") {
						cID = cID[:len(cID)-6]
					}

					targetPath := path.Join(basePath, cName)
					fileInfo, err := os.Stat(targetPath)
					if err != nil {
						continue
					}
					if !fileInfo.IsDir() {
						continue
					}

					containerInfo, found := containerInfoMap[cID]
					if !found {
						continue
					}

					cpuStat, err := docker.CgroupCPU(cName, basePath)
					if err == nil {
						containerInfo.cpuStat = cpuStat
					}
					memStat, err := docker.CgroupMem(cName, basePath)
					if err == nil {
						containerInfo.memStat = memStat
					}
					containerInfo.qos = qos
				}
			}
		}
	}

	return containerInfoMap
}

func (s *NexAgent) addPodContainerInfos(containerInfoMap map[string]*ContainerInfo) map[string]*ContainerInfo {

	guaranteedCpuPaths := []string{
		"/sys/fs/cgroup/cpuacct/kubepods/",
		"/sys/fs/cgroup/cpuacct/kubepods.slice/",
	}
	guaranteedMemPaths := []string{
		"/sys/fs/cgroup/memory/kubepods/",
		"/sys/fs/cgroup/memory/kubepods.slice/",
	}
	burstableCpuPaths := []string{
		"/sys/fs/cgroup/cpuacct/kubepods/burstable",
		"/sys/fs/cgroup/cpuacct/kubepods.slice/kubepods-burstable.slice",
	}
	burstableMemPaths := []string{
		"/sys/fs/cgroup/memory/kubepods/burstable",
		"/sys/fs/cgroup/memory/kubepods.slice/kubepods-burstable.slice",
	}
	bestEffortCpuPaths := []string{
		"/sys/fs/cgroup/cpuacct/kubepods/besteffort",
		"/sys/fs/cgroup/cpuacct/kubepods.slice/kubepods-besteffort.slice",
	}
	bestEffortMemPaths := []string{
		"/sys/fs/cgroup/memory/kubepods/besteffort",
		"/sys/fs/cgroup/memory/kubepods.slice/kubepods-besteffort.slice",
	}

	s.findPodContainers("guaranteed",
		guaranteedCpuPaths, guaranteedMemPaths, true, containerInfoMap)
	s.findPodContainers("burstable",
		burstableCpuPaths, burstableMemPaths, false, containerInfoMap)
	s.findPodContainers("besteffort",
		bestEffortCpuPaths, bestEffortMemPaths, false, containerInfoMap)

	return containerInfoMap
}

func (s *NexAgent) sendDockerMetrics(ts *time.Time) {
	defer func() {
		if r := recover(); r != nil {
			log.Printf("Error: %v\n", r)
		}
	}()

	dockerStats, err := docker.GetDockerStat()
	if err != nil {
		return
	}

	dockerStatMap := make(map[string]*docker.CgroupDockerStat)
	for _, dockerStat := range dockerStats {
		dockerStatMap[dockerStat.ContainerID] = &dockerStat
	}

	containers := make([]*pb.Container, 0, len(dockerStats))
	containerInfoMap := make(map[string]*ContainerInfo)

	for _, dockerStatValue := range dockerStats {
		dockerStat := dockerStatValue
		if !dockerStat.Running {
			continue
		}

		cID := dockerStat.ContainerID
		containerInfoMap[cID] = &ContainerInfo{
			dockerStat: &dockerStat,
			cpuStat:    nil,
			memStat:    nil,
		}

		cpuStat, err := docker.CgroupCPUDocker(cID)
		if err != nil {
			continue
		}
		memStat, err := docker.CgroupMemDocker(cID)
		if err != nil {
			continue
		}

		containerInfoMap[cID].cpuStat = cpuStat
		containerInfoMap[cID].memStat = memStat
	}

	s.addPodContainerInfos(containerInfoMap)

	for _, containerInfo := range containerInfoMap {
		containerMetrics := &pb.Metrics{
			Metrics: make([]*pb.Metric, 0, 8),
		}

		dockerStat := containerInfo.dockerStat
		cpuStat := containerInfo.cpuStat
		memStat := containerInfo.memStat

		if cpuStat == nil || memStat == nil {
			continue
		}

		metrics := &BasicMetrics{
			&BasicMetric{
				Name:  "container_cpu_usage_total",
				Label: fmt.Sprintf("host=%s,container=%s", s.hostName, dockerStat.Name),
				Type:  "counter",
				Value: cpuStat.Total(),
			},
			&BasicMetric{
				Name:  "container_cpu_usage_user",
				Label: fmt.Sprintf("host=%s,container=%s", s.hostName, dockerStat.Name),
				Type:  "counter",
				Value: cpuStat.User,
			},
			&BasicMetric{
				Name:  "container_cpu_usage_system",
				Label: fmt.Sprintf("host=%s,container=%s", s.hostName, dockerStat.Name),
				Type:  "counter",
				Value: cpuStat.System,
			},
			&BasicMetric{
				Name:  "container_memory_rss_total",
				Label: fmt.Sprintf("host=%s,container=%s", s.hostName, dockerStat.Name),
				Type:  "counter",
				Value: float64(memStat.TotalRSS),
			},
			&BasicMetric{
				Name:  "container_memory_rss",
				Label: fmt.Sprintf("host=%s,container=%s", s.hostName, dockerStat.Name),
				Type:  "gauge",
				Value: float64(memStat.RSS),
			},
		}

		s.appendMetrics(containerMetrics, metrics, "/container/metrics",
			pb.Metric_CONTAINER, dockerStat.ContainerID, 0, ts)

		containers = append(containers, &pb.Container{
			Type:        "docker",
			ContainerId: dockerStat.ContainerID,
			Name:        dockerStat.Name,
			Image:       dockerStat.Image,
			Metrics:     containerMetrics,
		})
	}

	containersAll := &pb.ContainerAll{
		Cluster:    s.config.Agent.Cluster,
		Host:       s.hostName,
		Containers: containers,
	}

	resp, err := s.collectorClient.UpdateContainer(s.ctx, containersAll)
	if err != nil {
		log.Printf("Failed UpdateContainer: %v\n", err)
	}
	if !resp.Success {
		log.Printf("Failed UpdateContainer from remote: %v\n", err)
	}
}
