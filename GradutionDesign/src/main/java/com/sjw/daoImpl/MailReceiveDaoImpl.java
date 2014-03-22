package com.sjw.daoImpl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sjw.dao.MailReceiveDao;
import com.sjw.pojo.Mail;
import com.sjw.utils.MailConnection;
import com.sjw.utils.MailContentAnalysis;
import com.sun.mail.imap.IMAPFolder;

public class MailReceiveDaoImpl implements MailReceiveDao {

	@Override
	public List<Mail> initialMailReceiveDao(HttpSession session) throws Exception {
		List<Mail> requestedmail = new ArrayList<Mail>();
		int total = 0;
		int end=100;
        
        IMAPFolder folder=null;
        if(MailConnection.getInboxFolder()==null){
        	return null;
        }
        else
       	 	folder=MailConnection.getInboxFolder();
        //取得邮件总数 
        total = folder.getMessageCount();
        if(total<10&&total!=0)
        	end=1;
        else
        	end=total-9;
        for(int i=total;i>=end;i--){
       	 	Message message = folder.getMessage(i);
       	 	Mail mail = new Mail();
       	 	if(message.getSentDate()!=null)
       	 		mail.setDate(DateFormat.getDateInstance(DateFormat.MEDIUM).format(message.getSentDate()));
       	 	mail.setSubject(message.getSubject());
       	 	mail.setMessagenum(message.getMessageNumber());
       	 	//mail.setReceivers(message.getAllRecipients());
       	 	Flags flags = message.getFlags();    
       	 	if (flags.contains(Flags.Flag.SEEN))
       	 		mail.setFlags(false);
       	 	else {    
       	 		mail.setFlags(true);
       	 	}
            InternetAddress address[] = (InternetAddress[])message.getFrom();
            String sender=address[0].getPersonal();
            if(sender==null)
           	 	sender="匿名";
            else
            	if(sender.contains("\""))
            		sender=sender.substring(sender.indexOf("\"")+1, sender.length()-1);	 
            mail.setSender(sender);
            	
            requestedmail.add(mail);
        }
        MailConnection.closeInboxFolder();
        MailConnection.closeConnection();
        return requestedmail;
	}

	@Override
	public int getTotalMailCountDao(HttpSession session) throws Exception {
		int total = 0;
        
        IMAPFolder folder=null;
        MailConnection.getConnection(session.getAttribute("username").toString(),session.getAttribute("password").toString());
        MailConnection.setIndoxFolder();
        if(MailConnection.getInboxFolder()==null){
        	return -1;
        }
        else
       	 	folder=MailConnection.getInboxFolder();
        //取得邮件总数  
        total = folder.getMessageCount();
		return total;
	}
	
	@Override
	public String deleteSelectedEmailDao(String[] messagenum,HttpSession session) throws Exception {
		int i=0;
		Message[] messages=new Message[messagenum.length];
		IMAPFolder folder=null;
		//IMAPFolder folderDel=null;
	    MailConnection.getConnection(session.getAttribute("username").toString(),session.getAttribute("password").toString());
	    MailConnection.setIndoxFolder();
	    if(MailConnection.getInboxFolder()==null){
	       return "fail";
	    }
	    else{
	    	//MailConnection.setDelFolder();
	    	/*if(MailConnection.getDelFolder()==null)
	    		return "fail";
	    	else{
	    		folder=MailConnection.getInboxFolder();
	    		folderDel=MailConnection.getDelFolder();
	    	}  		*/
	    	folder=MailConnection.getInboxFolder();
	    }    
	   /* for(String msg : messagenum){
	       int msgnum=Integer.parseInt(msg);
	       messages[i]=folder.getMessage(msgnum);
	       i++;
	    }
	    folder.copyMessages(messages, folderDel);
	    i=0;*/
	    for(String msg : messagenum){
		       int msgnum=Integer.parseInt(msg);
		       messages[i]=folder.getMessage(msgnum);
		       messages[i].setFlag(Flags.Flag.DELETED, true);
		       i++;
		}
	    MailConnection.closeInboxFolder();
	    //MailConnection.closeDelFolder();
	    MailConnection.closeConnection();
	    return "success";
	}
}
