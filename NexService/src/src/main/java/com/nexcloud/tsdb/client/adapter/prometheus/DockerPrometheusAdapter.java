package com.nexcloud.tsdb.client.adapter.prometheus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nexcloud.tsdb.adapter.DockerAdapter;
import com.nexcloud.tsdb.client.prometheus.PrometheusClient;

@Component
public class DockerPrometheusAdapter implements DockerAdapter {
	@Autowired private PrometheusClient prometheusClient;

}
