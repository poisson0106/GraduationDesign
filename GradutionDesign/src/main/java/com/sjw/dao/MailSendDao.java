package com.sjw.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sjw.pojo.Mail;

public interface MailSendDao {
	public Boolean SendOneEmailDao(Mail mail,HttpServletRequest request,HttpSession session) throws Exception;
	
	public Boolean uploadAttachmentDao(HttpServletRequest request,HttpSession session) throws Exception;
	
	public Boolean saveOneEmailDao(Mail mail,HttpSession session) throws Exception;
	
	public List<Map> findReceiversDao(Map keywords) throws Exception;
	
	public Boolean saveDraftAutoDao(Mail mail,HttpSession session) throws Exception; 

}
