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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nexcloud.agent.domain.Header;
import com.nexcloud.agent.domain.ResponseData;
import com.nexcloud.api.akka.domain.SendData;
import com.nexcloud.api.redis.RedisCluster;
import com.nexcloud.util.Const;
import com.nexcloud.util.Util;
import com.nexcloud.workflow.host.domain.CPU;
import com.nexcloud.workflow.host.domain.Host;

import akka.actor.UntypedActor;

@Component
@Scope("prototype")
public class JsonHostParserActor extends UntypedActor{

	static final Logger 	logger 			= LoggerFactory.getLogger(JsonHostParserActor.class);

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
			logger.error("jsonHostParser Exception::", e);
			
		}
	}
	
	public void exec( SendData sendData, String data )
	{
		// TODO Auto-generated method stub
		RedisCluster redisCluster				= null;

		ResponseData resData					= null;
        Header header							= null;
        String body								= null;
        
        String pattern 							= "#####.###";
        DecimalFormat dformat 					= new DecimalFormat( pattern );
        
        Gson gson 								= new GsonBuilder().setPrettyPrinting().serializeNulls().create();
		
		try{
	        // Redis Cluster connection
	        redisCluster						= RedisCluster.getInstance(sendData.getRedis_host(), Integer.parseInt(sendData.getRedis_port()));
	        
			Host host							= null; 
			
			List<com.nexcloud.workflow.host.domain.Process> processes			= null;

			List<String> ips					= null;
			
			// CPU Core별 사용량
			List<CPU> cpus						= null;
			Map<String, Host> hMap				= null; 
			/**
	         * Agent metric data
	         */
			resData								= Util.JsonTobean(data, ResponseData.class);
	        header								= resData.getHeader();
			body								= resData.getBody();
			body								= new String(Util.decompress(Util.hexStringToByteArray(body)));
				
			host								= Util.JsonTobean(body, Host.class);
			host.setHost_ip(header.getNode_ip());
			host.setHost_name(header.getNode_name());

			processes							= host.getProcess();
			
			// CPU Core별 사용량
			cpus								= host.getCpus();
			
			// Process 
			for( com.nexcloud.workflow.host.domain.Process process : processes )
			{
				try{
					if( process.getArgs() != null )
					{
						String[] args			= new String[process.getArgs().length];
						int idx					= 0;
						for( String arg : process.getArgs() )
						{
							if( arg == null )
								arg				= "";
							
							arg					= arg.replaceAll("\\\\","");
							arg					= arg.replaceAll("\"","");
							arg					= arg.replaceAll("\r","");
							arg					= arg.replaceAll("\n","");

							args[idx]			= arg;
							idx++;
						}
						
						process.setArgs(args);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}

			
			// Cpu Core별
			int cpu_core = 0;
			for( CPU coreCpu : cpus )
			{
				if( coreCpu.getCpu_per() >= 100 ) continue;
				coreCpu.setCore(cpu_core);
				
				cpu_core++;
			}
			// Host Mpa Redis In/Out
			String hostIPs						= redisCluster.get(Const.HOST, Const.LIST);
			ips					  				= gson.fromJson(hostIPs, new TypeToken<List<String>>(){}.getType());
			if( ips == null )
				ips								= new ArrayList<String>();
			
			if( !ips.contains(header.getNode_ip()))
				ips.add(header.getNode_ip());
			
			redisCluster.put(Const.HOST, Const.LIST, Util.beanToJson(ips));
			
			logger.error("jsonHostParser End");
		}catch(Exception e){
			logger.error("jsonHostParser Exception::", e);
		}
	}
}