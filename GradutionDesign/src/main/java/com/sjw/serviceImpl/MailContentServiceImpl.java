package com.sjw.serviceImpl;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjw.dao.MailContentDao;
import com.sjw.pojo.Mail;
import com.sjw.service.MailContentService;

@Service
public class MailContentServiceImpl implements MailContentService {
	@Autowired
	MailContentDao mailContentDao;
	
	@Override
	public Mail showMailContentService(int messagenum,String frompage,HttpSession session) throws Exception {
		return mailContentDao.showMailContentDao(messagenum,frompage,session);
	}
	
	@Override
	public String downloadSelectedAttachmentService(String fileName,int messagenum,String frompage,HttpServletResponse response,HttpSession session) throws Exception {
		return mailContentDao.downloadSelectedAttachmentDao(fileName,messagenum,frompage,response,session);
	}
}
