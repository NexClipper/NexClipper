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

import com.nexcloud.tsdb.adapter.HostCpuAdapter;
import com.nexcloud.tsdb.client.influx.InfluxClient;

@Component
public class HostCpuInfluxAdapter implements HostCpuAdapter {
	
	@Autowired private InfluxClient influxClient;

	public String getCpuUsedPercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(cpu_used_percent) AS used"
			+ " FROM \"host\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + "), host_ip fill(linear)"
			//+ " ORDER By time asc"
			+ " LIMIT " + limit 
		);
	}
	
	public String getCpuUserPercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(cpu_used_percent) AS used"
			+ " FROM \"host\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + "), host_ip fill(linear)"
			//+ " ORDER By time asc"
			+ " LIMIT " + limit 
		);
	}
	
	public String getCpuIdlePercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(cpu_idle_percent) AS idle"
			+ " FROM \"host\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + "), host_ip fill(linear)"
			+ " ORDER By time asc"
			+ " LIMIT " + limit 
		);
	}
	
	public String getCpuIrqPercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(cpu_irq_percent) AS irq"
			+ " FROM \"host\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + "), host_ip fill(linear)"
			+ " ORDER By time asc"
			+ " LIMIT " + limit 
		);
	}
	
	public String getCpuNicePercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(cpu_nice_percent) AS nice"
			+ " FROM \"host\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + "), host_ip fill(linear)"
			+ " ORDER By time asc"
			+ " LIMIT " + limit 
		);
	}
	
	public String getCpuSorfirqPercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(cpu_sorfirq_percent) AS sorfirq"
			+ " FROM \"host\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + "), host_ip fill(linear)"
			+ " ORDER By time asc"
			+ " LIMIT " + limit 
		);
	}
	public String getCpuStolenPercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(cpu_stolen_percent) AS stolen"
			+ " FROM \"host\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + "), host_ip fill(linear)"
			+ " ORDER By time asc"
			+ " LIMIT " + limit 
		);
	}
	
	public String getCpuSysPercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(cpu_sys_percent) AS sys"
			+ " FROM \"host\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + "), host_ip fill(linear)"
			+ " ORDER By time asc"
			+ " LIMIT " + limit 
		);
	}
	
	public String getCpuWaitPercentByHostIp(String hostIp, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(cpu_wait_percent) AS wait"
			+ " FROM \"host\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + "), host_ip fill(linear)"
			+ " ORDER By time asc"
			+ " LIMIT " + limit 
		);
	}
	
	public String getCpuTotalCoreByHostIp(String hostIp, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(cpu_total) AS core"
			+ " FROM \"host\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + "), host_ip fill(linear)"
			+ " ORDER By time asc"
			+ " LIMIT " + limit 
		);
	}
	
	public String getCpuLoad1ByHostIp(String hostIp, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(load1) AS load1"
			+ " FROM \"host\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + "), host_ip fill(linear)"
			+ " ORDER By time asc"
			+ " LIMIT " + limit 
		);
	}
	
	public String getCpuLoad5ByHostIp(String hostIp, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(load5) AS load5"
			+ " FROM \"host\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + "), host_ip fill(linear)"
			+ " ORDER By time asc"
			+ " LIMIT " + limit 
		);
	}
	
	public String getCpuLoad15ByHostIp(String hostIp, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(load15) AS load15"
			+ " FROM \"host\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + "), host_ip fill(linear)"
			+ " ORDER By time asc"
			+ " LIMIT " + limit 
		);
	}
}
