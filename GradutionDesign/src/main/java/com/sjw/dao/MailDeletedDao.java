package com.sjw.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.sjw.pojo.Mail;

public interface MailDeletedDao {
	public int getMailDeletedCountDao(HttpSession session) throws Exception;
	
	public List<Mail> initialMailDeletedDao(HttpSession session) throws Exception;
	
	public String deleteMailPavemently(String[] messagenum,HttpSession session) throws Exception;
}
