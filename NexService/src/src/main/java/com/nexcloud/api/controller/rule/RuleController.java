package com.nexcloud.api.controller.rule;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.nexcloud.api.service.rule.RuleService;

@RestController
@RequestMapping(value = "/api/v1/rule")
public class RuleController {
	static final Logger logger = LoggerFactory.getLogger(RuleController.class);

	@Autowired private RuleService ruleService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String  getRule() {
		return ruleService.getRuleList();
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
	@RequestMapping(value="/{targetSystem}", method=RequestMethod.GET)
	public String taskCount() {
		System.out.println("rule targer");
		return "rule targer";
	}
}
