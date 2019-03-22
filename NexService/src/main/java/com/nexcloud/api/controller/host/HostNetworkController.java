package com.nexcloud.api.controller.host;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexcloud.api.service.host.HostNetworkService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/v1/host/{hostIp}/network")
public class HostNetworkController {
	static final Logger logger = LoggerFactory.getLogger(HostNetworkController.class);
	
	@Autowired
	private HostNetworkService hostNetworkService;


	@RequestMapping(value="/interface/{interfaceId}/rx/byte", method=RequestMethod.GET)
	@ApiOperation(value="Host network interface rx byte", httpMethod="GET", notes="Host network interface rx byte")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "interfaceId", 
	            value = "Netwrk Interface ID(ex) eth0, enp0s25 ...", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "startTime", 
	            value = "조회 기간(ex) 1h: 현재부터 한시간전, 1d: 현재부터 하루전..", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "time", 
	            value = "시간별 평균값(ex) 1s:조회가간중 1초간 평균값, 5s:조회기간중 5초간 평균값", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    ),@ApiImplicitParam(
	            name = "limit", 
	            value = "조회 건수 (ex) 1000 : 1천건까지조회", 
	            required = true, 
	            dataType = "int", 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getNetworkRxbyte(
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="interfaceId", required=true) String interfaceId
			,@RequestParam(value="startTime", required=false, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=false, defaultValue="5s") String time
			,@RequestParam(value="limit", required=false, defaultValue="1000") int limit
	) {
		return hostNetworkService.getNetworkRxbyte(hostIp, interfaceId, startTime, time, limit);
	}
	
	@RequestMapping(value="/interface/{interfaceId}/rx/drop", method=RequestMethod.GET)
	@ApiOperation(value="Host network interface rx dropped", httpMethod="GET", notes="Host network interface rx dropped")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "interfaceId", 
	            value = "Netwrk Interface ID(ex) eth0, enp0s25 ...", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "startTime", 
	            value = "조회 기간(ex) 1h: 현재부터 한시간전, 1d: 현재부터 하루전..", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "time", 
	            value = "시간별 평균값(ex) 1s:조회가간중 1초간 평균값, 5s:조회기간중 5초간 평균값", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    ),@ApiImplicitParam(
	            name = "limit", 
	            value = "조회 건수 (ex) 1000 : 1천건까지조회", 
	            required = true, 
	            dataType = "int", 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getNetworkRxdrop(
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="interfaceId", required=true) String interfaceId
			,@RequestParam(value="startTime", required=false, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=false, defaultValue="5s") String time
			,@RequestParam(value="limit", required=false, defaultValue="1000") int limit
	) {
		return hostNetworkService.getNetworkRxdrop(hostIp, interfaceId, startTime, time, limit);
	}
	
	@RequestMapping(value="/interface/{interfaceId}/rx/error", method=RequestMethod.GET)
	@ApiOperation(value="Host network interface rx errors", httpMethod="GET", notes="Host network interface rx errors")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "interfaceId", 
	            value = "Netwrk Interface ID(ex) eth0, enp0s25 ...", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "startTime", 
	            value = "조회 기간(ex) 1h: 현재부터 한시간전, 1d: 현재부터 하루전..", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "time", 
	            value = "시간별 평균값(ex) 1s:조회가간중 1초간 평균값, 5s:조회기간중 5초간 평균값", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    ),@ApiImplicitParam(
	            name = "limit", 
	            value = "조회 건수 (ex) 1000 : 1천건까지조회", 
	            required = true, 
	            dataType = "int", 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getNetworkRxerror(
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="interfaceId", required=true) String interfaceId
			,@RequestParam(value="startTime", required=false, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=false, defaultValue="5s") String time
			,@RequestParam(value="limit", required=false, defaultValue="1000") int limit
	) {
		return hostNetworkService.getNetworkRxerror(hostIp, interfaceId, startTime, time, limit);
	}
	
	@RequestMapping(value="/interface/{interfaceId}/rx/overrun", method=RequestMethod.GET)
	@ApiOperation(value="Host network interface rx overrun", httpMethod="GET", notes="Host network interface rx overrun")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "interfaceId", 
	            value = "Netwrk Interface ID(ex) eth0, enp0s25 ...", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "startTime", 
	            value = "조회 기간(ex) 1h: 현재부터 한시간전, 1d: 현재부터 하루전..", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "time", 
	            value = "시간별 평균값(ex) 1s:조회가간중 1초간 평균값, 5s:조회기간중 5초간 평균값", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    ),@ApiImplicitParam(
	            name = "limit", 
	            value = "조회 건수 (ex) 1000 : 1천건까지조회", 
	            required = true, 
	            dataType = "int", 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getNetworkRxoverrun(
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="interfaceId", required=true) String interfaceId
			,@RequestParam(value="startTime", required=false, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=false, defaultValue="5s") String time
			,@RequestParam(value="limit", required=false, defaultValue="1000") int limit
	) {
		return hostNetworkService.getNetworkRxoverrun(hostIp, interfaceId, startTime, time, limit);
	}
	
	@RequestMapping(value="/interface/{interfaceId}/rx/packet", method=RequestMethod.GET)
	@ApiOperation(value="Host network interface rx packet", httpMethod="GET", notes="Host network interface rx packet")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "interfaceId", 
	            value = "Netwrk Interface ID(ex) eth0, enp0s25 ...", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "startTime", 
	            value = "조회 기간(ex) 1h: 현재부터 한시간전, 1d: 현재부터 하루전..", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "time", 
	            value = "시간별 평균값(ex) 1s:조회가간중 1초간 평균값, 5s:조회기간중 5초간 평균값", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    ),@ApiImplicitParam(
	            name = "limit", 
	            value = "조회 건수 (ex) 1000 : 1천건까지조회", 
	            required = true, 
	            dataType = "int", 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getNetworkRxpacket(
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="interfaceId", required=true) String interfaceId
			,@RequestParam(value="startTime", required=false, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=false, defaultValue="5s") String time
			,@RequestParam(value="limit", required=false, defaultValue="1000") int limit
	) {
		return hostNetworkService.getNetworkRxpacket(hostIp, interfaceId, startTime, time, limit);
	}
	
	@RequestMapping(value="/interface/{interfaceId}/tx/byte", method=RequestMethod.GET)
	@ApiOperation(value="Host network interface tx byte", httpMethod="GET", notes="Host network interface tx byte")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "interfaceId", 
	            value = "Netwrk Interface ID(ex) eth0, enp0s25 ...", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "startTime", 
	            value = "조회 기간(ex) 1h: 현재부터 한시간전, 1d: 현재부터 하루전..", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "time", 
	            value = "시간별 평균값(ex) 1s:조회가간중 1초간 평균값, 5s:조회기간중 5초간 평균값", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    ),@ApiImplicitParam(
	            name = "limit", 
	            value = "조회 건수 (ex) 1000 : 1천건까지조회", 
	            required = true, 
	            dataType = "int", 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getNetworkTxbyte(
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="interfaceId", required=true) String interfaceId
			,@RequestParam(value="startTime", required=false, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=false, defaultValue="5s") String time
			,@RequestParam(value="limit", required=false, defaultValue="1000") int limit
	) {
		return hostNetworkService.getNetworkTxbyte(hostIp, interfaceId, startTime, time, limit);
	}
	
	@RequestMapping(value="/interface/{interfaceId}/tx/drop", method=RequestMethod.GET)
	@ApiOperation(value="Host network interface tx dropped", httpMethod="GET", notes="Host network interface tx dropped")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "interfaceId", 
	            value = "Netwrk Interface ID(ex) eth0, enp0s25 ...", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "startTime", 
	            value = "조회 기간(ex) 1h: 현재부터 한시간전, 1d: 현재부터 하루전..", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "time", 
	            value = "시간별 평균값(ex) 1s:조회가간중 1초간 평균값, 5s:조회기간중 5초간 평균값", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    ),@ApiImplicitParam(
	            name = "limit", 
	            value = "조회 건수 (ex) 1000 : 1천건까지조회", 
	            required = true, 
	            dataType = "int", 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getNetworkTxdrop(
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="interfaceId", required=true) String interfaceId
			,@RequestParam(value="startTime", required=false, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=false, defaultValue="5s") String time
			,@RequestParam(value="limit", required=false, defaultValue="1000") int limit
	) {
		return hostNetworkService.getNetworkTxdrop(hostIp, interfaceId, startTime, time, limit);
	}
	
	@RequestMapping(value="/interface/{interfaceId}/tx/error", method=RequestMethod.GET)
	@ApiOperation(value="Host network interface tx errors", httpMethod="GET", notes="Host network interface tx errors")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "interfaceId", 
	            value = "Netwrk Interface ID(ex) eth0, enp0s25 ...", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "startTime", 
	            value = "조회 기간(ex) 1h: 현재부터 한시간전, 1d: 현재부터 하루전..", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "time", 
	            value = "시간별 평균값(ex) 1s:조회가간중 1초간 평균값, 5s:조회기간중 5초간 평균값", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    ),@ApiImplicitParam(
	            name = "limit", 
	            value = "조회 건수 (ex) 1000 : 1천건까지조회", 
	            required = true, 
	            dataType = "int", 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getNetworkTxerror(
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="interfaceId", required=true) String interfaceId
			,@RequestParam(value="startTime", required=false, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=false, defaultValue="5s") String time
			,@RequestParam(value="limit", required=false, defaultValue="1000") int limit
	) {
		return hostNetworkService.getNetworkTxerror(hostIp, interfaceId, startTime, time, limit);
	}
	
	@RequestMapping(value="/interface/{interfaceId}/tx/overrun", method=RequestMethod.GET)
	@ApiOperation(value="Host network interface tx overrun", httpMethod="GET", notes="Host network interface tx overrun")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "interfaceId", 
	            value = "Netwrk Interface ID(ex) eth0, enp0s25 ...", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "startTime", 
	            value = "조회 기간(ex) 1h: 현재부터 한시간전, 1d: 현재부터 하루전..", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "time", 
	            value = "시간별 평균값(ex) 1s:조회가간중 1초간 평균값, 5s:조회기간중 5초간 평균값", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    ),@ApiImplicitParam(
	            name = "limit", 
	            value = "조회 건수 (ex) 1000 : 1천건까지조회", 
	            required = true, 
	            dataType = "int", 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getNetworkTxoverrun(
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="interfaceId", required=true) String interfaceId
			,@RequestParam(value="startTime", required=false, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=false, defaultValue="5s") String time
			,@RequestParam(value="limit", required=false, defaultValue="1000") int limit
	) {
		return hostNetworkService.getNetworkTxoverrun(hostIp, interfaceId, startTime, time, limit);
	}
	
	@RequestMapping(value="/interface/{interfaceId}/tx/packet", method=RequestMethod.GET)
	@ApiOperation(value="Host network interface tx packet", httpMethod="GET", notes="Host network interface tx packet")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "interfaceId", 
	            value = "Netwrk Interface ID(ex) eth0, enp0s25 ...", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "startTime", 
	            value = "조회 기간(ex) 1h: 현재부터 한시간전, 1d: 현재부터 하루전..", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "time", 
	            value = "시간별 평균값(ex) 1s:조회가간중 1초간 평균값, 5s:조회기간중 5초간 평균값", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    ),@ApiImplicitParam(
	            name = "limit", 
	            value = "조회 건수 (ex) 1000 : 1천건까지조회", 
	            required = true, 
	            dataType = "int", 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getNetworkTxpacket(
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="interfaceId", required=true) String interfaceId
			,@RequestParam(value="startTime", required=false, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=false, defaultValue="5s") String time
			,@RequestParam(value="limit", required=false, defaultValue="1000") int limit
	) {
		return hostNetworkService.getNetworkTxpacket(hostIp, interfaceId, startTime, time, limit);
	}
	
	@RequestMapping(value="/interface/{interfaceId}/tx/carrier", method=RequestMethod.GET)
	@ApiOperation(value="Host network interface tx carrier", httpMethod="GET", notes="Host network interface tx carrier")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "interfaceId", 
	            value = "Netwrk Interface ID(ex) eth0, enp0s25 ...", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "startTime", 
	            value = "조회 기간(ex) 1h: 현재부터 한시간전, 1d: 현재부터 하루전..", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "time", 
	            value = "시간별 평균값(ex) 1s:조회가간중 1초간 평균값, 5s:조회기간중 5초간 평균값", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    ),@ApiImplicitParam(
	            name = "limit", 
	            value = "조회 건수 (ex) 1000 : 1천건까지조회", 
	            required = true, 
	            dataType = "int", 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getNetworkTxcarrier(
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="interfaceId", required=true) String interfaceId
			,@RequestParam(value="startTime", required=false, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=false, defaultValue="5s") String time
			,@RequestParam(value="limit", required=false, defaultValue="1000") int limit
	) {
		return hostNetworkService.getNetworkTxcarrier(hostIp, interfaceId, startTime, time, limit);
	}
	
	@RequestMapping(value="/interface/{interfaceId}/tx/collision", method=RequestMethod.GET)
	@ApiOperation(value="Host network interface tx collision", httpMethod="GET", notes="Host network interface tx collision")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "interfaceId", 
	            value = "Netwrk Interface ID(ex) eth0, enp0s25 ...", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "startTime", 
	            value = "조회 기간(ex) 1h: 현재부터 한시간전, 1d: 현재부터 하루전..", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "time", 
	            value = "시간별 평균값(ex) 1s:조회가간중 1초간 평균값, 5s:조회기간중 5초간 평균값", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    ),@ApiImplicitParam(
	            name = "limit", 
	            value = "조회 건수 (ex) 1000 : 1천건까지조회", 
	            required = true, 
	            dataType = "int", 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getNetworkTxcollision(
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="interfaceId", required=true) String interfaceId
			,@RequestParam(value="startTime", required=false, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=false, defaultValue="5s") String time
			,@RequestParam(value="limit", required=false, defaultValue="1000") int limit
	) {
		return hostNetworkService.getNetworkTxcollision(hostIp, interfaceId, startTime, time, limit);
	}
	
	@RequestMapping(value="/interface/{interfaceId}/speed", method=RequestMethod.GET)
	@ApiOperation(value="Host network interface speed", httpMethod="GET", notes="Host network interface speed")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "interfaceId", 
	            value = "Netwrk Interface ID(ex) eth0, enp0s25 ...", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "startTime", 
	            value = "조회 기간(ex) 1h: 현재부터 한시간전, 1d: 현재부터 하루전..", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "time", 
	            value = "시간별 평균값(ex) 1s:조회가간중 1초간 평균값, 5s:조회기간중 5초간 평균값", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    ),@ApiImplicitParam(
	            name = "limit", 
	            value = "조회 건수 (ex) 1000 : 1천건까지조회", 
	            required = true, 
	            dataType = "int", 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getNetworkSpeed(
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="interfaceId", required=true) String interfaceId
			,@RequestParam(value="startTime", required=false, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=false, defaultValue="5s") String time
			,@RequestParam(value="limit", required=false, defaultValue="1000") int limit
	) {
		return hostNetworkService.getNetworkSpeed(hostIp, interfaceId, startTime, time, limit);
	}
}
