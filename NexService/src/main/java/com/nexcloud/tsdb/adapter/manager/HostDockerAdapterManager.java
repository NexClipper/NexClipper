package com.nexcloud.tsdb.adapter.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nexcloud.tsdb.adapter.HostDockerAdapter;
import com.nexcloud.tsdb.client.adapter.influx.HostDockerInfluxAdapter;
import com.nexcloud.tsdb.client.adapter.prometheus.HostDockerPrometheusAdapter;

@Component
public class HostDockerAdapterManager {
	@Value("${tsdb}") private String DB;
	@Autowired private HostDockerInfluxAdapter influxAdapter; 
	@Autowired private HostDockerPrometheusAdapter prometheusAdapter;

	public HostDockerAdapter adapter() {
		if (DB.equals("influx")) return influxAdapter;
		else if (DB.equals("prometheus")) return prometheusAdapter;
		else {
			System.out.println("err");
			return null;
		}
	}
}
