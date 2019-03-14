package com.nexcloud.tsdb.client.adapter.influx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nexcloud.tsdb.adapter.DockerAdapter;
import com.nexcloud.tsdb.client.influx.InfluxClient;

@Component
public class DockerInfluxAdapter implements DockerAdapter{
	
	@Autowired private InfluxClient influxClient;
}
