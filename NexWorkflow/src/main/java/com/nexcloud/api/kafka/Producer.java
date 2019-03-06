package com.nexcloud.api.kafka;

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
	private static Producer thisObj 	= null;
	static final Logger 	logger 		= LoggerFactory.getLogger(Producer.class);
	
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
