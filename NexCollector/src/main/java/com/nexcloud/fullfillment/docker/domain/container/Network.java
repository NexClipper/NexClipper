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
package com.nexcloud.fullfillment.docker.domain.container;
import java.util.HashMap;
import java.util.Map;

public class Network {
	private boolean PublishAllPorts;
	private Map<String, Ports[]> Ports;
	private String NetworkMode;
	private String Gateway;
	private String IPAddress;
	
	public boolean isPublishAllPorts() {
		return PublishAllPorts;
	}
	
	public void setPublishAllPorts(boolean publishAllPorts) {
		PublishAllPorts = publishAllPorts;
	}
	
	public Map<String, Ports[]> getPorts() {
		if( Ports == null )
			Ports = new HashMap<String, Ports[]>();
		return Ports;
	}
	
	public void setPorts(Map<String, Ports[]> ports) {
		Ports = ports;
	}
	
	public String getNetworkMode() {
		return NetworkMode;
	}
	
	public void setNetworkMode(String networkMode) {
		NetworkMode = networkMode;
	}
	
	public String getGateway() {
		return Gateway;
	}
	
	public void setGateway(String gateway) {
		Gateway = gateway;
	}
	
	public String getIPAddress() {
		return IPAddress;
	}
	
	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}
}