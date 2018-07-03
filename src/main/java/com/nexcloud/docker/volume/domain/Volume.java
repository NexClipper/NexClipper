package com.nexcloud.docker.volume.domain;

public class Volume {
	private String Driver;
	private Object Labels;
	private String Mountpoint;
	private String Name;
	private Object Options;
	private String Scope;

	public String getDriver() {
		return Driver;
	}

	public void setDriver(String driver) {
		Driver = driver;
	}

	public Object getLabels() {
		return Labels;
	}

	public void setLabels(Object labels) {
		Labels = labels;
	}

	public String getMountpoint() {
		return Mountpoint;
	}

	public void setMountpoint(String mountpoint) {
		Mountpoint = mountpoint;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Object getOptions() {
		return Options;
	}

	public void setOptions(Object options) {
		Options = options;
	}

	public String getScope() {
		return Scope;
	}

	public void setScope(String scope) {
		Scope = scope;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("***** Options *****\n");
		sb.append("Driver     = " + getDriver() + "\n");
		sb.append("Labels     = " + getLabels() + "\n");
		sb.append("Mountpoint = " + getMountpoint() + "\n");
		sb.append("Name       = " + getName() + "\n");
		sb.append("Options    = " + getOptions() + "\n");
		sb.append("Scope      = " + getScope() + "\n");
		return sb.toString();
	}
}
