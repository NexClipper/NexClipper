package com.nexcloud.workflow.host.domain;

import java.util.HashMap;
import java.util.Map;

public class HostMap {
	private Map<Integer, Map<String, Host>> hostMap;

	public Map<Integer, Map<String, Host>> getHostMap() {
		if( hostMap == null )
			hostMap = new HashMap<Integer, Map<String, Host>>();
		return hostMap;
	}

	public void setHostMap(Map<Integer, Map<String, Host>> hostMap) {
		this.hostMap = hostMap;
	}
}

