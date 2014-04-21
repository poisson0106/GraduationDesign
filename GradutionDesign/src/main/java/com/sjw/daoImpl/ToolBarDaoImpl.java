package com.sjw.daoImpl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sjw.dao.ToolBarDao;
import com.sjw.pojo.Mail;
import com.sjw.utils.MailConnection;
import com.sun.mail.imap.IMAPFolder;

public class ToolBarDaoImpl implements ToolBarDao {
	@Override
	public String setMailSeenDao(String[] messagenum,HttpSession session,String from) throws Exception {
		int i=0;
		Message[] messages=new Message[messagenum.length];
		IMAPFolder folder=null;
		MailConnection.getConnection(session.getAttribute("username").toString(),session.getAttribute("password").toString());
	    if("inboxmenu".equals(from)){
			MailConnection.setIndoxFolder();
			if(MailConnection.getInboxFolder()==null){
       	 		return null;
			}
			else
       	 		folder=MailConnection.getInboxFolder();
		}
		else if("draftboxmenu".equals(from)){
			MailConnection.setDraftFolder();
			if(MailConnection.getDraftFolder()==null){
       	 		return null;
			}
			else
       	 		folder=MailConnection.getDraftFolder();
		}
		else if("delboxmenu".equals(from)){
			MailConnection.setDelFolder();
			if(MailConnection.getDelFolder()==null){
       	 		return null;
			}
			else
       	 		folder=MailConnection.getDelFolder();
		}
		else if("sentboxmenu".equals(from)){
			MailConnection.setSentFolder();
			if(MailConnection.getSentFolder()==null){
       	 		return null;
			}
			else
       	 		folder=MailConnection.getSentFolder();
		}
	    for(String msg : messagenum){
		       int msgnum=Integer.parseInt(msg);
		       messages[i]=folder.getMessage(msgnum);
		       messages[i].setFlag(Flags.Flag.SEEN, true);
		       i++;
		}
	    if("inboxmenu".equals(from)){
	    	//设置未读邮件数
	        session.setAttribute("nummail", folder.getUnreadMessageCount());
	        MailConnection.closeInboxFolder();
	    }
	    else if("draftboxmenu".equals(from))
	    	MailConnection.closeDraftFolder();
	    else if("sentboxmenu".equals(from))
	    	MailConnection.closeSentFolder();
	    else if("delboxmenu".equals(from))
	     	   MailConnection.closeDelFolder();
	    MailConnection.closeConnection();
		return "success";
	}

	@Override
	public String setMailUnSeenDao(String[] messagenum,HttpSession session,String from) throws Exception {
		int i=0;
		Message[] messages=new Message[messagenum.length];
		IMAPFolder folder=null;
		MailConnection.getConnection(session.getAttribute("username").toString(),session.getAttribute("password").toString());
		if("inboxmenu".equals(from)){
			MailConnection.setIndoxFolder();
			if(MailConnection.getInboxFolder()==null){
	       	 	return null;
			}
			else
	       	 	folder=MailConnection.getInboxFolder();
		}
		else if("draftboxmenu".equals(from)){
			MailConnection.setDraftFolder();
			if(MailConnection.getDraftFolder()==null){
	       	 	return null;
			}
			else
	       	 	folder=MailConnection.getDraftFolder();
		}
		else if("delboxmenu".equals(from)){
			MailConnection.setDelFolder();
			if(MailConnection.getDelFolder()==null){
       	 		return null;
			}
			else
       	 		folder=MailConnection.getDelFolder();
		}
		else if("sentboxmenu".equals(from)){
			MailConnection.setSentFolder();
			if(MailConnection.getSentFolder()==null){
	       	 	return null;
			}
			else
	       	 	folder=MailConnection.getSentFolder();
		}
	    for(String msg : messagenum){
		       int msgnum=Integer.parseInt(msg);
		       messages[i]=folder.getMessage(msgnum);
		       messages[i].setFlag(Flags.Flag.SEEN, false);
		       i++;
		}
	    if("inboxmenu".equals(from)){
	    	//设置未读邮件数
	        session.setAttribute("nummail", folder.getUnreadMessageCount());
	        MailConnection.closeInboxFolder();
	    }
	    else if("draftboxmenu".equals(from))
	    	MailConnection.closeDraftFolder();
	    else if("sentboxmenu".equals(from))
	    	MailConnection.closeSentFolder();
	    else if("delboxmenu".equals(from))
	     	   MailConnection.closeDelFolder();
	    MailConnection.closeConnection();
		return "success";
	}
	
