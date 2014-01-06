package com.sjw.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sjw.utils.MailConnection;
import com.sun.mail.imap.IMAPFolder;

@Controller
public class MailReceiveController {
	@RequestMapping(value="initialMailReceive",method=RequestMethod.GET)
	public String InitialMailReceive(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 List<Map> requestedmail = new ArrayList();      
         int total = 0;
         
         IMAPFolder folder=null;
         if(MailConnection.getInboxFolder()==null){
        	 return "content/error";
         }
         else
        	 folder=MailConnection.getInboxFolder();
         // 获取总邮件数    
         total = folder.getMessageCount();
        // Message[] messages = folder.getMessages();    
        /* if((total-pagenum*10)<0){
        	 end=1;
         }
         else{
        	 end=total-(pagenum-1)*10-9;
         }*/
         for(int i=total;i>=total-10;i--){
        	 Message message = folder.getMessage(i);
        	 Map map = new HashMap();
        	 map.put("date", DateFormat.getDateInstance(DateFormat.MEDIUM).format(message.getSentDate()));
        	 map.put("subject", message.getSubject());
        	 map.put("content", message.getContent());
        	 map.put("messagenum", message.getMessageNumber());
        	 map.put("receivers", message.getAllRecipients());
        	 Flags flags = message.getFlags();    
             if (flags.contains(Flags.Flag.SEEN))
            	 map.put("flag", false);
             else {    
                 map.put("flag", true);    
             }
             String reply = message.getReplyTo()[0].toString();
             reply = reply.substring(reply.indexOf("<") + 1, reply.indexOf(">"));
             map.put("sender", reply);
             requestedmail.add(map);
         }
         request.setAttribute("mail", requestedmail);
         request.setAttribute("allnum", total/10+1);
         request.setAttribute("page", 1);
         request.setAttribute("chosed", 2);
         
         return "mailreceive.definition";
	}
	
	@RequestMapping(value="listOnePageEmail",method=RequestMethod.GET)
	public String listOnePageEmail(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 List<Map> requestedmail = new ArrayList();
		 
		 int pagenum=Integer.parseInt(request.getParameter("page"));
		 int total=0;
		 int end=0;
         
         IMAPFolder folder=null;
         if(MailConnection.getInboxFolder()==null){
        	 return "content/error";
         }
         else
        	 folder=MailConnection.getInboxFolder();
         total = folder.getMessageCount();
         if((total-pagenum*10)<0){
        	 end=1;
         }
         else{
        	 end=total-(pagenum-1)*10-9;
         }
         for(int i=total;i>=total-10;i--){
        	 Message message = folder.getMessage(i);
        	 Map map = new HashMap();
        	 map.put("date", DateFormat.getDateInstance(DateFormat.MEDIUM).format(message.getSentDate()));
        	 map.put("subject", message.getSubject());
        	 map.put("content", message.getContent());
        	 map.put("messagenum", message.getMessageNumber());
        	 map.put("receivers", message.getAllRecipients());
        	 Flags flags = message.getFlags();    
             if (flags.contains(Flags.Flag.SEEN))
            	 map.put("flag", false);
             else {    
                 map.put("flag", true);    
             }
             String reply = message.getReplyTo()[0].toString();
             reply = reply.substring(reply.indexOf("<") + 1, reply.indexOf(">"));
             map.put("sender", reply);
             requestedmail.add(map);
         }
         request.setAttribute("mail", requestedmail);
         request.setAttribute("allnum", total/10+1);
         request.setAttribute("page", pagenum);
        /* request.setAttribute("chosed", 2);*/
         
        
         return "mailreceive.definition";
	}
}
