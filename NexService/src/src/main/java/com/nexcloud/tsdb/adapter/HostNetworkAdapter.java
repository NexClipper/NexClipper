package com.nexcloud.tsdb.adapter;

public interface HostNetworkAdapter {
	public String getNetworkRxbyte(String hostIp, String interfaceId, String startTime, String time, int limit);

	public String getNetworkRxdrop(String hostIp, String interfaceId, String startTime, String time, int limit);
	
	public String getNetworkRxerror(String hostIp, String interfaceId, String startTime, String time, int limit);
	
	public String getNetworkRxoverrun(String hostIp, String interfaceId, String startTime, String time, int limit);
	
	public String getNetworkRxpacket(String hostIp, String interfaceId, String startTime, String time, int limit);
	
	public String getNetworkTxbyte(String hostIp, String interfaceId, String startTime, String time, int limit);
	
	public String getNetworkTxdrop(String hostIp, String interfaceId, String startTime, String time, int limit);
	
	public String getNetworkTxerror(String hostIp, String interfaceId, String startTime, String time, int limit);
	
	public String getNetworkTxoverrun(String hostIp, String interfaceId, String startTime, String time, int limit);
	
	public String getNetworkTxpacket(String hostIp, String interfaceId, String startTime, String time, int limit);
	
	public String getNetworkTxcarrier(String hostIp, String interfaceId, String startTime, String time, int limit);
	
	public String getNetworkTxcollision(String hostIp, String interfaceId, String startTime, String time, int limit);
	
	public String getNetworkSpeed(String hostIp, String interfaceId, String startTime, String time, int limit);
}
