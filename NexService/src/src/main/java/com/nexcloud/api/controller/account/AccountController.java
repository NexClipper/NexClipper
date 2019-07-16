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
package com.nexcloud.api.controller.account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nexcloud.api.service.account.AccountService;
import com.nexcloud.rdb.domain.mysql.User;
import com.nexcloud.util.response.Mysql;
import com.nexcloud.util.security.HashUtil;

@RestController
@RequestMapping(value = "/api/v1/account")
public class AccountController {
	static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired private AccountService accountService;
	@Autowired private Mysql mysql;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String getAccounts() {
		return accountService.getAccounts();
	}
	@RequestMapping(value="", method=RequestMethod.POST)
	public String addAccount(
			@RequestParam(value="userId") String userId,
			@RequestParam(value="companyName") String companyName,
			@RequestParam(value="password") String password,
			@RequestParam(value="rpassword") String rpassword) {
		String emptyCheckStr = emptyCheck(userId, companyName, password, rpassword);
		if ("Success".equals(emptyCheckStr)) {
			if (!isEmail(userId))
				return "It is not an email format.";
			if (idCheck(userId))
				return "There is a duplicate ID.";
			if (!password.equals(rpassword))
				return "The passwords are not the same.";
			boolean result = accountService.addAccount(new User(userId, HashUtil.sha256(password), companyName));
			if (result) {
				return "success";
			} else {
				return "fail";
			}
		} else {
			return emptyCheckStr;
		}
	}

	@RequestMapping(value="/active", method=RequestMethod.GET)
	public String getActive() {
		return accountService.getActive();
	}
	@RequestMapping(value="/iam", method=RequestMethod.GET)
	public String getIam() {
		return accountService.getIam();
	}
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String deleteAccount(
			@RequestParam(value="userId") String userId) {
		if (accountService.deleteAccount(userId)) 
			return "success";
		else 
			return "fail";
	}
	@RequestMapping(value="/changePassword", method=RequestMethod.POST)
	public String changePassword(
			@RequestParam(value="userId") String userId,
			@RequestParam(value="password") String password) {
		if (accountService.changePassword(userId, HashUtil.sha256(password))) 
			return "success";
		else 
			return "fail";
	}
	private boolean idCheck (String userId) {
		User user = accountService.getAccount(userId);
		if (user != null && userId.equals(user.getUserId().toString()))
			return true;
		else
			return false;
	}
	private boolean isEmail(String email) {
	    return email.matches("^[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}");
	}
	private String emptyCheck (
			String userId, String companyName, String password, String rpassword) {
		if (isEmpty(userId))
			return "Id is empry.";
		else if (isEmpty(companyName)) {
			return "Company name is empry.";
		}
		else if (isEmpty(password)) {
			return "Password is empry.";
		}
		else if (isEmpty(rpassword)) {
			return "Second password is empry.";
		}
			return "Success";
	}
	private boolean isEmpty(String str) {
		if (str == null || str == "")
			return true;
		else
			return false;
	}
}
