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
package com.nexcloud.workflow;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexcloud.api.akka.actor.KafkaDockerConsumerActor;
import com.nexcloud.api.akka.actor.KafkaHostConsumerActor;
import com.nexcloud.api.akka.actor.KafkaK8SAPIConsumerActor;
import com.nexcloud.api.akka.domain.SendData;
import com.nexcloud.api.kafka.CreateTopic;
import com.nexcloud.api.redis.RedisCluster;
import com.nexcloud.rule.domain.Notification;
import com.nexcloud.util.Const;
import com.nexcloud.util.Util;
import com.nexcloud.workflow.mapper.DBMapper;
import com.nexcloud.workflow.service.WorkflowService;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BlockedListener;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownListener;
import com.rabbitmq.client.ShutdownSignalException;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

@Configuration
@RestController
@EnableAutoConfiguration
@SpringBootApplication
@EnableScheduling
@PropertySource("classpath:application.properties")
@RequestMapping("/v1")
public class Workflow extends SpringBootServletInitializer implements WebApplicationInitializer {
	@Autowired
	private WorkflowService workflowService;
	
	@Autowired
	DBMapper dbMapper;
	
	@Value("${spring.kafka.zookeeper}")
	private String kafka_zookeeper;
	
	@Value("${spring.kafka.host}")
	private String kafka_host;
	
	@Value("${spring.kafka.port}")
	private String kafka_port;
	
	@Value("${spring.redis.host}")
	private String redis_host;

	@Value("${spring.redis.port}")
	private String redis_port;
	
	@Value("${spring.broker}")
	private String broker;
	
	@Value("${spring.rabbitmq.host}")
	private String rabbitmq_host;
	
	@Value("${spring.rabbitmq.port}")
	private String rabbitmq_port;
	
	@Value("${spring.rabbitmq.username}")
	private String rabbitmq_username;
	
	@Value("${spring.rabbitmq.password}")
	private String rabbitmq_password;

	static final Logger logger = LoggerFactory.getLogger(Workflow.class);
	
	@RequestMapping("/check")
    public String checkBit( ) throws Exception {
		logger.info("Check Bit");		
		return "Check Bit";
    }
	
	/**
	 * Rule set workflow Engine
	 * 
	 * @throws Exception
	 */
	@Scheduled(fixedDelay = 1000, initialDelay = 1000)
	public void scheduleRule() throws Exception {
		workflowService.ruleSetWorkflowEngine();
	}
	
	/**
	 * Rule set에의해 filtering된 data를 notification에 등록 및 수정
	 * 
	 * @throws Exception
	 */
	@Scheduled(fixedDelay = 1000, initialDelay = 1000)
	public void scheduleIncidentCrawling() throws Exception {
		workflowService.incidentCrawlig();
	}

