package com.nexcloud.mdb.client.redis;
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
    	try {
    		hashOps.put(key, hashKey, data);
		} catch (Exception e) {
			logger.debug("[Redis Put Error key ["+ key +"], hashKey["+ hashKey +"], data["+ data +"]");
		}
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
    		logger.error("[Redis Get Error]"
	    			+ "\n KEY [" + key + "]" + "\n HASH KEY ["+ hashKey +"]");
    		
    		return gson.toJson(new Error(HTTP.CODE.EXCEPTION, HTTP.MESSAGE.EXCEPTION, Util.makeStackTrace(e)));
    	}
    }
}
