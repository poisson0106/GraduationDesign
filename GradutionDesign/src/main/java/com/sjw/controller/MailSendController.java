package com.sjw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sjw.pojo.Mail;
import com.sjw.service.MailSendService;

@Controller
public class MailSendController {
	
	@Autowired
	MailSendService mailSendService;
	
	@RequestMapping(value="initialMailSend",method=RequestMethod.GET)
	public String InitialMailSend(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return "mailsend.definition";
	}
	
	@RequestMapping(value="sendOneEmail",method=RequestMethod.POST)
	public String SendOneEmail(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String receiver=request.getParameter("receiver");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String sender=request.getParameter("sender");
		Boolean issend=false;
		Mail mail=new Mail();
		mail.setReceivers(receiver);
		mail.setSender(sender);
		mail.setSubject(subject);
		mail.setContent(content);
		issend=mailSendService.SendOneEmailService(mail);
		if(issend)
			return "sendsuccess.definition";
		else
			return "senderror.definition";
	}
	
	@RequestMapping(value="replyOneEmail",method=RequestMethod.GET)
	public String ReplyOneEmail(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String receiver=request.getParameter("receiver");
		String subject=request.getParameter("subject");
		subject=new String(subject.getBytes("ISO-8859-1"),"UTF8");
		request.setAttribute("receiver", receiver.substring(receiver.indexOf("[")+1, receiver.indexOf("]")));
		request.setAttribute("subject", subject);
		return "mailsend.definition";
	}
}
