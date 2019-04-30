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
package com.nexcloud.api.rabitmq;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nexcloud.fullfillment.host.domain.CPU;
import com.rabbitmq.client.BlockedListener;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ShutdownListener;
import com.rabbitmq.client.ShutdownSignalException;


/**
 * Hello world!
 *
 */
public class Publish extends Thread 
{
	private static Publish thisObj 						= null;
	static final Logger 	logger 						= LoggerFactory.getLogger(Publish.class);
	private Connection connection 						= null;
	
	private static List<Map<String, String>> list		= null;
	
	public static String rabbitMqIP						= null;
	public static String rabbitMqPort					= null;
	
	public static String rabbitMqUser					= null;
	public static String rabbitMqPassword				= null;
	
	public Publish(){ 
		start();
	}
	public synchronized static Publish getInstance( ){
		if ( thisObj == null ){
			logger.debug("Rabbit Publish!!!!!!!!!!!!!!!");
			try {
				thisObj = new Publish();
			}catch(IndexOutOfBoundsException ie){
				logger.error("Publish Class getInstance IndexOutOfBoundsException Error = " +ie);
			}catch(NullPointerException  ne){
				logger.error("Publish Class getInstance NullPointerException Error = " +ne);
			} catch(Exception e) {
				logger.error("Publish Class getInstance Exception Error = " + e);
			}	
		}	
		return thisObj; 		
	}
	
	public synchronized static Publish getInstance( String rabbitMqIP, String rabbitMqPort, String rabbitMqUser, String rabbitMqPassword ){
		if ( thisObj == null ){
			logger.debug("Rabbit Publish!!!!!!!!!!!!!!!");
			try {
				thisObj = new Publish();
				list	= new ArrayList<Map<String, String>>();
				
				thisObj.rabbitMqIP			= rabbitMqIP;
				thisObj.rabbitMqPort		= rabbitMqPort;
				thisObj.rabbitMqUser		= rabbitMqUser;
				thisObj.rabbitMqPassword	= rabbitMqPassword;
				
				thisObj.connection(rabbitMqIP, rabbitMqPort, rabbitMqUser, rabbitMqPassword);
				while(true)
				{
					if( thisObj.connection == null || !thisObj.connection.isOpen() )
						thisObj.connection(rabbitMqIP, rabbitMqPort, rabbitMqUser, rabbitMqPassword);
					else
						break;
				}
				
			}catch(IndexOutOfBoundsException ie){
				logger.error("Publish Class getInstance IndexOutOfBoundsException Error = " +ie);
			}catch(NullPointerException  ne){
				logger.error("Publish Class getInstance NullPointerException Error = " +ne);
			} catch(Exception e) {
				logger.error("Publish Class getInstance Exception Error = " + e);
			}	
		}	
		return thisObj; 		
	}
	
	public synchronized void connection( String rabbitMqIP, String rabbitMqPort,String rabbitMqUser, String rabbitMqPassword )
	{
		try{
			logger.error("Rabbit MQ Connection!!!!!!!!!!!!!!!");
			
			ConnectionFactory factory 	= new ConnectionFactory();
			factory.setHost(rabbitMqIP);
			factory.setPort(Integer.parseInt(rabbitMqPort)); //5672
			factory.setUsername(rabbitMqUser);
			factory.setPassword(rabbitMqPassword);
			
			factory.setAutomaticRecoveryEnabled(true);
			factory.setRequestedHeartbeat(60);
	        
			factory.setConnectionTimeout(1000);
	        
	        factory.setRequestedChannelMax(0);
	        factory.setRequestedFrameMax(0);
			
			if( connection != null )
			{
				connection.close();
				connection				= null;
			}
			connection 					= factory.newConnection();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public synchronized void put( String channelName, String message )
	{
		try{
			Map<String,String> map	= new HashMap<String,String>();
			map.put(channelName, message);
			list.add(map);
			map						= null;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Resource Data Get
	 * @param key
	 * @return
	 */
	public synchronized Map<String,String> get( )
	{
		try{
			if( list.size() > 0)
				return list.remove(0);
			else
				return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void run()
	{
		Map<String,String> data				= null;
        while(true)
        {
			try
			{
				data						= get();
				if( data != null )
				{
					for( String key : data.keySet() )
						this.send(key, data.get(key));
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        }
	}
	
	
	/**
	 * 
	 * @param rabbitMqIP 	: Rabbit MQ Connection IP
	 * @param rabbitMqPort	: Rabbit MQ Connection Port
	 * @param topic			: Rabbit MQ Channel Name
	 */
	public void send( String channelName, String message )
	{
		try{
			if( connection == null || !connection.isOpen() )
				connection( rabbitMqIP, rabbitMqPort, rabbitMqUser, rabbitMqPassword );
			
			connection.addBlockedListener(new BlockedListener() {
			    public void handleBlocked(String reason) throws IOException {
			        logger.error("Publisher RabbitMQ Blocked::"+reason);
			    }

			    public void handleUnblocked() throws IOException {
			        // Connection is now unblocked
			    }
			});
			
			connection.addShutdownListener(new ShutdownListener() {
			    @Override
			    public void shutdownCompleted(ShutdownSignalException cause) {
			    	logger.error("Publisher RabbitMQ Connection Shutdown::", cause);
			    }
			});
			
			Channel channel = connection.createChannel();
			
			//logger.error("Channel name::"+channelName);

			Map<String, Object> args = new HashMap<String, Object>();
			args.put("x-queue-mode", "lazy");
			channel.queueDeclare(channelName, false, false, false, args);
			//channel.queueDeclare(channelName, false, false, true, null);
			channel.basicPublish("", channelName, null, message.getBytes());
			
			channel.queueDeclare(channelName+"_work", false, false, false, args);
			//channel.queueDeclare(channelName+"_work", false, false, true, null);
			channel.basicPublish("", channelName+"_work", null, message.getBytes());

			channel.close();
		}catch(Exception e){
			try {
				connection.close();
				connection  = null;
			} catch (IOException e1) {
			}
			
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param rabbitMqIP 	: Rabbit MQ Connection IP
	 * @param rabbitMqPort	: Rabbit MQ Connection Port
	 * @param topic			: Rabbit MQ Channel Name
	 */
	public void send1( String rabbitMqIP, String rabbitMqPort, String channelName, String message )
	{
		Connection connection 	= null;
		Channel channel 		= null; 	
		try{
			logger.debug("Publish init 실행!!!!!!!!!!!!!!!"+channelName);
			
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(rabbitMqIP);
			factory.setPort(Integer.parseInt(rabbitMqPort)); //5672
			factory.setConnectionTimeout(1000*10);
			
			connection = factory.newConnection();
			channel = connection.createChannel();

			channel.queueDeclare(channelName, false, false, true, null);
			channel.basicPublish("", channelName, null, message.getBytes());
			
			channel.queueDeclare(channelName+"_work", false, false, true, null);
			channel.basicPublish("", channelName+"_work", null, message.getBytes());
		}catch(Exception e){
			//e.printStackTrace();
		}finally{
			try {
				if( channel != null && channel.isOpen() )
					channel.close();
				
				if( connection != null && connection.isOpen() )
					connection.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
