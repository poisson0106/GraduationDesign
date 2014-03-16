package com.sjw.dao;

import com.sjw.pojo.User;

public interface UserDao {
	public String LoginOneUserDao(String username);
	
	public Boolean registerOneUserDao(User user);
}
