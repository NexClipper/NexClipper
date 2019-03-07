package com.nexcloud.docker.network.domain;

import com.google.gson.annotations.SerializedName;

public class Options {

	@SerializedName("com.docker.network.bridge.default_bridge")
	private Boolean bridge_default_bridge;

	@SerializedName("com.docker.network.bridge.enable_icc")
	private Boolean bridge_enable_icc;

	@SerializedName("com.docker.network.bridge.enable_ip_masquerade")
	private Boolean bridge_enable_ip_masquerade;

	@SerializedName("com.docker.network.bridge.host_binding_ipv4")
	private String bridge_host_binding_ipv4;

	@SerializedName("com.docker.network.bridge.name")
	private String bridge_name;

	@SerializedName("com.docker.network.driver.mtu")
	private String driver_mtu;

	public Boolean getBridge_default_bridge() {
		return bridge_default_bridge;
	}

	public void setBridge_default_bridge(Boolean bridge_default_bridge) {
		this.bridge_default_bridge = bridge_default_bridge;
	}

	public Boolean getBridge_enable_icc() {
		return bridge_enable_icc;
	}

	public void setBridge_enable_icc(Boolean bridge_enable_icc) {
		this.bridge_enable_icc = bridge_enable_icc;
	}

	public Boolean getBridge_enable_ip_masquerade() {
		return bridge_enable_ip_masquerade;
	}

	public void setBridge_enable_ip_masquerade(Boolean bridge_enable_ip_masquerade) {
		this.bridge_enable_ip_masquerade = bridge_enable_ip_masquerade;
	}

	public String getBridge_host_binding_ipv4() {
		return bridge_host_binding_ipv4;
	}

	public void setBridge_host_binding_ipv4(String bridge_host_binding_ipv4) {
		this.bridge_host_binding_ipv4 = bridge_host_binding_ipv4;
	}

	public String getBridge_name() {
		return bridge_name;
	}

	public void setBridge_name(String bridge_name) {
		this.bridge_name = bridge_name;
	}

	public String getDriver_mtu() {
		return driver_mtu;
	}

	public void setDriver_mtu(String driver_mtu) {
		this.driver_mtu = driver_mtu;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("***** Options *****\n");
		sb.append("bridge_default_bridge       = " + getBridge_default_bridge() + "\n");
		sb.append("bridge_enable_icc           = " + getBridge_enable_icc() + "\n");
		sb.append("bridge_enable_ip_masquerade = " + getBridge_enable_ip_masquerade() + "\n");
		sb.append("bridge_host_binding_ipv4    = " + getBridge_host_binding_ipv4() + "\n");
		sb.append("bridge_name                 = " + getBridge_name() + "\n");
		sb.append("driver_mtu                  = " + getDriver_mtu() + "\n");
		return sb.toString();
	}
}