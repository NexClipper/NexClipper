package com.nexcloud.log.domain;

public class Log {
	private Integer agent_id;
	
	private String host_ip;
	
	private String container_id;
	
	private String log;
	
	private String stream;
	
	private String time;

	public Integer getAgent_id() {
		return agent_id;
	}

	public void setAgent_id(Integer agent_id) {
		this.agent_id = agent_id;
	}

	public String getHost_ip() {
		return host_ip;
	}

	public void setHost_ip(String host_ip) {
		this.host_ip = host_ip;
	}

	public String getContainer_id() {
		return container_id;
	}

	public void setContainer_id(String container_id) {
		this.container_id = container_id;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
