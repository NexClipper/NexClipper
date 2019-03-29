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
package com.nexcloud.api.controller.k8s;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexcloud.api.service.k8s.K8sClusterService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/v1/kubernetes/cluster")
public class K8SClusterController {
	static final Logger logger = LoggerFactory.getLogger(K8SClusterController.class);
	@Autowired private K8sClusterService k8sClusterService;
	
	@ApiOperation(value="Cpu Total", httpMethod="GET", notes="Cpu Total")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "startTime", paramType = "query", 
				defaultValue = "1h", value = "조회 시간 (ex) 20분전 : 20m, 한시가전 : 1h, 두시간전 :2h .."),
		@ApiImplicitParam(name = "time", paramType = "query", 
				defaultValue = "5s", value = "group by time (ex) 1s"),
		@ApiImplicitParam(name = "limit", paramType = "query", 
				defaultValue = "1000", value = "limit (ex) 1000")
	}) 
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/cpu/total", method=RequestMethod.GET)
	public String getCpuTotal(
		@RequestParam(value="startTime", defaultValue="1h") String startTime,
		@RequestParam(value="time", defaultValue="5s") String time,
		@RequestParam(value="limit", defaultValue="1000") int limit) {
		return k8sClusterService.getCpuTotal(startTime, time, limit);
	}
	
	@ApiOperation(value="Cpu Used", httpMethod="GET", notes="Cpu Used")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "startTime", paramType = "query", 
				defaultValue = "1h", value = "조회 시간 (ex) 20분전 : 20m, 한시가전 : 1h, 두시간전 :2h .."),
		@ApiImplicitParam(name = "time", paramType = "query", 
				defaultValue = "5s", value = "group by time (ex) 1s"),
		@ApiImplicitParam(name = "limit", paramType = "query", 
				defaultValue = "1000", value = "limit (ex) 1000")
	}) 
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/cpu/used", method=RequestMethod.GET)
	public String getCpuUsed(
		@RequestParam(value="startTime", defaultValue="1h") String startTime,
		@RequestParam(value="time", defaultValue="5s") String time,
		@RequestParam(value="limit", defaultValue="1000") int limit) {
		return k8sClusterService.getCpuUsed(startTime, time, limit);
	}
	
	@ApiOperation(value="Cpu Used Percent", httpMethod="GET", notes="Cpu Used Percent")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "startTime", paramType = "query", 
				defaultValue = "1h", value = "조회 시간 (ex) 20분전 : 20m, 한시가전 : 1h, 두시간전 :2h .."),
		@ApiImplicitParam(name = "time", paramType = "query", 
				defaultValue = "5s", value = "group by time (ex) 1s"),
		@ApiImplicitParam(name = "limit", paramType = "query", 
				defaultValue = "1000", value = "limit (ex) 1000")
	}) 
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/cpu/used/percent", method=RequestMethod.GET)
	public String getCpuUsedPercent(
		@RequestParam(value="startTime", defaultValue="1h") String startTime,
		@RequestParam(value="time", defaultValue="5s") String time,
		@RequestParam(value="limit", defaultValue="1000") int limit) {
		return k8sClusterService.getCpuUsedPercent(startTime, time, limit);
	}	
	
	@ApiOperation(value="Memory Total", httpMethod="GET", notes="Memory Total")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "startTime", paramType = "query", 
				defaultValue = "1h", value = "조회 시간 (ex) 20분전 : 20m, 한시가전 : 1h, 두시간전 :2h .."),
		@ApiImplicitParam(name = "time", paramType = "query", 
				defaultValue = "5s", value = "group by time (ex) 1s"),
		@ApiImplicitParam(name = "limit", paramType = "query", 
				defaultValue = "1000", value = "limit (ex) 1000")
	}) 
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/memory/total", method=RequestMethod.GET)
	public String getMemoryTotal(
		@RequestParam(value="startTime", defaultValue="1h") String startTime,
		@RequestParam(value="time", defaultValue="5s") String time,
		@RequestParam(value="limit", defaultValue="1000") int limit) {
		return k8sClusterService.getMemoryTotal(startTime, time, limit);
	}

	@ApiOperation(value="Memory Used", httpMethod="GET", notes="Memory Used")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "startTime", paramType = "query", 
				defaultValue = "1h", value = "조회 시간 (ex) 20분전 : 20m, 한시가전 : 1h, 두시간전 :2h .."),
		@ApiImplicitParam(name = "time", paramType = "query", 
				defaultValue = "5s", value = "group by time (ex) 1s"),
		@ApiImplicitParam(name = "limit", paramType = "query", 
				defaultValue = "1000", value = "limit (ex) 1000")
	}) 
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/memory/used", method=RequestMethod.GET)
	public String getMemoryUsed(
		@RequestParam(value="startTime", defaultValue="1h") String startTime,
		@RequestParam(value="time", defaultValue="5s") String time,
		@RequestParam(value="limit", defaultValue="1000") int limit) {
		return k8sClusterService.getMemoryUsed(startTime, time, limit);
	}

	@ApiOperation(value="Memory Used Percent", httpMethod="GET", notes="Memory Used Percent")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "startTime", paramType = "query", 
				defaultValue = "1h", value = "조회 시간 (ex) 20분전 : 20m, 한시가전 : 1h, 두시간전 :2h .."),
		@ApiImplicitParam(name = "time", paramType = "query", 
				defaultValue = "5s", value = "group by time (ex) 1s"),
		@ApiImplicitParam(name = "limit", paramType = "query", 
				defaultValue = "1000", value = "limit (ex) 1000")
	}) 
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/memory/used/percent", method=RequestMethod.GET)
	public String getMemoryUsedPercent(
		@RequestParam(value="startTime", defaultValue="1h") String startTime,
		@RequestParam(value="time", defaultValue="5s") String time,
		@RequestParam(value="limit", defaultValue="1000") int limit) {
		return k8sClusterService.getMemoryUsedPercent(startTime, time, limit);
	}

	@ApiOperation(value="Pod Total", httpMethod="GET", notes="Pod Total")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "startTime", paramType = "query", 
				defaultValue = "1h", value = "조회 시간 (ex) 20분전 : 20m, 한시가전 : 1h, 두시간전 :2h .."),
		@ApiImplicitParam(name = "time", paramType = "query", 
				defaultValue = "5s", value = "group by time (ex) 1s"),
		@ApiImplicitParam(name = "limit", paramType = "query", 
				defaultValue = "1000", value = "limit (ex) 1000")
	}) 
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/pod/total", method=RequestMethod.GET)
	public String getPodTotal(
		@RequestParam(value="startTime", defaultValue="1h") String startTime,
		@RequestParam(value="time", defaultValue="5s") String time,
		@RequestParam(value="limit", defaultValue="1000") int limit) {
		return k8sClusterService.getPodTotal(startTime, time, limit);
	}

	@ApiOperation(value="Pod Used", httpMethod="GET", notes="Pod Used")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "startTime", paramType = "query", 
				defaultValue = "1h", value = "조회 시간 (ex) 20분전 : 20m, 한시가전 : 1h, 두시간전 :2h .."),
		@ApiImplicitParam(name = "time", paramType = "query", 
				defaultValue = "5s", value = "group by time (ex) 1s"),
		@ApiImplicitParam(name = "limit", paramType = "query", 
				defaultValue = "1000", value = "limit (ex) 1000")
	}) 
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/pod/used", method=RequestMethod.GET)
	public String getPodUsed(
		@RequestParam(value="startTime", defaultValue="1h") String startTime,
		@RequestParam(value="time", defaultValue="5s") String time,
		@RequestParam(value="limit", defaultValue="1000") int limit) {
		return k8sClusterService.getPodUsed(startTime, time, limit);
	}

	@ApiOperation(value="Pod Used Percent", httpMethod="GET", notes="Pod Used Percent")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "startTime", paramType = "query", 
				defaultValue = "1h", value = "조회 시간 (ex) 20분전 : 20m, 한시가전 : 1h, 두시간전 :2h .."),
		@ApiImplicitParam(name = "time", paramType = "query", 
				defaultValue = "5s", value = "group by time (ex) 1s"),
		@ApiImplicitParam(name = "limit", paramType = "query", 
				defaultValue = "1000", value = "limit (ex) 1000")
	}) 
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/pod/used/percent", method=RequestMethod.GET)
	public String getPodUsedPercent(
		@RequestParam(value="startTime", defaultValue="1h") String startTime,
		@RequestParam(value="time", defaultValue="5s") String time,
		@RequestParam(value="limit", defaultValue="1000") int limit) {
		return k8sClusterService.getPodUsedPercent(startTime, time, limit);
	}
}
