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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/manager")
public class ManagerView {
	static final Logger logger = LoggerFactory.getLogger(ManagerView.class);

	@RequestMapping(value="/cluster", method=RequestMethod.GET)
	public String cluster() {
		return "manager/cluster/view";
	}
	@RequestMapping(value="/agent/{clusterInfo}", method=RequestMethod.GET)
	public ModelAndView agent(@PathVariable(value="clusterInfo", required=false) String clusterInfo) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manager/agent/view");
		mav.addObject("clusterInfo", clusterInfo);
		return mav;
	}
}
