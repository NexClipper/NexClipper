package com.nexcloud.tsdb.adapter;
public interface K8sContainerAdapter {
	public String getCpuUsed(String startTime, String time, int limit);
	public String getCpuUsed(String container, String startTime, String time, int limit);
	
	public String getCpuUsedPercent(String startTime, String time, int limit);
	public String getCpuUsedPercent(String container, String startTime, String time, int limit);
	
	public String getCpuLimit(String startTime, String time, int limit);
	public String getCpuLimit(String container, String startTime, String time, int limit);
	
	public String getCpuRequest(String startTime, String time, int limit);
	public String getCpuRequest(String container, String startTime, String time, int limit);
	
	
	public String getMemoryUsed(String startTime, String time, int limit);
	public String getMemoryUsed(String container, String startTime, String time, int limit);
	
	public String getMemoryUsedPercent(String startTime, String time, int limit);
	public String getMemoryUsedPercent(String container, String startTime, String time, int limit);
	
	public String getMemoryLimit(String startTime, String time, int limit);
	public String getMemoryLimit(String container, String startTime, String time, int limit);
	
	public String getMemoryRequest(String startTime, String time, int limit);
	public String getMemoryRequest(String container, String startTime, String time, int limit);
}
