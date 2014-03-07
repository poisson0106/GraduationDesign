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
		
		total=mailReceiveService.getTotalMailCountService();
		if(total==-1){
			return "message/error";
		}
		else{
			mail=mailReceiveService.initialMailReceiveService();
			if(mail==null){
				return "message/error";
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
		String result=mailReceiveService.deleteSelectedMailService(messagenum);
		if(result=="success")
			return null;
		else
			return "message/error";
	}
}
