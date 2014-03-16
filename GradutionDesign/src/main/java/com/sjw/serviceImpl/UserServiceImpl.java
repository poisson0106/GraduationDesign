package com.sjw.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjw.dao.UserDao;
import com.sjw.pojo.User;
import com.sjw.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public String LoginOneUserService(String username) {
		return userDao.LoginOneUserDao(username);
	}

	@Override
	public Boolean registerOneUserService(User user) {
		return userDao.registerOneUserDao(user);
	}

}
