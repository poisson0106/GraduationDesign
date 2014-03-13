package com.sjw.dao;

import javax.servlet.http.HttpServletRequest;

import com.sjw.pojo.Mail;

public interface MailSendDao {
	public Boolean SendOneEmailDao(Mail mail,HttpServletRequest request) throws Exception;
	
	public Boolean uploadAttachmentDao(HttpServletRequest request) throws Exception;
	
	public Boolean saveOneEmailDao(Mail mail) throws Exception;

}
