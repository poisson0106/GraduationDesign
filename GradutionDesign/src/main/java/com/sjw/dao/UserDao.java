package com.sjw.dao;

import javax.servlet.http.HttpSession;

import com.sjw.pojo.User;

public interface UserDao {
	public String LoginOneUserDao(String username);
	
	public Boolean registerOneUserDao(User user) throws Exception;
}
