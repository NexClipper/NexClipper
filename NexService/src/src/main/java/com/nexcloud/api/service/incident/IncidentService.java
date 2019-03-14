package com.nexcloud.api.service.incident;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexcloud.rdb.domain.mysql.Incident;
import com.nexcloud.rdb.domain.mysql.Rule;
import com.nexcloud.rdb.mapper.mysql.IncidentRepository;
import com.nexcloud.util.response.Mysql;
@Service
public class IncidentService{
	@Autowired private IncidentRepository incidentRepository;
	@Autowired private Mysql mysql;
	
	public String getStartEvents( String idx ){
		return mysql.resultToResponse(incidentRepository.getStartEvents(idx));
	}
	
	public String getEvents( Incident incident ){
		return mysql.resultToResponse(incidentRepository.getEvents(incident));
	}
	
	public String getEventByTime( String start_time ){
		return mysql.resultToResponse(incidentRepository.getEventByTime(start_time));
	}
	
	public String getEventByIp( String start_time ){
		return mysql.resultToResponse(incidentRepository.getEventByIp(start_time));
	}
	
	public String getEventByTarget( String start_time ){
		return mysql.resultToResponse(incidentRepository.getEventByTarget(start_time));
	}
	
	public String getEventMap( Incident incident ){
		return mysql.resultToResponse(incidentRepository.getEventMap(incident));
	}
	
	public String getTargetSystem( Rule rule ){
		return mysql.resultToResponse(incidentRepository.getTargetSystem(rule));
	}
	
	public String getTarget( Rule rule ){
		return mysql.resultToResponse(incidentRepository.getTarget(rule));
	}
}
