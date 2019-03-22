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
import com.nexcloud.tsdb.adapter.K8sNodeAdapter;
import com.nexcloud.tsdb.client.influx.InfluxClient;

@Component
public class K8sNodeInfluxAdapter implements K8sNodeAdapter {
	
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
	public String getCpuAllocate(String startTime, String time, int limit) {
		return getCpuAllocate(null, startTime, time, limit);
	}
	@Override
	public String getCpuTotal(String startTime, String time, int limit) {
		return getCpuTotal(null, startTime, time, limit);
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
	public String getMemoryAllocate(String startTime, String time, int limit) {
		return getMemoryAllocate(null, startTime, time, limit);
	}
	@Override
	public String getMemoryTotal(String startTime, String time, int limit) {
		return getMemoryTotal(null, startTime, time, limit);
	}
	@Override
	public String getPodAllocate(String startTime, String time, int limit) {
		return getPodAllocate(null, startTime, time, limit);
	}
	@Override
	public String getPodTotal(String startTime, String time, int limit) {
		return getPodTotal(null, startTime, time, limit);
	}
	
	@Override
	public String getCpuUsed(String node, String startTime, String time, int limit) {
		return influxClient.get(getQuery("cpu_used", "cpuUsed", node, startTime, time, limit));
	}
	@Override
	public String getCpuUsedPercent(String node, String startTime, String time, int limit) {
		return influxClient.get(getQuery("cpu_used_percent", "cpuUsedPercent", node, startTime, time, limit));
	}
	@Override
	public String getCpuAllocate(String node, String startTime, String time, int limit) {
		return influxClient.get(getQuery("allocate_cpu", "cpuAllocate", node, startTime, time, limit));
	}
	@Override
	public String getCpuTotal(String node, String startTime, String time, int limit) {
		return influxClient.get(getQuery("total_cpu", "cpuTotal", node, startTime, time, limit));
	}

	@Override
	public String getMemoryUsed(String node, String startTime, String time, int limit) {
		return influxClient.get(getQuery("mem_used", "memoryUsed", node, startTime, time, limit));
	}
	@Override
	public String getMemoryUsedPercent(String node, String startTime, String time, int limit) {
		return influxClient.get(getQuery("mem_used_percent", "memoryUsedPercent", node, startTime, time, limit));
	}
	@Override
	public String getMemoryAllocate(String node, String startTime, String time, int limit) {
		return influxClient.get(getQuery("allocate_mem", "memoryAllocate", node, startTime, time, limit));
	}
	@Override
	public String getMemoryTotal(String node, String startTime, String time, int limit) {
		return influxClient.get(getQuery("total_mem", "memoryTotal", node, startTime, time, limit));
	}
	@Override
	public String getPodAllocate(String node, String startTime, String time, int limit) {
		return influxClient.get(getQuery("allocate_pod", "podAllocate", node, startTime, time, limit));
	}
	@Override
	public String getPodTotal(String node, String startTime, String time, int limit) {
		return influxClient.get(getQuery("total_pod", "podTotal", node, startTime, time, limit));
	}
	private String getQuery(String field, String alias, String node, String startTime, String time, int limit) {
		return  "SELECT mean(" + field + ") AS " + alias
				+ " FROM \"k8s_node\""
				+ " WHERE time > now() - " + startTime
				+ ((node != null) ? " AND node_name = '" + node + "'" : "")
				+ " GROUP By time(" + time + ") fill(linear)"
				+ " ORDER By time asc"
				+ " LIMIT " + limit;
	}
}
