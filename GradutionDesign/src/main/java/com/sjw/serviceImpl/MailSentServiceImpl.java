package com.sjw.serviceImpl;

import java.util.List;

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
	public int getTotalMailSentCountService() throws Exception {
		return mailSentDao.getMailSentCountDao();
	}

	@Override
	public List<Mail> initialMailSentService() throws Exception {
		return mailSentDao.initialMailSentDao();
	}

}
