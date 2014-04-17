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
		
		total=mailDeletedService.getTotalMailDeletedCountService(request.getSession());
		
		if(total==-1){
			return "error.definition";
		}
		else{
			mail=mailDeletedService.initialMailDeletedService(request.getSession());
			if(mail==null){
				return "error.definition";
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
		String result=mailDeletedService.deleteMailPavementlyService(messagenum,request.getSession());
		if(result=="success")
			return null;
		else
			return "error.definition";
	}
	
	@RequestMapping(value="redoMailByBox",method=RequestMethod.POST)
	public String redoMailByBox(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String to=request.getParameter("to");
		String[] messagenum=request.getParameter("selected").split(",");
		Boolean isredo=mailDeletedService.redoMailByBoxService(messagenum, to, request.getSession());
		if(isredo)
			return null;
		else
			return "error.definition";
	}
}
