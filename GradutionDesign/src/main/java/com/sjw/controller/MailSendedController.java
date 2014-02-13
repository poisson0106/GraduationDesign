package com.sjw.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sjw.pojo.Mail;
import com.sjw.service.MailSendedService;

@Controller
public class MailSendedController {
	@Autowired
	MailSendedService mailSendedService;
	
	@RequestMapping(value="initialMailSended",method=RequestMethod.GET)
	public String InitialMailSended(HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<Mail> mail=new ArrayList<Mail>();
		int total;
		
		total=mailSendedService.getTotalMailSendedCountService();
		
		if(total==-1){
			return "error.definition";
		}
		else{
			mail=mailSendedService.initialMailSendedService();
			if(mail==null){
				return "error.definition";
			}
			else{
				request.setAttribute("mail", mail);
				request.setAttribute("allpagenum", total/10+1);
				request.setAttribute("page", 1);
	         
	        return "mailsended.definition";
			}
		}
	}
}
