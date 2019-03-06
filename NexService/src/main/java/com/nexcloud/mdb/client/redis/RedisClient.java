package com.nexcloud.mdb.client.redis;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.nexcloud.util.Util;
import com.nexcloud.util.consts.HTTP;
import com.nexcloud.util.response.Error;
import com.nexcloud.util.response.Success;

@Service
public class RedisClient {
	private Gson gson = new Gson();
	static final Logger logger = LoggerFactory.getLogger(RedisClient.class);
	
	@Resource(name = "redisTemplate") 
	private HashOperations<String, String, String> hashOps; 
    
    public void put( String key, String hashKey, String data) {
    	hashOps.put(key, hashKey, data);
    }
    
    public String get( String key, String hashKey) {
    	try{
	    	logger.debug("[Redis Request]"
	    			+ "\n KEY [" + key + "]" + "\n HASH KEY ["+ hashKey +"]");
			
	    	String data = null;
	    	data = hashOps.get(key, hashKey);
	    	
	    	logger.debug("[Redis response]" + "\n Response [" + data + "]");
			return gson.toJson(new Success(HTTP.CODE.OK, HTTP.MESSAGE.OK, "Redis", data));
    	}catch(Exception e){
    		logger.error("[Redis Request]"
	    			+ "\n KEY [" + key + "]" + "\n HASH KEY ["+ hashKey +"]");
    		
    		return gson.toJson(new Error(HTTP.CODE.EXCEPTION, HTTP.MESSAGE.EXCEPTION, Util.makeStackTrace(e)));
    	}
    }
}
