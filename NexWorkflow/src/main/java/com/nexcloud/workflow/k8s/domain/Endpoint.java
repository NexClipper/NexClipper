package com.nexcloud.workflow.k8s.domain;

import java.util.HashMap;
import java.util.Map;

public class Endpoint {
	private Map<String, String> map;
	
	public Map<String, String> getMap() {
		if( map == null )
			map	= new HashMap<String, String>();
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
	
	private Endpoint kubeletEndpoint;
	
	private Long Port;

	public Endpoint getKubeletEndpoint() {
		if( kubeletEndpoint == null )
			kubeletEndpoint = new Endpoint();
		return kubeletEndpoint;
	}

	public void setKubeletEndpoint(Endpoint kubeletEndpoint) {
		this.kubeletEndpoint = kubeletEndpoint;
	}

	public Long getPort() {
		return Port;
	}

	public void setPort(Long port) {
		Port = port;
	}
}
