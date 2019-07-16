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
package com.nexcloud.api.controller.rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexcloud.api.service.rule.RuleService;
import com.nexcloud.rdb.domain.mysql.Rule;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/v1/rule")
public class RuleController {
	static final Logger logger = LoggerFactory.getLogger(RuleController.class);

	@Autowired private RuleService ruleService;
	
	/**
	 * Ruleset manager List
	 * @param target_system
	 * @param target
	 * @param metric
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	@ApiOperation(value="조회기간중 이벤트 발생내용 조회( List )", httpMethod="GET", notes="조회기간중 이벤트 발생내용 조회( List )")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "target_system", 
	            value = "이벤트 발생 대상 (ex) Docker, Host, POD... ", 
	            dataType = "string", 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "target", 
	            value = "이벤트 발생 metric 대상 (ex) CPU, Memory... ", 
	            dataType = "string", 
	            paramType = "query"
	    ),
		@ApiImplicitParam(
	            name = "metric", 
	            value = "이벤트 발생 metric  (ex) cpu_used_percent ", 
	            dataType = "string", 
	            paramType = "query"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getRuleList(	 @RequestParam(value="target_system", required=false) String target_system
								,@RequestParam(value="target", required=false) String target
								,@RequestParam(value="metric", required=false) String metric
								) throws Exception {
		Rule rule	 = new Rule();
		rule.setTarget_system(target_system);
		rule.setTarget(target);
		rule.setMetric(metric);
		return ruleService.getRuleList( rule );
	}
	
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String setRule() {
		System.out.println("add rule");
		return "add rule";
	}
	
	@RequestMapping(value="", method=RequestMethod.PUT)
	public String updateRule() {
		System.out.println("update rule");
		return "update rule";
	}
	
	@RequestMapping(value="", method=RequestMethod.DELETE)
	public String deleteRule() {
		System.out.println("delete rule");
		return "delete rule";
	}
	
	@RequestMapping(value="/targetsystem", method=RequestMethod.GET)
	@ApiOperation(value="이벤트 발생 대상", httpMethod="GET", notes="이벤트 발생 대상")
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getTargetSystem( ) throws Exception {
		return ruleService.getTargetSystem(  );
	}
	
	@RequestMapping(value="/{target_system}/target", method=RequestMethod.GET)
	@ApiOperation(value="이벤트 발생 대상에서 수집할수 있는 대상 metirc", httpMethod="GET", notes="이벤트 발생 대상에서 수집할수 있는 대상 metirc")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "target_system", 
	            value = "이벤트 발생 대상 (ex) Docker, Host, POD... ", 
	            required = true,
	            dataType = "string", 
	            paramType = "path"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getTarget( @PathVariable(value="target_system") String target_system ) throws Exception {
		Rule rule		= new Rule();
		rule.setTarget_system(target_system);
		return ruleService.getTarget( rule );
	}
	
	
	/**
	 * Rule set 설정 및 수정
	 */
	@RequestMapping(value="/ruleset/targetsystem", method=RequestMethod.GET)
	@ApiOperation(value="룰셋 설정 가능한 대상 시스템", httpMethod="GET", notes="룰셋 설정 가능한 대상 시스템")
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getRulesetTargetSystem( ) throws Exception {
		return ruleService.getRulesetTargetSystem(  );
	}
	
	@RequestMapping(value="/ruleset/target_system/{target_system}/target", method=RequestMethod.GET)
	@ApiOperation(value="룰셋 시스템의  대상 metric", httpMethod="GET", notes="룰셋 시스템의  대상 metric")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "target_system", 
	            value = "이벤트 발생 대상 (ex) Docker, Host, POD... ", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getRulesetTarget( @PathVariable(value="target_system") String target_system ) throws Exception {
		Rule rule		= new Rule();
		rule.setTarget_system(target_system);
		return ruleService.getRulesetTarget( rule );
	}
	
	@RequestMapping(value="/ruleset/target_system/{target_system}/target/{target}/metric", method=RequestMethod.GET)
	@ApiOperation(value="룰셋 대상 metric의 상세 metric", httpMethod="GET", notes="룰셋 대상 metric의 상세 metric")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "target_system", 
	            value = "이벤트 발생 대상 (ex) Docker, Host, POD...", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    ),
		@ApiImplicitParam(
	            name = "target", 
	            value = "이벤트 발생 metric 대상 (ex) CPU, Memory... ", 
	            required = true, 
	            dataType = "string", 
	            paramType = "path"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getRulesetMetric( @PathVariable(value="target_system") String target_system, @PathVariable(value="target") String target ) throws Exception {
		Rule rule		= new Rule();
		rule.setTarget_system(target_system);
		rule.setTarget(target);
		return ruleService.getRulesetMetric( rule );
	}
	
	////////////////////////
	/**
	 * 
	 * @param target_system
	 * @param target
	 * @param metric
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{idx}", method=RequestMethod.GET)
	@ApiOperation(value="설정된 룰셋 조회", httpMethod="GET", notes="설정된 룰셋 조회")
	@ApiImplicitParams({
		@ApiImplicitParam(
	            name = "idx", 
	            value = "설정된 rule set key (ex) 1,2,... ", 
	            required = true,
	            dataType = "int", 
	            paramType = "path"
	    )
	})
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String getRulesetDetail(	 @PathVariable(value="idx") int idx ) throws Exception {
		return ruleService.getRulesetDetail( idx );
	}
	
	/**
	 * 
	 * @param target_system
	 * @param target
	 * @param metric
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ApiOperation(value="룰셋 저장 및 수정", httpMethod="POST", notes="룰셋 저장 및 수정")
	@ApiResponses(value={
			@ApiResponse( code=200, message="SUCCESS"),
			@ApiResponse( code=500, message="Internal Server Error")
	})
	public String createRuleset( Rule rule ) throws Exception {
		return ruleService.createRuleset( rule );
	}
}
