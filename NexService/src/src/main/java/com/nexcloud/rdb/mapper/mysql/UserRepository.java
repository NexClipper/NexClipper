package com.nexcloud.rdb.mapper.mysql;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.nexcloud.rdb.domain.mysql.User;

@Repository("UserMapper")
public interface UserRepository {
	public List<User> getUserList();
	public User getUser(String userId);
	public boolean addUser(User user);
}
