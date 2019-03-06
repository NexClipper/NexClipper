package com.nexcloud.workflow.k8s.domain;

public class Port {
private String name;
	
	private Long port;
	
	private String protocol;
	
	private Object containerPort;
	
	private Object targetPort;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPort() {
		return port;
	}

	public void setPort(Long port) {
		this.port = port;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public Object getContainerPort() {
		return containerPort;
	}

	public void setContainerPort(Object containerPort) {
		this.containerPort = containerPort;
	}

	public Object getTargetPort() {
		return targetPort;
	}

	public void setTargetPort(Object targetPort) {
		this.targetPort = targetPort;
	}
}
