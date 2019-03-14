package com.nexcloud.tsdb.adapter.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nexcloud.tsdb.adapter.DockerAdapter;
import com.nexcloud.tsdb.client.adapter.influx.DockerInfluxAdapter;
import com.nexcloud.tsdb.client.adapter.prometheus.DockerPrometheusAdapter;

@Component
public class DockerAdapterManager {
	@Value("${tsdb}") private String DB;
	@Autowired private DockerInfluxAdapter influxAdapter; 
	@Autowired private DockerPrometheusAdapter prometheusAdapter;

	public DockerAdapter adapter() {
		if (DB.equals("influx")) return influxAdapter;
		else if (DB.equals("prometheus")) return prometheusAdapter;
		else {
			System.out.println("err");
			return null;
		}
	}
}
