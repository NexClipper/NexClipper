package com.nexcloud.view.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nexcloud.api.service.user.UserService;
import com.nexcloud.rdb.domain.mysql.User;
import com.nexcloud.util.security.HashUtil;

@Controller
@RequestMapping(value = "/user")
public class UserView {
	@Autowired
	private UserService userService;
	
	static final Logger logger = LoggerFactory.getLogger(UserView.class);

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(@RequestParam(value="error", defaultValue="false") String error) {
		return "user/login";
	}
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public ModelAndView addUser(
		@RequestParam(value="userId") String userId,
		@RequestParam(value="companyName") String companyName,
		@RequestParam(value="password") String password,
		@RequestParam(value="rpassword") String rpassword
	) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/join");
		String emptyCheckStr = emptyCheck(userId, companyName, password, rpassword);
		if ("Success".equals(emptyCheckStr)) {
			if (idCheck(userId))
				return mav.addObject("msg", "There is a duplicate ID.");
			if (!password.equals(rpassword))
				return mav.addObject("msg", "The passwords are not the same.");
			boolean result = userService.addUser(new User(userId, HashUtil.sha256(password), companyName));
			if (result) {
				return mav.addObject("msg", "success");
			} else {
				return mav.addObject("msg", "fail");
			}
		} else {
			return mav.addObject("msg", emptyCheckStr);
		}
	}
	private boolean idCheck (String userId) {
		User user = userService.getUser(userId);
		if (user != null && userId.equals(user.getUserId().toString()))
			return true;
		else
			return false;
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
