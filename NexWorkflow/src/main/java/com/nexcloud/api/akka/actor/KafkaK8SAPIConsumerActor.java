package com.nexcloud.api.akka.actor;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nexcloud.api.akka.domain.SendData;
import com.nexcloud.api.kafka.K8SConsumer;
import com.nexcloud.util.Util;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class KafkaK8SAPIConsumerActor extends UntypedActor{
	static final Logger logger = LoggerFactory.getLogger(KafkaK8SAPIConsumerActor.class);
	
	@Override
	/**
	 * 액터에 전달된 메세지를 처리
	 */
	public void onReceive(Object message) throws Exception {
		ActorRef jsonParserActor 	= this.getContext().actorOf(Props.create(JsonK8SAPIParserActor.class),"JsonK8SAPIParserActor");
		SendData sendData			= (SendData)message;

		while(true)
		{
			try{
			if( K8SConsumer.getInstance().init(sendData.getKafka_zookeeper(), sendData.getKafka_host(), sendData.getKafka_port(), sendData.getKafka_topic(), sendData.getKafka_topic()+"_group") )
				{
					ConsumerRecords<String, String> records = K8SConsumer.getInstance().read( );

					if( records.count() > 0)
					{
						sendData.setRecords(records);
		    			jsonParserActor.tell(sendData, ActorRef.noSender() );
					}
					
		    		Thread.sleep(100);
				}
			}catch(Exception e){
				System.out.println("K8S Akka Terminated");
				e.printStackTrace();
				System.out.println(Util.makeStackTrace(e));
			}
			
    		Thread.sleep(100);
		}
	}
}