package com.nexcloud.docker.network.domain;

import java.util.ArrayList;

public class IPAM {
	private String Driver;
	private Object Options;
	private ArrayList<Config> Config;

	public String getDriver() {
		return Driver;
	}

	public void setDriver(String driver) {
		Driver = driver;
	}

	public Object getOptions() {
		return Options;
	}

	public void setOptions(Object options) {
		Options = options;
	}

	public ArrayList<Config> getConfig() {
		return Config;
	}

	public void setConfig(ArrayList<Config> config) {
		this.Config = config;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("***** IPAM *****\n");
		sb.append("Driver  = " + getDriver() + "\n");
		sb.append("Options = " + getOptions() + "\n");
		sb.append("Config  = " + getConfig() + "\n");
		return sb.toString();
	}
}
