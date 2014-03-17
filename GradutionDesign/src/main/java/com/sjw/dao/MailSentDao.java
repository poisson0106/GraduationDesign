package com.sjw.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.sjw.pojo.Mail;

public interface MailSentDao {
	public int getMailSentCountDao(HttpSession session) throws Exception;
	
	public List<Mail> initialMailSentDao(HttpSession session) throws Exception;
}
