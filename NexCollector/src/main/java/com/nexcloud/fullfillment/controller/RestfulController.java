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
package com.nexcloud.fullfillment.controller;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nexcloud.fullfillment.domain.common.RequestData;
import com.nexcloud.fullfillment.domain.common.ResponseData;
import com.nexcloud.fullfillment.service.CollectorService;
import com.nexcloud.util.Const;
import com.nexcloud.util.Util;

@Configuration
@RestController
@EnableAutoConfiguration
@ComponentScan
@RequestMapping(value = "/v1")
public class RestfulController extends SpringBootServletInitializer implements WebApplicationInitializer {
	
	@Autowired
    private CollectorService collectorService;
	
	static final Logger logger = LoggerFactory.getLogger(RestfulController.class);
	
	/**
	* Agent Validation Check
	* 
	* @return
	* @throws Exception
	*/

	@RequestMapping(value="/agent/validation", method=RequestMethod.POST)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> agentValidation( @RequestBody String data ) throws Exception {
		//System.out.println("/agent/validation");
		ResponseEntity<ResponseData> response 			= null;
		RequestData requestData							= null;
		try{
			requestData									= Util.JsonTobean(data, RequestData.class);
			
			response 									= collectorService.agentValidation( requestData );
		}catch(Exception e){
			e.printStackTrace();
			
			ResponseData resData	= new ResponseData();
			resData.setStatus(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	/**
	* Restful API를 이용한 데이터 수집
	* HTTP Metric
	* @return
	* @throws Exception
	*/

	@RequestMapping(value="/restful/collector/http", method=RequestMethod.POST)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getCollectorHttp( @RequestBody String data ) throws Exception {
		logger.info("/restful/collector/http");
		ResponseEntity<ResponseData> response 			= null;
		RequestData requestData							= null;
		try{
			requestData									= Util.JsonTobean(data, RequestData.class);
			
			response 									= collectorService.exec(requestData);
		}catch(Exception e){
			e.printStackTrace();
			
			ResponseData resData	= new ResponseData();
			resData.setStatus(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	
	/**
	* Restful API를 이용한 데이터 수집
	* Host Metric
	* @return
	* @throws Exception
	*/

	@RequestMapping(value="/restful/collector/host", method=RequestMethod.POST)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getCollectorHost( @RequestBody String data ) throws Exception {
		logger.info("/restful/collector/host");
		ResponseEntity<ResponseData> response 			= null;
		RequestData requestData							= null;
		try{
			requestData									= Util.JsonTobean(data, RequestData.class);
			
			response 									= collectorService.exec(requestData);
		}catch(Exception e){
			e.printStackTrace();
			
			ResponseData resData	= new ResponseData();
			resData.setStatus(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	
	/**
	* Restful API를 이용한 데이터 수집
	* Docker Metric
	* @return
	* @throws Exception
	*/

	@RequestMapping(value="/restful/collector/docker", method=RequestMethod.POST)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getCollectorDocker( @RequestBody String data ) throws Exception {
		logger.info("/restful/collector/docker");
		ResponseEntity<ResponseData> response 			= null;
		RequestData requestData							= null;
		try{
			requestData									= Util.JsonTobean(data, RequestData.class);
			
			response 									= collectorService.exec(requestData);
		}catch(Exception e){
			e.printStackTrace();
			
			ResponseData resData	= new ResponseData();
			resData.setStatus(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	/**
	* Restful API를 이용한 데이터 수집
	* Kubernetes Metric
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="/restful/collector/k8sapi", method=RequestMethod.POST)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> getCollectorK8SAPI( @RequestBody String data ) throws Exception {
		logger.info("/restful/collector/k8sapi");
		ResponseEntity<ResponseData> response 			= null;
		RequestData requestData							= null;
		try{
			requestData									= Util.JsonTobean(data, RequestData.class);
			
			response 									= collectorService.exec(requestData);
		}catch(Exception e){
			e.printStackTrace();
			
			ResponseData resData	= new ResponseData();
			resData.setStatus(Const.INTERNAL_SERVER_ERROR);
			resData.setMessage(Const.FAIL);
			response = new ResponseEntity<ResponseData>(resData, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
}
