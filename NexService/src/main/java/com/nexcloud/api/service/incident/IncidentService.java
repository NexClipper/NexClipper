package com.nexcloud.api.service.incident;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexcloud.rdb.domain.mysql.Incident;
import com.nexcloud.rdb.mapper.mysql.IncidentRepository;
import com.nexcloud.util.response.Mysql;
@Service
public class IncidentService{
	@Autowired private IncidentRepository incidentRepository;
	
	public String getStartEvents( String idx ){
		return Mysql.resultToResponse(incidentRepository.getStartEvents(idx));
	}
	
	public String getEvents( Incident incident ){
		return Mysql.resultToResponse(incidentRepository.getEvents(incident));
	}
	
	public String getEventByTime( String start_time ){
		return Mysql.resultToResponse(incidentRepository.getEventByTime(start_time));
	}
	
	public String getEventByIp( String start_time ){
		return Mysql.resultToResponse(incidentRepository.getEventByIp(start_time));
	}
	
	public String getEventByTarget( String start_time ){
		return Mysql.resultToResponse(incidentRepository.getEventByTarget(start_time));
	}
	
	public String getEventMap( Incident incident ){
		return Mysql.resultToResponse(incidentRepository.getEventMap(incident));
	}
}
