package com.nexcloud.api.redis;
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

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nexcloud.util.Util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


public class RedisCluster {
static final Logger logger = LoggerFactory.getLogger(RedisCluster.class);
	private static RedisCluster thisObj = null;
	
	//private Jedis jedis = null;
	private JedisPool pool = null;
	
	public synchronized static RedisCluster getInstance( ){
		if ( thisObj == null ){
			logger.debug("ConfigLoader getInstance 실행!!!!!!!!!!!!!!!");
			try {
				thisObj = new RedisCluster();
			}catch(IndexOutOfBoundsException ie){
				logger.error("Config Class getInstance IndexOutOfBoundsException Error = " +ie);
			}catch(NullPointerException  ne){
				logger.error("Config Class getInstance NullPointerException Error = " +ne);
			} catch(Exception e) {
				logger.error("Config Class getInstance Exception Error = " + e);
			}	
		}	
		
		return thisObj; 		
	}
	
	public synchronized static RedisCluster getInstance( String host, int port ){
		if ( thisObj == null ){
			logger.debug("ConfigLoader getInstance 실행!!!!!!!!!!!!!!!");
			try {
				thisObj = new RedisCluster();
				thisObj.init( host, port);
			}catch(IndexOutOfBoundsException ie){
				logger.error("Config Class getInstance IndexOutOfBoundsException Error = " +ie);
			}catch(NullPointerException  ne){
				logger.error("Config Class getInstance NullPointerException Error = " +ne);
			} catch(Exception e) {
				logger.error("Config Class getInstance Exception Error = " + e);
			}	
		}	
		
		return thisObj; 		
	}
	

	public synchronized void init( String host, int port )
	{
		try{
			System.out.println("host::"+host+",port::"+port);
			
			pool	= new JedisPool(new GenericObjectPoolConfig(), host,   port, 2000);
			//System.out.println("Redis Connection Success!!"+pool.getNumActive());
			
		}catch( Exception e ){
			System.out.println("Redis Connection Fail!!");
			
			e.printStackTrace();
		}
	}
	
	public synchronized void put( String key, String field, String data)
    {
		try{
			//System.out.println("Redis Pool "+pool.getNumWaiters());
			Jedis jedis = pool.getResource();
			jedis.hset(key, field, data);
			
			if( jedis != null ){
				jedis.close();
			}
			//pool.returnResource(jedis);
		}catch( Exception e ){
			System.out.println("Redis Put Error::"+Util.makeStackTrace(e));
	
			e.printStackTrace();

		}
    }
    
    public synchronized String get( String key, String field)
    {
    	String data = null;
    	try{
    		//System.out.println("Redis Pool "+pool.getNumWaiters());
    		Jedis jedis = pool.getResource();
    		data = jedis.hget(key, field);
    		
    		if( jedis != null ){
    			jedis.close();
    		}
    		//pool.returnResource(jedis);
    	}catch( Exception e ){
    		System.out.println("Redis Get Error::"+Util.makeStackTrace(e));
    	
			e.printStackTrace();
	
		}
    	return data;
    }
    
    public synchronized void remove( String key, String field)
    {
    	try{
    		Jedis jedis = pool.getResource();
    		jedis.hdel(key, field);
    	}catch(Exception e){
    		System.out.println("Redis Remove Error::"+Util.makeStackTrace(e));
        	
			e.printStackTrace();
    	}
    }
    
	/*
	public void init( String host, int port )
	{
		try{
			System.out.println("host::"+host+",port::"+port);
			if( jedis == null || !jedis.isConnected() )
			{
				jedis = null;
				jedis = new Jedis(host, port);
				//jedis.auth("1111");    / auth(패스워드)
				System.out.println("Redis Connection Success!!");
			}
			else
			{
				System.out.println("Redis Connection before connect!!");
			}
		}catch( Exception e ){
			if( jedis != null )
				jedis.close();
			jedis = null;
			thisObj = null;
			
			System.out.println("Redis Connection Fail!!");
			
			e.printStackTrace();
		}
	}
	
	public void put( String key, String field, String data)
    {
		try{
			jedis.hset(key, field, data);
		}catch( Exception e ){
			System.out.println("Redis Put Error::"+Util.makeStackTrace(e));
	
			if( jedis != null )
				jedis.close();
			jedis = null;
			thisObj = null;
			e.printStackTrace();

		}
    }
    
    public String get( String key, String field)
    {
    	String data = null;
    	try{
    		data = jedis.hget(key, field);
    	}catch( Exception e ){
    		System.out.println("Redis Get Error::"+Util.makeStackTrace(e));
    	
    		if( jedis != null )
				jedis.close();
    		
			jedis = null;
			thisObj = null;
			e.printStackTrace();
	
		}
    	return data;
    }*/
}
