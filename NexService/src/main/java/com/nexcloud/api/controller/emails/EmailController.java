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
package com.nexcloud.api.controller.emails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexcloud.api.service.email.EmailService;
import com.nexcloud.rdb.domain.mysql.Email;
@RestController
@RequestMapping(value = "/api/v1/email")
public class EmailController {
	static final Logger logger = LoggerFactory.getLogger(EmailController.class);
	
	@Autowired private EmailService emailService;
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String addEmail(
			@RequestParam(value="email") String email,
			@RequestParam(value="emailType") char emailType,
			@RequestParam(value="emailPassword") String emailPassword) {
		if (!isEmail(email)) {
			return "It is not an email format."; 
		}
		boolean result = false;
		result = emailService.addEmail(new Email(0, email, emailType, emailPassword));
		if (result) {
			return "success";
		} else {
			return "fail";
		}
	}
	@RequestMapping(value="", method=RequestMethod.GET)
	public String getEmail() {
		return emailService.getEmails();
	}
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String deleteEmail(
			@RequestParam(value="emailNo") int emailNo) {
		if (emailService.deleteEmail(emailNo))
			return "success";
		else 
			return "fail";
	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String updateEmail(
			@RequestParam(value="emailNo") int emailNo,
			@RequestParam(value="email") String email,
			@RequestParam(value="emailPassword") String emailPassword) {
		if (!isEmail(email)) {
			return "It is not an email format."; 
		}
		if (emailService.updateEmail(new Email(emailNo, email, 'S', emailPassword)))
			return "success";
		else 
			return "fail";
	}
	
	private boolean isEmail(String email) {
	    return email.matches("^[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}");
	}
}
