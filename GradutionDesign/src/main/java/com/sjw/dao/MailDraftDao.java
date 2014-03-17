package com.sjw.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.sjw.pojo.Mail;

public interface MailDraftDao {
	public int getMailDraftCountDao(HttpSession session) throws Exception;
	
	public List<Mail> initialMailDraftDao(HttpSession session) throws Exception;
}
