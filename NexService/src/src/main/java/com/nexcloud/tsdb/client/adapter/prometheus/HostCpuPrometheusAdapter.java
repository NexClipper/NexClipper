package com.nexcloud.tsdb.client.adapter.prometheus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nexcloud.tsdb.adapter.HostCpuAdapter;
import com.nexcloud.tsdb.client.prometheus.PrometheusClient;

@Component
public class HostCpuPrometheusAdapter implements HostCpuAdapter {
	@Autowired private PrometheusClient prometheusClient;

	@Override
	public String getCpuUsedPercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return prometheusClient.get("container_cpu_usage_seconds_total");
	}

	@Override
	public String getCpuIdlePercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return prometheusClient.get("container_cpu_system_seconds_total");
	}

	@Override
	public String getCpuIrqPercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return prometheusClient.get("container_cpu_usage_seconds_total");
	}

	@Override
	public String getCpuNicePercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return prometheusClient.get("container_cpu_user_seconds_total");
	}

	@Override
	public String getCpuSorfirqPercentByHostIp(String hostIp, String startTime, String time,
			int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCpuStolenPercentByHostIp(String hostIp, String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCpuSysPercentByHostIp(String hostIp, String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCpuWaitPercentByHostIp(String hostIp, String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getCpuTotalCoreByHostIp(String hostIp, String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getCpuLoad1ByHostIp(String hostIp, String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getCpuLoad5ByHostIp(String hostIp, String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getCpuLoad15ByHostIp(String hostIp, String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
//	PrometheusClient prometheusClient = new PrometheusClient();

	@Override
	public String getCpuUserPercentByHostIp(String hostIp, String startTime, String time, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
}
