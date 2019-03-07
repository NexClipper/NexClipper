package com.nexcloud.docker.image.inspect.domain;

import java.util.ArrayList;

public class ContainerConfig {
	private String Hostname;
	private String Domainname;
	private String User;
	private ArrayList<Object> Env = new ArrayList<Object>();
	private ArrayList<Object> Cmd = new ArrayList<Object>();
	private String Image;

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

	public ArrayList<Object> getEnv() {
		return Env;
	}

	public void setEnv(ArrayList<Object> env) {
		Env = env;
	}

	public ArrayList<Object> getCmd() {
		return Cmd;
	}

	public void setCmd(ArrayList<Object> cmd) {
		Cmd = cmd;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("***** ContainerConfig *****\n");
		sb.append("Hostname   = " + getHostname() + "\n");
		sb.append("Domainname = " + getDomainname() + "\n");
		sb.append("User       = " + getUser() + "\n");
		sb.append("Env        = " + getEnv() + "\n");
		sb.append("Cmd        = " + getCmd() + "\n");
		sb.append("Image      = " + getImage() + "\n");
		return sb.toString();
	}
}