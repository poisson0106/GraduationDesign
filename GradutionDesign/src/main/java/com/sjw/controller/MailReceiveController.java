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
import com.sjw.service.MailReceiveService;
import com.sjw.utils.MailConnection;


@Controller
public class MailReceiveController {
	@Autowired
	private MailReceiveService mailReceiveService;
	
	@RequestMapping(value="initialMailReceive",method=RequestMethod.GET)
	public String InitialMailReceive(HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<Mail> mail=new ArrayList<Mail>();
		int total;
		
		total=mailReceiveService.getTotalMailCountService(request.getSession());
		if(total==-1){
			return "error.definition";
		}
		else{
			mail=mailReceiveService.initialMailReceiveService(request.getSession());
			if(mail==null){
				return "error.definition";
			}
			else{
				request.setAttribute("mail", mail);
				request.setAttribute("allpagenum", total/10+1);
				request.setAttribute("page", 1);
				request.setAttribute("chosed", 2);
	         
	        return "mailreceive.definition";
			}
		}
		
	}
	
	
	@RequestMapping(value="deleteSelectedEmail",method=RequestMethod.POST)
	public String DeleteSelectedEmail(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String[] messagenum=request.getParameter("selected").split(",");
		String result=mailReceiveService.deleteSelectedMailService(messagenum,request.getSession());
		if(result=="success")
			return null;
		else
			return "error.definition";
	}
}
