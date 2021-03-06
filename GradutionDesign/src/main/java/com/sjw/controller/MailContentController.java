package com.sjw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sjw.pojo.Mail;
import com.sjw.service.MailContentService;

@Controller
public class MailContentController {
	@Autowired
	MailContentService mailContentService;
	
	@RequestMapping(value="showMailContent",method=RequestMethod.GET)
	public String ShowMailContent(HttpServletRequest request,HttpServletResponse response) throws Exception{
		int messagenum=Integer.parseInt(request.getParameter("thismessagenum"));
		String frompage=request.getParameter("from");
		Mail mail=new Mail();
		
		mail=mailContentService.showMailContentService(messagenum,frompage,request.getSession());
		//将draftbox的内容转到发送页面
		if("draftboxmenu".equals(frompage)){
			String subject=mail.getSubject();
			String content=mail.getContent();
			String receiver=mail.getReceivers();
			String cc=null;
			String[] cclist=mail.getCc();
			for(String str : cclist){
				if(str.contains("["))
					str=str.substring(str.indexOf("[")+1,str.indexOf("]"));
				if(cc==null)
					cc=str;
				else
					cc=cc+","+str;
			}
			receiver=receiver.substring(receiver.indexOf("[")+1,receiver.indexOf("]"));
			request.setAttribute("content", content);
			request.setAttribute("subject", subject);
			request.setAttribute("receiver", receiver);
			request.setAttribute("cc", cc);
			request.setAttribute("mail", mail);
			request.setAttribute("messagenum", messagenum);
			return "mailsend.definition";
		}
		else{
			request.setAttribute("mail", mail);
			return "mailcontent.definition";
		}
       
	}
	
	@RequestMapping(value="downloadSelectedAttachment",method=RequestMethod.GET)
	public String DownloadSelectedAttachment(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String fileName=request.getParameter("selected");
		int messagenum=Integer.parseInt(request.getParameter("messagenum"));
		String frompage=request.getParameter("from");
		fileName = new String(fileName.getBytes("ISO-8859-1"),"UTF-8");
		String result=mailContentService.downloadSelectedAttachmentService(fileName,messagenum,frompage,response,request.getSession());
		if(result=="success")
			return null;
		else
			return "error.definition";
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
	
	@RequestMapping(value="forwardWithoutAttachment",method=RequestMethod.POST)
	public String ForwardWithoutAttachment(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String content=request.getParameter("content");
		String subject=request.getParameter("subject");
		subject=new String(subject.getBytes("ISO-8859-1"),"UTF8");
		content=new String(content.getBytes("ISO-8859-1"),"UTF8");
		request.setAttribute("content", content);
		request.setAttribute("subject", subject);
		return "mailsend.definition";
	}
	
	@RequestMapping(value="forwardWithAttachment",method=RequestMethod.POST)
	public String ForwardWithAttachment(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String content=request.getParameter("acontent");
		String subject=request.getParameter("asubject");
		String filenamelist=request.getParameter("fnamelist");
		Mail mail=new Mail();
		subject=new String(subject.getBytes("ISO-8859-1"),"UTF8");
		content=new String(content.getBytes("ISO-8859-1"),"UTF8");
		filenamelist=new String(filenamelist.getBytes("ISO-8859-1"),"UTF-8");
		mail.setAttachnames(filenamelist.split(","));
		mail.setWithattach(true);
		request.setAttribute("content", content);
		request.setAttribute("subject", subject);
		request.setAttribute("mail", mail);
		return "mailsend.definition";
	}
}
