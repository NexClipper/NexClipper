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
package com.nexcloud.view.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/incident")
public class IncidentView {
	static final Logger logger = LoggerFactory.getLogger(IncidentView.class);

	@RequestMapping(value="/trace", method=RequestMethod.GET)
	public String trace() {
		return "incident/trace/view";
	}
	@RequestMapping(value="/trace/detail", method=RequestMethod.GET)
	public String traceDetail() {
		return "incident/trace/detail";
	}
	@RequestMapping(value="/map", method=RequestMethod.GET)
	public String map() {
		return "incident/map/view";
	}
	@RequestMapping(value="/map/detail", method=RequestMethod.GET)
	public String mapDetail() {
		return "incident/map/detail";
	}
	@RequestMapping(value="/rule", method=RequestMethod.GET)
	public String rule() {
		return "incident/rule/view";
	}
	@RequestMapping(value="/rule/detail", method=RequestMethod.GET)
	public String ruleDetail() {
		return "incident/rule/detail";
	}
}
