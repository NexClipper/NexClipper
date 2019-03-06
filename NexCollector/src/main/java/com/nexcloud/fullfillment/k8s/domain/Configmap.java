package com.nexcloud.fullfillment.k8s.domain;

import java.util.HashMap;
import java.util.Map;

public class Configmap {
	private Map<String, String> map;

	public Map<String, String> getMap() {
		if( map == null )
			map	= new HashMap<String, String>();
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
	private String name;
	
	private Integer defaultMode;
	
	private Boolean optional;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDefaultMode() {
		return defaultMode;
	}

	public void setDefaultMode(Integer defaultMode) {
		this.defaultMode = defaultMode;
	}

	public Boolean getOptional() {
		return optional;
	}

	public void setOptional(Boolean optional) {
		this.optional = optional;
	}
}
