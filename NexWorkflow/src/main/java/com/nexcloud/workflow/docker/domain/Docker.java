package com.nexcloud.workflow.docker.domain;

import java.util.ArrayList;
import java.util.List;

import com.nexcloud.workflow.docker.domain.system.SystemInfo;

public class Docker {
	private SystemInfo docker_info;
	private List<Containers> containers;
	
	public SystemInfo getDocker_info() {
		return docker_info;
	}

	public void setDocker_info(SystemInfo docker_info) {
		this.docker_info = docker_info;
	}
	
	public Docker() {
		this.containers = new ArrayList<Containers>();
	}
	
	public List<Containers> getContainers() {
		if( containers == null )
			containers = new ArrayList<Containers>();
		return containers;
	}

	public void setContainers(List<Containers> containers) {
		this.containers = containers;
	}
}