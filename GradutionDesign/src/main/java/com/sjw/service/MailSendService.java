package com.sjw.service;

import com.sjw.pojo.Mail;

public interface MailSendService {
	public Boolean SendOneEmailService(Mail mail) throws Exception;
}
