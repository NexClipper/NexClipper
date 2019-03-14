package com.nexcloud.tsdb.adapter.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nexcloud.tsdb.adapter.HostDiskAdapter;
import com.nexcloud.tsdb.client.adapter.influx.HostDiskInfluxAdapter;
import com.nexcloud.tsdb.client.adapter.prometheus.HostDiskPrometheusAdapter;

@Component
public class HostDiskAdapterManager {
	@Value("${tsdb}") private String DB;
	@Autowired private HostDiskInfluxAdapter influxAdapter; 
	@Autowired private HostDiskPrometheusAdapter prometheusAdapter;

	public HostDiskAdapter adapter() {
		if (DB.equals("influx")) return influxAdapter;
		else if (DB.equals("prometheus")) return prometheusAdapter;
		else {
			System.out.println("err");
			return null;
		}
	}
}
