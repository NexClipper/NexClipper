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
import com.nexcloud.tsdb.adapter.K8sNodeAdapter;
import com.nexcloud.tsdb.client.influx.InfluxClient;

@Component
public class K8sNodeInfluxAdapter implements K8sNodeAdapter {
	
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
	public String getCpuAllocate(String clusterId, String startTime, String time, int limit) {
		return getCpuAllocate(clusterId, null, startTime, time, limit);
	}
	@Override
	public String getCpuTotal(String clusterId, String startTime, String time, int limit) {
		return getCpuTotal(clusterId, null, startTime, time, limit);
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
	public String getMemoryAllocate(String clusterId, String startTime, String time, int limit) {
		return getMemoryAllocate(clusterId, null, startTime, time, limit);
	}
	@Override
	public String getMemoryTotal(String clusterId, String startTime, String time, int limit) {
		return getMemoryTotal(clusterId, null, startTime, time, limit);
	}
	@Override
	public String getPodAllocate(String clusterId, String startTime, String time, int limit) {
		return getPodAllocate(clusterId, null, startTime, time, limit);
	}
	@Override
	public String getPodTotal(String clusterId, String startTime, String time, int limit) {
		return getPodTotal(clusterId, null, startTime, time, limit);
	}
	
	@Override
	public String getCpuUsed(String clusterId, String node, String startTime, String time, int limit) {
		return influxClient.get(getQuery(clusterId,"cpu_used", "cpuUsed", node, startTime, time, limit));
	}
	@Override
	public String getCpuUsedPercent(String clusterId, String node, String startTime, String time, int limit) {
		return influxClient.get(getQuery(clusterId,"cpu_used_percent", "cpuUsedPercent", node, startTime, time, limit));
	}
	@Override
	public String getCpuAllocate(String clusterId, String node, String startTime, String time, int limit) {
		return influxClient.get(getQuery(clusterId,"allocate_cpu", "cpuAllocate", node, startTime, time, limit));
	}
	@Override
	public String getCpuTotal(String clusterId, String node, String startTime, String time, int limit) {
		return influxClient.get(getQuery(clusterId,"total_cpu", "cpuTotal", node, startTime, time, limit));
	}

	@Override
	public String getMemoryUsed(String clusterId, String node, String startTime, String time, int limit) {
		return influxClient.get(getQuery(clusterId,"mem_used", "memoryUsed", node, startTime, time, limit));
	}
	@Override
	public String getMemoryUsedPercent(String clusterId, String node, String startTime, String time, int limit) {
		return influxClient.get(getQuery(clusterId,"mem_used_percent", "memoryUsedPercent", node, startTime, time, limit));
	}
	@Override
	public String getMemoryAllocate(String clusterId, String node, String startTime, String time, int limit) {
		return influxClient.get(getQuery(clusterId,"allocate_mem", "memoryAllocate", node, startTime, time, limit));
	}
	@Override
	public String getMemoryTotal(String clusterId, String node, String startTime, String time, int limit) {
		return influxClient.get(getQuery(clusterId,"total_mem", "memoryTotal", node, startTime, time, limit));
	}
	@Override
	public String getPodAllocate(String clusterId, String node, String startTime, String time, int limit) {
		return influxClient.get(getQuery(clusterId,"allocate_pod", "podAllocate", node, startTime, time, limit));
	}
	@Override
	public String getPodTotal(String clusterId, String node, String startTime, String time, int limit) {
		return influxClient.get(getQuery(clusterId,"total_pod", "podTotal", node, startTime, time, limit));
	}
	private String getQuery(String clusterId,String field, String alias, String node, String startTime, String time, int limit) {
		return  "SELECT mean(" + field + ") AS " + alias
				+ " FROM \"k8s_node\""
				+ " WHERE cluster_id = '" + clusterId + "' AND time > now() - " + startTime
				+ ((node != null) ? " AND node_name = '" + node + "'" : "")
				+ " GROUP By time(" + time + ") fill(linear)"
				+ " ORDER By time asc"
				+ " LIMIT " + limit;
	}
}
