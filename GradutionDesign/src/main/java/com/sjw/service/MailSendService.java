package com.sjw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sjw.pojo.Mail;

public interface MailSendService {
	public Boolean SendOneEmailService(Mail mail,HttpServletRequest request,HttpSession session) throws Exception;
	
	public Boolean uploadAttachmentService(HttpServletRequest request,HttpSession session) throws Exception;
	
	public Boolean saveOneEmailService(Mail mail,HttpSession session) throws Exception;
	
	public String findReceiversService(String receiver) throws Exception;
}
