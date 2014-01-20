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
        MailConnection.getConnection();
        MailConnection.setIndoxFolder();
        if(MailConnection.getInboxFolder()==null){
       	 	return "content/error";
        }
        else
        	folder=MailConnection.getInboxFolder();
        // 获取未读邮件数
        unreadnum=folder.getUnreadMessageCount();  
        session.setAttribute("nummail", unreadnum);
		request.setAttribute("chosed", 1);
		MailConnection.closeInboxFolder();
		MailConnection.closeConnection();
		return "base.definition";
	}
}
