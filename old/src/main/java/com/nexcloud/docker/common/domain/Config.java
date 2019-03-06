package com.nexcloud.docker.common.domain;

import java.util.List;

public class Config {
	private String Hostname;
	private String Domainname;
	private String User;
	private String Image;
	private List<String> Cmd;

	public String getHostname() {
		return Hostname;
	}

	public void setHostname(String hostname) {
		Hostname = hostname;
	}

	public String getDomainname() {
		return Domainname;
	}

	public void setDomainname(String domainname) {
		Domainname = domainname;
	}

	public String getUser() {
		return User;
	}

	public void setUser(String user) {
		User = user;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public List<String> getCmd() {
		return Cmd;
	}

	public void setCmd(List<String> cmd) {
		Cmd = cmd;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("***** Config *****\n");
		sb.append("Hostname   = " + getHostname() + "\n");
		sb.append("Domainname = " + getDomainname() + "\n");
		sb.append("User       = " + getUser() + "\n");
		sb.append("Image      = " + getImage() + "\n");
		sb.append("Cmd        = " + getCmd() + "\n");
		return sb.toString();
	}
}