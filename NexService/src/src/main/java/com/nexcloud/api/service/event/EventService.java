package com.nexcloud.api.service.event;

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
public class EventService {
	@Autowired private RedisClient redisClient;
	
	public String getHosts() {
		Gson gson 					= new GsonBuilder().setPrettyPrinting().serializeNulls().create();
		String data					= redisClient.get(REDIS.HASH.HOST, REDIS.KEY.LIST);
		Success success				= Util.JsonTobean(data, Success.class);
		
		List<String> hosts			= new ArrayList<String>();
		
		// Redis Data get Success
		if( success.getResponseCode() == HTTP.CODE.OK && success.getResponseBody() != null )
		{
		
			List<String> ips		= gson.fromJson(data, new TypeToken<List<String>>(){}.getType());
			ips						= Util.asc(ips);
			
			for( String ip : ips )
			{
				data				= redisClient.get(REDIS.HASH.HOST, ip);
				success				= Util.JsonTobean(data, Success.class);
				if( success.getResponseCode() == HTTP.CODE.OK && success.getResponseBody() != null )
					hosts.add(success.getResponseBody());
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
	
	public String getHost(String hostIp) {
		return redisClient.get(REDIS.HASH.HOST, hostIp);
		
	}
}
