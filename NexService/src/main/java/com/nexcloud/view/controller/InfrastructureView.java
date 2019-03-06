package com.nexcloud.view.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value="/container/detail", method=RequestMethod.GET)
	public String containerDetail() {
		return "infrastructure/container/detail";
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

	@RequestMapping(value="/resource", method=RequestMethod.GET)
	public String resource() {
		return "infrastructure/resource/view";
	}
	
	@RequestMapping(value="/resource/detail", method=RequestMethod.GET)
	public String resourceDetail() {
		return "infrastructure/resource/detail";
	}
	
}
