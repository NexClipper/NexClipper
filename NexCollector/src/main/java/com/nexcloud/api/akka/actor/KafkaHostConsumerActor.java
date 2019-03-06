package com.nexcloud.api.akka.actor;

import java.net.URLEncoder;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nexcloud.api.akka.domain.SendData;
import com.nexcloud.api.kafka.HostConsumer;
import com.nexcloud.util.Util;
import com.nexcloud.util.rest.HttpAPI;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class KafkaHostConsumerActor extends UntypedActor{
	static final Logger logger = LoggerFactory.getLogger(KafkaHostConsumerActor.class);
	
	@Override
	/**
	 * 액터에 전달된 메세지를 처리
	 */
	public void onReceive(Object message) throws Exception {
		ActorRef jsonParserActor 	= this.getContext().actorOf(Props.create(JsonHostParserActor.class),"JsonHostParserActor");
		SendData sendData			= (SendData)message;

		/**
		 * Influex db Create
		 */
		this.send(sendData.getInfluxdb_endpoint(), sendData.getInfluxdb_datasource(), null);
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