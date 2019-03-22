package com.nexcloud.api.controller.event;
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

import com.nexcloud.api.service.event.EventService;

@RestController
@RequestMapping(value = "/api/v1/event")
public class EventController {
	static final Logger logger = LoggerFactory.getLogger(EventController.class);
	@Autowired private EventService eventService;

	@RequestMapping(value="", method=RequestMethod.GET)
	public String event() {
		System.out.println("event list");
		return "event list";
	}
	@RequestMapping(value="/task/count", method=RequestMethod.GET)
	public String taskCount() {
		System.out.println("task count");
		return "task count";
	}
	@RequestMapping(value="/node/count", method=RequestMethod.GET)
	public String nodeCount() {
		System.out.println("node count");
		return "node count";
	}
	@RequestMapping(value="/time", method=RequestMethod.GET)
	public String time() {
		System.out.println("time data");
		return "time data";
	}
	@RequestMapping(value="/node", method=RequestMethod.GET)
	public String node() {
		System.out.println("node data");
		return "node data";
	}
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public String detail() {
		System.out.println("event detail");
		return "event detail";
	}
	@RequestMapping(value="/task/history", method=RequestMethod.GET)
	public String taskHistory() {
		System.out.println("task history");
		return "task history";
	}
	@RequestMapping(value="/agent/history", method=RequestMethod.GET)
	public String agentHistory() {
		System.out.println("agent history");
		return "agent history";
	}
}
