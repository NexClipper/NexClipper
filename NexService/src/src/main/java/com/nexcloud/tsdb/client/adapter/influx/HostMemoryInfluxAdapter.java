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

import com.nexcloud.tsdb.adapter.HostMemoryAdapter;
import com.nexcloud.tsdb.client.influx.InfluxClient;

@Component
public class HostMemoryInfluxAdapter implements HostMemoryAdapter {
	
	@Autowired private InfluxClient influxClient;
	
	public String getActualFreeMemory(String clusterId, String hostIp, String startTime, String time, int limit){
		return influxClient.get(
			"SELECT mean(mem_actual_free) AS mem_actual_free"
			+ " FROM \"host\""
			+ " WHERE cluster_id = '" + clusterId + "' AND host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getActualUsedMemory(String clusterId, String hostIp, String startTime, String time, int limit){
		return influxClient.get(
			"SELECT mean(mem_actual_used) AS mem_actual_used"
			+ " FROM \"host\""
			+ " WHERE cluster_id = '" + clusterId + "' AND host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getFreeMemory(String clusterId, String hostIp, String startTime, String time, int limit){
		return influxClient.get(
			"SELECT mean(mem_free) AS mem_free"
			+ " FROM \"host\""
			+ " WHERE cluster_id = '" + clusterId + "' AND host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getFreePercentMemory(String clusterId, String hostIp, String startTime, String time, int limit){
		return influxClient.get(
			"SELECT mean(mem_free_percent) AS mem_free_percent"
			+ " FROM \"host\""
			+ " WHERE cluster_id = '" + clusterId + "' AND host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getUsedMemory(String clusterId, String hostIp, String startTime, String time, int limit){
		return influxClient.get(
			"SELECT mean(mem_used) AS mem_used"
			+ " FROM \"host\""
			+ " WHERE cluster_id = '" + clusterId + "' AND host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getUsedPercentMemory(String clusterId, String hostIp, String startTime, String time, int limit){
		return influxClient.get(
			"SELECT mean(mem_used_percent) AS mem_used_percent"
			+ " FROM \"host\""
			+ " WHERE cluster_id = '" + clusterId + "' AND host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getTotalMemory(String clusterId, String hostIp, String startTime, String time, int limit){
		return influxClient.get(
			"SELECT mean(mem_total) AS mem_total"
			+ " FROM \"host\""
			+ " WHERE cluster_id = '" + clusterId + "' AND host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getSwapFreeMemory(String clusterId, String hostIp, String startTime, String time, int limit){
		return influxClient.get(
			"SELECT mean(swap_free) AS swap_free"
			+ " FROM \"host\""
			+ " WHERE cluster_id = '" + clusterId + "' AND host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getSwapFreePercentMemory(String clusterId, String hostIp, String startTime, String time, int limit){
		return influxClient.get(
			"SELECT mean(swap_free_percent) AS swap_free_percent"
			+ " FROM \"host\""
			+ " WHERE cluster_id = '" + clusterId + "' AND host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getSwapUsedMemory(String clusterId, String hostIp, String startTime, String time, int limit){
		return influxClient.get(
			"SELECT mean(swap_used) AS swap_used"
			+ " FROM \"host\""
			+ " WHERE cluster_id = '" + clusterId + "' AND host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getSwapUsedPercentMemory(String clusterId, String hostIp, String startTime, String time, int limit){
		return influxClient.get(
			"SELECT mean(swap_used_percent) AS swap_used_percent"
			+ " FROM \"host\""
			+ " WHERE cluster_id = '" + clusterId + "' AND host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getSwapTotalMemory(String clusterId, String hostIp, String startTime, String time, int limit){
		return influxClient.get(
			"SELECT mean(swap_total) AS swap_total"
			+ " FROM \"host\""
			+ " WHERE cluster_id = '" + clusterId + "' AND host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getSwapPagein(String clusterId, String hostIp, String startTime, String time, int limit){
		return influxClient.get(
			"SELECT mean(swap_pagein) AS swap_pagein"
			+ " FROM \"host\""
			+ " WHERE cluster_id = '" + clusterId + "' AND host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getSwapPageout(String clusterId, String hostIp, String startTime, String time, int limit){
		return influxClient.get(
			"SELECT mean(swap_pageout) AS swap_pageout"
			+ " FROM \"host\""
			+ " WHERE cluster_id = '" + clusterId + "' AND host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
}
