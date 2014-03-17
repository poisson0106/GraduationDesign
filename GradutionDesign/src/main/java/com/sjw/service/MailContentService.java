package com.sjw.service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sjw.pojo.Mail;

public interface MailContentService {
	public Mail showMailContentService(int messagenum,String frompage,HttpSession session) throws Exception;
	
	public String downloadSelectedAttachmentService(String fileName,int messagenum,String frompage,HttpServletResponse response,HttpSession session) throws Exception;
}
