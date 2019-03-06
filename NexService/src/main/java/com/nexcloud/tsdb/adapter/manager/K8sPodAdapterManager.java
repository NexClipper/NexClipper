package com.nexcloud.tsdb.adapter.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.nexcloud.tsdb.adapter.K8sPodAdapter;
import com.nexcloud.tsdb.client.adapter.influx.K8sPodInfluxAdapter;
import com.nexcloud.tsdb.client.adapter.prometheus.K8sPodPrometheusAdapter;

@Component
public class K8sPodAdapterManager {
	@Value("${tsdb}") private String DB;
	@Autowired private K8sPodInfluxAdapter influxAdapter; 
	@Autowired private K8sPodPrometheusAdapter prometheusAdapter;

	public K8sPodAdapter adapter() {
		if (DB.equals("influx")) return influxAdapter;
		else if (DB.equals("prometheus")) return prometheusAdapter;
		else {
			System.out.println("err");
			return null;
		}
	}
}
