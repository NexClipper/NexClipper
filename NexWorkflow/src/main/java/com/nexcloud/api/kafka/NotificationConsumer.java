package com.nexcloud.api.kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class NotificationConsumer 
{
	private static NotificationConsumer thisObj 	= null;
	static final Logger 	logger 		= LoggerFactory.getLogger(NotificationConsumer.class);
	
	private KafkaConsumer<String, String> consumer = null;
	
	public synchronized static NotificationConsumer getInstance(){
		if ( thisObj == null ){
			logger.debug("Kafka Consumer!!!!!!!!!!!!!!!");
			try {
				thisObj = new NotificationConsumer();
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
			  logger.debug("Consumer init 실행!!!!!!!!!!!!!!!");
			  
			  int bufferSize		= 33554432;
			
			  props.put("group.id", kafka_group);
			  props.put("bootstrap.servers", kafka_host+":"+kafka_port);
			  props.put("zookeeper.connect", kafka_zookeeper);
			  props.put("enable.auto.commit", "true");
			  props.put("auto.offset.reset", "earliest");
			  props.put("consumer.timeout.ms", "5000");
			  props.put("auto.commit.interval.ms", "1000");
			  
			  //props.put("fetch.message.max.bytes", bufferSize );
			  
			  
			  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			  props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			  
//			  /System.out.println("Consumer Properties::"+ props);
			  
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
	public ConsumerRecords<String, String> read(  )
	{
		ConsumerRecords<String, String> records 	= null;
		Properties 						props 		= new Properties();
		try{
			  logger.debug("Consumer read 실행!!!!!!!!!!!!!!!");
		      records = consumer.poll(100);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return records;
	}
	
}
