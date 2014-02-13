package com.sjw.service;

import java.util.List;

import com.sjw.pojo.Mail;

public interface MailSendedService {
	public int getTotalMailSendedCountService() throws Exception;
	
	public List<Mail> initialMailSendedService() throws Exception;
}
