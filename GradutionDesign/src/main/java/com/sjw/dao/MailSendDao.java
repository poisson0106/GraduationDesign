package com.sjw.dao;

import com.sjw.pojo.Mail;

public interface MailSendDao {
	public Boolean SendOneEmailDao(Mail mail) throws Exception;

}
