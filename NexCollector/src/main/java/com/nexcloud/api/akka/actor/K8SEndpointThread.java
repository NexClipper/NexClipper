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
public class K8SEndpointThread extends Thread {

	static final Logger 	logger 				= LoggerFactory.getLogger(K8SEndpointThread.class);
	
	private String msg							= null;
	
	private List<Item> items					= null;
	
	
	private List<Map<String,Object>> list		= null;
	
	
	private static K8SEndpointThread thisObj 	= null;

	
	public synchronized static K8SEndpointThread getInstance(){
		if ( thisObj == null ){
			//System.out.println("ConfigLoader getInstance 실행!!!!!!!!!!!!!!!");
			try {
				thisObj = new K8SEndpointThread();
			}catch(IndexOutOfBoundsException ie){
				System.out.println("K8SEndpointThread Class getInstance IndexOutOfBoundsException Error = " +ie);
			}catch(NullPointerException ne){
				System.out.println("K8SEndpointThread Class getInstance NullPointerException Error = " +ne);
			} catch(Exception e) {
				System.out.println("K8SEndpointThread Class getInstance Exception Error = " + e);
			}	
		}	
		
		return thisObj;
	}
	
	public K8SEndpointThread(){ 
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
						msg 							+= "k8s_endpoint,endpoint="+item.getMetadata().getName()+",namespace="+item.getMetadata().getNamespace();
						int available					= 0;
						int not_ready					= 0;
						if( item.getSubsets() != null )
						{
							if( item.getSubsets().size() > 0 )
							{
								if( item.getSubsets().get(0).getAddresses() != null )
									available					= item.getSubsets().get(0).getAddresses().size()+item.getSubsets().get(0).getPorts().size();
							}
							
							if( item.getSubsets().size() > 0 )
							{
								if( item.getSubsets().get(0).getNotReadyAddresses() != null )
									not_ready					= item.getSubsets().get(0).getNotReadyAddresses().size()+item.getSubsets().get(0).getPorts().size();
							}
						}
						msg 							+= " address_available="+available+",address_not_ready="+not_ready+"\n";
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