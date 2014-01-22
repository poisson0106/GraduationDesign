package com.sjw.daoImpl;

import javax.mail.Flags;
import javax.mail.Message;

import com.sjw.dao.ToolBarDao;
import com.sjw.utils.MailConnection;
import com.sun.mail.imap.IMAPFolder;

public class ToolBarDaoImpl implements ToolBarDao {
	@Override
	public String setMailSeenDao(String[] messagenum) throws Exception {
		int i=0;
		Message[] messages=new Message[messagenum.length];
		IMAPFolder folder=null;
		MailConnection.getConnection();
	    MailConnection.setIndoxFolder();
	    if(MailConnection.getInboxFolder()==null){
	       return "fail";
	    }
	    else{
	    	folder=MailConnection.getInboxFolder();
	    }
	    for(String msg : messagenum){
		       int msgnum=Integer.parseInt(msg);
		       messages[i]=folder.getMessage(msgnum);
		       messages[i].setFlag(Flags.Flag.SEEN, true);
		       i++;
		}
	    MailConnection.closeInboxFolder();
	    MailConnection.closeConnection();
		return "success";
	}

	@Override
	public String setMailUnSeenDao(String[] messagenum) throws Exception {
		int i=0;
		Message[] messages=new Message[messagenum.length];
		IMAPFolder folder=null;
		MailConnection.getConnection();
	    MailConnection.setIndoxFolder();
	    if(MailConnection.getInboxFolder()==null){
	       return "fail";
	    }
	    else{
	    	folder=MailConnection.getInboxFolder();
	    }
	    for(String msg : messagenum){
		       int msgnum=Integer.parseInt(msg);
		       messages[i]=folder.getMessage(msgnum);
		       messages[i].setFlag(Flags.Flag.SEEN, false);
		       i++;
		}
	    MailConnection.closeInboxFolder();
	    MailConnection.closeConnection();
		return "success";
	}
}
