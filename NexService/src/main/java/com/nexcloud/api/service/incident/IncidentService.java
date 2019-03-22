package com.nexcloud.api.service.incident;
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
