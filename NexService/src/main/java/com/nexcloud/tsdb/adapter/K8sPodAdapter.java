package com.nexcloud.tsdb.adapter;
public interface K8sPodAdapter {
	public String getCpuUsed(String startTime, String time, int limit);
	public String getCpuUsed(String pod, String startTime, String time, int limit);
	
	public String getCpuUsedPercent(String startTime, String time, int limit);
	public String getCpuUsedPercent(String pod, String startTime, String time, int limit);
	
	public String getCpuLimit(String startTime, String time, int limit);
	public String getCpuLimit(String pod, String startTime, String time, int limit);
	
	public String getCpuRequest(String startTime, String time, int limit);
	public String getCpuRequest(String pod, String startTime, String time, int limit);
	
	
	public String getMemoryUsed(String startTime, String time, int limit);
	public String getMemoryUsed(String pod, String startTime, String time, int limit);
	
	public String getMemoryUsedPercent(String startTime, String time, int limit);
	public String getMemoryUsedPercent(String pod, String startTime, String time, int limit);
	
	public String getMemoryLimit(String startTime, String time, int limit);
	public String getMemoryLimit(String pod, String startTime, String time, int limit);
	
	public String getMemoryRequest(String startTime, String time, int limit);
	public String getMemoryRequest(String pod, String startTime, String time, int limit);
}
