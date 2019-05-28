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
package com.nexcloud.api.redis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class RedisCluster {
static final Logger logger = LoggerFactory.getLogger(RedisCluster.class);
	private static RedisCluster thisObj = null;
	
	private long actor_start; 
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
		else{
			if(thisObj.pool.isClosed())
				thisObj.init(host, port);
			else if( thisObj.pool.getNumActive() <= 0 )
			{
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return thisObj; 		
	}
	

	public synchronized void init( String host, int port )
	{
		try{
			System.out.println("host::"+host+",port::"+port);

			JedisPoolConfig config	 = new JedisPoolConfig();
			config.setMaxTotal(1000);
            config.setMaxIdle(500);
            config.setMinIdle(2);
            config.setMaxWaitMillis(100);
            config.setTestWhileIdle(true);
            config.setTestOnBorrow(true);
            config.setTestOnReturn(true);
            config.setMinEvictableIdleTimeMillis(10000);
            config.setTimeBetweenEvictionRunsMillis(5000);
            config.setNumTestsPerEvictionRun(10);

            pool	= new JedisPool(config, host, port, 1000);
			
			//pool	= new JedisPool(new GenericObjectPoolConfig(), host,   port, 1000);
			System.out.println("Redis Connection Success!!"+pool.getNumActive());
			
		}catch( Exception e ){
			System.out.println("Redis Connection Fail!!");
			
			e.printStackTrace();
		}
	}
	
	public synchronized void put( String key, String field, String data)
    {
		Jedis jedis 		= null;
		try{
			actor_start		= System.currentTimeMillis();
			//System.out.println("Redis Pool "+pool.getNumWaiters());
			jedis = pool.getResource();
			jedis.hset(key, field, data);
			
			if( jedis != null ){
				jedis.close();
			}
			//pool.returnResource(jedis);
		}catch( Exception e ){
			logger.error("Redis Put Exception["+key+"]["+field+"] : "+(System.currentTimeMillis() - actor_start), e);
			if( jedis != null ){
				jedis.close();
			}
		}
    }
    
    public synchronized String get( String key, String field)
    {
    	Jedis jedis 		= null;
    	String data = null;
    	try{
    		actor_start		= System.currentTimeMillis();
    		//System.out.println("Redis Pool "+pool.getNumWaiters());
    		jedis = pool.getResource();
    		data = jedis.hget(key, field);
    		
    		if( jedis != null ){
    			jedis.close();
    		}
    		//pool.returnResource(jedis);
    	}catch( Exception e ){
    		logger.error("Redis Get Exception["+key+"]["+field+"] : "+(System.currentTimeMillis() - actor_start), e);
    		if( jedis != null ){
    			jedis.close();
    		}
		}
    	return data;
    }
    
    public synchronized void remove( String key, String field)
    {
    	Jedis jedis 		= null;
    	try{
    		actor_start		= System.currentTimeMillis();
    		jedis = pool.getResource();
    		jedis.hdel(key, field);
    		
    		if( jedis != null ){
    			jedis.close();
    		}
    		
    	}catch(Exception e){
    		logger.error("Redis Remove Exception["+key+"]["+field+"] : "+(System.currentTimeMillis() - actor_start), e);
    		if( jedis != null ){
    			jedis.close();
    		}
    	}
    }
}
