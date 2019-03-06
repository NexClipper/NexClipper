package com.nexcloud.fullfillment.k8s.domain;

public class Strategy {
	private String type;
	
	private Strategy rollingUpdate;
	
	private String maxUnavailable;
	
	private String maxSurge;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Strategy getRollingUpdate() {
		if( rollingUpdate == null )
			rollingUpdate = new Strategy();
		return rollingUpdate;
	}

	public void setRollingUpdate(Strategy rollingUpdate) {
		this.rollingUpdate = rollingUpdate;
	}

	public String getMaxUnavailable() {
		return maxUnavailable;
	}

	public void setMaxUnavailable(String maxUnavailable) {
		this.maxUnavailable = maxUnavailable;
	}

	public String getMaxSurge() {
		return maxSurge;
	}

	public void setMaxSurge(String maxSurge) {
		this.maxSurge = maxSurge;
	}
}
