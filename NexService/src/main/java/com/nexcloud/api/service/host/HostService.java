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
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nexcloud.mdb.client.redis.RedisClient;
import com.nexcloud.util.Util;
import com.nexcloud.util.consts.HTTP;
import com.nexcloud.util.consts.REDIS;
import com.nexcloud.util.response.Success;
@Service
public class HostService {
	@Autowired private RedisClient redisClient;
	
	public String getHosts(String clusterId) {
		Gson gson 					= new GsonBuilder().setPrettyPrinting().serializeNulls().create();
		String data					= redisClient.get(clusterId + "_" + REDIS.HASH.HOST, REDIS.KEY.LIST);
		Success success				= Util.JsonTobean(data, Success.class);
		
		List<Object> hosts			= new ArrayList<Object>();
		
		// Redis Data get Success
		if( success.getResponseCode() == HTTP.CODE.OK && success.getResponseBody() != null )
		{
			List<String> ips		= gson.fromJson(success.getResponseBody(), new TypeToken<List<String>>(){}.getType());
			ips						= Util.asc(ips);
			
			for( String ip : ips )
			{
				data				= redisClient.get(clusterId + "_" + REDIS.HASH.HOST, ip);
				success				= Util.JsonTobean(data, Success.class);
				if( success.getResponseCode() == HTTP.CODE.OK && success.getResponseBody() != null )
					hosts.add(gson.fromJson(success.getResponseBody(), new TypeToken<Object>(){}.getType()));
				else
					return data;
			}
			
			return Util.beanToJson(new Success(HTTP.CODE.OK, HTTP.MESSAGE.OK, "Redis", Util.beanToJson(hosts)));
		}
		else
		{
			return data;
		}
	}
	
	public String getHost(String clusterId,String hostIp) {
		return redisClient.get(clusterId + "_" + REDIS.HASH.HOST, hostIp);
		
	}
	
	public String getIps(String clusterId ) {
		return redisClient.get(clusterId + "_" + REDIS.HASH.HOST, REDIS.KEY.LIST);
		
	}
}
