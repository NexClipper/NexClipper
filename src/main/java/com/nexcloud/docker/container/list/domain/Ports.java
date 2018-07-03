package com.nexcloud.docker.container.list.domain;

public class Ports {
	private String IP;
	private Integer PrivatePort;
	private Integer PublicPort;
	private String Type;

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public Integer getPrivatePort() {
		return PrivatePort;
	}

	public void setPrivatePort(Integer privatePort) {
		PrivatePort = privatePort;
	}

	public Integer getPublicPort() {
		return PublicPort;
	}

	public void setPublicPort(Integer publicPort) {
		PublicPort = publicPort;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	@Override 
	public String toString() { 
		StringBuilder sb = new StringBuilder();
		sb.append("***** Ports *****\n");
		sb.append("IP          = "+getIP()+"\n");
		sb.append("PrivatePort = "+getPrivatePort()+"\n");
		sb.append("PublicPort  = "+getPublicPort()+"\n");
		sb.append("Type        = "+getType()+"\n");
		return sb.toString();
	}
}