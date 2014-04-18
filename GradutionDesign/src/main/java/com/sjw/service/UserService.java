package com.sjw.service;

import com.sjw.pojo.User;

public interface UserService {
	public String LoginOneUserService(String username);
	
	public Boolean registerOneUserService(User user) throws Exception;
	
	public User checkUsernameRepeatService(String username) throws Exception;
	
	public User getPwdQuestionDaoService(String username) throws Exception;
	
	public Boolean findOnePasswordService(User user) throws Exception;
	
	public String getNicknameService(String username) throws Exception;
	
	public User initialUserInfoChangeService(String username) throws Exception;
	
	public Boolean changeUserInfoService(User user) throws Exception;
}
