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
import com.sjw.service.MailDeletedService;

@Controller
public class MailDeletedController {
	@Autowired
	MailDeletedService mailDeletedService;
	
	@RequestMapping(value="initialMailDeleted",method=RequestMethod.GET)
	public String InitialMailDeleted(HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<Mail> mail=new ArrayList<Mail>();
		int total;
		
		total=mailDeletedService.getTotalMailDeletedCountService();
		
		if(total==-1){
			return "message/error";
		}
		else{
			mail=mailDeletedService.initialMailDeletedService();
			if(mail==null){
				return "message/error";
			}
			else{
				request.setAttribute("mail", mail);
				request.setAttribute("allpagenum", total/10+1);
				request.setAttribute("page", 1);
	         
	        return "maildeleted.definition";
			}
		}
	}
	
	@RequestMapping(value="deleteMailPavemently",method=RequestMethod.POST)
	public String DeleteMailPavemently(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String[] messagenum=request.getParameter("selected").split(",");
		String result=mailDeletedService.deleteMailPavemently(messagenum);
		if(result=="success")
			return null;
		else
			return "message/error";
	}
}
