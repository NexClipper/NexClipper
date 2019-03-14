package com.nexcloud.tsdb.adapter.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nexcloud.tsdb.adapter.K8sContainerAdapter;
import com.nexcloud.tsdb.client.adapter.influx.K8sContainerInfluxAdapter;
import com.nexcloud.tsdb.client.adapter.prometheus.K8sContainerPrometheusAdapter;

@Component
public class K8sContainerAdapterManager {
	@Value("${tsdb}") private String DB;
	@Autowired private K8sContainerInfluxAdapter influxAdapter; 
	@Autowired private K8sContainerPrometheusAdapter prometheusAdapter;

	public K8sContainerAdapter adapter() {
		if (DB.equals("influx")) return influxAdapter;
		else if (DB.equals("prometheus")) return prometheusAdapter;
		else {
			System.out.println("err");
			return null;
		}
	}
}
