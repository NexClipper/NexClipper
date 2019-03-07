package com.nexcloud.docker.container.stats.domain;

public class CpuStats {
	public CpuUsage cpu_usage;
	public Long system_cpu_usage;
	
	public CpuUsage getCpu_usage() {
		return cpu_usage;
	}
	public void setCpu_usage(CpuUsage cpu_usage) {
		this.cpu_usage = cpu_usage;
	}
	public Long getSystem_cpu_usage() {
		return system_cpu_usage;
	}
	public void setSystem_cpu_usage(Long system_cpu_usage) {
		this.system_cpu_usage = system_cpu_usage;
	}
	
	@Override 
	public String toString() { 
		StringBuilder sb = new StringBuilder();
		sb.append("***** cpu_stats *****\n");
		sb.append("cpu_usage        = "+getCpu_usage()+"\n");
		sb.append("system_cpu_usage = "+getSystem_cpu_usage()+"\n");
		
		return sb.toString();
	}
}