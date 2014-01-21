package com.sjw.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjw.dao.MailDeletedDao;
import com.sjw.pojo.Mail;
import com.sjw.service.MailDeletedService;

@Service
public class MailDeletedServiceImpl implements MailDeletedService {

	@Autowired
	MailDeletedDao mailDeletedDao;
	
	@Override
	public int getTotalMailDeletedCountService() throws Exception {
		return mailDeletedDao.getMailDeletedCountDao();
	}

	@Override
	public List<Mail> initialMailDeletedService() throws Exception {
		return mailDeletedDao.initialMailDeletedDao();
	}

}
