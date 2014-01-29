package com.sjw.dao;

import javax.servlet.http.HttpServletRequest;

import com.sjw.pojo.Mail;

public interface MailSendDao {
	public Boolean SendOneEmailDao(Mail mail) throws Exception;
	
	public Boolean uploadAttachmentDao(HttpServletRequest request) throws Exception;

}
