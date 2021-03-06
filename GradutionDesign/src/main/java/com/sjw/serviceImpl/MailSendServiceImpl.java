package com.sjw.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjw.dao.MailSendDao;
import com.sjw.pojo.Mail;
import com.sjw.service.MailSendService;

@Service
public class MailSendServiceImpl implements MailSendService {

	@Autowired
	MailSendDao mailSendDao;
	
	@Override
	public Boolean SendOneEmailService(Mail mail,HttpServletRequest request,HttpSession session) throws Exception {
		return mailSendDao.SendOneEmailDao(mail,request,session);
	}

	@Override
	public Boolean uploadAttachmentService(HttpServletRequest request,HttpSession session)
			throws Exception {
		return mailSendDao.uploadAttachmentDao(request,session);
	}

	@Override
	public Boolean saveOneEmailService(Mail mail,HttpSession session) throws Exception {
		return mailSendDao.saveOneEmailDao(mail,session);
	}

	@Override
	public List<Map> findReceiversService(Map keywords) throws Exception {
		return mailSendDao.findReceiversDao(keywords);
	}

	@Override
	public int saveDraftAutoService(Mail mail,HttpSession session) throws Exception {
		return mailSendDao.saveDraftAutoDao(mail,session);
	}
	
}
