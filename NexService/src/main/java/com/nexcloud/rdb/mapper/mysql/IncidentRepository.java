package com.nexcloud.rdb.mapper.mysql;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.nexcloud.rdb.domain.mysql.Incident;
import com.nexcloud.rdb.domain.mysql.IncidentCount;

@Repository("IncidentMapper")
public interface IncidentRepository {
	public List<Incident> getStartEvents( String idx );
	public List<Incident> getEvents(Incident incident );
	public List<Incident> getEventByTime( String start_time );
	public List<Incident> getEventByIp( String start_time );
	public List<Incident> getEventByTarget( String start_time );
	public List<Incident> getEventMap(Incident incident );
}
