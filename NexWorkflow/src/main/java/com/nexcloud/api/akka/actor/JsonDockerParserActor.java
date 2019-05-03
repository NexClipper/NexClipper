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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nexcloud.agent.domain.AgentStatus;
import com.nexcloud.agent.domain.ResponseData;
import com.nexcloud.api.akka.domain.SendData;
import com.nexcloud.api.redis.RedisCluster;
import com.nexcloud.util.Const;
import com.nexcloud.util.Util;
import com.nexcloud.workflow.docker.domain.Containers;
import com.nexcloud.workflow.docker.domain.Docker;

import akka.actor.UntypedActor;

@Component
@Scope("prototype")
public class JsonDockerParserActor extends UntypedActor{

	static final Logger 	logger 				= LoggerFactory.getLogger(JsonDockerParserActor.class);
	
	@Override
	/**
	 * 액터에 전달된 메세지를 처리
	 */
	public synchronized void onReceive(Object message){
		// TODO Auto-generated method stub
		SendData sendData						= null; 
		ConsumerRecords<String, String> records = null;
		try{
	        sendData							= (SendData)message;

	        // kafka
			if( "kafka".equals(sendData.getBroker()) )
			{
				records							= sendData.getRecords();
		        for (ConsumerRecord<String, String> record : records)
		        {
	    			if( record.value() == null || "".equals(record.value())) continue;
	    			
	    			exec( sendData, record.value() );
		        }
			}
			
			// Rabbit MQ
			else
			{
				exec( sendData, sendData.getJson() );
				/*
				for( String json : jsons )
					exec( sendData, json );
				*/
				/*
				List<String> jsons	= sendData.getJsons();
				for (Iterator<String> iter = jsons.iterator(); iter.hasNext(); ) 
				{
					String json = iter.next();
					exec( sendData, json );
				}
				*/
			}
			// End of First Time Check
		}catch(Exception e){
			logger.error("jsonDockerParser Exception::", e);
		}
	}
	
	public void exec( SendData sendData, String data )
	{
		// TODO Auto-generated method stub
		RedisCluster redisCluster				= null;
		Gson gson 								= new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        List<String> ips						= null; 
		try{
	        // Redis Cluster connection
	        redisCluster						= RedisCluster.getInstance(sendData.getRedis_host(), Integer.parseInt(sendData.getRedis_port()));

	        
	        /**
	         * Agent metric data
	         */
	        ResponseData resData				= null;
	        String body							= null;
			Docker docker						= null; 
			
			String dataStatus					= null;
			AgentStatus status					= null;
			resData								= Util.JsonTobean(data, ResponseData.class);
			
			body								= resData.getBody();
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
				// Docker container info
				for( Containers container : docker.getContainers() )
				{
					container.setCommand((container.getCommand().replaceAll("\\\\","")).replaceAll("\"",""));
					container.setCommand((container.getCommand().replaceAll("\n","")).replaceAll("\r",""));
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
				container.setCommand((container.getCommand().replaceAll("\n","")).replaceAll("\r",""));
			}
			
			
			// Host Mpa Redis In/Out
			String hostIps								= redisCluster.get(Const.HOST, Const.LIST);
			ips					  					= gson.fromJson(hostIps, new TypeToken<List<String>>(){}.getType());
			if( ips == null )
				ips									= new ArrayList<String>();
			
			for( String ip : ips )
			{
				// Inactive상태인 Redis데이터 삭제
				dataStatus							= null;
				status								= null;
				
				dataStatus							= redisCluster.get(Const.AGENT_STATUS, ip );
	            
	            if( dataStatus == null )  continue;
	            
				status								= Util.JsonTobean(dataStatus, AgentStatus.class);
				
				if( !Const.INACTIVE.equals(status.getStatus()) ) continue;
				
				redisCluster.remove(Const.DOCKER, ip);
			}
			
			//logger.error("jsonDockerParser End");
		}catch(Exception e){
			logger.error("jsonDockerParser Exception::", e);
		}
	}
}