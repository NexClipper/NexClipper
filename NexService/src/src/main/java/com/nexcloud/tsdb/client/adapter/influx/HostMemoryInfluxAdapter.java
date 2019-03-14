package com.nexcloud.tsdb.client.adapter.influx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nexcloud.tsdb.adapter.HostMemoryAdapter;
import com.nexcloud.tsdb.client.influx.InfluxClient;

@Component
public class HostMemoryInfluxAdapter implements HostMemoryAdapter {
	
	@Autowired private InfluxClient influxClient;
	
	public String getActualFreeMemory(String hostIp, String startTime, String time, int limit){
		return influxClient.get(
			"SELECT mean(mem_actual_free) AS mem_actual_free"
			+ " FROM \"host\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getActualUsedMemory(String hostIp, String startTime, String time, int limit){
		return influxClient.get(
			"SELECT mean(mem_actual_used) AS mem_actual_used"
			+ " FROM \"host\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getFreeMemory(String hostIp, String startTime, String time, int limit){
		return influxClient.get(
			"SELECT mean(mem_free) AS mem_free"
			+ " FROM \"host\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getFreePercentMemory(String hostIp, String startTime, String time, int limit){
		return influxClient.get(
			"SELECT mean(mem_free_percent) AS mem_free_percent"
			+ " FROM \"host\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getUsedMemory(String hostIp, String startTime, String time, int limit){
		return influxClient.get(
			"SELECT mean(mem_used) AS mem_used"
			+ " FROM \"host\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getUsedPercentMemory(String hostIp, String startTime, String time, int limit){
		return influxClient.get(
			"SELECT mean(mem_used_percent) AS mem_used_percent"
			+ " FROM \"host\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getTotalMemory(String hostIp, String startTime, String time, int limit){
		return influxClient.get(
			"SELECT mean(mem_total) AS mem_total"
			+ " FROM \"host\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getSwapFreeMemory(String hostIp, String startTime, String time, int limit){
		return influxClient.get(
			"SELECT mean(swap_free) AS swap_free"
			+ " FROM \"host\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getSwapFreePercentMemory(String hostIp, String startTime, String time, int limit){
		return influxClient.get(
			"SELECT mean(swap_free_percent) AS swap_free_percent"
			+ " FROM \"host\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getSwapUsedMemory(String hostIp, String startTime, String time, int limit){
		return influxClient.get(
			"SELECT mean(swap_used) AS swap_used"
			+ " FROM \"host\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getSwapUsedPercentMemory(String hostIp, String startTime, String time, int limit){
		return influxClient.get(
			"SELECT mean(swap_used_percent) AS swap_used_percent"
			+ " FROM \"host\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getSwapTotalMemory(String hostIp, String startTime, String time, int limit){
		return influxClient.get(
			"SELECT mean(swap_total) AS swap_total"
			+ " FROM \"host\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getSwapPagein(String hostIp, String startTime, String time, int limit){
		return influxClient.get(
			"SELECT mean(swap_pagein) AS swap_pagein"
			+ " FROM \"host\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getSwapPageout(String hostIp, String startTime, String time, int limit){
		return influxClient.get(
			"SELECT mean(swap_pageout) AS swap_pageout"
			+ " FROM \"host\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
}
