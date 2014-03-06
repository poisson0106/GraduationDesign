package com.sjw.service;

import javax.servlet.http.HttpServletRequest;

import com.sjw.pojo.Mail;

public interface MailSendService {
	public Boolean SendOneEmailService(Mail mail,HttpServletRequest request) throws Exception;
	
	public Boolean uploadAttachmentService(HttpServletRequest request) throws Exception;
}
