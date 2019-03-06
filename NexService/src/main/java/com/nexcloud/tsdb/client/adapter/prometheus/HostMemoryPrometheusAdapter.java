package com.nexcloud.tsdb.client.adapter.prometheus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nexcloud.tsdb.adapter.HostMemoryAdapter;
import com.nexcloud.tsdb.client.prometheus.PrometheusClient;

@Component
public class HostMemoryPrometheusAdapter implements HostMemoryAdapter {
	@Autowired private PrometheusClient prometheusClient;

	public String getActualFreeMemory(String hostIp, String startTime, String time, int limit) {
		return null;
	}
	
	public String getActualUsedMemory(String hostIp, String startTime, String time, int limit) {
		return null;
	}
	
	public String getFreeMemory(String hostIp, String startTime, String time, int limit) {
		return null;
	}
	
	public String getFreePercentMemory(String hostIp, String startTime, String time, int limit) {
		return null;
	}
	
	public String getUsedMemory(String hostIp, String startTime, String time, int limit) {
		return null;
	}
	
	public String getUsedPercentMemory(String hostIp, String startTime, String time, int limit) {
		return null;
	}
	
	public String getTotalMemory(String hostIp, String startTime, String time, int limit) {
		return null;
	}
	
	public String getSwapFreeMemory(String hostIp, String startTime, String time, int limit) {
		return null;
	}
	
	public String getSwapFreePercentMemory(String hostIp, String startTime, String time, int limit) {
		return null;
	}
	
	public String getSwapUsedMemory(String hostIp, String startTime, String time, int limit) {
		return null;
	}
	
	public String getSwapUsedPercentMemory(String hostIp, String startTime, String time, int limit) {
		return null;
	}
	
	public String getSwapTotalMemory(String hostIp, String startTime, String time, int limit) {
		return null;
	}
	
	public String getSwapPagein(String hostIp, String startTime, String time, int limit) {
		return null;
	}
	
	public String getSwapPageout(String hostIp, String startTime, String time, int limit) {
		return null;
	}
}
