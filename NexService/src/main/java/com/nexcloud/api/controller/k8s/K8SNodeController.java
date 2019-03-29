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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexcloud.api.service.k8s.K8sNodeService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/v1/kubernetes/node")
public class K8SNodeController {
	static final Logger logger = LoggerFactory.getLogger(K8SNodeController.class);

	@Autowired private K8sNodeService k8sNodeService;
	
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
		return k8sNodeService.getCpuUsed(startTime, time, limit);
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
		return k8sNodeService.getCpuUsedPercent(startTime, time, limit);
	}
	@ApiOperation(value="Cpu Allocate", httpMethod="GET", notes="Cpu Allocate")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "startTime", paramType = "query", 
				defaultValue = "1h", value = "조회 시간 (ex) 20분전 : 20m, 한시가전 : 1h, 두시간전 :2h .."),
		@ApiImplicitParam(name = "time", paramType = "query", 
				defaultValue = "5s", value = "group by time (ex) 1s"),
		@ApiImplicitParam(name = "limit", paramType = "query", 
				defaultValue = "1000", value = "limit (ex) 1000")
	}) 
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/cpu/allocate", method=RequestMethod.GET)
	public String getCpuAllocate(
			@RequestParam(value="startTime", defaultValue="1h") String startTime,
			@RequestParam(value="time", defaultValue="5s") String time,
			@RequestParam(value="limit", defaultValue="1000") int limit) {
		return k8sNodeService.getCpuAllocate(startTime, time, limit);
	}
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
		return k8sNodeService.getCpuTotal(startTime, time, limit);
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
		return k8sNodeService.getMemoryUsed(startTime, time, limit);
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
		return k8sNodeService.getMemoryUsedPercent(startTime, time, limit);
	}
	@ApiOperation(value="Memory Allocate", httpMethod="GET", notes="Memory Allocate")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "startTime", paramType = "query", 
				defaultValue = "1h", value = "조회 시간 (ex) 20분전 : 20m, 한시가전 : 1h, 두시간전 :2h .."),
		@ApiImplicitParam(name = "time", paramType = "query", 
				defaultValue = "5s", value = "group by time (ex) 1s"),
		@ApiImplicitParam(name = "limit", paramType = "query", 
				defaultValue = "1000", value = "limit (ex) 1000")
	}) 
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/memory/allocate", method=RequestMethod.GET)
	public String getMemoryAllocate(
			@RequestParam(value="startTime", defaultValue="1h") String startTime,
			@RequestParam(value="time", defaultValue="5s") String time,
			@RequestParam(value="limit", defaultValue="1000") int limit) {
		return k8sNodeService.getMemoryAllocate(startTime, time, limit);
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
		return k8sNodeService.getMemoryTotal(startTime, time, limit);
	}

	@ApiOperation(value="Pod Allocate", httpMethod="GET", notes="Pod Allocate")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "startTime", paramType = "query", 
				defaultValue = "1h", value = "조회 시간 (ex) 20분전 : 20m, 한시가전 : 1h, 두시간전 :2h .."),
		@ApiImplicitParam(name = "time", paramType = "query", 
				defaultValue = "5s", value = "group by time (ex) 1s"),
		@ApiImplicitParam(name = "limit", paramType = "query", 
				defaultValue = "1000", value = "limit (ex) 1000")
	}) 
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/pod/allocate", method=RequestMethod.GET)
	public String getPodAllocate(
			@RequestParam(value="startTime", defaultValue="1h") String startTime,
			@RequestParam(value="time", defaultValue="5s") String time,
			@RequestParam(value="limit", defaultValue="1000") int limit) {
		return k8sNodeService.getPodAllocate(startTime, time, limit);
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
		return k8sNodeService.getPodTotal(startTime, time, limit);
	}
	
	// by node
	

	@ApiOperation(value="Cpu Used By Node", httpMethod="GET", notes="Cpu Used By Node")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "node", paramType = "path",
				required = true, value = "노드명 (ex) kubemaster"),
		@ApiImplicitParam(name = "startTime", paramType = "query", 
				defaultValue = "1h", value = "조회 시간 (ex) 20분전 : 20m, 한시가전 : 1h, 두시간전 :2h .."),
		@ApiImplicitParam(name = "time", paramType = "query", 
				defaultValue = "5s", value = "group by time (ex) 1s"),
		@ApiImplicitParam(name = "limit", paramType = "query", 
				defaultValue = "1000", value = "limit (ex) 1000")
	}) 
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/{node}/cpu/used", method=RequestMethod.GET)
	public String getCpuUsedByNode(
			@PathVariable(value="node") String node,
			@RequestParam(value="startTime", defaultValue="1h") String startTime,
			@RequestParam(value="time", defaultValue="5s") String time,
			@RequestParam(value="limit", defaultValue="1000") int limit) {
		return k8sNodeService.getCpuUsedByNode(node, startTime, time, limit);
	}
	@ApiOperation(value="Cpu Used Percent By Node", httpMethod="GET", notes="Cpu Used Percent By Node")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "node", paramType = "path",
				required = true, value = "노드명 (ex) kubemaster"),
		@ApiImplicitParam(name = "startTime", paramType = "query", 
				defaultValue = "1h", value = "조회 시간 (ex) 20분전 : 20m, 한시가전 : 1h, 두시간전 :2h .."),
		@ApiImplicitParam(name = "time", paramType = "query", 
				defaultValue = "5s", value = "group by time (ex) 1s"),
		@ApiImplicitParam(name = "limit", paramType = "query", 
				defaultValue = "1000", value = "limit (ex) 1000")
	}) 
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/{node}/cpu/used/percent", method=RequestMethod.GET)
	public String getCpuUsedPercentByNode(
			@PathVariable(value="node", required=false) String node,
			@RequestParam(value="startTime", defaultValue="1h") String startTime,
			@RequestParam(value="time", defaultValue="5s") String time,
			@RequestParam(value="limit", defaultValue="1000") int limit) {
		return k8sNodeService.getCpuUsedPercentByNode(node, startTime, time, limit);
	}
	@ApiOperation(value="Cpu Allocate By Node", httpMethod="GET", notes="Cpu Allocate By Node")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "node", paramType = "path",
				required = true, value = "노드명 (ex) kubemaster"),
		@ApiImplicitParam(name = "startTime", paramType = "query", 
				defaultValue = "1h", value = "조회 시간 (ex) 20분전 : 20m, 한시가전 : 1h, 두시간전 :2h .."),
		@ApiImplicitParam(name = "time", paramType = "query", 
				defaultValue = "5s", value = "group by time (ex) 1s"),
		@ApiImplicitParam(name = "limit", paramType = "query", 
				defaultValue = "1000", value = "limit (ex) 1000")
	}) 
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/{node}/cpu/allocate", method=RequestMethod.GET)
	public String getCpuAllocateByNode(
			@PathVariable(value="node", required=false) String node,
			@RequestParam(value="startTime", defaultValue="1h") String startTime,
			@RequestParam(value="time", defaultValue="5s") String time,
			@RequestParam(value="limit", defaultValue="1000") int limit) {
		return k8sNodeService.getCpuAllocateByNode(node, startTime, time, limit);
	}
	@ApiOperation(value="Cpu Total By Node", httpMethod="GET", notes="Cpu Total By Node")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "node", paramType = "path",
				required = true, value = "노드명 (ex) kubemaster"),
		@ApiImplicitParam(name = "startTime", paramType = "query", 
				defaultValue = "1h", value = "조회 시간 (ex) 20분전 : 20m, 한시가전 : 1h, 두시간전 :2h .."),
		@ApiImplicitParam(name = "time", paramType = "query", 
				defaultValue = "5s", value = "group by time (ex) 1s"),
		@ApiImplicitParam(name = "limit", paramType = "query", 
				defaultValue = "1000", value = "limit (ex) 1000")
	}) 
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/{node}/cpu/total", method=RequestMethod.GET)
	public String getCpuTotalByNode(
			@PathVariable(value="node", required=false) String node,
			@RequestParam(value="startTime", defaultValue="1h") String startTime,
			@RequestParam(value="time", defaultValue="5s") String time,
			@RequestParam(value="limit", defaultValue="1000") int limit) {
		return k8sNodeService.getCpuTotalByNode(node, startTime, time, limit);
	}
	
	@ApiOperation(value="Memory Used By Node", httpMethod="GET", notes="Memory Used By Node")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "node", paramType = "path",
				required = true, value = "노드명 (ex) kubemaster"),
		@ApiImplicitParam(name = "startTime", paramType = "query", 
				defaultValue = "1h", value = "조회 시간 (ex) 20분전 : 20m, 한시가전 : 1h, 두시간전 :2h .."),
		@ApiImplicitParam(name = "time", paramType = "query", 
				defaultValue = "5s", value = "group by time (ex) 1s"),
		@ApiImplicitParam(name = "limit", paramType = "query", 
				defaultValue = "1000", value = "limit (ex) 1000")
	}) 
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/{node}/memory/used", method=RequestMethod.GET)
	public String getMemoryUsedByNode(
			@PathVariable(value="node", required=false) String node,
			@RequestParam(value="startTime", defaultValue="1h") String startTime,
			@RequestParam(value="time", defaultValue="5s") String time,
			@RequestParam(value="limit", defaultValue="1000") int limit) {
		return k8sNodeService.getMemoryUsedByNode(node, startTime, time, limit);
	}
	@ApiOperation(value="Memory Used Percent By Node", httpMethod="GET", notes="Memory Used Percent By Node")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "node", paramType = "path",
				required = true, value = "노드명 (ex) kubemaster"),
		@ApiImplicitParam(name = "startTime", paramType = "query", 
				defaultValue = "1h", value = "조회 시간 (ex) 20분전 : 20m, 한시가전 : 1h, 두시간전 :2h .."),
		@ApiImplicitParam(name = "time", paramType = "query", 
				defaultValue = "5s", value = "group by time (ex) 1s"),
		@ApiImplicitParam(name = "limit", paramType = "query", 
				defaultValue = "1000", value = "limit (ex) 1000")
	}) 
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/{node}/memory/used/percent", method=RequestMethod.GET)
	public String getMemoryUsedPercentByNode(
			@PathVariable(value="node", required=false) String node,
			@RequestParam(value="startTime", defaultValue="1h") String startTime,
			@RequestParam(value="time", defaultValue="5s") String time,
			@RequestParam(value="limit", defaultValue="1000") int limit) {
		return k8sNodeService.getMemoryUsedPercentByNode(node, startTime, time, limit);
	}
	@ApiOperation(value="Memory Allocate By Node", httpMethod="GET", notes="Memory Allocate By Node")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "node", paramType = "path",
				required = true, value = "노드명 (ex) kubemaster"),
		@ApiImplicitParam(name = "startTime", paramType = "query", 
				defaultValue = "1h", value = "조회 시간 (ex) 20분전 : 20m, 한시가전 : 1h, 두시간전 :2h .."),
		@ApiImplicitParam(name = "time", paramType = "query", 
				defaultValue = "5s", value = "group by time (ex) 1s"),
		@ApiImplicitParam(name = "limit", paramType = "query", 
				defaultValue = "1000", value = "limit (ex) 1000")
	}) 
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/{node}/memory/allocate", method=RequestMethod.GET)
	public String getMemoryAllocateByNode(
			@PathVariable(value="node", required=false) String node,
			@RequestParam(value="startTime", defaultValue="1h") String startTime,
			@RequestParam(value="time", defaultValue="5s") String time,
			@RequestParam(value="limit", defaultValue="1000") int limit) {
		return k8sNodeService.getMemoryAllocateByNode(node, startTime, time, limit);
	}
	@ApiOperation(value="Memory Total By Node", httpMethod="GET", notes="Memory Total By Node")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "node", paramType = "path",
				required = true, value = "노드명 (ex) kubemaster"),
		@ApiImplicitParam(name = "startTime", paramType = "query", 
				defaultValue = "1h", value = "조회 시간 (ex) 20분전 : 20m, 한시가전 : 1h, 두시간전 :2h .."),
		@ApiImplicitParam(name = "time", paramType = "query", 
				defaultValue = "5s", value = "group by time (ex) 1s"),
		@ApiImplicitParam(name = "limit", paramType = "query", 
				defaultValue = "1000", value = "limit (ex) 1000")
	}) 
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/{node}/memory/total", method=RequestMethod.GET)
	public String getMemoryTotalByNode(
			@PathVariable(value="node", required=false) String node,
			@RequestParam(value="startTime", defaultValue="1h") String startTime,
			@RequestParam(value="time", defaultValue="5s") String time,
			@RequestParam(value="limit", defaultValue="1000") int limit) {
		return k8sNodeService.getMemoryTotalByNode(node, startTime, time, limit);
	}

	@ApiOperation(value="Pod Allocate By Node", httpMethod="GET", notes="Pod Allocate By Node")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "node", paramType = "path",
				required = true, value = "노드명 (ex) kubemaster"),
		@ApiImplicitParam(name = "startTime", paramType = "query", 
				defaultValue = "1h", value = "조회 시간 (ex) 20분전 : 20m, 한시가전 : 1h, 두시간전 :2h .."),
		@ApiImplicitParam(name = "time", paramType = "query", 
				defaultValue = "5s", value = "group by time (ex) 1s"),
		@ApiImplicitParam(name = "limit", paramType = "query", 
				defaultValue = "1000", value = "limit (ex) 1000")
	}) 
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/{node}/pod/allocate", method=RequestMethod.GET)
	public String getPodAllocateByNode(
			@PathVariable(value="node", required=false) String node,
			@RequestParam(value="startTime", defaultValue="1h") String startTime,
			@RequestParam(value="time", defaultValue="5s") String time,
			@RequestParam(value="limit", defaultValue="1000") int limit) {
		return k8sNodeService.getPodAllocateByNode(node, startTime, time, limit);
	}
	@ApiOperation(value="Pod Total By Node", httpMethod="GET", notes="Pod Total By Node")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "node", paramType = "path",
				required = true, value = "노드명 (ex) kubemaster"),
		@ApiImplicitParam(name = "startTime", paramType = "query", 
				defaultValue = "1h", value = "조회 시간 (ex) 20분전 : 20m, 한시가전 : 1h, 두시간전 :2h .."),
		@ApiImplicitParam(name = "time", paramType = "query", 
				defaultValue = "5s", value = "group by time (ex) 1s"),
		@ApiImplicitParam(name = "limit", paramType = "query", 
				defaultValue = "1000", value = "limit (ex) 1000")
	}) 
	@ApiResponses(value={@ApiResponse( code=200, message="SUCCESS"), @ApiResponse( code=500, message="Internal Server Error")})
	@RequestMapping(value="/{node}/pod/total", method=RequestMethod.GET)
	public String getPodTotalByNode(
			@PathVariable(value="node", required=false) String node,
			@RequestParam(value="startTime", defaultValue="1h") String startTime,
			@RequestParam(value="time", defaultValue="5s") String time,
			@RequestParam(value="limit", defaultValue="1000") int limit) {
		return k8sNodeService.getPodTotalByNode(node, startTime, time, limit);
	}
}
