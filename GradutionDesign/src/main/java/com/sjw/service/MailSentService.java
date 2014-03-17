package com.sjw.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.sjw.pojo.Mail;

public interface MailSentService {
	public int getTotalMailSentCountService(HttpSession session) throws Exception;
	
	public List<Mail> initialMailSentService(HttpSession session) throws Exception;
}
