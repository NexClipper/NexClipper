package com.nexcloud.api.akka.actor;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.nexcloud.workflow.host.domain.Disk;
import com.nexcloud.workflow.host.domain.Host;
import com.nexcloud.workflow.host.domain.HostMap;
import com.nexcloud.workflow.host.domain.Memory;
import com.nexcloud.workflow.host.domain.Network;
import com.nexcloud.workflow.host.domain.ProcessStatus;
import com.nexcloud.workflow.host.domain.SwapMemory;

import akka.actor.UntypedActor;

@Component
@Scope("prototype")
public class JsonHostParserActor extends UntypedActor{

	static final Logger 	logger 			= LoggerFactory.getLogger(JsonHostParserActor.class);

	@Override
	/**
	 * 액터에 전달된 메세지를 처리
	 */
	public void onReceive(Object message){
		// TODO Auto-generated method stub
		SendData sendData						= null; 
		RedisCluster redisCluster				= null;
		String msg								= null;
		String old_msg							= null;
		
		ResponseData resData					= null;
        Header header							= null;
        String body								= null;
        
        String pattern 							= "#####.###";
        DecimalFormat dformat 					= new DecimalFormat( pattern );
        
        ConsumerRecords<String, String> records = null;
        
        Gson gson 								= new GsonBuilder().setPrettyPrinting().serializeNulls().create();
		
		try{
			/**
	         * Kafka receive data
	         */
	        sendData							= (SendData)message;
	        // Redis Cluster connection
	        redisCluster						= RedisCluster.getInstance(sendData.getRedis_host(), Integer.parseInt(sendData.getRedis_port()));
	        
	        records								= sendData.getRecords();
	        msg									= "";
	        
			Host host							= null; 
			
			CPU cpu								= null;
			Memory mem							= null;
			Network net							= null;
			ProcessStatus procStatus			= null;
			SwapMemory swap						= null;

			List<Disk> disks					= null;
			List<com.nexcloud.workflow.host.domain.Process> processes			= null;

			List<String> ips					= null;
			
			// CPU Core별 사용량
			List<CPU> cpus						= null;
			Map<String, Host> hMap				= null; 
			
	        for (ConsumerRecord<String, String> record : records)
	        {
    			if( record.value() == null || "".equals(record.value())) continue;
		        /**
		         * Agent metric data
		         */
    			resData							= Util.JsonTobean(record.value(), ResponseData.class);
		        header							= resData.getHeader();
				body							= resData.getBody();
				body							= new String(Util.decompress(Util.hexStringToByteArray(body)));
					
				host							= Util.JsonTobean(body, Host.class);
				host.setHost_ip(header.getNode_ip());
				host.setHost_name(header.getNode_name());
				cpu								= host.getCpu();
				mem								= host.getMem();
				net								= host.getNet();
				procStatus						= host.getProcessStatus();
				swap							= host.getSwap();

				disks							= host.getDisks();
				processes						= host.getProcess();
				
				// CPU Core별 사용량
				cpus							= host.getCpus();
				
				// Process 
				for( com.nexcloud.workflow.host.domain.Process process : processes )
				{
					try{
						if( process.getArgs() != null )
						{
							String[] args		= new String[process.getArgs().length];
							int idx				= 0;
							for( String arg : process.getArgs() )
							{
								if( arg == null )
									arg			= "";
								
								arg				= arg.replaceAll("\\\\","");
								arg				= arg.replaceAll("\"","");
								arg				= arg.replaceAll("\r","");
								arg				= arg.replaceAll("\n","");

								args[idx]		= arg;
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
				String data						= redisCluster.get(Const.HOST, Const.LIST);
				ips					  			= gson.fromJson(data, new TypeToken<List<String>>(){}.getType());
				if( ips == null )
					ips							= new ArrayList<String>();
				
				if( !ips.contains(header.getNode_ip()))
					ips.add(header.getNode_ip());
				
				redisCluster.put(Const.HOST, Const.LIST, Util.beanToJson(ips));
			}
			// End of First Time Check
		}catch(Exception e){
			logger.error("body::"+body);
			e.printStackTrace();
			System.out.println(Util.makeStackTrace(e));
			
		}
	}
}