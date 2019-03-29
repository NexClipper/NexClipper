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

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nexcloud.api.akka.domain.SendData;
import com.nexcloud.api.kafka.HostConsumer;
import com.nexcloud.util.Util;
import com.nexcloud.util.rest.HttpAPI;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class KafkaHostConsumerActor extends UntypedActor{
	static final Logger logger = LoggerFactory.getLogger(KafkaHostConsumerActor.class);
	private SendData sendData;
	
	private ActorRef jsonParserActor;
	
	@Override
	/**
	 * 액터에 전달된 메세지를 처리
	 */
	public void onReceive(Object message) throws Exception {
		jsonParserActor 	= this.getContext().actorOf(Props.create(JsonHostParserActor.class),"JsonHostParserActor");
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
					if( HostConsumer.getInstance().init(sendData.getKafka_zookeeper(), sendData.getKafka_host(), sendData.getKafka_port(), sendData.getKafka_topic(), sendData.getKafka_topic()+"group") )
					{
						ConsumerRecords<String, String> records = HostConsumer.getInstance().read( );
						if( records.count() > 0)
						{
							sendData.setRecords(records);
							
			    			jsonParserActor.tell(sendData, ActorRef.noSender() );
	
			    			Thread.sleep((long)(Math.random() * 1000));
						}
						else
							Thread.sleep(10);
					}
					else
						Thread.sleep(10);
				}catch(Exception e){
					System.out.println("Host Akka Terminated");
					e.printStackTrace();
					System.out.println(Util.makeStackTrace(e));
				}
				
	    		//Thread.sleep(100);
			}
		}
		// rabbit mq
		else
		{
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(sendData.getRabbitmq_host());
			factory.setPort(Integer.parseInt(sendData.getRabbitmq_port())); // 5672 port
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
	
			channel.queueDeclare(sendData.getKafka_topic(), false, false, false, null);
			try{
				Consumer consumer = new DefaultConsumer(channel) {
					@Override
					public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
							byte[] body) throws IOException {
						String message = new String(body, "UTF-8");
						sendData.setJson(message);
						jsonParserActor.tell(sendData, ActorRef.noSender() );
						
					}
				};
		
				channel.basicConsume(sendData.getKafka_topic(), true, consumer);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Influx DB Create
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