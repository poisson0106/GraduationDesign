package com.sjw.daoImpl;

import java.text.DateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletResponse;

import com.sjw.dao.MailContentDao;
import com.sjw.pojo.Mail;
import com.sjw.utils.MailConnection;
import com.sjw.utils.MailContentAnalysis;
import com.sun.mail.imap.IMAPFolder;

public class MailContentDaoImpl implements MailContentDao {
	@Override
	public Mail showMailContentDao(int messagenum,String frompage) throws Exception {
		Mail mail=new Mail();
		
		IMAPFolder folder=null;
		MailConnection.getConnection();
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
        Message message = folder.getMessage(messagenum);
        
        //ȡ������
        MailContentAnalysis.setContent();
        MailContentAnalysis.getMailContent((Part) message);
        StringBuffer tempcontent=MailContentAnalysis.getContent();
        String content=tempcontent.toString();
        Pattern pattern=Pattern.compile("<style");
        Matcher matcher=pattern.matcher(content.toString());
        content=matcher.replaceAll("<!-- <style");
        pattern=Pattern.compile("</style>");
        matcher=pattern.matcher(content);
        content=matcher.replaceAll("</style> -->");
        //content=content.replaceAll("\n", "<p> </p>");
        pattern=Pattern.compile("<body .*>");
        matcher=pattern.matcher(content);
        content=matcher.replaceFirst("<body>");
        content=content.replaceAll("<title>", "<!-- <title>");
        content=content.replaceAll("</title>", "</title> -->");
        System.out.println(content);
        
        
        mail.setContent(content);
        
        //ȡ���ռ���
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
        
        //��ȡ����
        String subject = "";  
        subject = MimeUtility.decodeText(message.getSubject());  
        if (subject == null) {  
            subject = "No Subject";  
        }
        mail.setSubject(subject);
        
		//��ȡ�ռ���ַ
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
            
       //����
       if(!"draftboxmenu".equals(frompage))
    	   mail.setDate(DateFormat.getDateInstance(DateFormat.MEDIUM).format(message.getSentDate()));
       
       //�Ƿ���и���
       mail.setWithattach(MailContentAnalysis.isContainAttach((Part) message));
       
       //��ȡ�������б�
       if(mail.isWithattach()){
    	   MailContentAnalysis.listAttachMentName((Part) message);
    	   String filenamelist=MailContentAnalysis.getFileName();
    	   mail.setAttachnames(filenamelist.split(","));
       }
       
       //��ȡmessagenum
       mail.setMessagenum(messagenum);
       
       //��ȡȡ�õĺ���
       mail.setFrompage(frompage);
       
       if("inboxmenu".equals(frompage))
    	   MailConnection.closeInboxFolder();
       else if("delboxmenu".equals(frompage))
    	   MailConnection.closeDelFolder();
       else if("draftboxmenu".equals(frompage))
    	   MailConnection.closeDraftFolder();
       MailConnection.closeConnection();
       return mail;
	}
	
	@Override
	public String downloadSelectedAttachmentDao(String fileName,int messagenum,String frompage,HttpServletResponse response)
			throws Exception {
		MailContentAnalysis.fileName=fileName;
		
		IMAPFolder folder=null;
		MailConnection.getConnection();
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
        Message message = folder.getMessage(messagenum);
		MailContentAnalysis.saveAttachMent((Part) message,response);
		return null;
	}
}
