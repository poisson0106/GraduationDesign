package com.sjw.dao;

import java.util.List;

import com.sjw.pojo.Mail;

public interface MailSentDao {
	public int getMailSentCountDao() throws Exception;
	
	public List<Mail> initialMailSentDao() throws Exception;
}
