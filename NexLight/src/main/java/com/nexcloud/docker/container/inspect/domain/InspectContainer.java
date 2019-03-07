package com.nexcloud.docker.container.inspect.domain;

import java.util.List;

import com.nexcloud.docker.common.domain.Config;
import com.nexcloud.docker.common.domain.HostConfig;
import com.nexcloud.docker.common.domain.Mounts;

public class InspectContainer {
	private String Id;	
	private String Created;
	private String Path;
	private List<String> Args;
	private State State;
	private String Image;
	private String Name;
	private HostConfig HostConfig;
	private List<Mounts> Mounts;
	private Config Config;
	
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

	public String getPath() {
		return Path;
	}

	public void setPath(String path) {
		Path = path;
	}

	public List<String> getArgs() {
		return Args;
	}

	public void setArgs(List<String> args) {
		Args = args;
	}

	public State getState() {
		return State;
	}

	public void setState(State state) {
		State = state;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public HostConfig getHostConfig() {
		return HostConfig;
	}

	public void setHostConfig(HostConfig hostconfig) {
		this.HostConfig = hostconfig;
	}

	public List<Mounts> getMounts() {
		return Mounts;
	}

	public void setMounts(List<Mounts> mounts) {
		Mounts = mounts;
	}

	public Config getConfig() {
		return Config;
	}

	public void setConfig(Config config) {
		Config = config;
	}

	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder();
		sb.append("***** Inspect Contaier *****\n");
		sb.append("Id         = "+getId()+"\n");
		sb.append("Created    = "+getCreated()+"\n");
		sb.append("Path       = "+getPath()+"\n");
		sb.append("Args       = "+getArgs()+"\n");
		sb.append("State      = "+getState()+"\n");
		sb.append("Image      = "+getImage()+"\n");
		sb.append("Name       = "+getName()+"\n");
		sb.append("HostConfig = "+getHostConfig()+"\n");
		sb.append("Mounts     = "+getMounts()+"\n");
		sb.append("Config     = "+getConfig()+"\n");
		return sb.toString();
	}
}