package com.nexcloud.api.redis.domain;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RedisNodeIP {
	private List<String> ips;

	public List<String> getIps() {
		if( ips == null )
			ips = new ArrayList<String>();
		return ips;
	}

	public void setIps(List<String> ips) {
		this.ips = ips;
	}
}
