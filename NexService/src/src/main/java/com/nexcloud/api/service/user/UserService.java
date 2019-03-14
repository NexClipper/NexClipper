package com.nexcloud.api.service.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexcloud.rdb.domain.mysql.User;
import com.nexcloud.rdb.mapper.mysql.UserRepository;
import com.nexcloud.util.response.Mysql;
@Service
public class UserService{
	@Autowired private UserRepository userRepository;
	@Autowired private Mysql mysql;

	public String getUserList(){
		return mysql.resultToResponse(userRepository.getUserList());
	}
	public User getUser(String userId){
		return userRepository.getUser(userId);
	}
	public boolean addUser(User user) {
		return userRepository.addUser(user);
	}
}
