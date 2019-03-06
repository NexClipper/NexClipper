package com.nexcloud.workflow.docker.domain.container;

public class Ports {
	private String HostIp;
	private String HostPort;

	public String getHostIp() {
		return HostIp;
	}

	public void setHostIp(String hostIp) {
		HostIp = hostIp;
	}

	public String getHostPort() {
		return HostPort;
	}

	public void setHostPort(String hostPort) {
		HostPort = hostPort;
	}
}
