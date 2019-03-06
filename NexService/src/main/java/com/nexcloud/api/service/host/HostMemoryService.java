package com.nexcloud.api.service.host;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nexcloud.mdb.client.redis.RedisClient;
import com.nexcloud.tsdb.adapter.manager.HostMemoryAdapterManager;
import com.nexcloud.util.Util;
import com.nexcloud.util.consts.HTTP;
import com.nexcloud.util.consts.REDIS;
import com.nexcloud.util.response.Success;
@Service
public class HostMemoryService {

	@Autowired private HostMemoryAdapterManager adapterManager;
	
	public String getActualFreeMemory(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getActualFreeMemory(hostIp, startTime, time, limit);
	}
	
	public String getActualUsedMemory(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getActualUsedMemory(hostIp, startTime, time, limit);
	}
	
	public String getFreeMemory(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getFreeMemory(hostIp, startTime, time, limit);
	}
	
	public String getFreePercentMemory(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getFreePercentMemory(hostIp, startTime, time, limit);
	}
	
	public String getUsedMemory(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getUsedMemory(hostIp, startTime, time, limit);
	}
	
	public String getUsedPercentMemory(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getUsedPercentMemory(hostIp, startTime, time, limit);
	}
	
	public String getTotalMemory(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getTotalMemory(hostIp, startTime, time, limit);
	}
	
	public String getSwapFreeMemory(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getSwapFreeMemory(hostIp, startTime, time, limit);
	}
	
	public String getSwapFreePercentMemory(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getSwapFreePercentMemory(hostIp, startTime, time, limit);
	}
	
	public String getSwapUsedMemory(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getSwapUsedMemory(hostIp, startTime, time, limit);
	}
	
	public String getSwapUsedPercentMemory(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getSwapUsedPercentMemory(hostIp, startTime, time, limit);
	}
	
	public String getSwapTotalMemory(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getSwapTotalMemory(hostIp, startTime, time, limit);
	}
	
	public String getSwapPagein(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getSwapPagein(hostIp, startTime, time, limit);
	}
	
	public String getSwapPageout(String hostIp, String startTime, String time, int limit) {
		return adapterManager.adapter().getSwapPageout(hostIp, startTime, time, limit);
	}
}
