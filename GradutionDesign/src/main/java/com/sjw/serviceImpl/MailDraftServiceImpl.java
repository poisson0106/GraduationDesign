package com.sjw.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjw.dao.MailDraftDao;
import com.sjw.service.MailDraftService;

@Service
public class MailDraftServiceImpl implements MailDraftService {
	@Autowired
	MailDraftDao mailDraftDao;
}
