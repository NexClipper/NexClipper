package com.nexcloud.docker.common.domain;

public class PortBindings {
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("***** PortBindings *****\n");
		sb.append("HostIp   = " + getHostIp() + "\n");
		sb.append("HostPort = " + getHostPort() + "\n");
		return sb.toString();
	}
}