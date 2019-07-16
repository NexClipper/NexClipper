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
package com.nexcloud.api.service.host;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nexcloud.mdb.client.redis.RedisClient;
import com.nexcloud.tsdb.adapter.manager.HostDockerAdapterManager;
import com.nexcloud.util.Util;
import com.nexcloud.util.consts.HTTP;
import com.nexcloud.util.consts.REDIS;
import com.nexcloud.util.response.Success;
@Service
public class HostDockerService {
	@Autowired private RedisClient redisClient;
	@Autowired private HostDockerAdapterManager adapterManager;
	
	public String getAllDocker(String clusterId ) {
		Gson gson 					= new GsonBuilder().setPrettyPrinting().serializeNulls().create();
		String data					= redisClient.get(clusterId + "_" + REDIS.HASH.HOST, REDIS.KEY.LIST);
		Success success				= Util.JsonTobean(data, Success.class);
		
		List<Map<String,Object>> dockers			= new ArrayList<Map<String,Object>>();
		Map<String, Object> map		= new HashMap<String, Object>();
		// Redis Data get Success
		if( success.getResponseCode() == HTTP.CODE.OK && success.getResponseBody() != null )
		{
		
			List<String> ips		= gson.fromJson(success.getResponseBody(), new TypeToken<List<String>>(){}.getType());
			ips						= Util.asc(ips);
			for( String ip : ips )
			{
				data				= redisClient.get(clusterId + "_" + REDIS.HASH.DOCKER, ip);
				success				= Util.JsonTobean(data, Success.class);
				if( success.getResponseCode() == HTTP.CODE.OK && success.getResponseBody() != null )
				{
					map.put(ip, gson.fromJson(success.getResponseBody(), new TypeToken<Object>(){}.getType()));
				}
			}
			
			return Util.beanToJson(new Success(HTTP.CODE.OK, HTTP.MESSAGE.OK, "Redis", Util.beanToJson(map)));
		}
		else
		{
			return data;
		}
	}

	public String getDocker(String clusterId, String hostIp) {
		return redisClient.get(clusterId + "_" + REDIS.HASH.DOCKER, hostIp);
	}
	
	public String getDockerCpuUsedByContainerId(String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerCpuUsedByContainerId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerCpuUsedByTaskId(String clusterId, String hostIp, String taskId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerCpuUsedByTaskId(clusterId, hostIp, taskId, startTime, time, limit);
	}
	
	public String getDockerMemoryAllocateByContainerId(String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerMemoryAllocateByContainerId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerMemoryUsedByteByContainerId(String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerMemoryUsedByteByContainerId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerMemoryUsedByteByTaskId(String clusterId, String hostIp, String taskId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerMemoryUsedByteByTaskId(clusterId, hostIp, taskId, startTime, time, limit);
	}
	
	public String getDockerMemoryUsedPercentByContainerId(String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerMemoryUsedPercentByContainerId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerMemoryUsedPercentByTaskId(String clusterId, String hostIp, String taskId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerMemoryUsedPercentByTaskId(clusterId, hostIp, taskId, startTime, time, limit);
	}
	
	public String getDockerDiskioReadByContainerId(String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerDiskioReadByContainerId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerDiskioReadByTaskId(String clusterId, String hostIp, String taskId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerDiskioReadByTaskId(clusterId, hostIp, taskId, startTime, time, limit);
	}
	
	public String getDockerDiskioWriteByContainerId(String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerDiskioWriteByContainerId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerDiskioWriteByTaskId(String clusterId, String hostIp, String taskId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerDiskioWriteByTaskId(clusterId, hostIp, taskId, startTime, time, limit);
	}
	
	public String getDockerNetworkRxbyteByContainerId(String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkRxbyteByContainerId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkRxdropByContainerId(String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkRxdropByContainerId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkRxerrorByContainerId(String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkRxerrorByContainerId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkRxpacketByContainerId(String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkRxpacketByContainerId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkTxbyteByContainerId(String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkTxbyteByContainerId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkTxdropByContainerId(String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkTxdropByContainerId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkTxerrorByContainerId(String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkTxerrorByContainerId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkTxpacketByContainerId(String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkTxpacketByContainerId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkRxbyteByTaskId(String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkRxbyteByTaskId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkRxdropByTaskId(String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkRxdropByTaskId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkRxerrorByTaskId(String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkRxerrorByTaskId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkRxpacketByTaskId(String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkRxpacketByTaskId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkTxbyteByTaskId(String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkTxbyteByTaskId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkTxdropByTaskId(String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkTxdropByTaskId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkTxerrorByTaskId(String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkTxerrorByTaskId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkTxpacketByTaskId(String clusterId, String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkTxpacketByTaskId(clusterId, hostIp, containerId, startTime, time, limit);
	}
}
