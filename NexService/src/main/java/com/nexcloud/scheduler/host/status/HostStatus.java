package com.nexcloud.scheduler.host.status;
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
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nexcloud.mdb.client.redis.RedisClient;
import com.nexcloud.util.consts.HTTP;
import com.nexcloud.util.consts.REDIS.HASH;
import com.nexcloud.util.consts.REDIS.KEY;
import com.nexcloud.util.response.Success;

@Component
public class HostStatus {
	@Autowired
	private RedisClient redisClient;
	private Gson gson = new Gson();
	
	public void statusCheck () {
		List<Map<String, Object>> allHostStatus = new ArrayList<>();
		List<String> hostIpList = getHostIpList();
		for (String ip : hostIpList) {
			Map<String, Object> m = new HashMap<>();
			long startTime = System.nanoTime();
			m.put("reachable", pingCheck(ip));
			m.put("responseTime", System.nanoTime() - startTime);
			putStatus(HASH.HOST_STATUS, ip, gson.toJson(m));
			
			m.put("ip", ip);
			allHostStatus.add(m);
		}
		putStatus(HASH.HOST_STATUS, KEY.LIST, gson.toJson(allHostStatus));
	}
	public boolean pingCheck (String ip) {
		try {
			InetAddress tagetIp = InetAddress.getByName(ip);
			return tagetIp.isReachable(2000);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public void putStatus (String hash, String key, String data) {
		redisClient.put(hash, key, data);
	}
	public List<String> getHostIpList () {
		String hostListResponse = redisClient.get(HASH.HOST, KEY.LIST);
		Success success = gson.fromJson(hostListResponse, Success.class);
		List<String> hostIpList = new ArrayList<>();
		if( success.getResponseCode() == HTTP.CODE.OK 
				&& success.getResponseBody() != null ) {
			hostIpList = gson.fromJson(success.getResponseBody(), new TypeToken<List<String>>(){}.getType());
		}
		return hostIpList;
	}
}
