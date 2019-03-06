package com.nexcloud.tsdb.adapter.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nexcloud.tsdb.adapter.K8sNodeAdapter;
import com.nexcloud.tsdb.client.adapter.influx.K8sNodeInfluxAdapter;
import com.nexcloud.tsdb.client.adapter.prometheus.K8sNodePrometheusAdapter;

@Component
public class K8sNodeAdapterManager {
	@Value("${tsdb}") private String DB;
	@Autowired private K8sNodeInfluxAdapter influxAdapter; 
	@Autowired private K8sNodePrometheusAdapter prometheusAdapter;

	public K8sNodeAdapter adapter() {
		if (DB.equals("influx")) return influxAdapter;
		else if (DB.equals("prometheus")) return prometheusAdapter;
		else {
			System.out.println("err");
			return null;
		}
	}
}
