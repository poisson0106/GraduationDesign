package com.sjw.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.sjw.pojo.Mail;

public interface MailReceiveService {
	public List<Mail> initialMailReceiveService() throws Exception;
	
	public int getTotalMailCountService() throws Exception;
	
	public List<Mail> listOnePageEmailService(int end,int pagenum) throws Exception;
	
	public Mail showMailContentService(int messagenum) throws Exception;
	
	public String deleteSelectedMailService(String[] messagenum) throws Exception;
	
	public String downloadSelectedAttachmentService(String fileName,int messagenum,HttpServletResponse response) throws Exception;
	
	
}
