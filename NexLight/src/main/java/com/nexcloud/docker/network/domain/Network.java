package com.nexcloud.docker.network.domain;

public class Network {

	private String Name;
	private String Id;
	private String Created;
	private String Scope;
	private String Driver;
	private Boolean EnableIPv6;
	private IPAM IPAM;
	private Options Options;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getCreated() {
		return Created;
	}

	public void setCreated(String created) {
		Created = created;
	}

	public String getScope() {
		return Scope;
	}

	public void setScope(String scope) {
		Scope = scope;
	}

	public String getDriver() {
		return Driver;
	}

	public void setDriver(String driver) {
		Driver = driver;
	}

	public Boolean getEnableIPv6() {
		return EnableIPv6;
	}

	public void setEnableIPv6(Boolean enableIPv6) {
		EnableIPv6 = enableIPv6;
	}

	public IPAM getIPAM() {
		return IPAM;
	}

	public void setIPAM(IPAM iPAM) {
		IPAM = iPAM;
	}

	public Options getOptions() {
		return Options;
	}

	public void setOptions(Options options) {
		Options = options;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("***** Network *****\n");
		sb.append("Name       = " + getName() + "\n");
		sb.append("Id         = " + getId() + "\n");
		sb.append("Created    = " + getCreated() + "\n");
		sb.append("Scope      = " + getScope() + "\n");
		sb.append("Driver     = " + getDriver() + "\n");
		sb.append("EnableIPv6 = " + getEnableIPv6() + "\n");
		sb.append("IPAM       = " + getIPAM() + "\n");
		sb.append("Options    = " + getOptions() + "\n");
		return sb.toString();
	}
}
