package com.nexcloud.workflow.docker.domain.container;

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