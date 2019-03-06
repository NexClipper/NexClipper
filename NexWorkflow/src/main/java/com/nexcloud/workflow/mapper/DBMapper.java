package com.nexcloud.workflow.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.nexcloud.db.domain.Agent;
import com.nexcloud.db.domain.Config;
import com.nexcloud.db.domain.NodeInfo;
import com.nexcloud.db.domain.Params;
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
