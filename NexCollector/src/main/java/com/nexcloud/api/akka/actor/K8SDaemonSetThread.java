
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

import com.nexcloud.fullfillment.k8s.domain.Item;
import com.nexcloud.util.SendDataLoader;
import com.nexcloud.util.Util;

@Configuration
@EnableAutoConfiguration
public class K8SDaemonSetThread extends Thread {

	static final Logger 	logger 				= LoggerFactory.getLogger(K8SDaemonSetThread.class);
	
	private String msg							= null;
	private String msg_cluster					= null;

	private List<Item> items					= null;
	
	private String cluster_id					= null;
	
	
	private List<Map<String,Object>> list		= null;
	
	
	private static K8SDaemonSetThread thisObj 	= null;

	
	public synchronized static K8SDaemonSetThread getInstance(){
		if ( thisObj == null ){
			//System.out.println("ConfigLoader getInstance 실행!!!!!!!!!!!!!!!");
			try {
				thisObj = new K8SDaemonSetThread();
			}catch(IndexOutOfBoundsException ie){
				System.out.println("K8SDaemonSetThread Class getInstance IndexOutOfBoundsException Error = " +ie);
			}catch(NullPointerException ne){
				System.out.println("K8SDaemonSetThread Class getInstance NullPointerException Error = " +ne);
			} catch(Exception e) {
				System.out.println("K8SDaemonSetThread Class getInstance Exception Error = " + e);
			}	
		}	
		
		return thisObj;
	}
	
	public K8SDaemonSetThread(){ 
		list				= new ArrayList<Map<String,Object>>();
		start();
	}
	
	
	/**
	 * Resource Data Set
	 * @param key
	 * @param object
	 */
	public synchronized void set( String cluster_id, List<Item> items )
	{
		Map<String,Object> data	= new HashMap<String, Object>();
		try{
			data.put("items", items);
			data.put("cluster_id", cluster_id);
			
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
		String pattern 						= "#####.###";
        DecimalFormat dformat 				= new DecimalFormat( pattern );
        
        Map<String,Object> data				= null;
        
        while(true)
        {
			try
			{
				data						= get();
				if( data != null )
				{
					items					= (List<Item>)data.get("items");
					cluster_id				= (String)data.get("cluster_id");
					
					msg						= "";
					msg_cluster				= "";
					
					for( Item item : items )
					{
						msg 				+= "k8s_daemonset,daemonset="+item.getMetadata().getName()+",namespace="+item.getMetadata().getNamespace();
						msg 				+= " current_number_scheduled="+item.getStatus().getCurrentNumberScheduled()+",desired_number_scheduled="+item.getStatus().getDesiredNumberScheduled()+",number_available="+item.getStatus().getNumberAvailable()+",number_misscheduled="+item.getStatus().getNumberMisscheduled()+",number_ready="+item.getStatus().getNumberReady()+",number_unavailable="+item.getStatus().getUnavailableReplicas()+",updated_number_scheduled="+item.getStatus().getUpdatedNumberScheduled()+",observed_generation="+item.getStatus().getObservedGeneration()+"\n";
						
						msg_cluster			+= "k8s_daemonset,cluster_id="+cluster_id+",daemonset="+item.getMetadata().getName()+",namespace="+item.getMetadata().getNamespace();
						msg_cluster			+= " current_number_scheduled="+item.getStatus().getCurrentNumberScheduled()+",desired_number_scheduled="+item.getStatus().getDesiredNumberScheduled()+",number_available="+item.getStatus().getNumberAvailable()+",number_misscheduled="+item.getStatus().getNumberMisscheduled()+",number_ready="+item.getStatus().getNumberReady()+",number_unavailable="+item.getStatus().getUnavailableReplicas()+",updated_number_scheduled="+item.getStatus().getUpdatedNumberScheduled()+",observed_generation="+item.getStatus().getObservedGeneration()+"\n";
					}
					
					if( msg != null && !"".equals(msg.trim()) )
						this.send(msg+msg_cluster);
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