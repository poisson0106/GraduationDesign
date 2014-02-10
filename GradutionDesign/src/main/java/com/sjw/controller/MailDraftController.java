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
import com.sjw.service.MailDraftService;

@Controller
public class MailDraftController {
	@Autowired
	MailDraftService mailDraftService;
	
	@RequestMapping(value="initialDraftBox",method=RequestMethod.GET)
	public String InitialDraftBox(HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<Mail> mail=new ArrayList<Mail>();
		int total;
		
		total=mailDraftService.getTotalMailDraftCountService();
		if(total==-1){
			return "message/error";
		}
		else{
			mail=mailDraftService.initialMailDraftService();
			if(mail==null){
				return "message/error";
			}
			else{
				request.setAttribute("mail", mail);
				request.setAttribute("allpagenum", total/10+1);
				request.setAttribute("page", 1);
	         
	        return "maildraft.definition";
			}
		}
	}
}
