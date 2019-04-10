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
package com.nexcloud.workflow.service;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nexcloud.agent.domain.AgentStatus;
import com.nexcloud.api.kafka.NotificationConsumer;
import com.nexcloud.db.domain.Rule;
import com.nexcloud.rule.domain.Notification;
import com.nexcloud.util.Const;
import com.nexcloud.util.Util;
import com.nexcloud.workflow.host.domain.Host;
import com.nexcloud.workflow.incident.domain.Assurance;
import com.nexcloud.workflow.incident.workflow.CustomWorkflow;
import com.nexcloud.workflow.incident.workflow.NoramlWorkflow;
import com.nexcloud.workflow.k8s.domain.Item;
import com.nexcloud.workflow.k8s.domain.K8SAPI;
import com.nexcloud.workflow.k8s.domain.K8SMain;
import com.nexcloud.workflow.mapper.DBMapper;
import com.nexcloud.workflow.redis.service.RedisService;

@Service
@Configuration
@PropertySource("classpath:application.properties")
public class WorkflowService {

	@Autowired
	DBMapper dbMapper;
	
	@Autowired
    private RedisService redisService;
	
	@Value("${spring.kafka.zookeeper}")
	private String kafka_zookeeper;
	
	@Value("${spring.kafka.host}")
	private String kafka_host;
	
	@Value("${spring.kafka.port}")
	private String kafka_port;
	
	@Value("${spring.influxdb.endpoint}")
	private String influxdb_endpoint;
	
	@Value("${spring.broker}")
	private String broker;
	
	@Value("${spring.rabbitmq.host}")
	private String rabbitmq_host;
	
	@Value("${spring.rabbitmq.port}")
	private String rabbitmq_port;

	static final Logger logger = LoggerFactory.getLogger(WorkflowService.class);

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////// Crawling Start //////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Rule set workflow Engine
	 * @throws Exception
	 */
	public void ruleSetWorkflowEngine() throws Exception {
		try {
			
			/**
			 * Agent List
			 */
			Map<String, Double> nodeCPUMap 			= new HashMap<String, Double>();
			Map<String, String> nodeMap				= new HashMap<String, String>();
			Gson gson 								= new GsonBuilder().setPrettyPrinting().serializeNulls().create();
			List<String> ips					  	= null;
			String data								= null;
			
			/**
			 * 각각의 Agent서 가지고 있는 Node, Task정보 세팅
			 */
			// Email 정보 조회
			String emails							= dbMapper.selectEmail(  );
			if( emails == null )
				emails								= "";
			redisService.put(Const.EMAIL, Const.LIST, emails);
			
			/**
			 * 특정 Agent의 Node List조회
			 */
			data									= redisService.get(Const.HOST, Const.LIST);
			ips					  				= gson.fromJson(data, new TypeToken<List<String>>(){}.getType());
			
			if( data != null && !"".equals(data) )
			{
				for( String ip : ips )
				{
					/**
					 * Task별 Node IP 정보 획득
					 */
					data							= redisService.get(Const.HOST, ip);
					
					// Redis에 있는 Node 정보 조회
					Host host						= Util.JsonTobean(data, Host.class);
					if( host == null )
						host						= new Host();
					
					nodeCPUMap.put( ip,Double.parseDouble(Integer.toString(host.getCpus().size())));
	
					nodeMap.put(host.getHost_name(), ip);
				}
			}
			//////////////////////////////////////////////////////////////

			Assurance assurance					= new Assurance();
			assurance.setNodeCPU(nodeCPUMap);
			assurance.setNodeIP(nodeMap);


			// DB에 등록되어 있는 룰정보 조회
			Map<String, String> params				= new HashMap<String, String>();
			
			// 일반적인 Assurance Workflow Engine Logic ( 전체 처리가능할 rule 조회 )
			{
				params.put("new_engine", "N");
				List<Rule> rules					= dbMapper.selectRule(params);
								
				assurance.setRules(rules);
				redisService.put(Const.INCIDENT, Const.ASSURANCE_NORMAL, Util.beanToJson(assurance));
				
				if( !NoramlWorkflow.getInstance().isProcessing() )
				{
					System.out.println("NoramlWorkflow Start....");
					NoramlWorkflow.getInstance().goRunning(influxdb_endpoint, broker, rabbitmq_host, rabbitmq_port, kafka_host, kafka_port, redisService);
				}
				else
				{
					if( NoramlWorkflow.getInstance( ).getState() == State.TERMINATED )
						NoramlWorkflow.getInstance( ).goRunning(influxdb_endpoint, broker, rabbitmq_host, rabbitmq_port, kafka_host, kafka_port, redisService);
				}
			}
			
			// Custom Assurance Workflow Engine Logic ( 전체 처리가능할 rule 조회 )
			{
				params.put("new_engine", "Y");
				List<Rule> rules						= dbMapper.selectRule(params);

				assurance.setRules(rules);
				redisService.put(Const.INCIDENT, Const.ASSURANCE_CUSTOM, Util.beanToJson(assurance));
				
				if( !CustomWorkflow.getInstance().isProcessing() )
				{
					System.out.println("CustomWorkflow Start....");
					CustomWorkflow.getInstance().goRunning(influxdb_endpoint, broker, rabbitmq_host, rabbitmq_port, kafka_host, kafka_port, redisService);
				}
				else
				{
					if( CustomWorkflow.getInstance( ).getState() == State.TERMINATED )
						CustomWorkflow.getInstance( ).goRunning(influxdb_endpoint, broker, rabbitmq_host, rabbitmq_port, kafka_host, kafka_port, redisService);
				}
			}
			
			// #######################################################################################################################
			
			// 이미 종료됐으나 종료 시간이 설정되지 않은 데이터 update
			{
				Notification notification 	= new Notification();
				notification.setFinish_time(Util.getTime());
				dbMapper.updateIncidentFinishTime(notification);
			}
		} catch ( Exception e) {
			e.printStackTrace();
			logger.error(Util.makeStackTrace(e));
		}
	}
	
