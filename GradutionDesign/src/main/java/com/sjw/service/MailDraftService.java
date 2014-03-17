package com.sjw.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.sjw.pojo.Mail;

public interface MailDraftService {
	public int getTotalMailDraftCountService(HttpSession session) throws Exception;
	
	public List<Mail> initialMailDraftService(HttpSession session) throws Exception;

}
