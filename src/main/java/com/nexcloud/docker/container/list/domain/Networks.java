package com.nexcloud.docker.container.list.domain;

public class Networks {
	//private String IPAMConfig;
	private String Links;
	private String Alias;
	private String NetworkID;
	private String EndpointID;
	private String Gateway;
	private String IPAddress;
	private int IPPrefixLen;
	private String IPv6Gateway;
	private String GlobalIPv6Address;
	private int GlobalIPv6PrefixLen;
	private String MacAddress;

	/*
	public String getIPAMConfig() {
		return IPAMConfig;
	}

	public void setIPAMConfig(String iPAMConfig) {
		IPAMConfig = iPAMConfig;
	}
	*/

	public String getLinks() {
		return Links;
	}

	public void setLinks(String links) {
		Links = links;
	}

	public String getAlias() {
		return Alias;
	}

	public void setAlias(String alias) {
		Alias = alias;
	}

	public String getNetworkID() {
		return NetworkID;
	}

	public void setNetworkID(String networkID) {
		NetworkID = networkID;
	}

	public String getEndpointID() {
		return EndpointID;
	}

	public void setEndpointID(String endpointID) {
		EndpointID = endpointID;
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

	public int getIPPrefixLen() {
		return IPPrefixLen;
	}

	public void setIPPrefixLen(int iPPrefixLen) {
		IPPrefixLen = iPPrefixLen;
	}

	public String getIPv6Gateway() {
		return IPv6Gateway;
	}

	public void setIPv6Gateway(String iPv6Gateway) {
		IPv6Gateway = iPv6Gateway;
	}

	public String getGlobalIPv6Address() {
		return GlobalIPv6Address;
	}

	public void setGlobalIPv6Address(String globalIPv6Address) {
		GlobalIPv6Address = globalIPv6Address;
	}

	public int getGlobalIPv6PrefixLen() {
		return GlobalIPv6PrefixLen;
	}

	public void setGlobalIPv6PrefixLen(int globalIPv6PrefixLen) {
		GlobalIPv6PrefixLen = globalIPv6PrefixLen;
	}

	public String getMacAddress() {
		return MacAddress;
	}

	public void setMacAddress(String macAddress) {
		MacAddress = macAddress;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("***** Networks *****\n");
		sb.append("NetworkID           = " + getNetworkID() + "\n");
		sb.append("EndpointID          = " + getEndpointID() + "\n");
		sb.append("Gateway             = " + getGateway() + "\n");
		sb.append("IPAddress           = " + getIPAddress() + "\n");
		sb.append("IPPrefixLen         = " + getIPPrefixLen() + "\n");
		sb.append("IPv6Gateway         = " + getIPv6Gateway() + "\n");
		sb.append("GlobalIPv6Address   = " + getGlobalIPv6Address() + "\n");
		sb.append("GlobalIPv6PrefixLen = " + getGlobalIPv6PrefixLen() + "\n");
		sb.append("MacAddress          = " + getMacAddress() + "\n");
		return sb.toString();
	}
}