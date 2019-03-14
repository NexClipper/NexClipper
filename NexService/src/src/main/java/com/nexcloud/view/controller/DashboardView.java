package com.nexcloud.view.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/dashboard")
public class DashboardView {
	static final Logger logger = LoggerFactory.getLogger(DashboardView.class);

	@RequestMapping(value="/default", method=RequestMethod.GET)
	public String defaultDashboard() {
		return "dashboard/default";
	}
}
