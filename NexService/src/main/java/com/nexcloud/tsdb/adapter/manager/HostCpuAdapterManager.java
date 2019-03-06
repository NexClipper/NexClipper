package com.nexcloud.tsdb.adapter.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nexcloud.tsdb.adapter.HostCpuAdapter;
import com.nexcloud.tsdb.client.adapter.influx.HostCpuInfluxAdapter;
import com.nexcloud.tsdb.client.adapter.prometheus.HostCpuPrometheusAdapter;

@Component
public class HostCpuAdapterManager {
	@Value("${tsdb}") private String DB;
	@Autowired private HostCpuInfluxAdapter influxAdapter; 
	@Autowired private HostCpuPrometheusAdapter prometheusAdapter;

	public HostCpuAdapter adapter() {
		if (DB.equals("influx")) return influxAdapter;
		else if (DB.equals("prometheus")) return prometheusAdapter;
		else {
			System.out.println("err");
			return null;
		}
	}
}
