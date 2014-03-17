package com.sjw.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjw.dao.MailSentDao;
import com.sjw.pojo.Mail;
import com.sjw.service.MailSentService;

@Service
public class MailSentServiceImpl implements MailSentService {
	
	@Autowired
	MailSentDao mailSentDao;
	
	@Override
	public int getTotalMailSentCountService(HttpSession session) throws Exception {
		return mailSentDao.getMailSentCountDao(session);
	}

	@Override
	public List<Mail> initialMailSentService(HttpSession session) throws Exception {
		return mailSentDao.initialMailSentDao(session);
	}

}
