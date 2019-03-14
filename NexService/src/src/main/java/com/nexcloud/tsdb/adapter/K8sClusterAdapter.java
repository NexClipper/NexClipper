package com.nexcloud.tsdb.adapter;

public interface K8sClusterAdapter {
	public String getCpuTotal(String startTime, String time, int limit);
	public String getCpuUsed(String startTime, String time, int limit);
	public String getCpuUsedPercent(String startTime, String time, int limit);
	
	public String getMemoryTotal(String startTime, String time, int limit);
	public String getMemoryUsed(String startTime, String time, int limit);
	public String getMemoryUsedPercent(String startTime, String time, int limit);
	
	public String getPodTotal(String startTime, String time, int limit);
	public String getPodUsed(String startTime, String time, int limit);
	public String getPodUsedPercent(String startTime, String time, int limit);
}
