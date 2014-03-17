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
import com.sjw.service.ToolBarService;

@Controller
public class ToolBarController {
	@Autowired
	ToolBarService toolBarService;
	
	@RequestMapping(value="setMailSeen",method=RequestMethod.POST)
	public String SetMailSeen(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String[] messagenum=request.getParameter("selected").split(",");
		String result=toolBarService.setMailSeenService(messagenum,request.getSession());
		if(result=="success")
			return null;
		else
			return "message/error";
	}
	
	@RequestMapping(value="setMailUnSeen",method=RequestMethod.POST)
	public String SetMailUnSeen(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String[] messagenum=request.getParameter("selected").split(",");
		String result=toolBarService.setMailUnSeenService(messagenum,request.getSession());
		if(result=="success")
			return null;
		else
			return "message/error";
	}
	
	@RequestMapping(value="listOnePageEmail",method=RequestMethod.GET)
	public String listOnePageEmail(HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<Mail> mail=new ArrayList<Mail>();
		int pagenum=Integer.parseInt(request.getParameter("page"));
		int total=0;
		int end=0;
		String frompage=request.getParameter("from");
		
		total=toolBarService.getOnePageEmailCountService(frompage,request.getSession());
		if(total==-1){
			return "message/error";
		}
		else {
			if ((total-pagenum*10)<0) {
				end=1;
			} else {
				end=total-(pagenum-1)*10-9;
			}
			mail = toolBarService.listOnePageEmailService(end, pagenum,frompage,request.getSession());
			if(mail==null){
				return "message/error";
			}
			else{
				request.setAttribute("mail", mail);
				request.setAttribute("allpagenum", total / 10 + 1);
				request.setAttribute("page", pagenum);
			    if("inboxmenu".equals(frompage))
			    	return "mailreceive.definition";
			    else if("delboxmenu".equals(frompage))
			    	return "maildeleted.definition";
			    else if("draftboxmenu".equals(frompage))
			    	return "maildraft.definition";
			    else if("sentboxmenu".equals(frompage))
			    	return "mailsent.definition";
			    else
			    	return "error.definition";
			}
		}
	}
}
