package com.nexcloud.workflow.docker.domain.container;

import java.util.List;

public class HostConfig {

	private String NetworkMode;
	private List<String> Binds;

	public String getNetworkMode() {
		return NetworkMode;
	}
	
	public void setNetworkMode(String networkMode) {
		NetworkMode = networkMode;
	}
	
	public List<String> getBinds() {
		return Binds;
	}
	
	public void setBinds(List<String> binds) {
		Binds = binds;
	}
}