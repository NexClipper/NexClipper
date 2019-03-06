package com.nexcloud.db.domain;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AgentMap {
	Map<String, Agent> map;

	public Map<String, Agent> getMap() {
		if( map == null )
			map = new HashMap<String, Agent>();
		
		return map;
	}

	public void setMap(Map<String, Agent> map) {
		this.map = map;
	}
}
