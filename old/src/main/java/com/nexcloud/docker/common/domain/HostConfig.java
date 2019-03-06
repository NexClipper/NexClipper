package com.nexcloud.docker.common.domain;

import java.util.Map;

public class HostConfig {
	private String NetworkMode;
	private Map<String, PortBindings[]> PortBindings;

	public String getNetworkMode() {
		return NetworkMode;
	}

	public void setNetworkMode(String networkMode) {
		NetworkMode = networkMode;
	}

	public Map<String, PortBindings[]> getPortBindings() {
		return PortBindings;
	}

	public void setPortBindings(Map<String, PortBindings[]> portBindings) {
		PortBindings = portBindings;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("***** HostConfig *****\n");
		sb.append("NetworkMode  = " + getNetworkMode() + "\n");
		sb.append("PortBindings = " + getPortBindings() + "\n");
		return sb.toString();
	}
}