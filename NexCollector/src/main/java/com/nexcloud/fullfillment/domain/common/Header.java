package com.nexcloud.fullfillment.domain.common;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Header {
	private String agent_key;
	
	private String metric_type;
	
	private Integer group_id;
	
	private String node_ip;
	
	private String node_name;
	
	private String version;

	public String getAgent_key() {
		return agent_key;
	}

	public void setAgent_key(String agent_key) {
		this.agent_key = agent_key;
	}

	public String getMetric_type() {
		return metric_type;
	}

	public void setMetric_type(String metric_type) {
		this.metric_type = metric_type;
	}

	public Integer getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	public String getNode_ip() {
		return node_ip;
	}

	public void setNode_ip(String node_ip) {
		this.node_ip = node_ip;
	}

	public String getNode_name() {
		return node_name;
	}

	public void setNode_name(String node_name) {
		this.node_name = node_name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}

