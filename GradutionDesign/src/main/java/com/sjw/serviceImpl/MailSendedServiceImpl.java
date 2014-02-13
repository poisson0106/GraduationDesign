package com.sjw.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjw.dao.MailSendedDao;
import com.sjw.pojo.Mail;
import com.sjw.service.MailSendedService;

@Service
public class MailSendedServiceImpl implements MailSendedService {
	
	@Autowired
	MailSendedDao mailSendedDao;
	
	@Override
	public int getTotalMailSendedCountService() throws Exception {
		return mailSendedDao.getMailSendedCountDao();
	}

	@Override
	public List<Mail> initialMailSendedService() throws Exception {
		return mailSendedDao.initialMailSendedDao();
	}

}
