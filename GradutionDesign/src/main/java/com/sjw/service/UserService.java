package com.sjw.service;

import com.sjw.pojo.User;

public interface UserService {
	public String LoginOneUserService(String username);
	
	public Boolean registerOneUserService(User user);
}
