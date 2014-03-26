package com.sjw.service;

import javax.servlet.http.HttpSession;

import com.sjw.pojo.User;

public interface UserService {
	public String LoginOneUserService(String username);
	
	public Boolean registerOneUserService(User user) throws Exception;
	
	public User checkUsernameRepeatService(String username) throws Exception;
}
