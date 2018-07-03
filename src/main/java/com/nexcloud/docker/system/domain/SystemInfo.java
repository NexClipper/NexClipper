package com.nexcloud.docker.system.domain;

public class SystemInfo {

	private String ID;
	private Integer Containers;
	private Integer ContainersRunning;
	private Integer ContainersPaused;
	private Integer ContainersStopped;
	private Integer Images;
	private String Driver;
	private String SystemTime;
	private String KernelVersion;
	private String OperatingSystem;
	private String OSType;
	private String Architecture;
	private Integer NCPU;
	private Long MemTotal;
	private String DockerRootDir;
	private String Name;
	private String ServerVersion;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

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

	public Integer getImages() {
		return Images;
	}

	public void setImages(Integer images) {
		Images = images;
	}

	public String getDriver() {
		return Driver;
	}

	public void setDriver(String driver) {
		Driver = driver;
	}

	public String getSystemTime() {
		return SystemTime;
	}

	public void setSystemTime(String systemTime) {
		SystemTime = systemTime;
	}

	public String getKernelVersion() {
		return KernelVersion;
	}

	public void setKernelVersion(String kernelVersion) {
		KernelVersion = kernelVersion;
	}

	public String getOperatingSystem() {
		return OperatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		OperatingSystem = operatingSystem;
	}

	public String getOSType() {
		return OSType;
	}

	public void setOSType(String oSType) {
		OSType = oSType;
	}

	public String getArchitecture() {
		return Architecture;
	}

	public void setArchitecture(String architecture) {
		Architecture = architecture;
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

	public String getDockerRootDir() {
		return DockerRootDir;
	}

	public void setDockerRootDir(String dockerRootDir) {
		DockerRootDir = dockerRootDir;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getServerVersion() {
		return ServerVersion;
	}

	public void setServerVersion(String serverVersion) {
		ServerVersion = serverVersion;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("***** SystemInfo *****\n");
		sb.append("ID                 = " + getID() + "\n");
		sb.append("Containers         = " + getContainers() + "\n");
		sb.append("ContainersRunning  = " + getContainersRunning() + "\n");
		sb.append("ContainersPaused   = " + getContainersPaused() + "\n");
		sb.append("ContainersStopped  = " + getContainersStopped() + "\n");
		sb.append("Images             = " + getImages() + "\n");
		sb.append("Driver             = " + getDriver() + "\n");
		sb.append("SystemTime         = " + getSystemTime() + "\n");
		sb.append("KernelVersion      = " + getKernelVersion() + "\n");
		sb.append("OperatingSystem    = " + getOperatingSystem() + "\n");
		sb.append("OSType             = " + getOSType() + "\n");
		sb.append("Architecture       = " + getArchitecture() + "\n");
		sb.append("NCPU               = " + getNCPU() + "\n");
		sb.append("MemTotal           = " + getMemTotal() + "\n");
		sb.append("DockerRootDir      = " + getDockerRootDir() + "\n");
		sb.append("Name               = " + getName() + "\n");
		sb.append("ServerVersion      = " + getServerVersion() + "\n");
		return sb.toString();
	}
}
