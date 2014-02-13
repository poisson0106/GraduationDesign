package com.sjw.dao;

import java.util.List;

import com.sjw.pojo.Mail;

public interface MailSendedDao {
	public int getMailSendedCountDao() throws Exception;
	
	public List<Mail> initialMailSendedDao() throws Exception;
}
