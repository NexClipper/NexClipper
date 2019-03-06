package com.nexcloud.api.service.host;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexcloud.tsdb.adapter.manager.HostCpuAdapterManager;

@Service
public class HostCpuService {
	@Autowired private HostCpuAdapterManager adapterManager;
	
	public String getCpuUsedPercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuUsedPercentByHostIp(hostIp, startTime, time, limit);
	}
	
	public String getCpuIdlePercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuIdlePercentByHostIp(hostIp, startTime, time, limit);
	}
	
	public String getCpuIrqPercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuIrqPercentByHostIp(hostIp, startTime, time, limit);
	}
	
	public String getCpuNicePercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuNicePercentByHostIp(hostIp, startTime, time, limit);
	}
	
	public String getCpuSorfirqPercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuSorfirqPercentByHostIp(hostIp, startTime, time, limit);
	}
	
	public String getCpuStolenPercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuStolenPercentByHostIp(hostIp, startTime, time, limit);
	}
	
	public String getCpuSysPercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuSysPercentByHostIp(hostIp, startTime, time, limit);
	}
	
	public String getCpuWaitPercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuWaitPercentByHostIp(hostIp, startTime, time, limit);
	}
	
	public String getCpuTotalCoreByHostIp(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuTotalCoreByHostIp(hostIp, startTime, time, limit);
	}
	
	public String getCpuLoad1ByHostIp(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuLoad1ByHostIp(hostIp, startTime, time, limit);
	}
	
	public String getCpuLoad5ByHostIp(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuLoad5ByHostIp(hostIp, startTime, time, limit);
	}
	
	public String getCpuLoad15ByHostIp(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getCpuLoad15ByHostIp(hostIp, startTime, time, limit);
	}
}
