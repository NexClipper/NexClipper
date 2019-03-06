package com.nexcloud.fullfillment.k8s.domain;

public class Resource {
	private String pods;
	
	private String cpu;
	
	private String memory;
	
	private Resource limits;
	
	private Resource requests;
	
	private Resource used;
	
	private Resource used_percent;

	public String getPods() {
		return pods;
	}

	public void setPods(String pods) {
		this.pods = pods;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public Resource getLimits() {
		if( limits == null )
			limits = new Resource();
		return limits;
	}

	public void setLimits(Resource limits) {
		this.limits = limits;
	}

	public Resource getRequests() {
		if( requests == null )
			requests = new Resource();
		return requests;
	}

	public void setRequests(Resource requests) {
		this.requests = requests;
	}

	public Resource getUsed() {
		if( used == null )
			used = new Resource();
		return used;
	}

	public void setUsed(Resource used) {
		this.used = used;
	}

	public Resource getUsed_percent() {
		if( used_percent == null )
			used_percent = new Resource();
		
		return used_percent;
	}

	public void setUsed_percent(Resource used_percent) {
		this.used_percent = used_percent;
	}
}
