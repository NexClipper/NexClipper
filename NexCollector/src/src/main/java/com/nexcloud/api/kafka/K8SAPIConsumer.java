package com.nexcloud.api.kafka;
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

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nexcloud.util.Util;

/**
 * Hello world!
 *
 */
public class K8SAPIConsumer 
{
	private static K8SAPIConsumer thisObj 	= null;
	static final Logger 	logger 		= LoggerFactory.getLogger(K8SAPIConsumer.class);
	
	private KafkaConsumer<String, String> consumer = null;
	
	public synchronized static K8SAPIConsumer getInstance(){
		if ( thisObj == null ){
			logger.debug("K8SAPI Consumer!!!!!!!!!!!!!!!");
			try {
				thisObj = new K8SAPIConsumer();
			}catch(IndexOutOfBoundsException ie){
				logger.error("Consumer Class getInstance IndexOutOfBoundsException Error = " +ie);
			}catch(NullPointerException  ne){
				logger.error("Consumer Class getInstance NullPointerException Error = " +ne);
			} catch(Exception e) {
				logger.error("Consumer Class getInstance Exception Error = " + e);
			}	
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
	public boolean init( String kafka_zookeeper, String kafka_host, String kafka_port, String topic, String kafka_group )
	{
		Properties 						props 		= new Properties();
		
		if( consumer != null )
			return true;
		
		try{
			  logger.error("K8SAPI Consumer init 실행["+kafka_group+","+kafka_host+":"+kafka_port+","+kafka_zookeeper+"]");
			
			  props.put("group.id", kafka_group);
			  props.put("bootstrap.servers", kafka_host+":"+kafka_port);
			  props.put("zookeeper.connect", kafka_zookeeper);
			  props.put("enable.auto.commit", "true");
			  props.put("auto.offset.reset", "earliest");
			  props.put("consumer.timeout.ms", "5000");
			  props.put("auto.commit.interval.ms", "1000");
			  props.put("max.poll.records", 1000);

			  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			  props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			  
			  consumer = new KafkaConsumer<String, String>(props);
		      consumer.subscribe(Arrays.asList(topic));
		      return true;
		}catch(Exception e){
			e.printStackTrace();
			
			return false;
		}
	}
	
	/**
	 * Kafka Producer세팅
	 * 
	 * @param kafka_host 	: kafaka 접속 IP 또는 도메인
	 * @param kafka_port	: Kafka port
	 * @param topic			: Kafka topic	
	 * @param message		: 전송할 message
	 */
	public boolean init( String kafka_zookeeper, String kafka_host, String kafka_port, List<String> topic, String kafka_group )
	{
		Properties 						props 		= new Properties();
		
		if( consumer != null )
			return true;
		
		try{
			  logger.error("K8SAPI Consumer init 실행["+kafka_group+","+kafka_host+":"+kafka_port+","+kafka_zookeeper+"]");
			
			  props.put("group.id", kafka_group);
			  props.put("bootstrap.servers", kafka_host+":"+kafka_port);
			  props.put("zookeeper.connect", kafka_zookeeper);
			  props.put("enable.auto.commit", "true");
			  props.put("auto.offset.reset", "earliest");
			  props.put("consumer.timeout.ms", "5000");
			  props.put("auto.commit.interval.ms", "1000");
			  props.put("max.poll.records", 1000);

			  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			  props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			  
			  consumer = new KafkaConsumer<String, String>(props);
		      consumer.subscribe(topic);
		      return true;
		}catch(Exception e){
			e.printStackTrace();
			
			return false;
		}
	}
	
	
	/**
	 * Kafka Producer세팅
	 * 
	 * @param kafka_host 	: kafaka 접속 IP 또는 도메인
	 * @param kafka_port	: Kafka port
	 * @param topic			: Kafka topic	
	 * @param message		: 전송할 message
	 */
	public ConsumerRecords<String, String> read(  )
	{
		ConsumerRecords<String, String> records 	= null;
		try{
			//logger.error("Mesos Consumer read 실행!!!!!!!!!!!!!!!");
		    records = consumer.poll(1000);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(Util.makeStackTrace(e));
		}
		
		return records;
	}
	
}
