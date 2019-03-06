package com.nexcloud.api.akka.actor;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nexcloud.api.akka.domain.SendData;
import com.nexcloud.api.kafka.DockerConsumer;
import com.nexcloud.util.Util;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class KafkaDockerConsumerActor extends UntypedActor{
	static final Logger logger = LoggerFactory.getLogger(KafkaDockerConsumerActor.class);
	
	@Override
	/**
	 * 액터에 전달된 메세지를 처리
	 */
	public void onReceive(Object message) throws Exception {
		ActorRef jsonParserActor 	= this.getContext().actorOf(Props.create(JsonDockerParserActor.class),"JsonDockerParserActor");
		SendData sendData			= (SendData)message;

		while(true)
		{
			try{
			if( DockerConsumer.getInstance().init(sendData.getKafka_zookeeper(), sendData.getKafka_host(), sendData.getKafka_port(), sendData.getKafka_topic(), sendData.getKafka_topic()+"_group") )
				{
					ConsumerRecords<String, String> records = DockerConsumer.getInstance().read( );

					if( records.count() > 0)
					{
						sendData.setRecords(records);
		    			jsonParserActor.tell(sendData, ActorRef.noSender() );
					}
					
					Thread.sleep(100);
				}
			}catch(Exception e){
				System.out.println("Docker Akka Terminated");
				e.printStackTrace();
				System.out.println(Util.makeStackTrace(e));
			}
			
    		Thread.sleep(100);
		}
	}
}