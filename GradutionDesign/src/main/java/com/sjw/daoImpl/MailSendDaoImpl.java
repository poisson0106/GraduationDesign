package com.sjw.daoImpl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;

import com.sjw.dao.MailSendDao;
import com.sjw.pojo.Mail;
import com.sjw.utils.MailContentAnalysis;

public class MailSendDaoImpl implements MailSendDao {

	@Override
	public Boolean SendOneEmailDao(Mail mail,HttpServletRequest request) throws Exception {
		/*HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.163.com");
		email.setAuthenticator(new DefaultAuthenticator("poisson0106@163.com", "19910106sjw"));
		email.setSSL(true);
		email.setFrom(mail.getSender());
		email.setSubject(mail.getSubject());
		email.setCharset("UTF-8");
		email.setHtmlMsg(mail.getContent());
		email.addTo(mail.getReceivers());
		email.send();*/
		System.out.println(mail.getSender());
		String foldername=mail.getSender().substring(0, mail.getSender().indexOf("@"));
		MailContentAnalysis.uploadAttachment(request,foldername);
		return true;
	}

}
