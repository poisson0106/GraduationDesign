package com.sjw.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpSession;

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
	public int getTotalMailDeletedCountService(HttpSession session) throws Exception {
		return mailDeletedDao.getMailDeletedCountDao(session);
	}

	@Override
	public List<Mail> initialMailDeletedService(HttpSession session) throws Exception {
		return mailDeletedDao.initialMailDeletedDao(session);
	}

	@Override
	public String deleteMailPavemently(String[] messagenum,HttpSession session) throws Exception {
		return mailDeletedDao.deleteMailPavemently(messagenum,session);
	}

}
