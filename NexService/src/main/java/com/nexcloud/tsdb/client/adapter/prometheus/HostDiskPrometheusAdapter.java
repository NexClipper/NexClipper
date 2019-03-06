package com.nexcloud.tsdb.client.adapter.prometheus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nexcloud.tsdb.adapter.HostDiskAdapter;
import com.nexcloud.tsdb.client.prometheus.PrometheusClient;

@Component
public class HostDiskPrometheusAdapter implements HostDiskAdapter {
	@Autowired private PrometheusClient prometheusClient;

	@Override
	public String getDiskFreeByte(String hostIp, String mountName, String startTime, String time, int limit) {
		return null;
	}

	@Override
	public String getDiskUsagePercent(String hostIp, String mountName, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDiskReadbyte(String hostIp, String mountName, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDiskWritebyte(String hostIp, String mountName, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDiskTotal(String hostIp, String mountName, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDiskUsedbyte(String hostIp, String mountName, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDiskReads(String hostIp, String mountName, String startTime, String time, int limit) {
		return null;
	}
	
	@Override
	public String getDiskWrites(String hostIp, String mountName, String startTime, String time, int limit) {
		return null;
	}
}
