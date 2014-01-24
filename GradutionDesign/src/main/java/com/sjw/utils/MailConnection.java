package com.sjw.utils;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Session;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;


public class MailConnection {
	private static IMAPFolder folder=null;
	private static IMAPStore store=null;
	private static IMAPFolder folderDel=null;
	
	public static void getConnection() throws Exception{
		String user = "poisson0106@163.com";// ������û���    
        String password = "19910106sjw"; // ���������   
        Properties prop = System.getProperties();
        prop.put("mail.store.protocol", "imap");    
        prop.put("mail.imap.host", "imap.163.com");
     
        Session session = Session.getInstance(prop); 
        
        store = (IMAPStore) session.getStore("imap"); // ʹ��imap�Ự���ƣ����ӷ�����    
        store.connect(user, password);
        
	}
	
	public static void setIndoxFolder() throws Exception{
		folder = (IMAPFolder) store.getFolder("INBOX"); // �ռ���  
        folder.open(Folder.READ_WRITE);
	}
	
	public static void setDelFolder() throws Exception{
		folderDel = (IMAPFolder) store.getFolder("�ͻ���ɾ��");
		folderDel.open(Folder.READ_WRITE);
	}
	
	
	public static IMAPFolder getInboxFolder() throws Exception{
		return folder;
	}
	
	public static IMAPFolder getDelFolder() throws Exception{
		return folderDel;
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
	
	public static void closeConnection() throws Exception{
		if (store != null)    
            store.close();   
	}
}
