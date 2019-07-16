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
package com.nexcloud.api.service.account;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nexcloud.rdb.domain.mysql.User;
import com.nexcloud.rdb.mapper.mysql.UserRepository;
import com.nexcloud.util.response.Mysql;
@Service
public class AccountService{
	@Value("${spring.profiles.active}")	private String ACTIVE;
	@Autowired private UserRepository userRepository;
	@Autowired private Mysql mysql;

	public String getAccounts(){
		String iam = SecurityContextHolder.getContext().getAuthentication().getName();
		List<User> accounts = userRepository.getUserList();
		if (iam.equals("root")) {
			return mysql.resultToResponse(accounts);
		} else {
			List<User> tmpAccounts = new ArrayList<>();
			for (User account : accounts) {
				if (account.getUserId().equals(iam))
					tmpAccounts.add(account);
			}
			return mysql.resultToResponse(tmpAccounts);
		}
	}
	public User getAccount(String userId){
		return userRepository.getUser(userId);
	}
	public boolean addAccount(User user) {
		return userRepository.addUser(user);
	}
	public boolean deleteAccount(String userId) {
		return userRepository.deleteUser(userId);
	}
	public boolean changePassword(String userId,String password) {
		return userRepository.changePassword(new User(userId, password));
	}
	public String getActive() {
		return mysql.resultToResponse(ACTIVE);
	}
	public String getIam() {
		return mysql.resultToResponse(SecurityContextHolder.getContext().getAuthentication().getName());
	}
}
