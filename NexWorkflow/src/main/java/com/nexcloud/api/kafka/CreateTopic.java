package com.nexcloud.api.kafka;

import java.util.Properties;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.utils.ZKStringSerializer$;
import kafka.utils.ZkUtils;

/**
 * Hello world!
 *
 */
public class CreateTopic 
{
	private static CreateTopic thisObj 	= null;
	
	static final Logger 	logger 		= LoggerFactory.getLogger(CreateTopic.class);
	
	public synchronized static CreateTopic getInstance(){
		if ( thisObj == null ){
			logger.debug("CreateTopic!!!!!!!!!!!!!!!");
			try {
				thisObj = new CreateTopic();
			}catch(IndexOutOfBoundsException ie){
				logger.error("CreateTopic Class getInstance IndexOutOfBoundsException Error = " +ie);
			}catch(NullPointerException  ne){
				logger.error("CreateTopic Class getInstance NullPointerException Error = " +ne);
			} catch(Exception e) {
				logger.error("CreateTopic Class getInstance Exception Error = " + e);
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
	public boolean create( String kafka_zookeeper, String kafka_host, String kafka_port, String topic )
	{
		ZkClient zkClient = null;
        ZkUtils zkUtils = null;
		try{
			
			int sessionTimeOutInMs = 15 * 1000; // 15 secs
            int connectionTimeOutInMs = 10 * 1000; // 10 secs
			zkClient = new ZkClient(kafka_zookeeper, sessionTimeOutInMs, connectionTimeOutInMs, ZKStringSerializer$.MODULE$);
			zkUtils = new ZkUtils(zkClient, new ZkConnection(kafka_zookeeper), false);
			
			System.out.println("isTopic ["+topic+"]");
			if(!AdminUtils.topicExists(zkUtils, topic))
			{	
				System.out.println("Create ["+topic+"]");
				
				int noOfPartitions = 9;
	            int noOfReplication = 3;
	            
	            AdminUtils.createTopic(zkUtils, topic, noOfPartitions, noOfReplication, new Properties(), RackAwareMode.Enforced$.MODULE$);
	            //AdminUtils.createTopic(zkUtils, topic, noOfPartitions, noOfReplication, new Properties(), new RackAwareMode.Disabled$());
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
			return false;
		}finally{
			zkUtils.close();
			zkClient.close();
			
			zkUtils = null;
			zkClient = null;
		}
		
		return true;
	}
}
