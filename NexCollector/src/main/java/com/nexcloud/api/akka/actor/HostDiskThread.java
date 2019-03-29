
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

import com.nexcloud.fullfillment.host.domain.Disk;
import com.nexcloud.util.SendDataLoader;
import com.nexcloud.util.Util;

@Configuration
@EnableAutoConfiguration
public class HostDiskThread extends Thread {

	static final Logger 	logger 			= LoggerFactory.getLogger(HostDiskThread.class);
	
	private String msg						= null;

	private String node_name				= null;
	private String node_ip					= null;
	private List<Disk> disks				= null;
	private long timestamp					= 0l;
	
	private List<Map<String,Object>> list	= null;
	
	private static HostDiskThread thisObj 	= null;
	
	public synchronized static HostDiskThread getInstance(){
		if ( thisObj == null ){
			try {
				thisObj = new HostDiskThread();
			}catch(IndexOutOfBoundsException ie){
				System.out.println("HostDiskThread Class getInstance IndexOutOfBoundsException Error = " +ie);
			}catch(NullPointerException ne){
				System.out.println("HostDiskThread Class getInstance NullPointerException Error = " +ne);
			} catch(Exception e) {
				System.out.println("HostDiskThread Class getInstance Exception Error = " + e);
			}	
		}	
		
		return thisObj;
	}
	
	public HostDiskThread(){ 
		list								= new ArrayList<Map<String,Object>>();
		start();
	}
	
	
	/**
	 * Resource Data Set
	 * @param key
	 * @param object
	 */
	public synchronized void set(String node_name, String node_ip, List<Disk> disks, long timestamp)
	{
		Map<String,Object> data				= new HashMap<String, Object>();
		try{
			data.put("node_name", node_name);
			data.put("node_ip", node_ip);
			data.put("disks", disks);
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
					disks					= (List<Disk>)data.get("disks");
					
					actor_start				= System.currentTimeMillis();
					
					msg						= "";
					
					for( Disk disk : disks )
					{
						msg += "host_disk,host_name="+node_name+",host_ip="+node_ip+",dev_name="+disk.getDevName()+",mount_name="+disk.getMountName();
						msg += " free="+disk.getFree()+",used="+disk.getUsed()+",total="+disk.getTotal()+",used_percent="+disk.getUsed_per()+",readbyte="+disk.getReadBytes()+",writebyte="+disk.getWriteBytes()+",reads="+disk.getReads()+",writes="+disk.getWrites()+",timestamp="+timestamp+"\n";
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