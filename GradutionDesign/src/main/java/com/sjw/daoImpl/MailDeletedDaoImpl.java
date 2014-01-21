package com.sjw.daoImpl;


import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;

import com.sjw.dao.MailDeletedDao;
import com.sjw.pojo.Mail;
import com.sjw.utils.MailConnection;
import com.sun.mail.imap.IMAPFolder;

public class MailDeletedDaoImpl implements MailDeletedDao {

	@Override
	public int getMailDeletedCountDao() throws Exception {
		int total = 0;
        
        IMAPFolder folder=null;
        MailConnection.getConnection();
        MailConnection.setDelFolder();
        if(MailConnection.getDelFolder()==null){
        	return -1;
        }
        else
       	 	folder=MailConnection.getDelFolder();
        // 获取总邮件数    
        total = folder.getMessageCount();
		return total;
	}

	@Override
	public List<Mail> initialMailDeletedDao() throws Exception {
		List<Mail> requestedmail = new ArrayList<Mail>();
		int total = 0;
		int end=1;
        
        IMAPFolder folder=null;
        if(MailConnection.getDelFolder()==null){
        	return null;
        }
        else
       	 	folder=MailConnection.getDelFolder();
        // 获取总邮件数    
        total = folder.getMessageCount();
        if(total>10){
        	end=total-9;
        }
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
        MailConnection.closeDelFolder();
        MailConnection.closeConnection();
        return requestedmail;
	}

}
