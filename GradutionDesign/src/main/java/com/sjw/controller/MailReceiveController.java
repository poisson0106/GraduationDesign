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
	
	@RequestMapping(value="listOnePageEmail",method=RequestMethod.GET)
	public String listOnePageEmail(HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<Mail> mail=new ArrayList<Mail>();
		int pagenum=Integer.parseInt(request.getParameter("page"));
		int total=0;
		int end=0;
		
		total=mailReceiveService.getTotalMailCountService();
		if(total==-1){
			return "message/error";
		}
		else {
			if ((total-pagenum*10)<0) {
				end=1;
			} else {
				end=total-(pagenum-1)*10-9;
			}
			mail = mailReceiveService.listOnePageEmailService(end, pagenum);
			if(mail==null){
				return "message/error";
			}
			else{
				request.setAttribute("mail", mail);
				request.setAttribute("allpagenum", total / 10 + 1);
				request.setAttribute("page", pagenum);
				return "mailreceive.definition";
			}
		}
	}
	
	@RequestMapping(value="showMailContent",method=RequestMethod.GET)
	public String ShowMailContent(HttpServletRequest request,HttpServletResponse response) throws Exception{
		int messagenum=Integer.parseInt(request.getParameter("thismessagenum"));
		Mail mail=new Mail();
		
		mail=mailReceiveService.showMailContentService(messagenum);
		request.setAttribute("mail", mail);
        return "mailcontent.definition";
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
	
	@RequestMapping(value="downloadSelectedAttachment",method=RequestMethod.GET)
	public String DownloadSelectedAttachment(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String fileName=request.getParameter("selected");
		int messagenum=Integer.parseInt(request.getParameter("messagenum"));
		fileName = new String(fileName.getBytes("ISO-8859-1"),"UTF-8");
		String result=mailReceiveService.downloadSelectedAttachmentService(fileName,messagenum,response);
		if(result=="success")
			return null;
		else
			return "message/error";
	}
}