	/**
	 * Rule set에의해 filtering된 data를 notification에 등록 및 수정
	 * kafka topic : event_topic
	 * @throws Exception
	 */
	public void incidentCrawlig() throws Exception {
		
		try {
			
			if( "kafka".equals(broker))
			{
				if( NotificationConsumer.getInstance().init(kafka_zookeeper, kafka_host, kafka_port, Const.INCIDENT_TOPIC, Const.INCIDENT_TOPIC+"_group") )
				{
					ConsumerRecords<String, String> records = NotificationConsumer.getInstance().read( );
		    		
		    		for (ConsumerRecord<String, String> record : records)
			        {
		    			if( record.value() == null || "".equals(record.value())) continue;
		    			
		    			logger.error("incident consumer ::"+record.value());
		    			
		    			Notification notification = Util.JsonTobean(record.value(), Notification.class);
		    			
		    			int count = dbMapper.selectIncident(notification);
		    			if( count == 0 )
		    			{
		    				dbMapper.insertIncident( notification );
		    			}
		    			else
		    			{
		    				if( notification.getFinish_time() != null && !"".equals(notification.getFinish_time()) )
		    					dbMapper.updateIncident( notification );
		    			}
			        }
				}
			}
			// Rabbit MQ
			else
			{
				
			}
		} catch ( Exception e) {
			e.printStackTrace();
			logger.error(Util.makeStackTrace(e));
		}
	}
	
	
	/**
	 * Agent Status Update
	 * @throws Exception
	 */
	public void agentStatusUpdate( ) throws Exception 
	{
		Gson gson 							= new GsonBuilder().setPrettyPrinting().serializeNulls().create();
		List<String> ips					= null; 
		try{
			String data						= redisService.get(Const.HOST, Const.LIST);
			ips						 		= gson.fromJson(data, new TypeToken<List<String>>(){}.getType());
			List<String> delHosts			= new ArrayList<String>();	 
			AgentStatus status				= null;	 
			
			if( ips != null )
			{
				// IP Loop
				for( String ip : ips )
				{
					// Agent id별 host 조회
					data						= redisService.get(Const.HOST, ip);
	
					if( data == null ) continue;
					
					String dataStatus			= redisService.get(Const.AGENT_STATUS, ip);
					if( dataStatus == null ) continue;
					
					status					= Util.JsonTobean(dataStatus, AgentStatus.class);
					
					Long currTime			= new Date().getTime();
					
					// Agent 수집 시간이 60초 이상일 경우 오류 발생
					if( (currTime - status.getLast_update()) > (1000*60) )
					{
						status.setStatus(Const.INACTIVE);
						
						delHosts.add(ip);
						
						redisService.put(Const.AGENT_STATUS, ip, Util.beanToJson(status));
						
						try{
							// Docker정보 삭제
							redisService.remove(Const.DOCKER, ip);
						}catch(Exception e){}
					}
					
					// K8S Master 인지 체크
					data												= redisService.get(Const.K8S, Const.K8S);
					
					if( data != null )
					{
						K8SMain main									= null;
						K8SAPI k8s										= null;
						k8s												= Util.JsonTobean(data, K8SAPI.class);
						
						String node_ip									= null;
						// Node
						main = k8s.getNode();			
						for( Item item: main.getItems() ) {
							node_ip										= item.getMetadata().getNode_ip();
							if(!delHosts.contains(node_ip)) continue;
							
							if(item.getSpec().getTaints() != null &&  item.getSpec().getTaints().size() > 0 &&
							   item.getSpec().getTaints().get(0).getKey() != null &&
							   item.getSpec().getTaints().get(0).getKey().contains("master") )
							{
								redisService.remove(Const.K8S, Const.K8S);
								break;
							}
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
