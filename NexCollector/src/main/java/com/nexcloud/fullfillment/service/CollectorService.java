package com.nexcloud.fullfillment.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nexcloud.api.kafka.Producer;
import com.nexcloud.fullfillment.domain.common.AgentStatus;
import com.nexcloud.fullfillment.domain.common.RequestData;
import com.nexcloud.fullfillment.domain.common.ResponseData;
import com.nexcloud.fullfillment.redis.service.RedisService;
import com.nexcloud.util.Const;
import com.nexcloud.util.Util;

@Service
@Configuration
@PropertySource("classpath:application.properties")
public class CollectorService {
	@Autowired
    private RedisService redisService;
	
	@Value("${kafka.zookeeper}")
	private String kafka_zookeeper;
	
	@Value("${kafka.host}")
	String 		kafka_host;
	
	@Value("${kafka.port}")
	String 		kafka_port;
	
	static final Logger logger = LoggerFactory.getLogger(CollectorService.class);
	
	
	
	/**
	 * Agent수집 데이터 Parser
	 * @param model
	 * @throws Exception
	 */
	public ResponseEntity<ResponseData> exec( RequestData requestData )  throws Exception
	{
		ResponseEntity<ResponseData> response 		= null;
		ResponseData resData						= new ResponseData();

		try{
			// 수집 Type ( cluster, mesos_node, dcos )
			String metric_type						= requestData.getHeader().getMetric_type();
			
			/**
			 * Agent를 이용하여 Validation check
			 * Expire Time & Validation
			 */
			
			String kafka_topic						= null;
			kafka_topic								= metric_type+"_topic";
			
			// Kafka Send
			kafkaSend(kafka_zookeeper, kafka_host, kafka_port, kafka_topic, requestData);
			
			resData.setStatus(Const.OK);
			resData.setMessage(Const.SUCCESS);
			
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			resData.setStatus(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.OK);
			
			//response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	

	
	/**
	 * Agent Validation
	 * @param model
	 * @throws Exception
	 */
	public ResponseEntity<ResponseData> agentValidation( RequestData requestData )  throws Exception
	{
		ResponseEntity<ResponseData> response 		= null;
		ResponseData resData						= new ResponseData();

		try{
			String version							= requestData.getHeader().getVersion();
			String host_ip							= requestData.getHeader().getNode_ip();
			String host_name						= requestData.getHeader().getNode_name();
			
			/**
			 * Agent Status 관리
			 */
			String dataStatus						= redisService.get(Const.AGENT_STATUS, host_ip);
			AgentStatus status						= Util.JsonTobean(dataStatus, AgentStatus.class);
			if( status == null )
			{
				status								= new AgentStatus();
				status.setStart_time(Util.getToday("yyyy-MM-dd HH:mm:ss"));
			}
			
			if( version != null )
				status.setVersion(version);
			
			resData.setStatus(Const.OK);
			resData.setMessage(Const.SUCCESS);
			
			if( Const.INACTIVE.equals(status.getStatus()) )
				status.setStart_time(Util.getToday("yyyy-MM-dd HH:mm:ss"));
			
			status.setHost_ip(host_ip);
			status.setHost_name(host_name);
			status.setStatus(Const.ACTIVE);
			
			
			if( status.getTimestamp() != null )
				status.setTimestamp(new Date().getTime() - status.getLast_update() );
			else
				status.setTimestamp(new Date().getTime());
			
			status.setLast_update(new Date().getTime());
			
			//System.out.println("host_ip::"+host_ip);
			
			redisService.put(Const.AGENT_STATUS, host_ip, Util.beanToJson(status));
			
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			resData.setStatus(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.OK);
		}
		
		return response;
	}
	
	
	/**
	 * Kafka Send
	 */
	public void kafkaSend (String kafka_zookeeper, String kafka_host, String kafka_port, String kafka_topic, RequestData requestData)
	{
		try{
			Producer	prdocuer 		= null;
			prdocuer 					= Producer.getInstance( kafka_zookeeper, kafka_host, kafka_port, kafka_topic );
			prdocuer.send(kafka_host, kafka_port, kafka_topic, Util.beanToJson(requestData));

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
