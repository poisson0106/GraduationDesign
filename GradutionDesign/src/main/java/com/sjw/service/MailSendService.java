package com.sjw.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sjw.pojo.Mail;

public interface MailSendService {
	public Boolean SendOneEmailService(Mail mail,HttpServletRequest request,HttpSession session) throws Exception;
	
	public Boolean uploadAttachmentService(HttpServletRequest request,HttpSession session) throws Exception;
	
	public Boolean saveOneEmailService(Mail mail,HttpSession session) throws Exception;
	
	public List<Map> findReceiversService(Map keywords) throws Exception;
	
	public Boolean saveDraftAutoService(Mail mail,HttpSession session) throws Exception;
}
