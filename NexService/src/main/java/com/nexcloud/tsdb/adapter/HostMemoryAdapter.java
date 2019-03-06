package com.nexcloud.tsdb.adapter;

public interface HostMemoryAdapter {
	public String getActualFreeMemory(String hostIp, String startTime, String time, int limit);
	
	public String getActualUsedMemory(String hostIp, String startTime, String time, int limit);
	
	public String getFreeMemory(String hostIp, String startTime, String time, int limit);
	
	public String getFreePercentMemory(String hostIp, String startTime, String time, int limit);
	
	public String getUsedMemory(String hostIp, String startTime, String time, int limit);
	
	public String getUsedPercentMemory(String hostIp, String startTime, String time, int limit);
	
	public String getTotalMemory(String hostIp, String startTime, String time, int limit);
	
	public String getSwapFreeMemory(String hostIp, String startTime, String time, int limit);
	
	public String getSwapFreePercentMemory(String hostIp, String startTime, String time, int limit);
	
	public String getSwapUsedMemory(String hostIp, String startTime, String time, int limit);
	
	public String getSwapUsedPercentMemory(String hostIp, String startTime, String time, int limit);
	
	public String getSwapTotalMemory(String hostIp, String startTime, String time, int limit);
	
	public String getSwapPagein(String hostIp, String startTime, String time, int limit);
	
	public String getSwapPageout(String hostIp, String startTime, String time, int limit);
}
