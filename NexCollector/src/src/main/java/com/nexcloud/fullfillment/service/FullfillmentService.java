package com.nexcloud.fullfillment.service;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.nexcloud.api.kafka.LogDBConsumer;
import com.nexcloud.fullfillment.domain.common.Header;
import com.nexcloud.fullfillment.domain.common.ResponseData;
import com.nexcloud.fullfillment.mapper.DBMapper;
import com.nexcloud.log.domain.Log;
import com.nexcloud.util.Const;
import com.nexcloud.util.Util;

@Service
@Configuration
@PropertySource("classpath:application.properties")
public class FullfillmentService {

	@Autowired
	DBMapper dbMapper;
	
	@Value("${spring.kafka.zookeeper}")
	private String kafka_zookeeper;
	
	@Value("${spring.kafka.host}")
	private String kafka_host;
	
	@Value("${spring.kafka.port}")
	private String kafka_port;
	
	static final Logger logger = LoggerFactory.getLogger(FullfillmentService.class);

	/**
	 * Kafka Log topic데이터 Mysql에 저장
	 * kafka topic : log
	 * @throws Exception
	 */
	public void logCrawlig() throws Exception {
		
		try {
			Log log							= null;
			if( LogDBConsumer.getInstance().init(kafka_zookeeper, kafka_host, kafka_port, Const.LOG_TOPIC, Const.LOG_TOPIC+"_group") )
			{
				ConsumerRecords<String, String> records = LogDBConsumer.getInstance().read( );
	    		List<Log> list	= new ArrayList<Log>();
	    		for (ConsumerRecord<String, String> record : records)
		        {
	    			if( record.value() == null || "".equals(record.value())) continue;
	    			
	    			
	    			/////////////////////////////////////////////////////
	    			/**
	    	         * Docker Container Log data 
	    	         */
	    	        ResponseData resData			= null;
	    	        Header header					= null;
	    	        String body						= null;
	    	        String container_id				= null;
	    	        
	    	        resData							= Util.JsonTobean(record.value(), ResponseData.class);
	    	        header							= resData.getHeader();
	    			body							= resData.getBody();
	    			container_id					= resData.getContainer_id();	    			
	    			log								= Util.JsonTobean(body, Log.class);
	    			
	    			log.setContainer_id(container_id);
	    			log.setHost_ip(header.getNode_ip());

	    			list.add(log);
	    			log								= null;
		        }
	    		
	    		if( list.size() > 0 )
	    		{
		    		Map<String, Object> map	= new HashMap<String, Object>();
		    		map.put("list", list);
		    		dbMapper.insertLog(map);
	    		}
			}
		} catch ( Exception e) {
			e.printStackTrace();
			logger.error(Util.makeStackTrace(e));
		}
	}
}
