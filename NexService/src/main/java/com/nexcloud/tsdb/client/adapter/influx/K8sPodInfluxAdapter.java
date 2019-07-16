/*
* Copyright 2019 NexCloud Co.,Ltd.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.nexcloud.tsdb.client.adapter.influx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.nexcloud.tsdb.adapter.K8sPodAdapter;
import com.nexcloud.tsdb.client.influx.InfluxClient;

@Component
public class K8sPodInfluxAdapter implements K8sPodAdapter {
	
	@Autowired private InfluxClient influxClient;

	@Override
	public String getCpuUsed(String clusterId, String startTime, String time, int limit) {
		return getCpuUsed(clusterId, null, startTime, time, limit);
	}
	@Override
	public String getCpuUsedPercent(String clusterId, String startTime, String time, int limit) {
		return getCpuUsedPercent(clusterId, null, startTime, time, limit);
	}
	@Override
	public String getCpuLimit(String clusterId, String startTime, String time, int limit) {
		return getCpuLimit(clusterId, null, startTime, time, limit);
	}
	@Override
	public String getCpuRequest(String clusterId, String startTime, String time, int limit) {
		return getCpuRequest(clusterId, null, startTime, time, limit);
	}
	@Override
	public String getMemoryUsed(String clusterId, String startTime, String time, int limit) {
		return getMemoryUsed(clusterId, null, startTime, time, limit);
	}
	@Override
	public String getMemoryUsedPercent(String clusterId, String startTime, String time, int limit) {
		return getMemoryUsedPercent(clusterId, null, startTime, time, limit);
	}
	@Override
	public String getMemoryLimit(String clusterId, String startTime, String time, int limit) {
		return getMemoryLimit(clusterId, null, startTime, time, limit);
	}
	@Override
	public String getMemoryRequest(String clusterId, String startTime, String time, int limit) {
		return getMemoryRequest(clusterId, null, startTime, time, limit);
	}
	@Override
	public String getCpuUsed(String clusterId, String pod, String startTime, String time, int limit) {
		return influxClient.get(getQuery(clusterId,"cpu_used", "cpuUsed", pod, startTime, time, limit));
	}
	@Override
	public String getCpuUsedPercent(String clusterId, String pod, String startTime, String time, int limit) {
		return influxClient.get(getQuery(clusterId,"cpu_used_percent", "cpuUsedPercent", pod, startTime, time, limit));
	}
	@Override
	public String getCpuLimit(String clusterId, String pod, String startTime, String time, int limit) {
		return influxClient.get(getQuery(clusterId,"limit_cpu", "cpuLimit", pod, startTime, time, limit));
	}
	@Override
	public String getCpuRequest(String clusterId, String pod, String startTime, String time, int limit) {
		return influxClient.get(getQuery(clusterId,"request_cpu", "cpuRequest", pod, startTime, time, limit));
	}

	@Override
	public String getMemoryUsed(String clusterId, String pod, String startTime, String time, int limit) {
		return influxClient.get(getQuery(clusterId,"mem_used", "memoryUsed", pod, startTime, time, limit));
	}
	@Override
	public String getMemoryUsedPercent(String clusterId, String pod, String startTime, String time, int limit) {
		return influxClient.get(getQuery(clusterId,"mem_used_percent", "memoryUsedPercent", pod, startTime, time, limit));
	}
	@Override
	public String getMemoryLimit(String clusterId, String pod, String startTime, String time, int limit) {
		return influxClient.get(getQuery(clusterId,"limit_mem", "memoryLimit", pod, startTime, time, limit));
	}
	@Override
	public String getMemoryRequest(String clusterId, String pod, String startTime, String time, int limit) {
		return influxClient.get(getQuery(clusterId,"request_mem", "memoryRequest", pod, startTime, time, limit));
	}
	private String getQuery(String clusterId,String field, String alias, String pod, String startTime, String time, int limit) {
		return  "SELECT mean(" + field + ") AS " + alias
				+ " FROM \"k8s_pod\""
				+ " WHERE cluster_id = '" + clusterId + "' AND time > now() - " + startTime
				+ ((pod != null) ? " AND pod = '" + pod + "'" : "")
				+ " GROUP By time(" + time + ") fill(linear)"
				+ " ORDER By time asc"
				+ " LIMIT " + limit;
	}
}
