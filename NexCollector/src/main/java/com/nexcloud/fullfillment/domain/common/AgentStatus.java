package com.nexcloud.fullfillment.domain.common;

public class AgentStatus {
	private String host_ip;
	
	private String host_name;
	
	private String status;
	
	private String start_time;
	
	private String version;
	
	private Long timestamp;
	
	private Long last_update;
	
	public String getHost_ip() {
		return host_ip;
	}

	public void setHost_ip(String host_ip) {
		this.host_ip = host_ip;
	}

	public String getHost_name() {
		return host_name;
	}

	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public Long getLast_update() {
		return last_update;
	}

	public void setLast_update(Long last_update) {
		this.last_update = last_update;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
