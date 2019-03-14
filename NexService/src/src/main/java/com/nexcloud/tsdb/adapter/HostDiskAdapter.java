package com.nexcloud.tsdb.adapter;

public interface HostDiskAdapter {
	public String getDiskFreeByte (String hostIp, String mountName, String startTime, String time, int limit);
	
	public String getDiskUsagePercent (String hostIp, String mountName, String startTime, String time, int limit);
	
	public String getDiskReadbyte (String hostIp, String mountName, String startTime, String time, int limit);
	
	public String getDiskWritebyte (String hostIp, String mountName, String startTime, String time, int limit);
	
	public String getDiskTotal (String hostIp, String mountName, String startTime, String time, int limit);
	
	public String getDiskUsedbyte (String hostIp, String mountName, String startTime, String time, int limit);
	
	public String getDiskReads (String hostIp, String mountName, String startTime, String time, int limit);
	
	public String getDiskWrites (String hostIp, String mountName, String startTime, String time, int limit);
}
