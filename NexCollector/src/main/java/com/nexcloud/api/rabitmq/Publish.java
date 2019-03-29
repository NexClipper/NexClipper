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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


/**
 * Hello world!
 *
 */
public class Publish 
{
	private static Publish thisObj 						= null;
	static final Logger 	logger 						= LoggerFactory.getLogger(Publish.class);
	
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
	
	/**
	 * 
	 * @param rabbitMqIP 	: Rabbit MQ Connection IP
	 * @param rabbitMqPort	: Rabbit MQ Connection Port
	 * @param topic			: Rabbit MQ Channel Name
	 */
	public void send( String rabbitMqIP, String rabbitMqPort, String channelName, String message )
	{
		try{
			logger.debug("Publish init 실행!!!!!!!!!!!!!!!");
			
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(rabbitMqIP);
			factory.setPort(Integer.parseInt(rabbitMqPort)); //5672
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();

			channel.queueDeclare(channelName, false, false, false, null);
			channel.basicPublish("", channelName, null, message.getBytes());
			
			channel.queueDeclare(channelName+"_work", false, false, false, null);
			channel.basicPublish("", channelName+"_work", null, message.getBytes());

			channel.close();
			connection.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
