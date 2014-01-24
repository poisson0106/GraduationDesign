package com.sjw.dao;

import javax.servlet.http.HttpServletResponse;

import com.sjw.pojo.Mail;

public interface MailContentDao {
	public Mail showMailContentDao(int messagenum) throws Exception;
	
	public String downloadSelectedAttachmentDao(String fileName,int messagenum,HttpServletResponse response) throws Exception;
}
