
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
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.nexcloud.api.akka.domain.SendData;
import com.nexcloud.api.redis.RedisCluster;
import com.nexcloud.fullfillment.domain.common.Header;
import com.nexcloud.fullfillment.domain.common.ResponseData;
import com.nexcloud.fullfillment.host.domain.CPU;
import com.nexcloud.fullfillment.host.domain.Disk;
import com.nexcloud.fullfillment.host.domain.Host;
import com.nexcloud.fullfillment.host.domain.Memory;
import com.nexcloud.fullfillment.host.domain.Network;
import com.nexcloud.fullfillment.host.domain.Process;
import com.nexcloud.fullfillment.host.domain.ProcessStatus;
import com.nexcloud.fullfillment.host.domain.SwapMemory;
import com.nexcloud.util.Const;
import com.nexcloud.util.SendDataLoader;
import com.nexcloud.util.Util;

import akka.actor.UntypedActor;

@Component
@Scope("prototype")
public class JsonHostParserActor extends UntypedActor{

	static final Logger 	logger 					= LoggerFactory.getLogger(JsonHostParserActor.class);

	private long actor_start;
	@Override
	/**
	 * 액터에 전달된 메세지를 처리
	 */
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
			// Rabbit MQ
			else
			{
				exec( sendData, sendData.getJson() );
			}			
			// End of First Time Check
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(Util.makeStackTrace(e));
			
		}
	}
	
	public void exec( SendData sendData, String data )
	{
		RedisCluster redisCluster					= null;
		String msg									= null;
		
		ResponseData resData						= null;
        Header header								= null;
        String body									= null;
        
		try{
	        // Redis Cluster connection
	        redisCluster							= RedisCluster.getInstance(sendData.getRedis_host(), Integer.parseInt(sendData.getRedis_port()));
	        
	        msg										= "";
	        
			Host host								= null; 
			
			CPU cpu									= null;
			Memory mem								= null;
			Network net								= null;
			ProcessStatus procStatus				= null;
			SwapMemory swap							= null;
			List<Disk> disks						= null;
			List<Process> processes					= null;
			
			// CPU Core별 사용량
			List<CPU> cpus							= null;

			// Process Thread
			HostPrcessThread processThread			= HostPrcessThread.getInstance();
			
			// Network interface Thread
			HostIFThread ifThread					= HostIFThread.getInstance();
			
			// Host disk thread
			HostDiskThread diskThread				= HostDiskThread.getInstance();

			//Host Cpu Thread
			HostCpuThread cpuThread					= HostCpuThread.getInstance();

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
			
			cpu									= host.getCpu();
			mem									= host.getMem();
			net									= host.getNet();
			procStatus							= host.getProcessStatus();
			swap								= host.getSwap();

			disks								= host.getDisks();
			processes							= host.getProcess();
			
			// CPU Core별 사용량
			cpus								= host.getCpus();
			if( cpu.getCpu_user_per() < 100 )
			{
				msg += "host,host_name="+header.getNode_name()+",host_ip="+header.getNode_ip();
				
				// CPU
				msg +=" load1="+cpu.getLoad1()+",load5="+cpu.getLoad5()+",load15="+cpu.getLoad15()+",cpu_total="+cpu.getCpu_total()+",cpu_used_percent="+cpu.getCpu_per()+",cpu_idle_percent="+cpu.getCpu_idle_per()+",cpu_irq_percent="+cpu.getCpu_irq_per()+",cpu_nice_percent="+cpu.getCpu_nice_per()+",cpu_sorfirq_percent="+cpu.getCpu_sorfirq_per()+",cpu_stolen_percent="+cpu.getCpu_stolen_per()+",cpu_sys_percent="+cpu.getCpu_sys_per()+",cpu_user_percent="+cpu.getCpu_user_per()+",cpu_wait_percent="+cpu.getCpu_wait_per();
				
				// Memory
				msg +=",mem_total="+mem.getTotal()+",mem_free="+mem.getFree()+",mem_used="+mem.getUsed()+",mem_actual_free="+mem.getActual_free()+",mem_actual_used="+mem.getActual_used()+",mem_free_percent="+mem.getFree_per()+",mem_used_percent="+mem.getUsed_per();
				
				// Swap
				if( swap.getFree_per() != null && swap.getUsed_per() != null)
					msg +=",swap_total="+swap.getTotal()+",swap_free="+swap.getFree()+",swap_pagein="+swap.getPageIn()+",swap_pageout="+swap.getPageOut()+",swap_used="+swap.getUsed()+",swap_free_percent="+swap.getFree_per()+",swap_used_percent="+swap.getUsed_per();
				else
					msg +=",swap_total="+swap.getTotal()+",swap_free="+swap.getFree()+",swap_pagein="+swap.getPageIn()+",swap_pageout="+swap.getPageOut()+",swap_used="+swap.getUsed()+",swap_free_percent=0,swap_used_percent=0";
				
				// Process
				msg += ",proc_total="+procStatus.getTotal()+",proc_sleeping="+procStatus.getSleeping()+",proc_running="+procStatus.getRunning()+",proc_stopped="+procStatus.getStopped()+",proc_zombie="+procStatus.getZombie()+",proc_idle="+procStatus.getIdle()+",timestamp="+resData.getTimestamp()+"\n";
			}
			
			// Process
			processThread.set(header.getNode_name(), header.getNode_ip(), processes, mem.getTotal(), resData.getTimestamp());
			
			// Network
			ifThread.set(header.getNode_name(), header.getNode_ip(), net.getIfaces(), resData.getTimestamp());
			
			// Disk
			diskThread.set(header.getNode_name(), header.getNode_ip(), disks, resData.getTimestamp());
			
			// Cpu
			cpuThread.set( header.getNode_name(), header.getNode_ip(), cpus, resData.getTimestamp());
			
			
			// Process 
			for( com.nexcloud.fullfillment.host.domain.Process process : processes )
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
			
			redisCluster.put(Const.HOST, header.getNode_ip(), Util.beanToJson(host));
			
			this.send(msg);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(Util.makeStackTrace(e));
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
		logger.error("Host Data Parsing Timestamp :"+(System.currentTimeMillis() - actor_start));
	}
}