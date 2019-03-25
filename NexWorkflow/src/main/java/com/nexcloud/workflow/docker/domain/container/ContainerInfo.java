package com.nexcloud.workflow.docker.domain.container;
/*
* Copyright 2019 NexCloud Co.,Ltd.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

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