	@Override
	public List<Mail> listOnePageEmail(int end,int pagenum,String frompage,HttpSession session) throws Exception {
		List<Mail> requestedmail = new ArrayList<Mail>();
		int total=0;
		
		IMAPFolder folder=null;
		if("inboxmenu".equals(frompage)){
			MailConnection.setIndoxFolder();
			if(MailConnection.getInboxFolder()==null){
       	 		return null;
			}
			else
       	 		folder=MailConnection.getInboxFolder();
		}
		else if("delboxmenu".equals(frompage)){
			MailConnection.setDelFolder();
			if(MailConnection.getDelFolder()==null){
       	 		return null;
			}
			else
       	 		folder=MailConnection.getDelFolder();
		}
		else if("draftboxmenu".equals(frompage)){
			MailConnection.setDraftFolder();
			if(MailConnection.getDraftFolder()==null){
       	 		return null;
			}
			else
       	 		folder=MailConnection.getDraftFolder();
		}
		else if("sentboxmenu".equals(frompage)){
			MailConnection.setSentFolder();
			if(MailConnection.getSentFolder()==null){
       	 		return null;
			}
			else
       	 		folder=MailConnection.getSentFolder();
		}
        //取得邮件总数
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
        if("inboxmenu".equals(frompage))
     	   MailConnection.closeInboxFolder();
        else if("delboxmenu".equals(frompage))
     	   MailConnection.closeDelFolder();
        else if("draftboxmenu".equals(frompage))
     	   MailConnection.closeDraftFolder();
        else if("sentboxmenu".equals(frompage))
     	   MailConnection.closeSentFolder();
        MailConnection.closeConnection();
		return requestedmail;
	}

	@Override
	public int getOnePageEmailCountDao(String frompage,HttpSession session) throws Exception {
		int total=0;
		
		IMAPFolder folder=null;
		MailConnection.getConnection(session.getAttribute("username").toString(),session.getAttribute("password").toString());
		if("inboxmenu".equals(frompage)){
			MailConnection.setIndoxFolder();
			if(MailConnection.getInboxFolder()==null){
       	 		return -1;
			}
			else
       	 		folder=MailConnection.getInboxFolder();
		}
		else if("delboxmenu".equals(frompage)){
			MailConnection.setDelFolder();
			if(MailConnection.getDelFolder()==null){
       	 		return -1;
			}
			else
       	 		folder=MailConnection.getDelFolder();
		}
		else if("draftboxmenu".equals(frompage)){
			MailConnection.setDraftFolder();
			if(MailConnection.getDraftFolder()==null){
       	 		return -1;
			}
			else
       	 		folder=MailConnection.getDraftFolder();
		}
		else if("sentboxmenu".equals(frompage)){
			MailConnection.setSentFolder();
			if(MailConnection.getSentFolder()==null){
       	 		return -1;
			}
			else
       	 		folder=MailConnection.getSentFolder();
		}
        //取得邮件总数
        total = folder.getMessageCount();
        if("inboxmenu".equals(frompage))
        	MailConnection.closeInboxFolder();
        else if("delboxmenu".equals(frompage))
        	MailConnection.closeDelFolder();
        else if("draftboxmenu".equals(frompage))
        	MailConnection.closeDraftFolder();
        else if("sentboxmenu".equals(frompage))
        	MailConnection.closeSentFolder();
		return total;
	}

	@Override
	public String deleteSelectedEmailDao(String[] messagenum,HttpSession session,String from) throws Exception {
		int i=0;
		Message[] messages=new Message[messagenum.length];
		IMAPFolder folder=null;
		IMAPFolder folderDel=null;
	    MailConnection.getConnection(session.getAttribute("username").toString(),session.getAttribute("password").toString());
	    MailConnection.setIndoxFolder();
	    if("inboxmenu".equals(from)){
			MailConnection.setIndoxFolder();
			if(MailConnection.getInboxFolder()==null){
       	 		return "error";
			}
			else
       	 		folder=MailConnection.getInboxFolder();
		}
		else if("draftboxmenu".equals(from)){
			MailConnection.setDraftFolder();
			if(MailConnection.getDraftFolder()==null){
       	 		return "error";
			}
			else
       	 		folder=MailConnection.getDraftFolder();
		}
		else if("sentboxmenu".equals(from)){
			MailConnection.setSentFolder();
			if(MailConnection.getSentFolder()==null){
       	 		return "error";
			}
			else
       	 		folder=MailConnection.getSentFolder();
		}
	    
	    MailConnection.setDelFolder();
	    if(MailConnection.getDelFolder()==null)
    		return "error";
    	else{
    		folderDel=MailConnection.getDelFolder();
    	}
	    
	    for(String msg : messagenum){
	       int msgnum=Integer.parseInt(msg);
	       messages[i]=folder.getMessage(msgnum);
	       i++;
	    }
	    folder.copyMessages(messages, folderDel);
	    i=0;
	    for(String msg : messagenum){
		       int msgnum=Integer.parseInt(msg);
		       messages[i]=folder.getMessage(msgnum);
		       messages[i].setFlag(Flags.Flag.DELETED, true);
		       i++;
		}
	    if("inboxmenu".equals(from)){
	    	//设置未读邮件数
	        session.setAttribute("nummail", folder.getUnreadMessageCount());
	        MailConnection.closeInboxFolder();
	    }
	    else if("draftboxmenu".equals(from))
	    	MailConnection.closeDraftFolder();
	    else if("sentboxmenu".equals(from))
	    	MailConnection.closeSentFolder();
	    MailConnection.closeDelFolder();
	    MailConnection.closeConnection();
	    return "success";
	}
	
}
