package com.nexcloud.workflow.docker.domain.stat;

public class MemoryStats {
	private Long usage;
	private Long max_usage;
	private Long limit;
	private Float mem_usage;

	public Long getUsage() {
		return usage;
	}

	public void setUsage(Long usage) {
		this.usage = usage;
	}

	public Long getMax_usage() {
		return max_usage;
	}

	public void setMax_usage(Long max_usage) {
		this.max_usage = max_usage;
	}

	public Long getLimit() {
		return limit;
	}

	public void setLimit(Long limit) {
		this.limit = limit;
	}
	
	public Float getMem_usage() {
		return (float)this.usage/this.limit*100;
	}
}
