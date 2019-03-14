package com.nexcloud.api.controller.host;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nexcloud.api.service.host.HostAgentService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/v1/host")
public class HostAgentController {
	static final Logger logger = LoggerFactory.getLogger(HostAgentController.class);

	@Autowired private HostAgentService hostAgentService;
	
	/**
	 * All Agent status조회
	 * @return
	 */
	@ApiOperation(value="All Agent status 조회", httpMethod="GET", notes="host ip, host name, status")
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	@RequestMapping(value="/agent/status", method=RequestMethod.GET)
	public String agentStatus( ) {
		return hostAgentService.getAgentStatus( );
	}
	
	/**
	 * Host별 Agent Status 조회
	 * @param hostIp
	 * @return
	 */
	@ApiOperation(value="Host Agent status 조회", httpMethod="GET", notes="host ip, host name, status")
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
	@RequestMapping(value="/{hostIp}/agent/status", method=RequestMethod.GET)
	public String agentStatus( @PathVariable(value="hostIp", required=false) String hostIp) {
		return hostAgentService.getAgentStatus(hostIp);
	}
}
