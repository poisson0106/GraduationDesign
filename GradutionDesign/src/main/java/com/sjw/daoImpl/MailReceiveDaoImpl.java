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

import com.sjw.dao.MailReceiveDao;
import com.sjw.pojo.Mail;
import com.sjw.utils.MailConnection;
import com.sjw.utils.MailContentAnalysis;
import com.sun.mail.imap.IMAPFolder;

public class MailReceiveDaoImpl implements MailReceiveDao {

	@Override
	public List<Mail> initialMailReceiveDao() throws Exception {
		List<Mail> requestedmail = new ArrayList<Mail>();
		int total = 0;
        
        IMAPFolder folder=null;
        if(MailConnection.getInboxFolder()==null){
        	return null;
        }
        else
       	 	folder=MailConnection.getInboxFolder();
        // 获取总邮件数    
        total = folder.getMessageCount();
        for(int i=total;i>=total-9;i--){
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
	public int getTotalMailCountDao() throws Exception {
		int total = 0;
        
        IMAPFolder folder=null;
        MailConnection.getConnection();
        MailConnection.setIndoxFolder();
        if(MailConnection.getInboxFolder()==null){
        	return -1;
        }
        else
       	 	folder=MailConnection.getInboxFolder();
        // 获取总邮件数    
        total = folder.getMessageCount();
		return total;
	}

	@Override
	public List<Mail> listOnePageEmail(int end,int pagenum) throws Exception {
		List<Mail> requestedmail = new ArrayList<Mail>();
		int total=0;
		
		IMAPFolder folder=null;
        if(MailConnection.getInboxFolder()==null){
        	return null;
        }
        else
       	 	folder=MailConnection.getInboxFolder();
        // 获取总邮件数    
        total = folder.getMessageCount();
        for(int i=total-(pagenum-1)*10;i>=end;i--){
        	Message message = folder.getMessage(i);
       	 	Mail mail = new Mail();
       	 	if(message.getSentDate()!=null)
       	 		mail.setDate(DateFormat.getDateInstance(DateFormat.MEDIUM).format(message.getSentDate()));
       	 	mail.setSubject(MimeUtility.decodeText(message.getSubject()));
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
            	mail.setSender(MimeUtility.decodeText(sender));
            requestedmail.add(mail);
        }
        MailConnection.closeInboxFolder();
        MailConnection.closeConnection();
		return requestedmail;
	}

	@Override
	public Mail showMailContentDao(int messagenum) throws Exception {
		Mail mail=new Mail();
		
		IMAPFolder folder=null;
		MailConnection.getConnection();
        MailConnection.setIndoxFolder();
        if(MailConnection.getInboxFolder()==null){
       	 	return null;
        }
        else
       	 	folder=MailConnection.getInboxFolder();
        Message message = folder.getMessage(messagenum);
        
        //取得内容
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
        //content=content.replaceAll("\n", "<p> </p>");
        pattern=Pattern.compile("<body style=\"(.*);\">");
        matcher=pattern.matcher(content);
        content=matcher.replaceFirst("<body>");
        content=content.replaceAll("<title>", "<!-- <title>");
        content=content.replaceAll("</title>", "</title> -->");
        
        
        System.out.print(content);
        mail.setContent(content);
        
        //取得收件人
        InternetAddress address[] = (InternetAddress[]) message.getFrom();  
        String from = address[0].getAddress();  
        if (from == null) {  
            from = "without address";  
        }  
        String personal = address[0].getPersonal();  
  
        if (personal == null) {  
            personal = "without name";  
        }  
  
        String fromAddr = null;  
        if (personal != null || from != null) {  
            fromAddr = personal + ":[" + from + "]";  
        }
        mail.setSender(fromAddr);
        
        //获取主题
        String subject = "";  
        subject = MimeUtility.decodeText(message.getSubject());  
        if (subject == null) {  
            subject = "No Subject";  
        }
        mail.setSubject(subject);
        
		//获取收件地址
		String addresslist = "";
		address=null;
  
        address = (InternetAddress[]) message.getRecipients(Message.RecipientType.TO);  
            if (address != null) {  
                for (int i = 0; i < address.length; i++) {  
                    String emailAddr = address[i].getAddress();  
                    if (emailAddr == null) {  
                        emailAddr = "";  
                    } 
                    else 
                        emailAddr = MimeUtility.decodeText(emailAddr);  
                    personal = address[i].getPersonal();  
                    if (personal == null) {  
                        personal = "";  
                    } 
                    else
                        personal = MimeUtility.decodeText(personal);
                    String compositeto = personal + ":[" + emailAddr + "]";  
                    addresslist += "," + compositeto;  
                }  
                addresslist = addresslist.substring(1);
            }
       mail.setReceivers(addresslist);
            
       //日期
       mail.setDate(DateFormat.getDateInstance(DateFormat.MEDIUM).format(message.getSentDate()));
       
       MailConnection.closeInboxFolder();
       MailConnection.closeConnection();
       return mail;
	}

	@Override
	public String deleteSelectedEmailDao(String[] messagenum) throws Exception {
		int i=0;
		Message[] messages=new Message[messagenum.length];
		IMAPFolder folder=null;
		//IMAPFolder folderDel=null;
	    MailConnection.getConnection();
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
