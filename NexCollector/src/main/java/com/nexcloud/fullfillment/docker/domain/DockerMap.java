package com.nexcloud.fullfillment.docker.domain;

import java.util.HashMap;
import java.util.Map;

public class DockerMap {
	private Map<Integer, Map<String, Docker>> dockerMap;

	public Map<Integer, Map<String, Docker>> getDockerMap() {
		if( dockerMap == null )
			dockerMap = new HashMap<Integer, Map<String, Docker>>();
		return dockerMap;
	}

	public void setDockerMap(Map<Integer, Map<String, Docker>> dockerMap) {
		this.dockerMap = dockerMap;
	}
}

