package com.sjw.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sjw.utils.MailConnection;
import com.sjw.utils.MailContentAnalysis;
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
         for(int i=total;i>=total-9;i--){
        	 Message message = folder.getMessage(i);
        	 Map map = new HashMap();
        	 if(message.getSentDate()!=null)
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
             InternetAddress address[] = (InternetAddress[])message.getFrom();
             String sender=address[0].getPersonal();
             if(sender==null)
            	 sender="匿名";
             if(sender.contains("\""))
            	 sender=sender.substring(sender.indexOf("\"")+1, sender.length()-1);
            /*  if(from.contains("<")&&from.contains(">"))
            	 from = from.substring(from.indexOf("<") + 1, from.indexOf(">"));*/
             map.put("sender", sender);
             requestedmail.add(map);
         }
         request.setAttribute("mail", requestedmail);
         request.setAttribute("allpagenum", total/10+1);
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
         for(int i=total-(pagenum-1)*10;i>=end;i--){
        	 Message message = folder.getMessage(i);
        	 Map map = new HashMap();
        	 if(message.getSentDate()!=null)
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
             InternetAddress address[] = (InternetAddress[])message.getFrom();
             String sender=address[0].getPersonal();
             if(sender==null)
            	 sender="匿名";
             if(sender.contains("\""))
            	 sender=sender.substring(sender.indexOf("\"")+1, sender.length()-1);
            /*  if(from.contains("<")&&from.contains(">"))
            	 from = from.substring(from.indexOf("<") + 1, from.indexOf(">"));*/
             map.put("sender", sender);
             requestedmail.add(map);
         }
         request.setAttribute("mail", requestedmail);
         request.setAttribute("allpagenum", total/10+1);
         request.setAttribute("page", pagenum);
         request.setAttribute("chosed", 2);
         
        
         return "mailreceive.definition";
	}
	
	@RequestMapping(value="showMailContent",method=RequestMethod.GET)
	public String ShowMailContent(HttpServletRequest request,HttpServletResponse response) throws Exception{
		int messagenum=Integer.parseInt(request.getParameter("thismessagenum"));
		IMAPFolder folder=null;
        if(MailConnection.getInboxFolder()==null){
       	 	return "content/error";
        }
        else
       	 	folder=MailConnection.getInboxFolder();
        Message message = folder.getMessage(messagenum);
        MailContentAnalysis.setContent();
        MailContentAnalysis.getMailContent((Part) message);
        StringBuffer tempcontent=MailContentAnalysis.getContent();
        String content=tempcontent.toString();
        Pattern pattern=Pattern.compile("<style");
        Matcher matcher=pattern.matcher(content.toString());
        content=matcher.replaceFirst("<!-- <style");
        pattern=Pattern.compile("</style>");
        matcher=pattern.matcher(content);
        content=matcher.replaceFirst("</style> -->");
        request.setAttribute("content", content);
        return "mailcontent.definition";
	}
}
