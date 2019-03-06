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
public class K8SReplicasetThread extends Thread {

	static final Logger 	logger 				= LoggerFactory.getLogger(K8SReplicasetThread.class);
	
	private String msg							= null;
	
	private List<Item> items					= null;
	
	
	private List<Map<String,Object>> list		= null;
	
	
	private static K8SReplicasetThread thisObj 	= null;

	
	public synchronized static K8SReplicasetThread getInstance(){
		if ( thisObj == null ){
			//System.out.println("ConfigLoader getInstance 실행!!!!!!!!!!!!!!!");
			try {
				thisObj = new K8SReplicasetThread();
			}catch(IndexOutOfBoundsException ie){
				System.out.println("K8SReplicasetThread Class getInstance IndexOutOfBoundsException Error = " +ie);
			}catch(NullPointerException ne){
				System.out.println("K8SReplicasetThread Class getInstance NullPointerException Error = " +ne);
			} catch(Exception e) {
				System.out.println("K8SReplicasetThread Class getInstance Exception Error = " + e);
			}	
		}	
		
		return thisObj;
	}
	
	public K8SReplicasetThread(){ 
		list				= new ArrayList<Map<String,Object>>();
		start();
	}
	
	
	/**
	 * Resource Data Set
	 * @param key
	 * @param object
	 */
	public synchronized void set( List<Item> items )
	{
		Map<String,Object> data	= new HashMap<String, Object>();
		try{
			data.put("items", items);
			
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
        
		//logger.error("["+Thread.currentThread()+"]Host Start!!");
        Map<String,Object> data				= null;
        
        while(true)
        {
			try
			{
				data						= get();
				if( data != null )
				{
					items					= (List<Item>)data.get("items");
					
					msg						= "";
					
					for( Item item : items )
					{
						msg 							+= "k8s_replicaset,replicaset="+item.getMetadata().getName()+",namespace="+item.getMetadata().getNamespace();
						msg 							+= " metadata_generation="+item.getMetadata().getGeneration()+",spec_replicas="+item.getSpec().getReplicas()+",fully_labeled_replicas="+item.getStatus().getFullyLabeledReplicas();
						msg 							+= ",observed_generation="+item.getStatus().getObservedGeneration()+",ready_replicas="+item.getStatus().getReadyReplicas()+",recently_replicas="+item.getStatus().getReplicas()+"\n";
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
		//logger.error("["+Thread.currentThread()+"]Host End!!");
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