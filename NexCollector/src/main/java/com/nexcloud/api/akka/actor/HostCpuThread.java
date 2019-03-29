
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import com.nexcloud.fullfillment.host.domain.CPU;
import com.nexcloud.util.SendDataLoader;
import com.nexcloud.util.Util;

@Configuration
@EnableAutoConfiguration
public class HostCpuThread extends Thread {

	static final Logger 	logger 			= LoggerFactory.getLogger(HostCpuThread.class);
	
	private String msg						= null;

	private String node_name				= null;
	private String node_ip					= null;
	private List<CPU> cpus					= null;
	private long timestamp					= 0l;
	
	private List<Map<String,Object>> list	= null;
	
	private static HostCpuThread thisObj 	= null;

	public synchronized static HostCpuThread getInstance(){
		if ( thisObj == null ){
			try {
				thisObj = new HostCpuThread();
			}catch(IndexOutOfBoundsException ie){
				System.out.println("HostCpuThread Class getInstance IndexOutOfBoundsException Error = " +ie);
			}catch(NullPointerException ne){
				System.out.println("HostCpuThread Class getInstance NullPointerException Error = " +ne);
			} catch(Exception e) {
				System.out.println("HostCpuThread Class getInstance Exception Error = " + e);
			}	
		}	
		
		return thisObj;
	}
	
	public HostCpuThread(){ 
		list								= new ArrayList<Map<String,Object>>();
		start();
	}
	
	
	/**
	 * Resource Data Set
	 * @param key
	 * @param object
	 */
	public synchronized void set(String node_name, String node_ip, List<CPU> cpus, long timestamp)
	{
		Map<String,Object> data				= new HashMap<String, Object>();
		try{
			data.put("node_name", node_name);
			data.put("node_ip", node_ip);
			data.put("cpus", cpus);
			data.put("timestamp", timestamp);
			
			list.add(data);
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
		String pattern 						= "#####.###";
        DecimalFormat dformat 				= new DecimalFormat( pattern );
        
        Map<String,Object> data				= null;
        long actor_start					= 0;
        
        while(true)
        {
			try
			{
				data						= get();
				if( data != null )
				{
					node_name				= (String)data.get("node_name");
							
					node_ip					= (String)data.get("node_ip");
					timestamp				= (Long)data.get("timestamp");
					cpus					= (List<CPU>)data.get("cpus");
					
					actor_start				= System.currentTimeMillis();
					
					msg						= "";
					
					int cpu_core = 0;
					for( CPU coreCpu : cpus )
					{
						if( coreCpu.getCpu_per() >= 100 ) continue;
						
						msg += "host_cpu,host_name="+node_name+",host_ip="+node_ip+",core="+cpu_core;
						
						// CPU
						msg +=" cpu_used_percent="+coreCpu.getCpu_per()+",cpu_idle_percent="+coreCpu.getCpu_idle_per()+",cpu_irq_percent="+coreCpu.getCpu_irq_per()+",cpu_nice_percent="+coreCpu.getCpu_nice_per()+",cpu_sorfirq_percent="+coreCpu.getCpu_sorfirq_per()+",cpu_stolen_percent="+coreCpu.getCpu_stolen_per()+",cpu_sys_percent="+coreCpu.getCpu_sys_per()+",cpu_user_percent="+coreCpu.getCpu_user_per()+",cpu_wait_percent="+coreCpu.getCpu_wait_per()+",timestamp="+timestamp+"\n";
						
						coreCpu.setCore(cpu_core);
						
						cpu_core++;
					}
					
					if( msg != null && !"".equals(msg.trim()) )
					{
						this.send(msg);
					}
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