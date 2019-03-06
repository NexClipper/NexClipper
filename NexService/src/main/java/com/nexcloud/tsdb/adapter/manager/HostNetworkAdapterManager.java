package com.nexcloud.tsdb.adapter.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nexcloud.tsdb.adapter.HostNetworkAdapter;
import com.nexcloud.tsdb.client.adapter.influx.HostNetworkInfluxAdapter;
import com.nexcloud.tsdb.client.adapter.prometheus.HostNetworkPrometheusAdapter;

@Component
public class HostNetworkAdapterManager {
	@Value("${tsdb}") private String DB;
	@Autowired private HostNetworkInfluxAdapter influxAdapter; 
	@Autowired private HostNetworkPrometheusAdapter prometheusAdapter;

	public HostNetworkAdapter adapter() {
		if (DB.equals("influx")) return influxAdapter;
		else if (DB.equals("prometheus")) return prometheusAdapter;
		else {
			System.out.println("err");
			return null;
		}
	}
}
