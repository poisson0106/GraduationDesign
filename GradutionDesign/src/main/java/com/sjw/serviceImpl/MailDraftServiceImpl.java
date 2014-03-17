package com.sjw.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjw.dao.MailDraftDao;
import com.sjw.pojo.Mail;
import com.sjw.service.MailDraftService;

@Service
public class MailDraftServiceImpl implements MailDraftService {
	@Autowired
	MailDraftDao mailDraftDao;

	@Override
	public int getTotalMailDraftCountService(HttpSession session) throws Exception {
		return mailDraftDao.getMailDraftCountDao(session);
	}

	@Override
	public List<Mail> initialMailDraftService(HttpSession session) throws Exception {
		return mailDraftDao.initialMailDraftDao(session);
	}
}
