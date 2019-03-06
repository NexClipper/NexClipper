package com.nexcloud.fullfillment.k8s.domain;

public class Address {
	private String ip;
	
	private String nodeName;
	
	private String hostname;
	
	private Metadata targetRef;
	
	private String type;
	
	private String address;
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public Metadata getTargetRef() {
		if( targetRef == null )
			targetRef = new Metadata();
		return targetRef;
	}

	public void setTargetRef(Metadata targetRef) {
		this.targetRef = targetRef;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
}
