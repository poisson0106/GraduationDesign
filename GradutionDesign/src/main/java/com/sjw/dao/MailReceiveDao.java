package com.sjw.dao;

import java.util.List;

import com.sjw.pojo.Mail;

public interface MailReceiveDao {
	public List<Mail> initialMailReceiveDao() throws Exception;
	
	public int getTotalMailCountDao() throws Exception;
	
	public List<Mail> listOnePageEmail(int end,int pagenum) throws Exception;
	
	public Mail showMailContentDao(int messagenum) throws Exception;
}
