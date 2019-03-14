package com.nexcloud.tsdb.adapter;

public interface HostDockerAdapter {
	public String getDockerCpuUsedByContainerId (String hostIp, String containerId, String startTime, String time, int limit);
	
	public String getDockerCpuUsedByTaskId (String hostIp, String taskId, String startTime, String time, int limit);
	
	public String getDockerMemoryAllocateByContainerId (String hostIp, String containerId, String startTime, String time, int limit);
	
	public String getDockerMemoryUsedByteByContainerId (String hostIp, String containerId, String startTime, String time, int limit);
	
	public String getDockerMemoryUsedByteByTaskId (String hostIp, String taskId, String startTime, String time, int limit);
	
	public String getDockerMemoryUsedPercentByContainerId (String hostIp, String containerId, String startTime, String time, int limit);
	
	public String getDockerMemoryUsedPercentByTaskId (String hostIp, String taskId, String startTime, String time, int limit);
	
	public String getDockerDiskioReadByContainerId (String hostIp, String containerId, String startTime, String time, int limit);
	
	public String getDockerDiskioReadByTaskId (String hostIp, String taskId, String startTime, String time, int limit);
	
	public String getDockerDiskioWriteByContainerId (String hostIp, String containerId, String startTime, String time, int limit);
	
	public String getDockerDiskioWriteByTaskId (String hostIp, String taskId, String startTime, String time, int limit);
	
	public String getDockerNetworkRxbyteByContainerId (String hostIp, String containerId, String startTime, String time, int limit);
	
	public String getDockerNetworkRxdropByContainerId (String hostIp, String containerId, String startTime, String time, int limit);
	
	public String getDockerNetworkRxerrorByContainerId (String hostIp, String containerId, String startTime, String time, int limit);
	
	public String getDockerNetworkRxpacketByContainerId (String hostIp, String containerId, String startTime, String time, int limit);
	
	public String getDockerNetworkTxbyteByContainerId (String hostIp, String containerId, String startTime, String time, int limit);
	
	public String getDockerNetworkTxdropByContainerId (String hostIp, String containerId, String startTime, String time, int limit);
	
	public String getDockerNetworkTxerrorByContainerId (String hostIp, String containerId, String startTime, String time, int limit);
	
	public String getDockerNetworkTxpacketByContainerId (String hostIp, String containerId, String startTime, String time, int limit);
	
	public String getDockerNetworkRxbyteByTaskId (String hostIp, String taskId, String startTime, String time, int limit);
	
	public String getDockerNetworkRxdropByTaskId (String hostIp, String taskId, String startTime, String time, int limit);
	
	public String getDockerNetworkRxerrorByTaskId (String hostIp, String taskId, String startTime, String time, int limit);
	
	public String getDockerNetworkRxpacketByTaskId (String hostIp, String taskId, String startTime, String time, int limit);
	
	public String getDockerNetworkTxbyteByTaskId (String hostIp, String taskId, String startTime, String time, int limit);
	
	public String getDockerNetworkTxdropByTaskId (String hostIp, String taskId, String startTime, String time, int limit);
	
	public String getDockerNetworkTxerrorByTaskId (String hostIp, String taskId, String startTime, String time, int limit);
	
	public String getDockerNetworkTxpacketByTaskId (String hostIp, String taskId, String startTime, String time, int limit);
}
