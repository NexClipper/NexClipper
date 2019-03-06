package com.nexcloud.fullfillment.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nexcloud.fullfillment.domain.common.ResponseData;
import com.nexcloud.fullfillment.domain.common.RequestData;
import com.nexcloud.fullfillment.service.CollectorService;
import com.nexcloud.util.Const;
import com.nexcloud.util.Util;

@Controller
@RequestMapping(value="/v1")
public class LogController {

	@Autowired
    private CollectorService collectorService;
	
	static final Logger logger = LoggerFactory.getLogger(LogController.class);
	
	@RequestMapping(value="/log", method=RequestMethod.POST)
	@ResponseBody
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<ResponseData> setLog( @RequestBody String data ) throws Exception {
		
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
