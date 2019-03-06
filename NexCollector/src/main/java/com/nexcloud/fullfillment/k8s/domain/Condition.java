package com.nexcloud.fullfillment.k8s.domain;

public class Condition {
	private String type;
	
	// True, False, Unknown
	private String status;
	
	private String lastHeartbeatTime;
	
	private String lastTransitionTime;
	
	private String lastUpdateTime;
	
	private String reason;
	
	private String message;
	
	private String lastProbeTime;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLastHeartbeatTime() {
		return lastHeartbeatTime;
	}

	public void setLastHeartbeatTime(String lastHeartbeatTime) {
		this.lastHeartbeatTime = lastHeartbeatTime;
	}

	public String getLastTransitionTime() {
		return lastTransitionTime;
	}

	public void setLastTransitionTime(String lastTransitionTime) {
		this.lastTransitionTime = lastTransitionTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLastProbeTime() {
		return lastProbeTime;
	}

	public void setLastProbeTime(String lastProbeTime) {
		this.lastProbeTime = lastProbeTime;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}
