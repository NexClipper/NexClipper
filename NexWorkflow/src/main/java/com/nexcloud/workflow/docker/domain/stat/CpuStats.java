package com.nexcloud.workflow.docker.domain.stat;

public class CpuStats {
	private CpuUsage cpu_usage;
	private Long system_cpu_usage;
	
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

}
