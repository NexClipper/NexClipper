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

import com.nexcloud.tsdb.adapter.HostNetworkAdapter;
import com.nexcloud.tsdb.client.influx.InfluxClient;

@Component
public class HostNetworkInfluxAdapter implements HostNetworkAdapter {
	
	@Autowired private InfluxClient influxClient;
	
	public String getNetworkRxbyte(String hostIp, String interfaceId, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(rxbyte) AS rxbyte"
			+ " FROM \"host_net\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " AND interface='" + interfaceId + "'"
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	
	public String getNetworkRxdrop(String hostIp, String interfaceId, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(rxdropped) AS rxdropped"
			+ " FROM \"host_net\"" 
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " AND interface='" + interfaceId + "'"
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getNetworkRxerror(String hostIp, String interfaceId, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(rxerrors) AS rxerrors"
			+ " FROM \"host_net\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " AND interface='" + interfaceId + "'"
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getNetworkRxoverrun(String hostIp, String interfaceId, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(rxoverrun) AS rxoverrun"
			+ " FROM \"host_net\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " AND interface='" + interfaceId + "'"
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getNetworkRxpacket(String hostIp, String interfaceId, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(rxpacket) AS rxpacket"
			+ " FROM \"host_net\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " AND interface='" + interfaceId + "'"
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	//
	public String getNetworkTxbyte(String hostIp, String interfaceId, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(txbyte) AS txbyte"
			+ " FROM \"host_net\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " AND interface='" + interfaceId + "'"
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	
	public String getNetworkTxdrop(String hostIp, String interfaceId, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(txdropped) AS txdropped"
			+ " FROM \"host_net\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " AND interface='" + interfaceId + "'"
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getNetworkTxerror(String hostIp, String interfaceId, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(txerrors) AS txerrors"
			+ " FROM \"host_net\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " AND interface='" + interfaceId + "'"
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getNetworkTxoverrun(String hostIp, String interfaceId, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(txoverrun) AS txoverrun"
			+ " FROM \"host_net\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " AND interface='" + interfaceId + "'"
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getNetworkTxpacket(String hostIp, String interfaceId, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(txpacket) AS txpacket"
			+ " FROM \"host_net\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " AND interface='" + interfaceId + "'"
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getNetworkTxcarrier(String hostIp, String interfaceId, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(txcarrier) AS txcarrier"
			+ " FROM \"host_net\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " AND interface='" + interfaceId + "'"
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getNetworkTxcollision(String hostIp, String interfaceId, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(txcollision) AS txcollision"
			+ " FROM \"host_net\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " AND interface='" + interfaceId + "'"
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
	
	public String getNetworkSpeed(String hostIp, String interfaceId, String startTime, String time, int limit) {
		return influxClient.get(
			"SELECT mean(speed) AS speed"
			+ " FROM \"host_net\""
			+ " WHERE host_ip='" + hostIp + "'"
			+ " AND time > now() - " + startTime
			+ " AND interface='" + interfaceId + "'"
			+ " GROUP By time(" + time + ") fill(linear)"
			+ " LIMIT " + limit 
		);
	}
}
