package com.nexcloud.fullfillment.docker.domain.container;

public class ContainerInfo {
	
	private Config Config;
	private HostConfig HostConfig;
	private boolean PublishAllPorts;
	private NetworkSettings NetworkSettings;
	
	public Config getConfig() {
		return Config;
	}
	
	public void setConfig(Config config) {
		Config = config;
	}
	
	public HostConfig getHostConfig() {
		return HostConfig;
	}
	
	public void setHostConfig(HostConfig hostConfig) {
		HostConfig = hostConfig;
	}
	
	public boolean getPublishAllPorts() {
		return PublishAllPorts;
	}
	
	public void setPublishAllPorts(boolean publishAllPorts) {
		PublishAllPorts = publishAllPorts;
	}
	
	public NetworkSettings getNetworkSettings() {
		return NetworkSettings;
	}
	
	public void setNetworkSettings(NetworkSettings networkSettings) {
		NetworkSettings = networkSettings;
	}
}
