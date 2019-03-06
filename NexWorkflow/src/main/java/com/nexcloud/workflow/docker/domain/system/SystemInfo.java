package com.nexcloud.workflow.docker.domain.system;

public class SystemInfo {

	private Integer Containers;
	private Integer ContainersRunning;
	private Integer ContainersPaused;
	private Integer ContainersStopped;
	private String Name;
	private Integer NCPU;
	private Long MemTotal;
	private String OperatingSystem;
	private String KernelVersion;
	private String ServerVersion;
	private String DockerRootDir;
	private Integer Images;

	public Integer getContainers() {
		return Containers;
	}

	public void setContainers(Integer containers) {
		Containers = containers;
	}

	public Integer getContainersRunning() {
		return ContainersRunning;
	}

	public void setContainersRunning(Integer containersRunning) {
		ContainersRunning = containersRunning;
	}

	public Integer getContainersPaused() {
		return ContainersPaused;
	}

	public void setContainersPaused(Integer containersPaused) {
		ContainersPaused = containersPaused;
	}

	public Integer getContainersStopped() {
		return ContainersStopped;
	}

	public void setContainersStopped(Integer containersStopped) {
		ContainersStopped = containersStopped;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Integer getNCPU() {
		return NCPU;
	}

	public void setNCPU(Integer nCPU) {
		NCPU = nCPU;
	}

	public Long getMemTotal() {
		return MemTotal;
	}

	public void setMemTotal(Long memTotal) {
		MemTotal = memTotal;
	}

	public String getOperatingSystem() {
		return OperatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		OperatingSystem = operatingSystem;
	}

	public String getKernelVersion() {
		return KernelVersion;
	}

	public void setKernelVersion(String kernelVersion) {
		KernelVersion = kernelVersion;
	}

	public String getServerVersion() {
		return ServerVersion;
	}

	public void setServerVersion(String serverVersion) {
		ServerVersion = serverVersion;
	}

	public String getDockerRootDir() {
		return DockerRootDir;
	}

	public void setDockerRootDir(String dockerRootDir) {
		DockerRootDir = dockerRootDir;
	}

	public Integer getImages() {
		return Images;
	}

	public void setImages(Integer images) {
		Images = images;
	}

}
