package com.nexcloud.workflow.docker.domain.stat;

import java.util.List;

public class CpuUsage {
	private Long total_usage;
	private List<Long> percpu_usage;
	private Long usage_in_kernelmode;
	private Long usage_in_usermode;
	
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
}
