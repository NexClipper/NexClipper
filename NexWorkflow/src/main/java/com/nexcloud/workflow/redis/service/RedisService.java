package com.nexcloud.workflow.redis.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, String> template;
    
    @Resource(name="redisTemplate") 
    private ValueOperations<String, String> valueOps; 
    
    @Resource(name = "redisTemplate") 
    private ListOperations<String, String> listOps; 
    
    @Resource(name = "redisTemplate") 
    private HashOperations<String, String, String> hashOps; 
    
    @Resource(name = "redisTemplate") 
    private SetOperations<String, String> setOps; 
    
    @Resource(name="redisTemplate") 
    private ZSetOperations<String, String> zSetOps;
    
    
    public synchronized void put( String key, String hashKey, String data)
    {
    	hashOps.put(key, hashKey, data);
    }
    
    public synchronized String get( String key, String hashKey)
    {
    	String data = null;
    	try{
    		data = hashOps.get(key, hashKey);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return data;
    }
    
    public void remove( String key, String hashKey )
    {
    	hashOps.delete(key, hashKey);
    }
}
