package com.sjw.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.sjw.pojo.Mail;

public interface MailDeletedDao {
	public int getMailDeletedCountDao(HttpSession session) throws Exception;
	
	public List<Mail> initialMailDeletedDao(HttpSession session) throws Exception;
	
	public String deleteMailPavementlyDao(String[] messagenum,HttpSession session) throws Exception;
	
	public Boolean redoMailByBoxDao(String[] messagenum,String to,HttpSession session) throws Exception;
}
