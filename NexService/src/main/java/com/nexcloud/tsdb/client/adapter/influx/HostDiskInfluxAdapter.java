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

import com.nexcloud.tsdb.adapter.HostDiskAdapter;
import com.nexcloud.tsdb.client.influx.InfluxClient;

@Component
public class HostDiskInfluxAdapter implements HostDiskAdapter {
	
	@Autowired private InfluxClient influxClient;
	
	public String getDiskFreeByte(String hostIp, String mountName, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(free) AS free"
			+ " FROM \"host_disk\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " AND mount_name='" + mountName + "'"
			+ " GROUP By time(" + time + "), host_ip, mount_name fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getDiskUsagePercent(String hostIp, String mountName, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(used_percent) AS used_percent"
			+ " FROM \"host_disk\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " AND mount_name='" + mountName + "'"
			+ " GROUP By time(" + time + "), host_ip, mount_name fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getDiskReadbyte(String hostIp, String mountName, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(readbyte) AS readbyte"
			+ " FROM \"host_disk\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " AND mount_name='" + mountName + "'"
			+ " GROUP By time(" + time + "), host_ip, mount_name fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getDiskWritebyte(String hostIp, String mountName, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(writebyte) AS writebyte"
			+ " FROM \"host_disk\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " AND mount_name='" + mountName + "'"
			+ " GROUP By time(" + time + "), host_ip, mount_name fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getDiskTotal(String hostIp, String mountName, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(total) AS total"
			+ " FROM \"host_disk\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " AND mount_name='" + mountName + "'"
			+ " GROUP By time(" + time + "), host_ip, mount_name fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getDiskUsedbyte(String hostIp, String mountName, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(used) AS used"
			+ " FROM \"host_disk\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " AND mount_name='" + mountName + "'"
			+ " GROUP By time(" + time + "), host_ip, mount_name fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getDiskReads(String hostIp, String mountName, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(reads) AS reads"
			+ " FROM \"host_disk\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " AND mount_name='" + mountName + "'"
			+ " GROUP By time(" + time + "), host_ip, mount_name fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getDiskWrites(String hostIp, String mountName, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(writes) AS writes"
			+ " FROM \"host_disk\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " AND mount_name='" + mountName + "'"
			+ " GROUP By time(" + time + "), host_ip, mount_name fill(linear)"
			+ " LIMIT " + limit 
		);
	}
}
