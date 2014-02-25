package com.sjw.service;

import java.util.List;

import com.sjw.pojo.Mail;

public interface MailSentService {
	public int getTotalMailSentCountService() throws Exception;
	
	public List<Mail> initialMailSentService() throws Exception;
}
