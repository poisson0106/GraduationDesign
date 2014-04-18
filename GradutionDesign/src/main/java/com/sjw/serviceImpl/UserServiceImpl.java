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
	public Boolean registerOneUserService(User user) throws Exception{
		return userDao.registerOneUserDao(user);
	}

	@Override
	public User checkUsernameRepeatService(String username) throws Exception {
		return userDao.checkUsernameRepeatDao(username);
	}

	@Override
	public User getPwdQuestionDaoService(String username) throws Exception {
		return userDao.getPwdQuestionDao(username);
	}

	@Override
	public Boolean findOnePasswordService(User user) throws Exception {
		return userDao.findOnePasswordDao(user);
	}

	@Override
	public String getNicknameService(String username) throws Exception {
		return userDao.getNicknameDao(username);
	}

	@Override
	public User initialUserInfoChangeService(String username) throws Exception {
		return userDao.initialUserInfoChangeDao(username);
	}

	@Override
	public Boolean changeUserInfoService(User user) throws Exception {
		return userDao.changeUserInfoDao(user);
	}

}
