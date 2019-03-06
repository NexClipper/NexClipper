package com.nexcloud.api.service.host;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexcloud.tsdb.adapter.manager.HostDiskAdapterManager;

@Service
public class HostDiskService {
	@Autowired private HostDiskAdapterManager adapterManager;
	
	public String getDiskFreeByte(String hostIp, String mountName, String startTime, String time, int limit) {
		return adapterManager.adapter().getDiskFreeByte(hostIp, mountName, startTime, time, limit);
	}
	
	public String getDiskUsagePercent(String hostIp, String mountName, String startTime, String time, int limit) {
		return adapterManager.adapter().getDiskUsagePercent(hostIp, mountName, startTime, time, limit);
	}
	
	public String getDiskReadbyte(String hostIp, String mountName, String startTime, String time, int limit) {
		return adapterManager.adapter().getDiskReadbyte(hostIp, mountName, startTime, time, limit);
	}
	
	public String getDiskWritebyte(String hostIp, String mountName, String startTime, String time, int limit) {
		return adapterManager.adapter().getDiskWritebyte(hostIp, mountName, startTime, time, limit);
	}
	
	public String getDiskTotal(String hostIp, String mountName, String startTime, String time, int limit) {
		return adapterManager.adapter().getDiskTotal(hostIp, mountName, startTime, time, limit);
	}
	
	public String getDiskUsedbyte(String hostIp, String mountName, String startTime, String time, int limit) {
		return adapterManager.adapter().getDiskUsedbyte(hostIp, mountName, startTime, time, limit);
	}
	
	public String getDiskReads(String hostIp, String mountName, String startTime, String time, int limit) {
		return adapterManager.adapter().getDiskReads(hostIp, mountName, startTime, time, limit);
	}
	
	public String getDiskWrites(String hostIp, String mountName, String startTime, String time, int limit) {
		return adapterManager.adapter().getDiskWrites(hostIp, mountName, startTime, time, limit);
	}
}
