package com.sjw.dao;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.sjw.pojo.Mail;

public interface MailReceiveDao {
	public List<Mail> initialMailReceiveDao() throws Exception;
	
	public int getTotalMailCountDao() throws Exception;
	
	public List<Mail> listOnePageEmail(int end,int pagenum) throws Exception;
	
	public Mail showMailContentDao(int messagenum) throws Exception;
	
	public String deleteSelectedEmailDao(String[] messagenum) throws Exception;
	
	public String downloadSelectedAttachmentDao(String fileName,int messagenum,HttpServletResponse response) throws Exception;
}
