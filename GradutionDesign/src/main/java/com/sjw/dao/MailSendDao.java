package com.sjw.dao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sjw.pojo.Mail;

public interface MailSendDao {
	public Boolean SendOneEmailDao(Mail mail,HttpServletRequest request,HttpSession session) throws Exception;
	
	public Boolean uploadAttachmentDao(HttpServletRequest request,HttpSession session) throws Exception;
	
	public Boolean saveOneEmailDao(Mail mail,HttpSession session) throws Exception;

}
