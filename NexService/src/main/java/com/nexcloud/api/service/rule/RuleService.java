package com.nexcloud.api.service.rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nexcloud.rdb.mapper.mysql.RuleRepository;
import com.nexcloud.util.response.Mysql;
@Service
public class RuleService{
	@Autowired private RuleRepository ruleRepository;
	@Autowired private Mysql mysql;
	
	public String getRuleList(){
		return mysql.resultToResponse(ruleRepository.getRuleList());
	}
}
