package com.nexcloud.tsdb.adapter;

public interface HostCpuAdapter {
	public String getCpuUsedPercentByHostIp (String hostIp, String startTime, String time, int limit);
	public String getCpuUserPercentByHostIp (String hostIp, String startTime, String time, int limit);
	public String getCpuIdlePercentByHostIp (String hostIp, String startTime, String time, int limit);
	public String getCpuIrqPercentByHostIp (String hostIp, String startTime, String time, int limit);
	public String getCpuNicePercentByHostIp (String hostIp, String startTime, String time, int limit);
	public String getCpuSorfirqPercentByHostIp (String hostIp, String startTime, String time, int limit);
	public String getCpuStolenPercentByHostIp (String hostIp, String startTime, String time, int limit);
	public String getCpuSysPercentByHostIp (String hostIp, String startTime, String time, int limit);
	public String getCpuWaitPercentByHostIp (String hostIp, String startTime, String time, int limit);
	public String getCpuTotalCoreByHostIp (String hostIp, String startTime, String time, int limit);
	public String getCpuLoad1ByHostIp (String hostIp, String startTime, String time, int limit);
	public String getCpuLoad5ByHostIp (String hostIp, String startTime, String time, int limit);
	public String getCpuLoad15ByHostIp (String hostIp, String startTime, String time, int limit);
}
