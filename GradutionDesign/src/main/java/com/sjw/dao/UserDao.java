package com.sjw.dao;

import com.sjw.pojo.User;

public interface UserDao {
	public String LoginOneUserDao(String username);
	
	public Boolean registerOneUserDao(User user) throws Exception;
	
	public User checkUsernameRepeatDao(String username) throws Exception;
	
	public User getPwdQuestionDao(String username) throws Exception;
	
	public Boolean findOnePasswordDao(User user) throws Exception;
	
	public String getNicknameDao(String username) throws Exception;
	
	public User initialUserInfoChangeDao(String username) throws Exception;
	
	public Boolean changeUserInfoDao(User user) throws Exception;
}
