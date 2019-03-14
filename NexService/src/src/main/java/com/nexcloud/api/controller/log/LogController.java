package com.nexcloud.api.controller.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.nexcloud.api.service.log.LogService;

@RestController
@RequestMapping(value = "/api/v1/log")
public class LogController {
	static final Logger logger = LoggerFactory.getLogger(LogController.class);

	@Autowired private LogService logService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String getLog() {
		return logService.getLogList();
	}
}
