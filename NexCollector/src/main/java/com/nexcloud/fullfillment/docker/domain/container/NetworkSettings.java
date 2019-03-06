package com.nexcloud.fullfillment.docker.domain.container;

import java.util.Map;

public class NetworkSettings {
	private Map<String, Ports[]> Ports;
	private String IPAddress;
	private Integer IPPrefixLen;
	private String Gateway;
	
	public Map<String, Ports[]> getPorts() {
		return Ports;
	}
	
	public void setPorts(Map<String, Ports[]> ports) {
		Ports = ports;
	}
	
	public String getIPAddress() {
		return IPAddress;
	}
	
	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}
	
	public Integer getIPPrefixLen() {
		return IPPrefixLen;
	}
	
	public void setIPPrefixLen(Integer iPPrefixLen) {
		IPPrefixLen = iPPrefixLen;
	}

	public String getGateway() {
		return Gateway;
	}

	public void setGateway(String gateway) {
		Gateway = gateway;
	}
}
