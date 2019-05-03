package com.nexcloud.workflow.k8s.domain;

public class Source {
	private String component;
	
	private String host;

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
}
