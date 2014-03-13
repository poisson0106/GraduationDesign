package com.sjw.utils;

import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Session;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;


public class MailConnection {
	private static IMAPFolder folder=null;
	private static IMAPStore store=null;
	private static IMAPFolder folderDel=null;
	private static IMAPFolder folderDraft=null;
	private static IMAPFolder folderSent=null;
	private static Session session;
	
	public static void getConnection() throws Exception{
		String user = "username@163.com";// 用户名
        String password = "password"; // 密码   
        Properties prop = System.getProperties();
        prop.put("mail.store.protocol", "imap");    
        prop.put("mail.imap.host", "imap.163.com");
     
        session = Session.getInstance(prop);
        
        store = (IMAPStore) session.getStore("imap"); // imap方式连接
        store.connect(user, password);
        
	}
	
	public static Session getSession() throws Exception{
		return session;
	}
	
	public static void getConnectionPOP3() throws Exception{
		
	}
	
	public static void setIndoxFolder() throws Exception{
		folder = (IMAPFolder) store.getFolder("INBOX"); // 打开收件箱
        folder.open(Folder.READ_WRITE);
	}
	
	public static void setDelFolder() throws Exception{
		folderDel = (IMAPFolder) store.getFolder("客户端删信"); //打开已删除
		folderDel.open(Folder.READ_WRITE);
	}
	
	public static void setDraftFolder() throws Exception{
		folderDraft = (IMAPFolder) store.getFolder("草稿箱"); //打开草稿箱
		folderDraft.open(Folder.READ_WRITE);
	}
	
	public static void setSentFolder() throws Exception{
		folderSent = (IMAPFolder) store.getFolder("已发送"); //打开发件箱
		folderSent.open(Folder.READ_WRITE);
	}
	
	public static IMAPFolder getInboxFolder() throws Exception{
		return folder;
	}
	
	public static IMAPFolder getDelFolder() throws Exception{
		return folderDel;
	}
	
	public static IMAPFolder getSentFolder() throws Exception{
		return folderSent;
	}
	
	public static IMAPFolder getDraftFolder() throws Exception{
		return folderDraft;
	}
	
	//Logout ʱʹ��
	public static void closeInboxFolder() throws Exception{
		if (folder != null)    
            folder.close(true);
	}
	
	public static void closeDelFolder() throws Exception{
		if(folderDel!=null)
			folderDel.close(true);
	}
	
	public static void closeDraftFolder() throws Exception{
		if(folderDraft!=null)
			folderDraft.close(true);
	}
	
	public static void closeSentFolder() throws Exception{
		if(folderSent!=null)
			folderSent.close(true);
	}
	
	public static void closeConnection() throws Exception{
		if (store != null)    
            store.close();   
	}
}
