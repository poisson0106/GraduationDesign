package com.sjw.serviceImpl;

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
	public Boolean SendOneEmailService(Mail mail) throws Exception {
		return mailSendDao.SendOneEmailDao(mail);
	}
	
}
