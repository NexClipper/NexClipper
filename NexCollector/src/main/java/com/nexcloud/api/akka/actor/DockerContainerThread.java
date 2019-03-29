
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import com.nexcloud.fullfillment.docker.domain.Containers;
import com.nexcloud.fullfillment.docker.domain.stat.NetworkStats;
import com.nexcloud.util.SendDataLoader;
import com.nexcloud.util.Util;

@Configuration
@EnableAutoConfiguration
public class DockerContainerThread extends Thread {

	static final Logger 	logger 					= LoggerFactory.getLogger(DockerContainerThread.class);
	
	private String msg								= null;
	
	private String node_name						= null;
	private String node_ip							= null;
	private List<Containers> containers				= null;
	private long timestamp							= 0l;
	
	private List<Map<String,Object>> list			= null;
	
	private static DockerContainerThread thisObj 	= null;

	
	public synchronized static DockerContainerThread getInstance(){
		if ( thisObj == null ){
			try {
				thisObj = new DockerContainerThread();
			}catch(IndexOutOfBoundsException ie){
				System.out.println("DockerContainerThread Class getInstance IndexOutOfBoundsException Error = " +ie);
			}catch(NullPointerException ne){
				System.out.println("DockerContainerThread Class getInstance NullPointerException Error = " +ne);
			} catch(Exception e) {
				System.out.println("DockerContainerThread Class getInstance Exception Error = " + e);
			}	
		}	
		
		return thisObj;
	}
	
	public DockerContainerThread(){ 
		list										= new ArrayList<Map<String,Object>>();
		start();
	}
	
	
	/**
	 * Resource Data Set
	 * @param key
	 * @param object
	 */
	public synchronized void set(String node_name, String node_ip, List<Containers> containers, long timestamp)
	{
		Map<String,Object> data						= new HashMap<String, Object>();
		try{
			data.put("node_name"		, node_name);
			data.put("node_ip"			, node_ip);
			data.put("containers"		, containers);
			data.put("timestamp"		, timestamp);
			
			list.add(data);
			//inputdata++;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Resource Data Get
	 * @param key
	 * @return
	 */
	public synchronized Map<String,Object> get( )
	{
		try{
			if( list.size() > 0)
			{
				return list.remove(0);
			}
			else
				return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void run()
	{
        Map<String,Object> data						= null;
        String type									= null; 
		String task_id								= null; 
		String container_id							= null; 
		
		Map<String, NetworkStats> networks			= null; 
		Iterator<String> keys 						= null; 
		String key									= null;
		NetworkStats network						= null;
		
        while(true)
        {
			try
			{
				data								= get();
				if( data != null )
				{
					node_name						= (String)data.get("node_name");
							
					node_ip							= (String)data.get("node_ip");
					timestamp						= (Long)data.get("timestamp");
					containers						= (List<Containers>)data.get("containers");
					
					msg								= "";
				
					for( Containers container : containers )
					{
						type						= container.getType();
						task_id						= container.getNames().get(0);
						container_id				= container.getId();

						// Docker type is kubernetes
						if( "KUBERNETES".indexOf(type.toUpperCase()) != -1)
						{
							if("container".indexOf(container.getLabels().getKubernetes_docker_type()) != -1)
								task_id			= container.getLabels().getKubernetes_container_name();
							else
								continue;
						}
						
						msg += "docker_container,host_name="+node_name+",host_ip="+node_ip+",type="+type+",task_id="+task_id+",container_id="+container_id;
						
						// CPU/Memory/Disk IO
						msg += " cpu_used_percent="+container.getCpuPercent()+",mem_used_percent="+container.getMemPercent()+",mem_used="+container.getUsed_mem()+",mem_limit="+container.getLimit_mem()+",disk_io_read="+container.getBlock_io_read()+",disk_io_write="+container.getBlock_io_write()+",timestamp="+timestamp+"\n";
						
						
						// Network
						networks							= container.getNetworks();
						keys 								= networks.keySet().iterator();
						while( keys.hasNext() ){
				            key 							= keys.next();
				            network							= networks.get(key);
				            msg += "docker_network,host_name="+node_name+",host_ip="+node_ip+",type="+type+",task_id="+task_id+",container_id="+container_id+",interface="+key;
				            msg += " rx_bytes="+network.getRx_bytes()+",rx_packets="+network.getRx_packets()+",rx_errors="+network.getRx_errors()+",rx_dropped="+network.getRx_dropped()+",tx_bytes="+network.getTx_bytes()+",tx_packets="+network.getTx_packets()+",tx_errors="+network.getTx_errors()+",tx_dropped="+network.getTx_dropped()+",timestamp="+timestamp+"\n";
				        }
					}
					
					if( msg != null && !"".equals(msg.trim()) )
						this.send(msg);
				}
				else
					Thread.sleep(10);
	
			}catch(Exception e){
				e.printStackTrace();
			}
        }
	}
	
	/**
	 * Influx DB Write
	 * @param influxDB
	 * @param msg
	 */
	public void send( String msg )
	{
		try {
			SendDataLoader.getInstance().set(msg);
		} catch (Exception e) {
			System.out.println("influx send data :: " + msg );
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(Util.makeStackTrace(e));
		}
	}
}