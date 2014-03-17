package com.sjw.dao;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sjw.pojo.Mail;

public interface MailContentDao {
	public Mail showMailContentDao(int messagenum,String frompage,HttpSession session) throws Exception;
	
	public String downloadSelectedAttachmentDao(String fileName,int messagenum,String frompage,HttpServletResponse response,HttpSession session) throws Exception;
}
