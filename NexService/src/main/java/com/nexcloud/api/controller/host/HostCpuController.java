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
package com.nexcloud.api.controller.host;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexcloud.api.service.host.HostCpuService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/v1/cluster/{clusterId}/host/{hostIp}/cpu")
public class HostCpuController {
	static final Logger logger = LoggerFactory.getLogger(HostCpuController.class);
	
	@Autowired
	private HostCpuService hostCpuService;
	@RequestMapping(value="/used/percent",method=RequestMethod.GET)
	@ApiOperation(value="Host별 CPU usage", httpMethod="GET", notes="cpu used percent")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
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
	
	public String getCpuUsedPercentByHostIp(@PathVariable(value="clusterId", required=false) String clusterId,
		@PathVariable(value="hostIp", required=true) String hostIp,
		@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime,
		@RequestParam(value="time", required=true, defaultValue="5s") String time,
		@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		logger.debug("used");
		return hostCpuService.getCpuUsedPercentByHostIp(clusterId, hostIp, startTime, time, limit);
	}
	
	
	
	@RequestMapping(value="/idle/percent",method=RequestMethod.GET)
	@ApiOperation(value="Host별 CPU Idle usage", httpMethod="GET", notes="cpu idle percent")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
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
	public String getCpuIdlePercentByHostIp(@PathVariable(value="clusterId", required=false) String clusterId,
		@PathVariable(value="hostIp", required=true) String hostIp,
		@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime,
		@RequestParam(value="time", required=true, defaultValue="5s") String time,
		@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostCpuService.getCpuIdlePercentByHostIp(clusterId, hostIp, startTime, time, limit); 
	}
	
	
	@RequestMapping(value="/irq/percent",method=RequestMethod.GET)
	@ApiOperation(value="Host별 CPU IRQ usage", httpMethod="GET", notes="cpu irq percent")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
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
	public String getCpuIrqPercentByHostIp(@PathVariable(value="clusterId", required=false) String clusterId,
		@PathVariable(value="hostIp", required=true) String hostIp,
		@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime,
		@RequestParam(value="time", required=true, defaultValue="5s") String time,
		@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostCpuService.getCpuIrqPercentByHostIp(clusterId, hostIp, startTime, time, limit);
	}
	
	
	@RequestMapping(value="/nice/percent",method=RequestMethod.GET)
	@ApiOperation(value="Host별 CPU nice usage", httpMethod="GET", notes="cpu nice percent")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
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
	public String getCpuNicePercentByHostIp(@PathVariable(value="clusterId", required=false) String clusterId,
		@PathVariable(value="hostIp", required=true) String hostIp,
		@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime,
		@RequestParam(value="time", required=true, defaultValue="5s") String time,
		@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostCpuService.getCpuNicePercentByHostIp(clusterId, hostIp, startTime, time, limit);
	}
	
	
	
