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
package com.nexcloud.api.service.rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexcloud.rdb.domain.mysql.Rule;
import com.nexcloud.rdb.mapper.mysql.RuleRepository;
import com.nexcloud.util.response.Mysql;
@Service
public class RuleService{
	@Autowired private RuleRepository ruleRepository;
	@Autowired private Mysql mysql;
	
	public String getRuleList( Rule rule ){
		return mysql.resultToResponse(ruleRepository.getRuleList( rule ));
	}
	
	public String getTargetSystem( ){
		return mysql.resultToResponse(ruleRepository.getTargetSystem( ));
	}
	
	public String getTarget( Rule rule ){
		return mysql.resultToResponse(ruleRepository.getTarget( rule ));
	}
	
	public String getRulesetTargetSystem( ){
		return mysql.resultToResponse(ruleRepository.getRulesetTargetSystem( ));
	}
	
	public String getRulesetTarget( Rule rule ){
		return mysql.resultToResponse(ruleRepository.getRulesetTarget( rule ));
	}
	
	public String getRulesetMetric( Rule rule ){
		return mysql.resultToResponse(ruleRepository.getRulesetMetric( rule ));
	}
	
	public String getRulesetDetail( int idx){
		return mysql.resultToResponse(ruleRepository.getRulesetDetail( idx ));
	}
	
	
	public String createRuleset( Rule rule ){
		
		/**
		 * Message 및 Incident 조건 문 생성  
		 */
		String equility						= rule.getEquility();
		String calc_value					= rule.getCalc_value();
		String calc_value_sel				= rule.getCalc_value_sel();
		String duration						= rule.getDuration();
		String notify						= rule.getNotify();
		String status						= rule.getStatus();
		String message						= rule.getMessage();
		
		// Condition 설정
		String condition					= null;
		
		if( rule.getMetric().indexOf("load") != -1 )
			calc_value						= calc_value_sel;
		
		condition							= equility+""+calc_value+" and >"+duration+"m";
		rule.setCondition(condition);
		
		if( "CPU".equals(rule.getTarget()) && rule.getMetric().indexOf("load") != -1 )
			rule.setCalc_value(rule.getCalc_value_sel());
		
		Rule dataRule						= ruleRepository.selectRuleCheck( rule );
		
		// insert
		if( dataRule == null )
		{
			return mysql.resultToResponse(ruleRepository.createRuleset( rule ));
		}
		// update
		else
		{
			rule.setIdx(dataRule.getIdx());
			rule.setMetric(dataRule.getMetric());
			return mysql.resultToResponse(ruleRepository.updateRuleSet(rule));
		}
		
	}
}
