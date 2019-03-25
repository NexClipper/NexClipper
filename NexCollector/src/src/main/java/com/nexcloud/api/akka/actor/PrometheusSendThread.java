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

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import com.nexcloud.util.Util;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.PushGateway;

@Configuration
@EnableAutoConfiguration
public class PrometheusSendThread extends Thread {

	static final Logger 	logger 			= LoggerFactory.getLogger(PrometheusSendThread.class);
	
	private String msg						= null;

	private String pushgateway_endpoint		= null;
	
	private List<String> list				= null;
	
	private static PrometheusSendThread thisObj 	= null;
	
	public PrometheusSendThread( String pushgateway_endpoint )
	{
		this.pushgateway_endpoint			= pushgateway_endpoint;
		list								= new ArrayList<String>();
		start();
	}

	public synchronized static PrometheusSendThread getInstance(String pushgateway_endpoint){
		if ( thisObj == null ){
			try {
				thisObj = new PrometheusSendThread(pushgateway_endpoint);
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
	
	public PrometheusSendThread(){ 
		list								= new ArrayList<String>();
		start();
	}
	
	
	/**
	 * Resource Data Set
	 * @param key
	 * @param object
	 */
	public synchronized void set( String msg)
	{
		try{
			list.add(msg);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Resource Data Get
	 * @param key
	 * @return
	 */
	public synchronized String get( )
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
		CollectorRegistry registry 				= new CollectorRegistry();
        String data								= null;
        long actor_start						= 0;
        String[]arrayData						= null;
        String[]arrayLineData					= null;
        String[]dummy							= null;
        Gauge gauge 							= null;
        String[]labelNames						= null;
        String[]labels							= null;
        String name								= null;
        String[]keyValue						= null;
        int size								= 0;
        String job								= null;
        
        while(true)
        {
			try
			{
				data							= get();
				if( data != null )
				{
					arrayData					= Util.split(data, "\n\r");
					for( String lineData : arrayData )
					{
						actor_start				= System.currentTimeMillis();
						// arrayData[0] = 칼럼
						// arrayData[1]	= 데이터
						arrayLineData			= Util.split(lineData, " ");
						dummy					= Util.split(arrayLineData[0], ",");
						
						labelNames				= null;
						labels					= null;
						
						labelNames				= new String[dummy.length-1];
						labels					= new String[dummy.length-1];
						size					= dummy.length;
						
						name					= dummy[0];
						
						job						= "";
						for( int i=1;i<dummy.length;i++ )
						{
							keyValue			= Util.split(dummy[i], "=");
							labelNames[i-1]		= keyValue[0];
							labels[i-1]			= keyValue[1];
							
							job					+= keyValue[1].replaceAll("/","_");
						}
						
						if( "".equals(job))
						{
							if( name.indexOf("cluster") != -1)
								job					= "cluster";
							else
								job					= Util.randomJobName();
						}
						
						// Labels 데이터 세팅
						//gauge				= null;
						//gauge 				= Gauge.build().name(name).labelNames(labelNames).register(registry);
						//gauge.labels(labels);

						// Value Set
						dummy					= Util.split(arrayLineData[1], ",");
						for( int i=0;i<dummy.length;i++ )
						{
							keyValue			= Util.split(dummy[i], "=");
							
							gauge				= null;
							
							registry			= null;
							registry 			= new CollectorRegistry();
							gauge 				= Gauge.build().name(name+"_"+keyValue[0]).help("Nexclipper agent metric : "+keyValue[0]).labelNames(labelNames).register(registry);
							gauge.labels(labels).set(Double.parseDouble(keyValue[1]));
							//gauge.set(Double.parseDouble(keyValue[1]));
							
							prometheusSend(registry, job);
						}
						
						logger.debug("Data parsing and prometheus send time stamp :"+(System.currentTimeMillis()-actor_start));
					}
				}
				else
					Thread.sleep(10);
	
			}catch(Exception e){
				e.printStackTrace();
			}
        }
	}
	
	
	public void prometheusSend( CollectorRegistry registry, String job )
	{
		try{
			long start	= System.currentTimeMillis();
		    PushGateway pg = new PushGateway(this.pushgateway_endpoint);
		    pg.pushAdd(registry, job);
		    //logger.error("Prometheus Send time stamp :"+(System.currentTimeMillis()-start));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
	}
}