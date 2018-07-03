package com.nexcloud.docker.container.list.domain;

import java.util.Map;

public class NetworkSettings {
	private Map<String, Networks> Networks;

	public Map<String, Networks> getNetworks() {
		return Networks;
	}

	public void setNetworks(Map<String, Networks> networks) {
		Networks = networks;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("***** NetworkSetting *****\n");
		sb.append("Networks = " + getNetworks() + "\n");
		return sb.toString();
	}
}