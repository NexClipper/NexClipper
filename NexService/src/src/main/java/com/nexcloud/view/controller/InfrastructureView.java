package com.nexcloud.view.controller;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/infrastructure")
public class InfrastructureView {
	static final Logger logger = LoggerFactory.getLogger(InfrastructureView.class);

	@RequestMapping(value="/dashboard", method=RequestMethod.GET)
	public String dashboard() {
		return "infrastructure/dashboard";
	}
	@RequestMapping(value="/container", method=RequestMethod.GET)
	public String container() {
		return "infrastructure/container/view";
	}
	
	@RequestMapping(value="/{hostIp}/container/{container}/detail", method=RequestMethod.GET)
	public ModelAndView containerDetail(
		@PathVariable(value="container", required=false) String container,
		@PathVariable(value="hostIp", required=false) String hostIp) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("infrastructure/container/detail");
		mav.addObject("container", container);
		mav.addObject("hostIp", hostIp);
		return mav;
	}

	@RequestMapping(value="/host", method=RequestMethod.GET)
	public String host() {
		return "infrastructure/host/view";
	}

	@RequestMapping(value="/host/{hostIp}/detail", method=RequestMethod.GET)
	public ModelAndView hostDetail(@PathVariable(value="hostIp", required=false) String hostIp) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("infrastructure/host/detail");
		mav.addObject("hostIp", hostIp);
		return mav;
	}
	@RequestMapping(value="/host/{hostIp}/disk/detail", method=RequestMethod.GET)
	public ModelAndView hostResourceDisk(
		@PathVariable(value="hostIp", required=false) String hostIp,
		@RequestParam(value="mountName", required=true, defaultValue="/") String mountName) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("infrastructure/host/disk");
		mav.addObject("hostIp", hostIp);
		mav.addObject("mountName", mountName);
		return mav;
	}
	@RequestMapping(value="/host/{hostIp}/network/{interfaceId}/detail", method=RequestMethod.GET)
	public ModelAndView hostResourceNetwork(
		@PathVariable(value="hostIp", required=false) String hostIp,
		@PathVariable(value="interfaceId", required=false) String interfaceId) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("infrastructure/host/network");
		mav.addObject("hostIp", hostIp);
		mav.addObject("interface", interfaceId);
		return mav;
	}
	@RequestMapping(value="/host/{hostIp}/cpu/detail", method=RequestMethod.GET)
	public ModelAndView hostResourceCpu(
		@PathVariable(value="hostIp", required=false) String hostIp) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("infrastructure/host/cpu");
		mav.addObject("hostIp", hostIp);
		return mav;
	}
	@RequestMapping(value="/host/{hostIp}/memory/detail", method=RequestMethod.GET)
	public ModelAndView hostResourceMemory(
		@PathVariable(value="hostIp", required=false) String hostIp) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("infrastructure/host/memory");
		mav.addObject("hostIp", hostIp);
		return mav;
	}

	@RequestMapping(value="/resource", method=RequestMethod.GET)
	public String resource() {
		return "infrastructure/resource/view"; 
	}
	
	@RequestMapping(value="/resource/detail", method=RequestMethod.GET)
	public String resourceDetail() {
		return "infrastructure/resource/detail";
	}
	
}
