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
import com.nexcloud.api.service.host.HostDockerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/v1/cluster/{clusterId}/host")
public class HostDockerController {
	static final Logger logger = LoggerFactory.getLogger(HostDockerController.class);

	@Autowired
	private HostDockerService hostDockerService;
	
	@RequestMapping(value="/docker/snapshot", method=RequestMethod.GET)
	@ApiOperation(value="Host전체 Docker snapshot", httpMethod="GET", notes="Redis에 있는 host전체 Docker 데이터")
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getAllDocker( @PathVariable(value="clusterId", required=false) String clusterId) {
		return hostDockerService.getAllDocker(clusterId );
	}
	
	@RequestMapping(value="/{hostIp}/docker/snapshot", method=RequestMethod.GET)
	@ApiOperation(value="Host별  Docker snapshot", httpMethod="GET", notes="Redis에 있는 특정 host의 Docker 데이터")
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
	public String dockerInfo(@PathVariable(value="clusterId", required=false) String clusterId,
			@PathVariable(value="hostIp", required=true) String hostIp) {
		return hostDockerService.getDocker(clusterId, hostIp);
	}
	
	
	@RequestMapping(value="/{hostIp}/docker/container/{containerId}/cpu/usage", method=RequestMethod.GET)
	@ApiOperation(value="docker container의 cpu used percent ", httpMethod="GET", notes="특정 host의 docker container cpu 사용량")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "containerId", 
	            value = "Docker Container id: 221f2b062db1e7c350cfdfa69eede687692eb5e709a8434ab787e98ad0ff3896", 
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
	public String getDockerCpuUsedByContainerId(@PathVariable(value="clusterId", required=false) String clusterId,
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="containerId", required=true) String containerId
			,@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=true, defaultValue="5s") String time
			,@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDockerService.getDockerCpuUsedByContainerId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	@RequestMapping(value="/{hostIp}/docker/task/{taskId}/cpu/usage", method=RequestMethod.GET)
	@ApiOperation(value="docker task의 cpu used percent ", httpMethod="GET", notes="특정 host의 docker task cpu 사용량 Like 검색( Docker attach name )")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "taskId", 
	            value = "Docker task id: (ex) redis", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
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
	public String getDockerCpuUsedByTaskId(@PathVariable(value="clusterId", required=false) String clusterId,
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="taskId", required=true) String taskId
			,@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=true, defaultValue="5s") String time
			,@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDockerService.getDockerCpuUsedByTaskId(clusterId, hostIp, taskId, startTime, time, limit);
	}
	
	@RequestMapping(value="/{hostIp}/docker/container/{containerId}/memory/allocate", method=RequestMethod.GET)
	@ApiOperation(value="docker container의 memory allocate ", httpMethod="GET", notes="특정 host의 docker container memory allocate정보")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "containerId", 
	            value = "Docker Container id: 221f2b062db1e7c350cfdfa69eede687692eb5e709a8434ab787e98ad0ff3896", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
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
	public String getDockerMemoryAllocateByContainerId(@PathVariable(value="clusterId", required=false) String clusterId,
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="containerId", required=true) String containerId
			,@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=true, defaultValue="5s") String time
			,@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDockerService.getDockerMemoryAllocateByContainerId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	
	@RequestMapping(value="/{hostIp}/docker/container/{containerId}/memory/used", method=RequestMethod.GET)
	@ApiOperation(value="docker container의 memory used byte ", httpMethod="GET", notes="특정 host의 docker container memory used byte")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "containerId", 
	            value = "Docker Container id: 221f2b062db1e7c350cfdfa69eede687692eb5e709a8434ab787e98ad0ff3896", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
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
	public String getDockerMemoryUsedByteByContainerId(@PathVariable(value="clusterId", required=false) String clusterId,
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="containerId", required=true) String containerId
			,@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=true, defaultValue="5s") String time
			,@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDockerService.getDockerMemoryUsedByteByContainerId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	@RequestMapping(value="/{hostIp}/docker/task/{taskId}/memory/used", method=RequestMethod.GET)
	@ApiOperation(value="docker task의 memory used byte ", httpMethod="GET", notes="특정 host의 docker task memory used byte Like 검색( Docker attach name )")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "taskId", 
	            value = "Docker task id: (ex) redis", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
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
	public String getDockerMemoryUsedByteByTaskId(@PathVariable(value="clusterId", required=false) String clusterId,
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="taskId", required=true) String taskId
			,@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=true, defaultValue="5s") String time
			,@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDockerService.getDockerMemoryUsedByteByTaskId(clusterId, hostIp, taskId, startTime, time, limit);
	}
	
	
	@RequestMapping(value="/{hostIp}/docker/container/{containerId}/memory/usage", method=RequestMethod.GET)
	@ApiOperation(value="docker container의 memory used percent ", httpMethod="GET", notes="특정 host의 docker container memory used percent")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "containerId", 
	            value = "Docker Container id: 221f2b062db1e7c350cfdfa69eede687692eb5e709a8434ab787e98ad0ff3896", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
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
	public String getDockerMemoryUsedPercentByContainerId(@PathVariable(value="clusterId", required=false) String clusterId,
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="containerId", required=true) String containerId
			,@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=true, defaultValue="5s") String time
			,@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDockerService.getDockerMemoryUsedPercentByContainerId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	@RequestMapping(value="/{hostIp}/docker/task/{taskId}/memory/usage", method=RequestMethod.GET)
	@ApiOperation(value="docker task의 memory used percent ", httpMethod="GET", notes="특정 host의 docker task memory used percent Like 검색( Docker attach name )")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "taskId", 
	            value = "Docker task id: (ex) redis", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
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
	public String getDockerMemoryUsedPercentByTaskId(@PathVariable(value="clusterId", required=false) String clusterId,
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="taskId", required=true) String taskId
			,@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=true, defaultValue="5s") String time
			,@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDockerService.getDockerMemoryUsedPercentByTaskId(clusterId, hostIp, taskId, startTime, time, limit);
	}
	
	@RequestMapping(value="/{hostIp}/docker/container/{containerId}/diskio/read", method=RequestMethod.GET)
	@ApiOperation(value="docker container의  disk io read byte", httpMethod="GET", notes="특정 host의 docker container disk io read byte")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "containerId", 
	            value = "Docker Container id: 221f2b062db1e7c350cfdfa69eede687692eb5e709a8434ab787e98ad0ff3896", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
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
	public String getDockerDiskioReadByContainerId(@PathVariable(value="clusterId", required=false) String clusterId,
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="containerId", required=true) String containerId
			,@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=true, defaultValue="5s") String time
			,@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDockerService.getDockerDiskioReadByContainerId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	@RequestMapping(value="/{hostIp}/docker/task/{taskId}/diskio/read", method=RequestMethod.GET)
	@ApiOperation(value="docker task의 disk io read byte ", httpMethod="GET", notes="특정 host의 docker task disk io read byte Like 검색( Docker attach name )")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "taskId", 
	            value = "Docker task id: (ex) redis", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
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
	public String getDockerDiskioReadByTaskId(@PathVariable(value="clusterId", required=false) String clusterId,
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="taskId", required=true) String taskId
			,@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=true, defaultValue="5s") String time
			,@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDockerService.getDockerDiskioReadByTaskId(clusterId, hostIp, taskId, startTime, time, limit);
	}
	
	@RequestMapping(value="/{hostIp}/docker/container/{containerId}/diskio/write", method=RequestMethod.GET)
	@ApiOperation(value="docker container의  disk io write byte", httpMethod="GET", notes="특정 host의 docker container disk io write byte")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "containerId", 
	            value = "Docker Container id: 221f2b062db1e7c350cfdfa69eede687692eb5e709a8434ab787e98ad0ff3896", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
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
	public String getDockerDiskioWriteByContainerId(@PathVariable(value="clusterId", required=false) String clusterId,
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="containerId", required=true) String containerId
			,@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=true, defaultValue="5s") String time
			,@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDockerService.getDockerDiskioWriteByContainerId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	@RequestMapping(value="/{hostIp}/docker/task/{taskId}/diskio/write", method=RequestMethod.GET)
	@ApiOperation(value="docker task의 disk io read byte ", httpMethod="GET", notes="특정 host의 docker task disk io read byte Like 검색( Docker attach name )")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "taskId", 
	            value = "Docker task id: (ex) redis", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
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
	public String getDockerDiskioWriteByTaskId(@PathVariable(value="clusterId", required=false) String clusterId,
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="taskId", required=true) String taskId
			,@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=true, defaultValue="5s") String time
			,@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDockerService.getDockerDiskioWriteByTaskId(clusterId, hostIp, taskId, startTime, time, limit);
	}
	
	@RequestMapping(value="/{hostIp}/docker/container/{containerId}/rxbyte", method=RequestMethod.GET)
	@ApiOperation(value="docker container network interface rx byte", httpMethod="GET", notes="특정 host의 docker container network interface rx byte")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
				name = "containerId", 
	            value = "Docker Container id: 221f2b062db1e7c350cfdfa69eede687692eb5e709a8434ab787e98ad0ff3896", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
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
	public String getDockerNetworkRxbyteByContainerId(@PathVariable(value="clusterId", required=false) String clusterId,
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="containerId", required=true) String containerId
			,@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=true, defaultValue="5s") String time
			,@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDockerService.getDockerNetworkRxbyteByContainerId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	@RequestMapping(value="/{hostIp}/docker/container/{containerId}/rxdrop", method=RequestMethod.GET)
	@ApiOperation(value="docker container network interface rx dropped", httpMethod="GET", notes="특정 host의 docker container network interface rx dropped")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
				name = "containerId", 
	            value = "Docker Container id: 221f2b062db1e7c350cfdfa69eede687692eb5e709a8434ab787e98ad0ff3896", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
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
	public String getDockerNetworkRxdropByContainerId(@PathVariable(value="clusterId", required=false) String clusterId,
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="containerId", required=true) String containerId
			,@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=true, defaultValue="5s") String time
			,@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDockerService.getDockerNetworkRxdropByContainerId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	@RequestMapping(value="/{hostIp}/docker/container/{containerId}/rxerror", method=RequestMethod.GET)
	@ApiOperation(value="docker container network interface rx errors", httpMethod="GET", notes="특정 host의 docker container network interface rx errors")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
				name = "containerId", 
	            value = "Docker Container id: 221f2b062db1e7c350cfdfa69eede687692eb5e709a8434ab787e98ad0ff3896", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
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
	public String getDockerNetworkRxerrorByContainerId(@PathVariable(value="clusterId", required=false) String clusterId,
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="containerId", required=true) String containerId
			,@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=true, defaultValue="5s") String time
			,@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDockerService.getDockerNetworkRxerrorByContainerId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	@RequestMapping(value="/{hostIp}/docker/container/{containerId}/rxpacket", method=RequestMethod.GET)
	@ApiOperation(value="docker container network interface rx packet", httpMethod="GET", notes="특정 host의 docker container network interface rx packet")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
				name = "containerId", 
	            value = "Docker Container id: 221f2b062db1e7c350cfdfa69eede687692eb5e709a8434ab787e98ad0ff3896", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
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
	public String getDockerNetworkRxpacketByContainerId(@PathVariable(value="clusterId", required=false) String clusterId,
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="containerId", required=true) String containerId
			,@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=true, defaultValue="5s") String time
			,@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDockerService.getDockerNetworkRxpacketByContainerId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	@RequestMapping(value="/{hostIp}/docker/container/{containerId}/txbyte", method=RequestMethod.GET)
	@ApiOperation(value="docker container network interface tx byte", httpMethod="GET", notes="특정 host의 docker container network interface tx byte")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
				name = "containerId", 
	            value = "Docker Container id: 221f2b062db1e7c350cfdfa69eede687692eb5e709a8434ab787e98ad0ff3896", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
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
	public String getDockerNetworkTxbyteByContainerId(@PathVariable(value="clusterId", required=false) String clusterId,
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="containerId", required=true) String containerId
			,@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=true, defaultValue="5s") String time
			,@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDockerService.getDockerNetworkTxbyteByContainerId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	@RequestMapping(value="/{hostIp}/docker/container/{containerId}/txdrop", method=RequestMethod.GET)
	@ApiOperation(value="docker container network interface tx dropped", httpMethod="GET", notes="특정 host의 docker container network interface tx dropped")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
				name = "containerId", 
	            value = "Docker Container id: 221f2b062db1e7c350cfdfa69eede687692eb5e709a8434ab787e98ad0ff3896", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
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
	public String getDockerNetworkTxdropByContainerId(@PathVariable(value="clusterId", required=false) String clusterId,
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="containerId", required=true) String containerId
			,@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=true, defaultValue="5s") String time
			,@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDockerService.getDockerNetworkTxdropByContainerId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	@RequestMapping(value="/{hostIp}/docker/container/{containerId}/txerror", method=RequestMethod.GET)
	@ApiOperation(value="docker container network interface tx errors", httpMethod="GET", notes="특정 host의 docker container network interface tx errors")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
				name = "containerId", 
	            value = "Docker Container id: 221f2b062db1e7c350cfdfa69eede687692eb5e709a8434ab787e98ad0ff3896", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
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
	public String getDockerNetworkTxerrorByContainerId(@PathVariable(value="clusterId", required=false) String clusterId,
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="containerId", required=true) String containerId
			,@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=true, defaultValue="5s") String time
			,@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDockerService.getDockerNetworkTxerrorByContainerId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	@RequestMapping(value="/{hostIp}/docker/container/{containerId}/txpacket", method=RequestMethod.GET)
	@ApiOperation(value="docker container network interface tx packet", httpMethod="GET", notes="특정 host의 docker container network interface tx packet")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
				name = "containerId", 
	            value = "Docker Container id: 221f2b062db1e7c350cfdfa69eede687692eb5e709a8434ab787e98ad0ff3896", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
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
	public String getDockerNetworkTxpacketByContainerId(@PathVariable(value="clusterId", required=false) String clusterId,
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="containerId", required=true) String containerId
			,@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=true, defaultValue="5s") String time
			,@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDockerService.getDockerNetworkTxpacketByContainerId(clusterId, hostIp, containerId, startTime, time, limit);
	}
	
	
	@RequestMapping(value="/{hostIp}/docker/task/{taskId}/rxbyte", method=RequestMethod.GET)
	@ApiOperation(value="docker taks network interface rx byte", httpMethod="GET", notes="특정 host의 docker task network interface rx byte Like 검색( Docker attach name )")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
				name = "taskId", 
	            value = "Docker task id: (ex) redis", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
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
	public String getDockerNetworkRxbyteByTaskId(@PathVariable(value="clusterId", required=false) String clusterId,
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="taskId", required=true) String taskId
			,@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=true, defaultValue="5s") String time
			,@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDockerService.getDockerNetworkRxbyteByTaskId(clusterId, hostIp, taskId, startTime, time, limit);
	}
	
	@RequestMapping(value="/{hostIp}/docker/task/{taskId}/rxdrop", method=RequestMethod.GET)
	@ApiOperation(value="docker network task interface rx dropped", httpMethod="GET", notes="특정 host의 docker network task interface rx dropped Like 검색( Docker attach name )")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
				name = "taskId", 
	            value = "Docker task id: (ex) redis", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
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
	public String getDockerNetworkRxdropByTaskId(@PathVariable(value="clusterId", required=false) String clusterId,
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="taskId", required=true) String taskId
			,@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=true, defaultValue="5s") String time
			,@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDockerService.getDockerNetworkRxdropByTaskId(clusterId, hostIp, taskId, startTime, time, limit);
	}
	
	@RequestMapping(value="/{hostIp}/docker/task/{taskId}/rxerror", method=RequestMethod.GET)
	@ApiOperation(value="docker task network interface rx errors", httpMethod="GET", notes="특정 host의 docker task network interface rx errors Like 검색( Docker attach name )")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
				name = "taskId", 
	            value = "Docker task id: (ex) redis", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
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
	public String getDockerNetworkRxerrorByTaskId(@PathVariable(value="clusterId", required=false) String clusterId,
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="taskId", required=true) String taskId
			,@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=true, defaultValue="5s") String time
			,@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDockerService.getDockerNetworkRxerrorByTaskId(clusterId, hostIp, taskId, startTime, time, limit);
	}
	
	@RequestMapping(value="/{hostIp}/docker/task/{taskId}/rxpacket", method=RequestMethod.GET)
	@ApiOperation(value="docker task network interface rx packet", httpMethod="GET", notes="특정 host의 docker task network interface rx packet Like 검색( Docker attach name )")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
				name = "taskId", 
	            value = "Docker task id: (ex) redis", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
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
	public String getDockerNetworkRxpacketByTaskId(@PathVariable(value="clusterId", required=false) String clusterId,
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="taskId", required=true) String taskId
			,@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=true, defaultValue="5s") String time
			,@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDockerService.getDockerNetworkRxpacketByTaskId(clusterId, hostIp, taskId, startTime, time, limit);
	}
	
	@RequestMapping(value="/{hostIp}/docker/task/{taskId}/txbyte", method=RequestMethod.GET)
	@ApiOperation(value="docker task network interface tx byte", httpMethod="GET", notes="특정 host의 docker task network interface tx byte Like 검색( Docker attach name )")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
				name = "taskId", 
	            value = "Docker task id: (ex) redis", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
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
	public String getDockerNetworkTxbyteByTaskId(@PathVariable(value="clusterId", required=false) String clusterId,
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="taskId", required=true) String taskId
			,@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=true, defaultValue="5s") String time
			,@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDockerService.getDockerNetworkTxbyteByTaskId(clusterId, hostIp, taskId, startTime, time, limit);
	}
	
	@RequestMapping(value="/{hostIp}/docker/task/{taskId}/txdrop", method=RequestMethod.GET)
	@ApiOperation(value="docker task network interface tx dropped", httpMethod="GET", notes="특정 host의 docker task network interface tx dropped Like 검색( Docker attach name )")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
				name = "taskId", 
	            value = "Docker task id: (ex) redis", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
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
	public String getDockerNetworkTxdropByTaskId(@PathVariable(value="clusterId", required=false) String clusterId,
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="taskId", required=true) String taskId
			,@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=true, defaultValue="5s") String time
			,@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDockerService.getDockerNetworkTxdropByTaskId(clusterId, hostIp, taskId, startTime, time, limit);
	}
	
	@RequestMapping(value="/{hostIp}/docker/task/{taskId}/txerror", method=RequestMethod.GET)
	@ApiOperation(value="docker task network interface tx errors", httpMethod="GET", notes="특정 host의 docker task network interface tx errors Like 검색( Docker attach name )")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
				name = "taskId", 
	            value = "Docker task id: (ex) redis", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
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
	public String getDockerNetworkTxerrorByTaskId(@PathVariable(value="clusterId", required=false) String clusterId,
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="taskId", required=true) String taskId
			,@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=true, defaultValue="5s") String time
			,@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDockerService.getDockerNetworkTxerrorByTaskId(clusterId, hostIp, taskId, startTime, time, limit);
	}
	
	@RequestMapping(value="/{hostIp}/docker/task/{taskId}/txpacket", method=RequestMethod.GET)
	@ApiOperation(value="docker task network interface tx packet", httpMethod="GET", notes="특정 host의 docker task network interface tx packet Like 검색( Docker attach name )")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
				name = "taskId", 
	            value = "Docker task id: (ex) redis", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
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
	public String getDockerNetworkTxpacketByTaskId(@PathVariable(value="clusterId", required=false) String clusterId,
			 @PathVariable(value="hostIp", required=true) String hostIp
			,@PathVariable(value="taskId", required=true) String taskId
			,@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime
			,@RequestParam(value="time", required=true, defaultValue="5s") String time
			,@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDockerService.getDockerNetworkTxpacketByTaskId(clusterId, hostIp, taskId, startTime, time, limit);
	}
}
