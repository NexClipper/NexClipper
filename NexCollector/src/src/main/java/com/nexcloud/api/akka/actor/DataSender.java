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

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import com.nexcloud.api.akka.domain.SendData;
import com.nexcloud.util.SendDataLoader;
import com.nexcloud.util.rest.HttpAPI;
import com.nexcloud.util.rest.RestClient.Method;

import akka.actor.UntypedActor;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.PushGateway;

public class DataSender extends UntypedActor{
	static final Logger logger = LoggerFactory.getLogger(DataSender.class);
	
	private String influxdb_endpoint;
	
	private String influxdb_datasource;
	
	private String timeseries_db;
	
	private String pushgateway_endpoint;
	
	@Override
	/**
	 * 액터에 전달된 메세지를 처리
	 */
	@Scope("prototype")
	public void onReceive(Object message) throws Exception {		
		SendData sendData				= (SendData)message;
		
		// Use timeseries db
		timeseries_db					= sendData.getTimeseries_db();
		
		// Influxdb data
        influxdb_endpoint				= sendData.getInfluxdb_endpoint();
        influxdb_datasource				= sendData.getInfluxdb_datasource();
        
        // Prometheus data
        pushgateway_endpoint			= sendData.getPushgateway_endpoint();
        
        String msg						= "";
        int idx							= 0;
        
        // tdb 0:ALL, 1:influxdb, 2:prometheus 
        int tdb							= 0;
        
        if( timeseries_db == null || "INFLUX".equals(timeseries_db.toUpperCase()) )
        	tdb							= 1;
        else if( timeseries_db != null && "PROMETHEUS".equals(timeseries_db.toUpperCase()) )
        	tdb							= 2;
        else
        	tdb							= 0;
        
        // Prometheus sender Thread
     	PrometheusSendThread pushgate	= PrometheusSendThread.getInstance( pushgateway_endpoint );
        
		while(true)
		{
			try{
				
				msg += SendDataLoader.getInstance().get();
				idx++;
				
				if( idx == 20 )
				{
					if( !"".equals(msg))
					{
						// Influxdb send
						if( tdb != 2 )
							influxSend(msg);
						
						// Promethues send
						if( tdb != 1 )
							pushgate.set(msg);
					}
					
					idx 				= 0;
					msg					= "";
					
					Thread.sleep(10);
				}
				
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
	public void influxSend( String msg )
	{
		try {
			long start	= System.currentTimeMillis();
			
			HttpAPI.request(influxdb_endpoint+"/write?consistency=any&db="+influxdb_datasource+"&precision=ns", MediaType.TEXT_PLAIN_TYPE, Method.POST, msg);
			logger.debug("Influx Send time stamp :"+(System.currentTimeMillis()-start));
			
		} catch (Exception e) {
			System.out.println("influx send data :: " + msg );
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}