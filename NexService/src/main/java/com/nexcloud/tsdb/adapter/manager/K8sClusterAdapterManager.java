package com.nexcloud.tsdb.adapter.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nexcloud.tsdb.adapter.K8sClusterAdapter;
import com.nexcloud.tsdb.client.adapter.influx.K8sClusterInfluxAdapter;
import com.nexcloud.tsdb.client.adapter.prometheus.K8sClusterPrometheusAdapter;

@Component
public class K8sClusterAdapterManager {
	@Value("${tsdb}") private String DB;
	@Autowired private K8sClusterInfluxAdapter influxAdapter; 
	@Autowired private K8sClusterPrometheusAdapter prometheusAdapter;

	public K8sClusterAdapter adapter() {
		if (DB.equals("influx")) return influxAdapter;
		else if (DB.equals("prometheus")) return prometheusAdapter;
		else {
			System.out.println("err");
			return null;
		}
	}
}
