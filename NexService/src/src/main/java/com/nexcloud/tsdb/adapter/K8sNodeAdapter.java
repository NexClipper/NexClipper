package com.nexcloud.tsdb.adapter;
public interface K8sNodeAdapter {
	public String getCpuUsed(String startTime, String time, int limit);
	public String getCpuUsed(String node, String startTime, String time, int limit);
	
	public String getCpuUsedPercent(String startTime, String time, int limit);
	public String getCpuUsedPercent(String node, String startTime, String time, int limit);
	
	public String getCpuAllocate(String startTime, String time, int limit);
	public String getCpuAllocate(String node, String startTime, String time, int limit);
	
	public String getCpuTotal(String startTime, String time, int limit);
	public String getCpuTotal(String node, String startTime, String time, int limit);

	
	public String getMemoryUsed(String startTime, String time, int limit);
	public String getMemoryUsed(String node, String startTime, String time, int limit);
	
	public String getMemoryUsedPercent(String startTime, String time, int limit);
	public String getMemoryUsedPercent(String node, String startTime, String time, int limit);
	
	public String getMemoryAllocate(String startTime, String time, int limit);
	public String getMemoryAllocate(String node, String startTime, String time, int limit);
	
	public String getMemoryTotal(String startTime, String time, int limit);
	public String getMemoryTotal(String node, String startTime, String time, int limit);
	

	public String getPodAllocate(String startTime, String time, int limit);
	public String getPodAllocate(String node, String startTime, String time, int limit);
	
	public String getPodTotal(String startTime, String time, int limit);
	public String getPodTotal(String node, String startTime, String time, int limit);
}
