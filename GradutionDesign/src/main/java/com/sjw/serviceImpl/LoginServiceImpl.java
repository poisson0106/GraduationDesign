package com.sjw.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjw.dao.LoginDao;
import com.sjw.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;
	
	@Override
	public String LoginOneUserService(String username) {
		return loginDao.LoginOneUserDao(username);
	}

}
