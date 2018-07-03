package com.nexcloud.docker.network.domain;

public class Config {
	private String Subnet;
	private String Gateway;
	
	public String getSubnet() {
		return Subnet;
	}
	public void setSubnet(String subnet) {
		Subnet = subnet;
	}
	public String getGateway() {
		return Gateway;
	}
	public void setGateway(String gateway) {
		Gateway = gateway;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("***** Image Inspect *****\n");
		sb.append("Subnet  = " + getSubnet() + "\n");
		sb.append("Gateway = " + getGateway() + "\n");
		return sb.toString();
	}
}
