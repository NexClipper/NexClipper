package com.nexcloud.rdb.mapper.mysql;
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
import java.util.List;

import org.springframework.stereotype.Repository;

import com.nexcloud.rdb.domain.mysql.Incident;
import com.nexcloud.rdb.domain.mysql.Rule;

@Repository("IncidentMapper")
public interface IncidentRepository {
	public List<Incident> getStartEvents( String idx );
	public List<Incident> getEvents(Incident incident );
	public List<Incident> getEventByTime( String start_time );
	public List<Incident> getEventByIp( String start_time );
	public List<Incident> getEventByTarget( String start_time );
	public List<Incident> getEventMap(Incident incident );
	public List<Rule> getTargetSystem( Rule rule );
	public List<Rule> getTarget( Rule rule );
}
