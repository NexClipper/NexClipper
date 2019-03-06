package com.nexcloud.api.controller.host;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nexcloud.api.service.host.HostService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/v1/host")
public class HostController {
	static final Logger logger = LoggerFactory.getLogger(HostController.class);

	@Autowired private HostService hostService;

	/**
	 * All Host information
	 * @return
	 */
	@ApiOperation(value="All host information", httpMethod="GET", notes="host ip, host name, uptime, ....")
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	@RequestMapping(value="/snapshot", method=RequestMethod.GET)
	public String getHosts() { 
		return hostService.getHosts();
	}
	
	
	/**
	 * Host별 Information
	 * @param hostIp
	 * @return
	 */
	@ApiOperation(value="Host information", httpMethod="GET", notes="host ip, host name, uptime, ....")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	@RequestMapping(value="/{hostIp}/snapshot", method=RequestMethod.GET)
	public String getHost(
		@PathVariable(value="hostIp", required=false) String hostIp) {
		return hostService.getHost(hostIp);
	}
}
