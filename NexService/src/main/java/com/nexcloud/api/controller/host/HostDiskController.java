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

import com.nexcloud.api.service.host.HostDiskService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/v1/host/{hostIp}/disk")
public class HostDiskController {
	static final Logger logger = LoggerFactory.getLogger(HostDiskController.class);
	
	@Autowired
	private HostDiskService hostDiskService;

	@RequestMapping(value="/check", method=RequestMethod.GET)
	public String check() {
		logger.debug("disk check");
		return "disk check";
	}
	
	
	@RequestMapping(value="/free", method=RequestMethod.GET)
	@ApiOperation(value="Host별  mount된 disk free byte", httpMethod="GET", notes="disk free byte")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "mountName", 
	            value = "Mount된 disk device name: /, /boot, /var 등", 
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
	public String getDiskFreeByte(
			@PathVariable(value="hostIp", required=true) String hostIp,
			@RequestParam(value="mountName", required=true, defaultValue="/") String mountName,
			@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime,
			@RequestParam(value="time", required=true, defaultValue="5s") String time,
			@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDiskService.getDiskFreeByte(hostIp, mountName, startTime, time, limit);
	}
	
	
	@RequestMapping(value="/usage", method=RequestMethod.GET)
	@ApiOperation(value="Host별  mount된 disk usage percent", httpMethod="GET", notes="disk used percent")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "mountName", 
	            value = "Mount된 disk device name: /, /boot, /var 등", 
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
	public String getDiskUsagePercent(
			@PathVariable(value="hostIp", required=true) String hostIp,
			@RequestParam(value="mountName", required=true, defaultValue="/") String mountName,
			@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime,
			@RequestParam(value="time", required=true, defaultValue="5s") String time,
			@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDiskService.getDiskUsagePercent(hostIp, mountName, startTime, time, limit);
	}

	@RequestMapping(value="/readbyte", method=RequestMethod.GET)
	@ApiOperation(value="Host별  mount된 disk read byte", httpMethod="GET", notes="disk read byte")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "mountName", 
	            value = "Mount된 disk device name: /, /boot, /var 등", 
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
	public String getDiskReadbyte(
			@PathVariable(value="hostIp", required=true) String hostIp,
			@RequestParam(value="mountName", required=true, defaultValue="/") String mountName,
			@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime,
			@RequestParam(value="time", required=true, defaultValue="5s") String time,
			@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDiskService.getDiskReadbyte(hostIp, mountName, startTime, time, limit);
	}
	
	@RequestMapping(value="/writebyte", method=RequestMethod.GET)
	@ApiOperation(value="Host별  mount된 disk write byte", httpMethod="GET", notes="disk write byte")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "mountName", 
	            value = "Mount된 disk device name: /, /boot, /var 등", 
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
	public String getDiskWritebyte(
			@PathVariable(value="hostIp", required=true) String hostIp,
			@RequestParam(value="mountName", required=true, defaultValue="/") String mountName,
			@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime,
			@RequestParam(value="time", required=true, defaultValue="5s") String time,
			@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDiskService.getDiskWritebyte(hostIp, mountName, startTime, time, limit);
	}
	
	@RequestMapping(value="/total", method=RequestMethod.GET)
	@ApiOperation(value="Host별  mount된 disk total byte", httpMethod="GET", notes="disk total byte")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "mountName", 
	            value = "Mount된 disk device name: /, /boot, /var 등", 
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
	public String getDiskTotal(
			@PathVariable(value="hostIp", required=true) String hostIp,
			@RequestParam(value="mountName", required=true, defaultValue="/") String mountName,
			@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime,
			@RequestParam(value="time", required=true, defaultValue="5s") String time,
			@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDiskService.getDiskTotal(hostIp, mountName, startTime, time, limit);
	}
	
	@RequestMapping(value="/used", method=RequestMethod.GET)
	@ApiOperation(value="Host별  mount된 disk used byte", httpMethod="GET", notes="disk used byte")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "mountName", 
	            value = "Mount된 disk device name: /, /boot, /var 등", 
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
	public String getDiskUsedbyte(
			@PathVariable(value="hostIp", required=true) String hostIp,
			@RequestParam(value="mountName", required=true, defaultValue="/") String mountName,
			@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime,
			@RequestParam(value="time", required=true, defaultValue="5s") String time,
			@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDiskService.getDiskUsedbyte(hostIp, mountName, startTime, time, limit);
	}
	
	@RequestMapping(value="/reads", method=RequestMethod.GET)
	@ApiOperation(value="Host별  mount된 disk reads", httpMethod="GET", notes="disk reads")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "mountName", 
	            value = "Mount된 disk device name: /, /boot, /var 등", 
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
	public String getDiskReads(
			@PathVariable(value="hostIp", required=true) String hostIp,
			@RequestParam(value="mountName", required=true, defaultValue="/") String mountName,
			@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime,
			@RequestParam(value="time", required=true, defaultValue="5s") String time,
			@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDiskService.getDiskReads(hostIp, mountName, startTime, time, limit);
	}
	
	@RequestMapping(value="/writes", method=RequestMethod.GET)
	@ApiOperation(value="Host별  mount된 disk writes", httpMethod="GET", notes="disk writes")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "hostIp", 
	            value = "Host IP (ex) 192.168.0.162", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "mountName", 
	            value = "Mount된 disk device name: /, /boot, /var 등", 
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
	public String getDiskWrites(
			@PathVariable(value="hostIp", required=true) String hostIp,
			@RequestParam(value="mountName", required=true, defaultValue="/") String mountName,
			@RequestParam(value="startTime", required=true, defaultValue="1h") String startTime,
			@RequestParam(value="time", required=true, defaultValue="5s") String time,
			@RequestParam(value="limit", required=true, defaultValue="1000") int limit
	) {
		return hostDiskService.getDiskWrites(hostIp, mountName, startTime, time, limit);
	}
}
