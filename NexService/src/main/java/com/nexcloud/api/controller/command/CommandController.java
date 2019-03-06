package com.nexcloud.api.controller.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.nexcloud.api.service.command.CommandService;

@RestController
@RequestMapping(value = "/api/v1/command")
public class CommandController {
	static final Logger logger = LoggerFactory.getLogger(CommandController.class);

	@Autowired private CommandService commandService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String getCommand() {
		return commandService.getCommandList();
	}
}