	@RequestMapping(value="/sorfirq/percent",method=RequestMethod.GET)
	@ApiOperation(value="Host별 CPU sorfirq usage", httpMethod="GET", notes="cpu sorfirq percent")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
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
	public String getCpuSorfirqPercentByHostIp(@PathVariable(value="clusterId", required=false) String clusterId,
		@PathVariable(value="hostIp", required=true) String hostIp,
		@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime,
		@RequestParam(value="time", required=true, defaultValue="5s") String time,
		@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostCpuService.getCpuSorfirqPercentByHostIp(clusterId, hostIp, startTime, time, limit);
	}
	
	
	@RequestMapping(value="/stolen/percent",method=RequestMethod.GET)
	@ApiOperation(value="Host별 CPU stolen usage", httpMethod="GET", notes="cpu stolen percent")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
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
	public String getCpuStolenPercentByHostIp(@PathVariable(value="clusterId", required=false) String clusterId,
			@PathVariable(value="hostIp", required=true) String hostIp,
			@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime,
			@RequestParam(value="time", required=true, defaultValue="5s") String time,
			@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostCpuService.getCpuStolenPercentByHostIp(clusterId, hostIp, startTime, time, limit);
	}
	
	
	@RequestMapping(value="/sys/percent",method=RequestMethod.GET)
	@ApiOperation(value="Host별 CPU system usage", httpMethod="GET", notes="cpu sys percent")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
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
	public String getCpuSysPercentByHostIp(@PathVariable(value="clusterId", required=false) String clusterId,
		@PathVariable(value="hostIp", required=true) String hostIp,
		@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime,
		@RequestParam(value="time", required=true, defaultValue="5s") String time,
		@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostCpuService.getCpuSysPercentByHostIp(clusterId, hostIp, startTime, time, limit);
	}
	
	
	@RequestMapping(value="/user/percent",method=RequestMethod.GET)
	@ApiOperation(value="Host별 CPU user usage", httpMethod="GET", notes="cpu user percent")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
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
	public String getCpuUserPercentByHostIp(@PathVariable(value="clusterId", required=false) String clusterId,
		@PathVariable(value="hostIp", required=true) String hostIp,
		@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime,
		@RequestParam(value="time", required=true, defaultValue="5s") String time,
		@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostCpuService.getCpuUserPercentByHostIp(clusterId, hostIp, startTime, time, limit);
	}
	
	
	@RequestMapping(value="/wait/percent",method=RequestMethod.GET)
	@ApiOperation(value="Host별 CPU wait usage", httpMethod="GET", notes="cpu wait percent")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
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
	public String getCpuWaitPercentByHostIp(@PathVariable(value="clusterId", required=false) String clusterId,
		@PathVariable(value="hostIp", required=true) String hostIp,
		@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime,
		@RequestParam(value="time", required=true, defaultValue="5s") String time,
		@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostCpuService.getCpuWaitPercentByHostIp(clusterId, hostIp, startTime, time, limit);
	}
	
	@RequestMapping(value="/total",method=RequestMethod.GET)
	@ApiOperation(value="Host별 CPU 총갯수", httpMethod="GET", notes="cpu total core")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
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
	public String getCpuTotalCoreByHostIp(@PathVariable(value="clusterId", required=false) String clusterId,
		@PathVariable(value="hostIp", required=true) String hostIp,
		@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime,
		@RequestParam(value="time", required=true, defaultValue="5s") String time,
		@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostCpuService.getCpuTotalCoreByHostIp(clusterId, hostIp, startTime, time, limit);
	}
	
	@RequestMapping(value="/load1",method=RequestMethod.GET)
	@ApiOperation(value="Host별 CPU load1", httpMethod="GET", notes="cpu load1")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
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
	public String getCpuLoad1ByHostIp(@PathVariable(value="clusterId", required=false) String clusterId,
		@PathVariable(value="hostIp", required=true) String hostIp,
		@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime,
		@RequestParam(value="time", required=true, defaultValue="5s") String time,
		@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostCpuService.getCpuLoad1ByHostIp(clusterId, hostIp, startTime, time, limit);
	}
	
	@RequestMapping(value="/load5",method=RequestMethod.GET)
	@ApiOperation(value="Host별 CPU load5", httpMethod="GET", notes="cpu load5")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
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
	public String getCpuLoad5ByHostIp(@PathVariable(value="clusterId", required=false) String clusterId,
		@PathVariable(value="hostIp", required=true) String hostIp,
		@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime,
		@RequestParam(value="time", required=true, defaultValue="5s") String time,
		@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostCpuService.getCpuLoad5ByHostIp(clusterId, hostIp, startTime, time, limit);
	}
	
	@RequestMapping(value="/load15",method=RequestMethod.GET)
	@ApiOperation(value="Host별 CPU load15", httpMethod="GET", notes="cpu load15")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
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
	public String getCpuLoad15ByHostIp(@PathVariable(value="clusterId", required=false) String clusterId,
		@PathVariable(value="hostIp", required=true) String hostIp,
		@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime,
		@RequestParam(value="time", required=true, defaultValue="5s") String time,
		@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostCpuService.getCpuLoad15ByHostIp(clusterId, hostIp, startTime, time, limit);
	}
}
