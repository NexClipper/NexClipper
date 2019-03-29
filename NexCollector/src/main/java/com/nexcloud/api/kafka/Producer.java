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
package com.nexcloud.api.kafka;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Hello world!
 *
 */
public class Producer 
{
	private static Producer thisObj 					= null;
	static final Logger 	logger 						= LoggerFactory.getLogger(Producer.class);
	private static Map<String, String> map 				= new HashMap<String, String>();
	private KafkaProducer<String, String> 	producer	= null;
	private Properties 		props 						= null; 
	
	private int maxRetries	 							= 0;
	private int batchSize								= 16384;
	private int lingerTime								= 1;
	private int bufferSize								= 33554432;
	private Random rnd 									= null;
	
	private int noOfPartitions 							= 9;
	private int noOfReplication 						= 3;
	
	public synchronized static Producer getInstance(){
		if ( thisObj == null ){
			logger.debug("Kafka Producer!!!!!!!!!!!!!!!");
			try {
				thisObj = new Producer();
			}catch(IndexOutOfBoundsException ie){
				logger.error("Producer Class getInstance IndexOutOfBoundsException Error = " +ie);
			}catch(NullPointerException  ne){
				logger.error("Producer Class getInstance NullPointerException Error = " +ne);
			} catch(Exception e) {
				logger.error("Producer Class getInstance Exception Error = " + e);
			}	
		}	
		
		return thisObj; 		
	}
	
	public synchronized static Producer getInstance(String kafka_zookeeper, String kafka_host, String kafka_port, String topic ){
		if ( thisObj == null ){
			logger.debug("Kafka Producer!!!!!!!!!!!!!!!");
			try {
				CreateTopic.getInstance().create(kafka_zookeeper, kafka_host, kafka_port, topic);
				thisObj = new Producer();
			}catch(IndexOutOfBoundsException ie){
				logger.error("Producer Class getInstance IndexOutOfBoundsException Error = " +ie);
			}catch(NullPointerException  ne){
				logger.error("Producer Class getInstance NullPointerException Error = " +ne);
			} catch(Exception e) {
				logger.error("Producer Class getInstance Exception Error = " + e);
			}	
		}	
		
		// Check Exist topic and create topic
		if( map.get(topic) == null )
		{
			CreateTopic.getInstance().create(kafka_zookeeper, kafka_host, kafka_port, topic);
			map.put(topic, topic);
		}
		return thisObj; 		
	}
	
	/**
	 * Kafka Producer세팅
	 * 
	 * @param kafka_host 	: kafaka 접속 IP 또는 도메인
	 * @param kafka_port	: Kafka port
	 * @param topic			: Kafka topic	
	 * @param message		: 전송할 message
	 */
	public void send( String kafka_host, String kafka_port, String topic, String message )
	{
		Properties 		props 		= new Properties();
		KafkaProducer<String, String> 	producer	= null;
		try{
			logger.debug("Producer init 실행!!!!!!!!!!!!!!!");
			
			int maxRetries	 	= 3;
			int batchSize		= 1638;
			int lingerTime		= 1;
			int bufferSize		= 33554432;
			Random rnd 			= new Random();
		  
			// Kafka initial 정보 세팅
			props.put("bootstrap.servers", kafka_host+":"+kafka_port);
			props.put("acks", "all");
			props.put("retries", maxRetries);
			props.put("batch.size", batchSize);
			props.put("linger.ms", lingerTime);
			props.put("buffer.memory", bufferSize);
			props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
			props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
			
			//System.out.println("Props::"+props);
			producer	=	new KafkaProducer<String, String>(props);
			int partition = rnd.nextInt(4);
			producer.send(new ProducerRecord<String, String>(topic, Integer.toString(partition), message));
			
			producer.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
