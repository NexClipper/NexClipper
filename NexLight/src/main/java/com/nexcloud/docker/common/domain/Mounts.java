package com.nexcloud.docker.common.domain;

public class Mounts {
	private String Type;
	private String Name;
	private String Source;
	private String Destination;
	private String Driver;
	private String Mode;
	private Boolean RW;
	private String Propagation;

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSource() {
		return Source;
	}

	public void setSource(String source) {
		Source = source;
	}

	public String getDestination() {
		return Destination;
	}

	public void setDestination(String destination) {
		Destination = destination;
	}

	public String getDriver() {
		return Driver;
	}

	public void setDriver(String driver) {
		Driver = driver;
	}

	public String getMode() {
		return Mode;
	}

	public void setMode(String mode) {
		Mode = mode;
	}

	public Boolean getRW() {
		return RW;
	}

	public void setRW(Boolean rw) {
		RW = rw;
	}

	public String getPropagation() {
		return Propagation;
	}

	public void setPropagation(String propagation) {
		Propagation = propagation;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("***** Mounts *****\n");
		sb.append("Type        = " + getType() + "\n");
		sb.append("Name        = " + getName() + "\n");
		sb.append("Source      = " + getSource() + "\n");
		sb.append("Destination = " + getDestination() + "\n");
		sb.append("Driver      = " + getDriver() + "\n");
		sb.append("Mode        = " + getMode() + "\n");
		sb.append("Rw          = " + getRW() + "\n");
		sb.append("Propagation = " + getPropagation() + "\n");
		
		return sb.toString();
	}
}