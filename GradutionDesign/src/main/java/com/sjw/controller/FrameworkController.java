package com.sjw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sjw.utils.MailConnection;
import com.sun.mail.imap.IMAPFolder;

@Controller
public class FrameworkController {
	@RequestMapping(value="mainframework",method=RequestMethod.GET)
	public String MainFramework(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception{
		int unreadnum = 0;
        IMAPFolder folder=null;
        MailConnection.getConnection(session.getAttribute("username").toString(),session.getAttribute("password").toString());
        MailConnection.setIndoxFolder();
        if(MailConnection.getInboxFolder()==null){
       	 	return "content/error";
        }
        else
        	folder=MailConnection.getInboxFolder();
        // 未读邮件
        unreadnum=folder.getUnreadMessageCount();  
        session.setAttribute("nummail", unreadnum);
		request.setAttribute("chosed", 1);
		MailConnection.closeInboxFolder();
		MailConnection.closeConnection();
		return "base.definition";
	}
	
	@RequestMapping(value="onerror",method=RequestMethod.GET)
	public String OnError(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return "error.definition";
	}
}
