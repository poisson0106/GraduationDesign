package com.sjw.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjw.dao.MailReceiveDao;
import com.sjw.pojo.Mail;
import com.sjw.service.MailReceiveService;

@Service
public class MailReceiveServiceImpl implements MailReceiveService {
	@Autowired
	private MailReceiveDao mailReceiveDao;
	
	@Override
	public List<Mail> initialMailReceiveService(HttpSession session) throws Exception {
		return mailReceiveDao.initialMailReceiveDao(session);
	}

	@Override
	public int getTotalMailCountService(HttpSession session) throws Exception {
		return mailReceiveDao.getTotalMailCountDao(session);
	}
}
