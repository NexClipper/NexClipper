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
package com.nexcloud.api.akka.actor;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nexcloud.api.akka.domain.SendData;
import com.nexcloud.api.kafka.K8SAPIConsumer;
import com.nexcloud.util.Util;
import com.nexcloud.util.rest.HttpAPI;
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
import akka.actor.Props;
import akka.actor.UntypedActor;

public class KafkaK8SAPIConsumerActor extends UntypedActor{
	static final Logger logger = LoggerFactory.getLogger(KafkaK8SAPIConsumerActor.class);
	private SendData sendData;

	private ActorRef actor1;
	private ActorRef actor2;
	private ActorRef actor3;
	private ActorRef actor4;
	private ActorRef actor5;
	private List<ActorRef> ref			= new ArrayList<ActorRef>();
	private int loop					= 0;
	
	@Override
	/**
	 * 액터에 전달된 메세지를 처리
	 */
	public synchronized void onReceive(Object message) throws Exception {
		actor1			 				= this.getContext().actorOf(Props.create(JsonK8SAPIParserActor.class),"JsonK8SAPIParserActor1");
		actor2			 				= this.getContext().actorOf(Props.create(JsonK8SAPIParserActor.class),"JsonK8SAPIParserActor2");
		actor3			 				= this.getContext().actorOf(Props.create(JsonK8SAPIParserActor.class),"JsonK8SAPIParserActor3");
		actor4			 				= this.getContext().actorOf(Props.create(JsonK8SAPIParserActor.class),"JsonK8SAPIParserActor4");
		actor5			 				= this.getContext().actorOf(Props.create(JsonK8SAPIParserActor.class),"JsonK8SAPIParserActor5");

		ref.add(actor1);
		ref.add(actor2);
		ref.add(actor3);
		ref.add(actor4);
		ref.add(actor5);
		sendData			= (SendData)message;
		
		/**
		 * Influex db Create
		 */
		this.send(sendData.getInfluxdb_endpoint(), sendData.getInfluxdb_datasource(), null);
		
		// kafka
		if( "kafka".equals(sendData.getBroker()) )
		{
			while(true)
			{
				try{
					if( K8SAPIConsumer.getInstance().init(sendData.getKafka_zookeeper(), sendData.getKafka_host(), sendData.getKafka_port(), sendData.getKafka_topic(), sendData.getKafka_topic()+"group") )
					{
						ConsumerRecords<String, String> records = K8SAPIConsumer.getInstance().read( );
						if( records.count() > 0)
						{
							sendData.setRecords(records);
							
							ref.get(loop).tell(sendData, ActorRef.noSender() );
							if( loop == 4 )
								loop = 0;
							else
								loop++;
			    			
			    			Thread.sleep((long)(Math.random() * 1000));
						}
						else
							Thread.sleep(10);
					}
					else
						Thread.sleep(10);
				}catch(Exception e){
					System.out.println("K8S Akka Terminated");
					e.printStackTrace();
					System.out.println(Util.makeStackTrace(e));
				}
				
	    		//Thread.sleep(100);
			}
		}
		
		// rabbit mq
		else
		{
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
			        logger.error("K8S RabbitMQ Connection Blocked::"+reason);
			    }

			    public void handleUnblocked() throws IOException {
			        // Connection is now unblocked
			    }
			});
			
			connection.addShutdownListener(new ShutdownListener() {
			    @Override
			    public void shutdownCompleted(ShutdownSignalException cause) {
			    	logger.error("K8S RabbitMQ Connection Shutdown::", cause);
			    }
			});
			
			Channel channel = connection.createChannel();
			
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("x-queue-mode", "lazy");
			channel.queueDeclare(sendData.getKafka_topic(), false, false, false, args);
			//channel.queueDeclare(sendData.getKafka_topic(), false, false, true, null);
			
			channel.addShutdownListener(new ShutdownListener() {
			    @Override
			    public void shutdownCompleted(ShutdownSignalException cause) {
			    	logger.error("K8S RabbitMQ Channel Shutdown::", cause);
			    }
			});
			
			Consumer consumer = new DefaultConsumer(channel){
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					try {
						String message = new String(body, "UTF-8");
						//logger.error("K8S Data Receive");
						
						sendData.setJson(message);
						
						ref.get(loop).tell(sendData, ActorRef.noSender() );
						if( loop == 4 )
							loop = 0;
						else
							loop++;
						Thread.sleep((long)(Math.random() * 1000));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			
			consumer.handleConsumeOk(sendData.getKafka_topic());
			//channel.basicConsume(sendData.getKafka_topic(), true, consumer);
			channel.basicConsume(sendData.getKafka_topic(), false, consumer);
		}
	}
	
	private Connection getConnection()
	{
		logger.error("KafkaK8SAPI RabbitMQ Connection Start!!");
		Connection conn		= null;
		try{
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(sendData.getRabbitmq_host());
			factory.setPort(Integer.parseInt(sendData.getRabbitmq_port())); // 5672 port
			factory.setUsername(sendData.getRabbitmq_username());
			factory.setPassword(sendData.getRabbitmq_password());
			
			factory.setAutomaticRecoveryEnabled(true);
			factory.setRequestedHeartbeat(60);
	        
			factory.setConnectionTimeout(1000);
	        
	        factory.setRequestedChannelMax(0);
	        factory.setRequestedFrameMax(0);
	        
	        conn 			= factory.newConnection();
	        
	        logger.error("KafkaK8SAPI RabbitMQ Connection End!!");
		}catch(Exception e){
			logger.error("KafkaK8SAPI RabbitMQ Connection Exception");
			conn			= null;
		}
		
		return conn;
	}
	
	/**
	 * Influx DB Write
	 * @param influxDB
	 * @param msg
	 */
	public void send( String influxDB, String influxDBTable, String msg )
	{
		try {
			influxDBTable = URLEncoder.encode("create DATABASE "+influxDBTable);
			influxDB = influxDB+"/query?q="+influxDBTable;
			
			System.out.println("influxdb Creae :: " + influxDB );
			
			HttpAPI.write(influxDB, null);
		} catch (Exception e) {
			System.out.println("influx send data :: " + msg );
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}