	/**
	 * Agent Status Update
	 * @throws Exception
	 */
	@Scheduled(fixedDelay = 10000, initialDelay = 1000)
	public void agentStatus() throws Exception {
		try{
			workflowService.agentStatusUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*	
	@Bean(name = "dataSource")
	public void dataSource() {
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    dataSource.setUrl("jdbc:mysql://mysql.nexclipper:3306/defaultdb?useUnicode=yes&amp;characterEncoding=UTF8&amp;autoReconnect=true&amp;autoReconnectForPools=true");
	    dataSource.setUsername("admin");
	    dataSource.setPassword("password");

	    // schema init
        Resource initData = new ClassPathResource("scripts/load.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initData);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);
	}
	*/
	
	public void createActor()
	{
		/**
    	 * Kafka Topic Create
    	 */
		if( "kafka".equals(broker) )
		{
	    	System.out.println(" Kafka Topic Create Start.....");
	
	    	CreateTopic.getInstance().create(kafka_zookeeper, kafka_host, kafka_port, Const.INCIDENT_TOPIC);
	    	
	    	CreateTopic.getInstance().create(kafka_zookeeper, kafka_host, kafka_port, Const.HOST_TOPIC);
	    	
	    	CreateTopic.getInstance().create(kafka_zookeeper, kafka_host, kafka_port, Const.DOCKER_TOPIC);
	    	
	    	CreateTopic.getInstance().create(kafka_zookeeper, kafka_host, kafka_port, Const.K8SAPI_TOPIC);
	    	
	    	CreateTopic.getInstance().create(kafka_zookeeper, kafka_host, kafka_port, Const.LOG_TOPIC);
	    	
	    	System.out.println(" Kafka Topic Create End.....");
		}
    	
    	// Connection Redis
		RedisCluster.getInstance(redis_host, Integer.parseInt(redis_port));
		
		/**
		 * Mysql, Kafka, Influxdb properties set
		 */
		SendData sendData			= new SendData();
		
		sendData.setRedis_host(redis_host);
		sendData.setRedis_port(redis_port);
		//sendData.setRedis_password(redis_password);
		
		sendData.setKafka_zookeeper(kafka_zookeeper);
		sendData.setKafka_host(kafka_host);
		sendData.setKafka_port(kafka_port);
		
		sendData.setBroker(broker);
		sendData.setRabbitmq_host(rabbitmq_host);
		sendData.setRabbitmq_port(rabbitmq_port);
		sendData.setRabbitmq_username(rabbitmq_username);
		sendData.setRabbitmq_password(rabbitmq_password);
		
		
		/**
		 * Host Kafka Consumer Actor
		 */
		sendData.setKafka_topic(Const.HOST_TOPIC);
		ActorSystem systemHost 			= ActorSystem.create("HostConsumer");
		ActorRef kafkaHostConsumer 	= null;
		kafkaHostConsumer 				= systemHost.actorOf(Props.create(KafkaHostConsumerActor.class),"KafkaHostConsumerActor");
		kafkaHostConsumer.tell(sendData, ActorRef.noSender());
		//System.out.println("Host :: "+ sendData.toString());
		
		/**
		 * Docker Kafka Consumer Actor
		 */
		sendData			= new SendData();
		
		sendData.setRedis_host(redis_host);
		sendData.setRedis_port(redis_port);
		//sendData.setRedis_password(redis_password);
		
		sendData.setKafka_zookeeper(kafka_zookeeper);
		sendData.setKafka_host(kafka_host);
		sendData.setKafka_port(kafka_port);
		
		sendData.setBroker(broker);
		sendData.setRabbitmq_host(rabbitmq_host);
		sendData.setRabbitmq_port(rabbitmq_port);
		sendData.setRabbitmq_username(rabbitmq_username);
		sendData.setRabbitmq_password(rabbitmq_password);
		
		sendData.setKafka_topic(Const.DOCKER_TOPIC);
		ActorSystem systemDocker		= ActorSystem.create("DockerConsumer");
		ActorRef kafkaDockerConsumer 	= null;
		kafkaDockerConsumer 			= systemDocker.actorOf(Props.create(KafkaDockerConsumerActor.class),"KafkaDockerConsumerActor");
		kafkaDockerConsumer.tell(sendData, ActorRef.noSender());
		//System.out.println("Docker :: "+ sendData.toString());
		
		/**
		 * Kubernete(API) Kafka Consumer Actor
		 */
		sendData			= new SendData();
		
		sendData.setRedis_host(redis_host);
		sendData.setRedis_port(redis_port);
		//sendData.setRedis_password(redis_password);
		
		sendData.setKafka_zookeeper(kafka_zookeeper);
		sendData.setKafka_host(kafka_host);
		sendData.setKafka_port(kafka_port);
		
		sendData.setBroker(broker);
		sendData.setRabbitmq_host(rabbitmq_host);
		sendData.setRabbitmq_port(rabbitmq_port);
		sendData.setRabbitmq_username(rabbitmq_username);
		sendData.setRabbitmq_password(rabbitmq_password);
		
		sendData.setKafka_topic(Const.K8SAPI_TOPIC);
		ActorSystem systemK8SAPI		= ActorSystem.create("K8SAPIConsumer");
		ActorRef kafkaK8SAPIConsumer	= null;
		kafkaK8SAPIConsumer 			= systemK8SAPI.actorOf(Props.create(KafkaK8SAPIConsumerActor.class),"KafkaK8SAPIConsumerActor");
		kafkaK8SAPIConsumer.tell(sendData, ActorRef.noSender());
		//System.out.println("Kubernetes API:: "+ sendData.toString());
		
		// 이벤트 handler 처리
		if( !"kafka".equals(broker) )
		{
			try{
				Connection connection 	= null;
				while( true )
				{
					if( (connection  = getConnection()) == null )
						Thread.sleep(1000);
					else
						break;
				}
				
				connection.addBlockedListener(new BlockedListener() {
				    public void handleBlocked(String reason) throws IOException {
				        logger.error("Workflow RabbitMQ Connection Blocked::"+reason);
				    }
	
				    public void handleUnblocked() throws IOException {
				        // Connection is now unblocked
				    }
				});
				
				connection.addShutdownListener(new ShutdownListener() {
				    @Override
				    public void shutdownCompleted(ShutdownSignalException cause) {
				    	logger.error("Workflow RabbitMQ Connection Shutdown::", cause);
				    }
				});
				
				Channel channel = connection.createChannel();
				Map<String, Object> args = new HashMap<String, Object>();
				args.put("x-queue-mode", "lazy");
				channel.queueDeclare(Const.INCIDENT_TOPIC, false, false, false, args);
				//channel.queueDeclare(Const.INCIDENT_TOPIC, false, false, true, null);
				
				channel.addShutdownListener(new ShutdownListener() {
				    @Override
				    public void shutdownCompleted(ShutdownSignalException cause) {
				    	logger.error("Workflow RabbitMQ Channel Shutdown::", cause);
				    }
				});
				
				Consumer consumer = new DefaultConsumer(channel) {
					@Override
					public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
							byte[] body) throws IOException {
						
						try{
							String message = new String(body, "UTF-8");
							
							logger.error("notification data::"+message);
							
							Notification notification = Util.JsonTobean(message, Notification.class);
			    			
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
	
							Thread.sleep((long)(Math.random() * 1000));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};
		
				consumer.handleConsumeOk(Const.INCIDENT_TOPIC);
				//channel.basicConsume(sendData.getKafka_topic()+"_work", true, consumer);
				channel.basicConsume(Const.INCIDENT_TOPIC, false, consumer);
			}catch(Exception e){
				logger.error("Workflow Main Application Exception", e);
			}
		}
	}
	
	private Connection getConnection()
	{
		logger.error("Workflow RabbitMQ Connection Start!!");
		Connection conn		= null;
		try{
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(rabbitmq_host);
			factory.setPort(Integer.parseInt(rabbitmq_port)); // 5672 port
			factory.setUsername(rabbitmq_username);
			factory.setPassword(rabbitmq_password);
			
			factory.setAutomaticRecoveryEnabled(true);
			factory.setRequestedHeartbeat(60);
	        
			factory.setConnectionTimeout(1000);
	        
	        factory.setRequestedChannelMax(0);
	        factory.setRequestedFrameMax(0);
	        conn 			= factory.newConnection();
	        
	        logger.error("Workflow RabbitMQ Connection End!!");
		}catch(Exception e){
			logger.error("Workflow RabbitMQ Connection Exception");
			conn			= null;
		}
		
		return conn;
	}
    public static void main(String[] args) throws Exception {
    	ConfigurableApplicationContext context 	= SpringApplication.run(Workflow.class, args);
    	Workflow workflow						= context.getBean(Workflow.class);
    	workflow.createActor();
    }
    
}
