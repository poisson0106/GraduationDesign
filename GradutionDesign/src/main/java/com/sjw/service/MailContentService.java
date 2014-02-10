package com.sjw.service;

import javax.servlet.http.HttpServletResponse;

import com.sjw.pojo.Mail;

public interface MailContentService {
	public Mail showMailContentService(int messagenum,String frompage) throws Exception;
	
	public String downloadSelectedAttachmentService(String fileName,int messagenum,HttpServletResponse response) throws Exception;
}
