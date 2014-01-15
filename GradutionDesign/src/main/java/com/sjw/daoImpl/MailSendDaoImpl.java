package com.sjw.daoImpl;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.sjw.dao.MailSendDao;
import com.sjw.pojo.Mail;

public class MailSendDaoImpl implements MailSendDao {

	@Override
	public Boolean SendOneEmailDao(Mail mail) throws Exception {
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.163.com");
		email.setAuthenticator(new DefaultAuthenticator("poisson0106@163.com", "19910106sjw"));
		email.setSSL(true);
		email.setFrom(mail.getSender());
		email.setSubject(mail.getSubject());
		email.setHtmlMsg(mail.getContent());
		email.addTo(mail.getReceivers());
		email.send();
		return null;
	}

}
