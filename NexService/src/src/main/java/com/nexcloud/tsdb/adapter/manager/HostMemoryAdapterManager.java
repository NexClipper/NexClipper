package com.nexcloud.tsdb.adapter.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nexcloud.tsdb.adapter.HostMemoryAdapter;
import com.nexcloud.tsdb.client.adapter.influx.HostMemoryInfluxAdapter;
import com.nexcloud.tsdb.client.adapter.prometheus.HostMemoryPrometheusAdapter;

@Component
public class HostMemoryAdapterManager {
	@Value("${tsdb}") private String DB;
	@Autowired private HostMemoryInfluxAdapter influxAdapter; 
	@Autowired private HostMemoryPrometheusAdapter prometheusAdapter;

	public HostMemoryAdapter adapter() {
		if (DB.equals("influx")) return influxAdapter;
		else if (DB.equals("prometheus")) return prometheusAdapter;
		else {
			System.out.println("err");
			return null;
		}
	}
}
