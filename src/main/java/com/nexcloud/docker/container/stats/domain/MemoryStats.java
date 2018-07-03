package com.nexcloud.docker.container.stats.domain;

public class MemoryStats {
	public Long usage;
	public Long max_usage;
	public Long limit;

	public Float mem_usage;

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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("***** MemoryStats *****\n");
		sb.append("usage     = " + getUsage() + "\n");
		sb.append("max_usage = " + getMax_usage() + "\n");
		sb.append("limit     = " + getLimit() + "\n");
		sb.append("mem_usage = " + getMem_usage() + "\n");
		return sb.toString();
	}
}
