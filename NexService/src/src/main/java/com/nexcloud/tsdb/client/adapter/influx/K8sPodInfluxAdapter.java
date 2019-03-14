package com.nexcloud.tsdb.client.adapter.influx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.nexcloud.tsdb.adapter.K8sPodAdapter;
import com.nexcloud.tsdb.client.influx.InfluxClient;

@Component
public class K8sPodInfluxAdapter implements K8sPodAdapter {
	
	@Autowired private InfluxClient influxClient;

	@Override
	public String getCpuUsed(String startTime, String time, int limit) {
		return getCpuUsed(null, startTime, time, limit);
	}
	@Override
	public String getCpuUsedPercent(String startTime, String time, int limit) {
		return getCpuUsedPercent(null, startTime, time, limit);
	}
	@Override
	public String getCpuLimit(String startTime, String time, int limit) {
		return getCpuLimit(null, startTime, time, limit);
	}
	@Override
	public String getCpuRequest(String startTime, String time, int limit) {
		return getCpuRequest(null, startTime, time, limit);
	}
	@Override
	public String getMemoryUsed(String startTime, String time, int limit) {
		return getMemoryUsed(null, startTime, time, limit);
	}
	@Override
	public String getMemoryUsedPercent(String startTime, String time, int limit) {
		return getMemoryUsedPercent(null, startTime, time, limit);
	}
	@Override
	public String getMemoryLimit(String startTime, String time, int limit) {
		return getMemoryLimit(null, startTime, time, limit);
	}
	@Override
	public String getMemoryRequest(String startTime, String time, int limit) {
		return getMemoryRequest(null, startTime, time, limit);
	}
	@Override
	public String getCpuUsed(String pod, String startTime, String time, int limit) {
		return influxClient.get(getQuery("cpu_used", "cpuUsed", pod, startTime, time, limit));
	}
	@Override
	public String getCpuUsedPercent(String pod, String startTime, String time, int limit) {
		return influxClient.get(getQuery("cpu_used_percent", "cpuUsedPercent", pod, startTime, time, limit));
	}
	@Override
	public String getCpuLimit(String pod, String startTime, String time, int limit) {
		return influxClient.get(getQuery("limit_cpu", "cpuLimit", pod, startTime, time, limit));
	}
	@Override
	public String getCpuRequest(String pod, String startTime, String time, int limit) {
		return influxClient.get(getQuery("request_cpu", "cpuRequest", pod, startTime, time, limit));
	}

	@Override
	public String getMemoryUsed(String pod, String startTime, String time, int limit) {
		return influxClient.get(getQuery("mem_used", "memoryUsed", pod, startTime, time, limit));
	}
	@Override
	public String getMemoryUsedPercent(String pod, String startTime, String time, int limit) {
		return influxClient.get(getQuery("mem_used_percent", "memoryUsedPercent", pod, startTime, time, limit));
	}
	@Override
	public String getMemoryLimit(String pod, String startTime, String time, int limit) {
		return influxClient.get(getQuery("limit_mem", "memoryLimit", pod, startTime, time, limit));
	}
	@Override
	public String getMemoryRequest(String pod, String startTime, String time, int limit) {
		return influxClient.get(getQuery("request_mem", "memoryRequest", pod, startTime, time, limit));
	}
	private String getQuery(String field, String alias, String pod, String startTime, String time, int limit) {
		return  "SELECT mean(" + field + ") AS " + alias
				+ " FROM \"k8s_pod\""
				+ " WHERE time > now() - " + startTime
				+ ((pod != null) ? " AND pod = '" + pod + "'" : "")
				+ " GROUP By time(" + time + ") fill(linear)"
				+ " ORDER By time asc"
				+ " LIMIT " + limit;
	}
}
