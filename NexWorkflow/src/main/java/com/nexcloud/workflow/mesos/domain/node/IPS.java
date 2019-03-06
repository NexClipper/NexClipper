package com.nexcloud.workflow.mesos.domain.node;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IPS {
	List<String> ips;

	public List<String> getIps() {
		if( ips == null )
			ips	= new ArrayList<String>();
		
		return ips;
	}

	public void setIps(List<String> ips) {
		this.ips = ips;
	}
}
