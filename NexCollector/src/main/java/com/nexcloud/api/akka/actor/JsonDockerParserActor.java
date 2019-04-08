
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
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.nexcloud.api.akka.domain.SendData;
import com.nexcloud.api.redis.RedisCluster;
import com.nexcloud.fullfillment.docker.domain.Containers;
import com.nexcloud.fullfillment.docker.domain.Docker;
import com.nexcloud.fullfillment.domain.common.Header;
import com.nexcloud.fullfillment.domain.common.ResponseData;
import com.nexcloud.util.Const;
import com.nexcloud.util.SendDataLoader;
import com.nexcloud.util.Util;

import akka.actor.UntypedActor;

@Component
@Scope("prototype")
public class JsonDockerParserActor extends UntypedActor{

	static final Logger 	logger 					= LoggerFactory.getLogger(JsonDockerParserActor.class);
	
	private DockerContainerThread containerThread	= DockerContainerThread.getInstance();
	
	private long actor_start;
	private int actor_count;
	
	/**
	 * 액터에 전달된 메세지를 처리
	 */
	@Override
	public void onReceive(Object message){
		// TODO Auto-generated method stub
		SendData sendData							= null; 
		ConsumerRecords<String, String> records 	= null;
		
		actor_start									= System.currentTimeMillis();
		
		try{
	        sendData								= (SendData)message;

	        // kafka
			if( "kafka".equals(sendData.getBroker()) )
			{
				records									= sendData.getRecords();
		        
		        for (ConsumerRecord<String, String> record : records)
		        {
	    			if( record.value() == null || "".equals(record.value())) continue;
	    			
	    			exec( sendData, record.value() );
					
		        }
			}
			// RabbitMQ
			else
			{
				exec( sendData, sendData.getJson() );
			}
			// End of First Time Check
		}catch(Exception e){
			logger.error("jsonHostParser Exception::", e);
		}
	}
	
	public void exec( SendData sendData, String data )
	{
		RedisCluster redisCluster				= null;
		String msg								= null;
		try{
        
	        // Redis Cluster connection
	        redisCluster						= RedisCluster.getInstance(sendData.getRedis_host(), Integer.parseInt(sendData.getRedis_port()));

	        /**
	         * Agent metric data
	         */
	        ResponseData resData				= null;
	        Header header						= null;
	        String body							= null;
	        
	        msg									= "";

	        Docker docker						= null; 
			
			resData								= Util.JsonTobean(data, ResponseData.class);
			
	        header								= resData.getHeader();
			body								= resData.getBody();
			
			// Data decompress and Hex string to byte array
			body								= new String(Util.decompress(Util.hexStringToByteArray(body)));
			
			docker								= Util.JsonTobean(body, Docker.class);

			// Host의 Docker info
			if( docker != null && docker.getDocker_info() != null )
			{
				if( docker.getDocker_info().getContainersRunning() == null )
				{
					docker.getDocker_info().setContainersRunning(docker.getDocker_info().getContainers());
					docker.getDocker_info().setContainersPaused(0);
					docker.getDocker_info().setContainersStopped(0);
				}
				msg	+= "docker,host_name="+header.getNode_name()+",host_ip="+header.getNode_ip();
				
				msg +=" containers="+docker.getDocker_info().getContainers()+",running="+docker.getDocker_info().getContainersRunning()+",paused="+docker.getDocker_info().getContainersPaused()+",stopped="+docker.getDocker_info().getContainersStopped()+",timestamp="+resData.getTimestamp()+"\n";
				
				// Docker container info
				containerThread.set(header.getNode_name(), header.getNode_ip(), docker.getContainers(), resData.getTimestamp());
				
				// Docker container info
				for( Containers container : docker.getContainers() )
				{
					container.setCommand((container.getCommand().replaceAll("\\\\","")).replaceAll("\"",""));
				}
			}
			
			// Redis Save
			// data size가 너무 커  필요 없다고 생각되는 데이터 삭제
			for( Containers container : docker.getContainers() )
			{
				container.setProcess(null);
				container.setEnv(null);
				container.setVolume(null);
				container.setCommand((container.getCommand().replaceAll("\\\\","")).replaceAll("\"",""));
			}
			
			redisCluster.put(Const.DOCKER, header.getNode_ip(), Util.beanToJson(docker));
			
			this.send(msg);
		}catch(Exception e){
			logger.error("jsonDockerParser Exception::", e);
		}
	}
	
	/**
	 * Influx DB Write
	 * @param influxDB
	 * @param msg
	 */
	public void send( String msg )
	{
		SendDataLoader.getInstance().set(msg);
		
		logger.error("Docker Data Parsing Timestamp :["+actor_count+"] "+(System.currentTimeMillis() - actor_start));
	}
}