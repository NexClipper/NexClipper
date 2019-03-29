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
package com.nexcloud.workflow.mapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.nexcloud.db.domain.Rule;
import com.nexcloud.rule.domain.Notification;

@Mapper
@Repository("dbMapper")
public interface DBMapper {
	public List<Rule> selectRule(Map<String, String> map) throws Exception;
	
	public List<Rule> selectAgentRule(Map<String, String> map) throws Exception;
	
	public int selectIncident( Notification notification ) throws Exception;
	
	public int insertIncident( Notification notification ) throws Exception;
	
	public int updateIncident( Notification notification ) throws Exception;
	
	public int insertLog( Map<String, Object> map ) throws Exception;
	
	public int updateIncidentFinishTime( Notification notification ) throws Exception;
	
	public String selectEmail(  ) throws Exception;
}
