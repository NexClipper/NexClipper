package com.nexcloud.api.controller.incident;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexcloud.api.service.incident.IncidentService;
import com.nexcloud.rdb.domain.mysql.Incident;
import com.nexcloud.rdb.domain.mysql.Rule;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
@RequestMapping(value = "/api/v1/incident")
public class IncidentController {
	static final Logger logger = LoggerFactory.getLogger(IncidentController.class);

	@Autowired private IncidentService incidentService;
	
	/**
	 * 종료되지 않은 이벤트 조회
	 * @param idx
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/start", method=RequestMethod.GET)
	@ApiOperation(value="종료되지 않은 이벤트 조회", httpMethod="GET", notes="종료되지 않은 이벤트 조회")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "idx", 
	            value = "이벤트 id(ex) 1 ", 
	            required = false, 
	            dataType = "string", 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getStartEvents( @RequestParam("idx") String idx ) throws Exception {
		return incidentService.getStartEvents(idx);
	}
	
	
	/**
	 * 조회기간중 이벤트 발생내용 조회
	 * @param start_time
	 * @param id
	 * @param target_ip
	 * @param target_system
	 * @param target
	 * @param status
	 * @param severity
	 * @param searchTxt
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/event", method=RequestMethod.GET)
	@ApiOperation(value="조회기간중 이벤트 발생내용 조회( List )", httpMethod="GET", notes="조회기간중 이벤트 발생내용 조회( List )")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "start_time", 
	            value = "이벤트 조회 시간 (ex) 한시가전 : 1h, 두시간전 :2h .. ", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "id", 
	            value = "이벤트가 발생한 Task(Container) ID (ex) nexclipper ", 
	            required = false, 
	            dataType = "string", 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "target_ip", 
	            value = "이벤트가 발생한 Node(Agent) IP (ex) 192.168.0.1 ", 
	            required = false, 
	            dataType = "string", 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "target_system", 
	            value = "이벤트 발생 대상 (ex) Docker, Host, POD... ", 
	            required = false, 
	            dataType = "string", 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "target", 
	            value = "이벤트 발생 metric  (ex) CPU, Memory... ", 
	            required = false, 
	            dataType = "string", 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "status", 
	            value = "이벤트  발생상태  (ex) S:발생중인 이벤트, F:종료된 이벤트 ", 
	            required = false, 
	            dataType = "string", 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "severity", 
	            value = "이벤트 등급  (ex) Critical, Warning ", 
	            required = false, 
	            dataType = "string", 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "searchTxt", 
	            value = "조회 하고자 하는 내용  (ex) private3 ", 
	            required = false, 
	            dataType = "string", 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getEvents(	 @RequestParam("start_time") String start_time
								,@RequestParam("id") String id
								,@RequestParam("target_ip") String target_ip
								,@RequestParam("target_system") String target_system
								,@RequestParam("target") String target
								,@RequestParam("status") String status
								,@RequestParam("severity") String severity
								,@RequestParam("searchTxt") String searchTxt
								) throws Exception {
		Incident incident		= new Incident();
		incident.setStart_time(start_time);
		incident.setId(id);
		incident.setTarget_ip(target_ip);
		incident.setTarget(target);
		incident.setTarget_system(target_system);
		incident.setStatus(status);
		incident.setSeverity(severity);
		incident.setSearchTxt(searchTxt);
		
		return incidentService.getEvents(incident);
	}
	
	/**
	 * 시간별 이벤트 건수
	 * @param start_time
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/event/time", method=RequestMethod.GET)
	@ApiOperation(value="시간별 이벤트 건수", httpMethod="GET", notes="시간별 이벤트 건수")
	@ApiImplicitParams({
		@ApiImplicitParam(
				name = "start_time", 
	            value = "이벤트 조회 시간 (ex) 한시가전 : 1h, 두시간전 :2h .. ", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getEventByTime( @RequestParam("start_time") String start_time ) throws Exception {
		return incidentService.getEventByTime(start_time);
	}
	
	/**
	 * IP별 이벤트 건수
	 * @param start_time
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/event/ip", method=RequestMethod.GET)
	@ApiOperation(value="IP별 이벤트 건수", httpMethod="GET", notes="IP별 이벤트 건수")
	@ApiImplicitParams({
		@ApiImplicitParam(
				name = "start_time", 
	            value = "이벤트 조회 시간 (ex) 한시가전 : 1h, 두시간전 :2h .. ", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getEventByIp( @RequestParam("start_time") String start_time ) throws Exception {
		return incidentService.getEventByIp(start_time);
	}
	
	/**
	 * Target별 이벤트 건수
	 * @param start_time
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/event/target", method=RequestMethod.GET)
	@ApiOperation(value="Target별 이벤트 건수", httpMethod="GET", notes="Target별 이벤트 건수")
	@ApiImplicitParams({
		@ApiImplicitParam(
				name = "start_time", 
	            value = "이벤트 조회 시간 (ex) 한시가전 : 1h, 두시간전 :2h .. ", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getEventByTarget( @RequestParam("start_time") String start_time ) throws Exception {
		return incidentService.getEventByTarget(start_time);
	}
	
	
	@RequestMapping(value="/event/map", method=RequestMethod.GET)
	@ApiOperation(value="조회기간중 이벤트 발생내용 조회( Map )", httpMethod="GET", notes="조회기간중 이벤트 발생내용 조회( Map )")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "start_time", 
	            value = "이벤트 조회 시간 (ex) 한시가전 : 1h, 두시간전 :2h .. ", 
	            required = true, 
	            dataType = "string", 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "id", 
	            value = "이벤트가 발생한 Task(Container) ID (ex) nexclipper ", 
	            required = false, 
	            dataType = "string", 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "target_ip", 
	            value = "이벤트가 발생한 Node(Agent) IP (ex) 192.168.0.1 ", 
	            required = false, 
	            dataType = "string", 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "target_system", 
	            value = "이벤트 발생 대상 (ex) Docker, Host, POD... ", 
	            required = false, 
	            dataType = "string", 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "target", 
	            value = "이벤트 발생 metric  (ex) CPU, Memory... ", 
	            required = false, 
	            dataType = "string", 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "severity", 
	            value = "이벤트 등급  (ex) Critical, Warning ", 
	            required = false, 
	            dataType = "string", 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "searchTxt", 
	            value = "조회 하고자 하는 내용  (ex) private3 ", 
	            required = false, 
	            dataType = "string", 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getEventMap(	 @RequestParam("start_time") String start_time
								,@RequestParam("id") String id
								,@RequestParam("target_ip") String target_ip
								,@RequestParam("target_system") String target_system
								,@RequestParam("target") String target
								,@RequestParam("severity") String severity
								,@RequestParam("searchTxt") String searchTxt
								) throws Exception {
		Incident incident		= new Incident();
		incident.setStart_time(start_time);
		incident.setId(id);
		incident.setTarget_ip(target_ip);
		incident.setTarget(target);
		incident.setTarget_system(target_system);
		incident.setSeverity(severity);
		incident.setSearchTxt(searchTxt);
		
		return incidentService.getEventMap(incident);
	}
	
	@RequestMapping(value="/rule/targetsystem", method=RequestMethod.GET)
	@ApiOperation(value="target system조회", httpMethod="GET", notes="target system조회")
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getTargetSystem(	) throws Exception {
		Rule rule			= new Rule();
		
		return incidentService.getTargetSystem(rule);
	}
	
	@RequestMapping(value="/rule/target", method=RequestMethod.GET)
	@ApiOperation(value="target 조회", httpMethod="GET", notes="target 조회")
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getTarget(	) throws Exception {
		Rule rule			= new Rule();
		
		return incidentService.getTarget(rule);
	}
}
