package com.sjw.dao;

import java.util.List;

import com.sjw.pojo.Mail;

public interface MailDeletedDao {
	public int getMailDeletedCountDao() throws Exception;
	
	public List<Mail> initialMailDeletedDao() throws Exception;
	
	public String deleteMailPavemently(String[] messagenum) throws Exception;
}
