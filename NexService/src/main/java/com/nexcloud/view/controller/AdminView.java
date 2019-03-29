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
@RequestMapping(value = "/admin")
public class AdminView {
	static final Logger logger = LoggerFactory.getLogger(AdminView.class);

	@RequestMapping(value="/agent", method=RequestMethod.GET)
	public String agent() { 
		return "admin/agent/view";
	}
	@RequestMapping(value="/agent/detail", method=RequestMethod.GET)
	public String agentDetail() {
		return "admin/agent/detail";
	}
	@RequestMapping(value="/account", method=RequestMethod.GET)
	public String account() {
		return "admin/account/view";
	}
	@RequestMapping(value="/account/detail", method=RequestMethod.GET)
	public String accountDetail() {
		return "admin/account/detail";
	}
	@RequestMapping(value="/role", method=RequestMethod.GET)
	public String role() {
		return "admin/role/view";
	}
	@RequestMapping(value="/role/detail", method=RequestMethod.GET)
	public String roleDetail() {
		return "admin/role/detail";
	}
}
