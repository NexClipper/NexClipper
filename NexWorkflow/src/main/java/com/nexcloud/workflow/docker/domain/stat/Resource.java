package com.nexcloud.workflow.docker.domain.stat;

import java.util.Map;

public class Resource {
	private Map<String, NetworkStats> networks;

	public Map<String, NetworkStats> getNetworks() {
		return networks;
	}

	public void setNetworks(Map<String, NetworkStats> networks) {
		this.networks = networks;
	}
}