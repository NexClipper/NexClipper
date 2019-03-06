package com.nexcloud.api.service.host;

import java.util.ArrayList;
import java.util.List;

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
	
	public String getAllDocker( ) {
		Gson gson 					= new GsonBuilder().setPrettyPrinting().serializeNulls().create();
		String data					= redisClient.get(REDIS.HASH.HOST, REDIS.KEY.LIST);
		Success success				= Util.JsonTobean(data, Success.class);
		
		List<Object> dockers			= new ArrayList<Object>();
		
		// Redis Data get Success
		if( success.getResponseCode() == HTTP.CODE.OK && success.getResponseBody() != null )
		{
		
			List<String> ips		= gson.fromJson(success.getResponseBody(), new TypeToken<List<String>>(){}.getType());
			ips						= Util.asc(ips);
			
			for( String ip : ips )
			{
				data				= redisClient.get(REDIS.HASH.DOCKER, ip);
				success				= Util.JsonTobean(data, Success.class);
				if( success.getResponseCode() == HTTP.CODE.OK && success.getResponseBody() != null )
					dockers.add(gson.fromJson(success.getResponseBody(), new TypeToken<Object>(){}.getType()));
				else
					return data;
			}
			
			return Util.beanToJson(new Success(HTTP.CODE.OK, HTTP.MESSAGE.OK, "Redis", Util.beanToJson(dockers)));
		}
		else
		{
			return data;
		}
	}

	public String getDocker(String hostIp) {
		return redisClient.get(REDIS.HASH.DOCKER, hostIp);
	}
	
	public String getDockerCpuUsedByContainerId(String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerCpuUsedByContainerId(hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerCpuUsedByTaskId(String hostIp, String taskId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerCpuUsedByTaskId(hostIp, taskId, startTime, time, limit);
	}
	
	public String getDockerMemoryAllocateByContainerId(String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerMemoryAllocateByContainerId(hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerMemoryUsedByteByContainerId(String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerMemoryUsedByteByContainerId(hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerMemoryUsedByteByTaskId(String hostIp, String taskId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerMemoryUsedByteByTaskId(hostIp, taskId, startTime, time, limit);
	}
	
	public String getDockerMemoryUsedPercentByContainerId(String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerMemoryUsedPercentByContainerId(hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerMemoryUsedPercentByTaskId(String hostIp, String taskId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerMemoryUsedPercentByTaskId(hostIp, taskId, startTime, time, limit);
	}
	
	public String getDockerDiskioReadByContainerId(String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerDiskioReadByContainerId(hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerDiskioReadByTaskId(String hostIp, String taskId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerDiskioReadByTaskId(hostIp, taskId, startTime, time, limit);
	}
	
	public String getDockerDiskioWriteByContainerId(String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerDiskioWriteByContainerId(hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerDiskioWriteByTaskId(String hostIp, String taskId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerDiskioWriteByTaskId(hostIp, taskId, startTime, time, limit);
	}
	
	public String getDockerNetworkRxbyteByContainerId(String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkRxbyteByContainerId(hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkRxdropByContainerId(String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkRxdropByContainerId(hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkRxerrorByContainerId(String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkRxerrorByContainerId(hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkRxpacketByContainerId(String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkRxpacketByContainerId(hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkTxbyteByContainerId(String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkTxbyteByContainerId(hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkTxdropByContainerId(String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkTxdropByContainerId(hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkTxerrorByContainerId(String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkTxerrorByContainerId(hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkTxpacketByContainerId(String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkTxpacketByContainerId(hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkRxbyteByTaskId(String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkRxbyteByTaskId(hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkRxdropByTaskId(String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkRxdropByTaskId(hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkRxerrorByTaskId(String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkRxerrorByTaskId(hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkRxpacketByTaskId(String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkRxpacketByTaskId(hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkTxbyteByTaskId(String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkTxbyteByTaskId(hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkTxdropByTaskId(String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkTxdropByTaskId(hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkTxerrorByTaskId(String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkTxerrorByTaskId(hostIp, containerId, startTime, time, limit);
	}
	
	public String getDockerNetworkTxpacketByTaskId(String hostIp, String containerId, String startTime, String time, int limit) {
		return adapterManager.adapter().getDockerNetworkTxpacketByTaskId(hostIp, containerId, startTime, time, limit);
	}
}
