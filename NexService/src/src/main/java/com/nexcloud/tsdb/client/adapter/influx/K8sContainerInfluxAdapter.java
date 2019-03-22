package com.nexcloud.tsdb.client.adapter.influx;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.nexcloud.tsdb.adapter.K8sContainerAdapter;
import com.nexcloud.tsdb.client.influx.InfluxClient;

@Component
public class K8sContainerInfluxAdapter implements K8sContainerAdapter {
	
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
	
	// by container

	@Override
	public String getCpuUsed(String container, String startTime, String time, int limit) {
		return influxClient.get(getQuery("cpu_used", "cpuUsed", container, startTime, time, limit));
	}

	@Override
	public String getCpuUsedPercent(String container, String startTime, String time, int limit) {
		return influxClient.get(getQuery("cpu_used_percent", "cpuUsedPercent", container, startTime, time, limit));
	}

	@Override
	public String getCpuLimit(String container, String startTime, String time, int limit) {
		return influxClient.get(getQuery("limit_cpu", "cpuLimit", container, startTime, time, limit));
	}

	@Override
	public String getCpuRequest(String container, String startTime, String time, int limit) {
		return influxClient.get(getQuery("request_cpu", "cpuRequest", container, startTime, time, limit));
	}

	@Override
	public String getMemoryUsed(String container, String startTime, String time, int limit) {
		return influxClient.get(getQuery("mem_used", "memoryUsed", container, startTime, time, limit));
	}

	@Override
	public String getMemoryUsedPercent(String container, String startTime, String time, int limit) {
		return influxClient.get(getQuery("mem_used_percent", "memoryUsedPercent", container, startTime, time, limit));
	}

	@Override
	public String getMemoryLimit(String container, String startTime, String time, int limit) {
		return influxClient.get(getQuery("limit_mem", "memoryLimit", container, startTime, time, limit));
	}

	@Override
	public String getMemoryRequest(String container, String startTime, String time, int limit) {
		return influxClient.get(getQuery("request_mem", "memoryRequest", container, startTime, time, limit));
	}
	private String getQuery(String field, String alias, String container, String startTime, String time, int limit) {
		return  "SELECT mean(" + field + ") AS " + alias
				+ " FROM \"k8s_container\""
				+ " WHERE time > now() - " + startTime
				+ ((container != null) ? " AND node = '" + container + "'" : "")
				+ " GROUP By time(" + time + ") fill(linear)"
				+ " ORDER By time asc"
				+ " LIMIT " + limit;
	}
}
