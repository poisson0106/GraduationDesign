package com.sjw.serviceImpl;

import java.util.List;

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
	public int getTotalMailDraftCountService() throws Exception {
		return mailDraftDao.getMailDraftCountDao();
	}

	@Override
	public List<Mail> initialMailDraftService() throws Exception {
		return mailDraftDao.initialMailDraftDao();
	}
}
