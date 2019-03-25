package com.nexcloud.api.akka.actor;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import com.nexcloud.fullfillment.host.domain.Process;
import com.nexcloud.util.SendDataLoader;
import com.nexcloud.util.Util;

@Configuration
@EnableAutoConfiguration
public class HostPrcessThread extends Thread{

	static final Logger 	logger 			= LoggerFactory.getLogger(HostPrcessThread.class);
	
	private String msg						= null;

	private String node_name				= null;
	private String node_ip					= null;
	private long mem_total					= 0l;
	private long timestamp					= 0l;

	
	private List<Process> processes;
	private List<Map<String,Object>> list	= null;
	
	
	private static HostPrcessThread thisObj = null;

	
	public synchronized static HostPrcessThread getInstance(){
		if ( thisObj == null ){
			try {
				thisObj = new HostPrcessThread();
			}catch(IndexOutOfBoundsException ie){
				System.out.println("HostPrcessThread Class getInstance IndexOutOfBoundsException Error = " +ie);
			}catch(NullPointerException ne){
				System.out.println("HostPrcessThread Class getInstance NullPointerException Error = " +ne);
			} catch(Exception e) {
				System.out.println("HostPrcessThread Class getInstance Exception Error = " + e);
			}	
		}	
		
		return thisObj;
	}
	
	public HostPrcessThread(){ 
		list								= new ArrayList<Map<String,Object>>();
		start();
	}
	
	
	/**
	 * Resource Data Set
	 * @param key
	 * @param object
	 */
	public synchronized void set(String node_name, String node_ip, List<Process> processes, long mem_total, long timestamp)
	{
		Map<String,Object> data				= new HashMap<String, Object>();
		try{
			data.put("node_name", node_name);
			data.put("node_ip", node_ip);
			data.put("processes", processes);
			data.put("mem_totla", mem_total);
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
		String pattern 										= "#####.###";
        DecimalFormat dformat 								= new DecimalFormat( pattern );
        
        Map<String,Object> data								= null;
        while(true)
        {
			try
			{
				data										= get();
				
				if( data != null )
				{
					node_name								= (String)data.get("node_name");
							
					node_ip									= (String)data.get("node_ip");
					mem_total								= (Long)data.get("mem_totla");
					timestamp								= (Long)data.get("timestamp");
					processes								= (List<Process>)data.get("processes");
					msg										= "";
					for( Process process : processes )
					{
						try{
							double cpu_used					= 0d;
							if( process.getCpu_usage() > 0 )
								cpu_used = Double.parseDouble(dformat.format((process.getCpu_usage()/100)));
							
							double mem_used 				= Double.parseDouble(dformat.format((Double.parseDouble(Long.toString(process.getMem_rss()))/(1024*1024*1024))));
							double mem_used_percent 		= Double.parseDouble(dformat.format((Double.parseDouble(Long.toString(process.getMem_rss()))/Double.parseDouble(Long.toString(mem_total))*100)));
							
							
							msg += "host_process,host_name="+node_name+",host_ip="+node_ip+",pid="+process.getPid()+",name="+process.getName();
							msg += " cpu_used_percent="+process.getCpu_usage()+",cpu_used="+cpu_used+",mem_used_percent="+mem_used_percent+",mem_used="+mem_used+",timestamp="+timestamp+"\n";
						}catch(Exception e){
							e.printStackTrace();
						}
					}
					
					if( msg != null && !"".equals(msg.trim()) )
					{
						this.send(msg);
					}
				}else
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