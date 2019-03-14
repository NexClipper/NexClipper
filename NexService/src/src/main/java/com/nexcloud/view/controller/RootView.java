package com.nexcloud.view.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class RootView {
	static final Logger logger = LoggerFactory.getLogger(RootView.class);
	@RequestMapping(value="", method=RequestMethod.GET)
	public String root() {
		return "dashboard/default";
	}
}
