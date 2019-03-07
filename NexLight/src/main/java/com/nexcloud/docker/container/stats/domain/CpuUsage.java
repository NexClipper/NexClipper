package com.nexcloud.docker.container.stats.domain;

import java.util.List;

public class CpuUsage {
	public Long total_usage;
	public List<Long> percpu_usage;
	public Long usage_in_kernelmode;
	public Long usage_in_usermode;
	
	public Long getTotal_usage() {
		return total_usage;
	}
	public void setTotal_usage(Long total_usage) {
		this.total_usage = total_usage;
	}
	public List<Long> getPercpu_usage() {
		return percpu_usage;
	}
	public void setPercpu_usage(List<Long> percpu_usage) {
		this.percpu_usage = percpu_usage;
	}
	public Long getUsage_in_kernelmode() {
		return usage_in_kernelmode;
	}
	public void setUsage_in_kernelmode(Long usage_in_kernelmode) {
		this.usage_in_kernelmode = usage_in_kernelmode;
	}
	public Long getUsage_in_usermode() {
		return usage_in_usermode;
	}
	public void setUsage_in_usermode(Long usage_in_usermode) {
		this.usage_in_usermode = usage_in_usermode;
	}
	
	@Override 
	public String toString() { 
		StringBuilder sb = new StringBuilder();
		sb.append("***** cpu_usage *****\n");
		sb.append("toal_usage          = "+getTotal_usage()+"\n");
		sb.append("percpu_usage        = "+getPercpu_usage()+"\n");
		sb.append("usage_in_kernelmode = "+getUsage_in_kernelmode()+"\n");
		sb.append("usage_in_usermode   = "+getUsage_in_usermode()+"\n");
		return sb.toString();
	}
}
