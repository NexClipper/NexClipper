package com.nexcloud.redis.domain;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.nexcloud.db.domain.Agent;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AgentMap {
	private Map<String, Agent> map;

	public Map<String, Agent> getMap() {
		if( map == null )
			map			= new HashMap<String, Agent>();
		return map;
	}

	public void setMap(Map<String, Agent> map) {
		this.map = map;
	}
}
