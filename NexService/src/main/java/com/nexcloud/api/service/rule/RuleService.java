package com.nexcloud.api.service.rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nexcloud.rdb.mapper.mysql.RuleRepository;
import com.nexcloud.util.response.Mysql;
@Service
public class RuleService{
	@Autowired private RuleRepository ruleRepository;
	
	public String getRuleList(){
		return Mysql.resultToResponse(ruleRepository.getRuleList());
	}
}
