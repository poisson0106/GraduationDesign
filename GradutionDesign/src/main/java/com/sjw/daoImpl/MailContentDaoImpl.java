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
       
       //是否带有附件
       mail.setWithattach(MailContentAnalysis.isContainAttach((Part) message));
       
       //获取附件名列表
       if(mail.isWithattach()){
    	   MailContentAnalysis.listAttachMentName((Part) message);
    	   String filenamelist=MailContentAnalysis.getFileName();
    	   mail.setAttachnames(filenamelist.split(","));
       }
       
       //获取messagenum
       mail.setMessagenum(messagenum);
       
       MailConnection.closeInboxFolder();
       MailConnection.closeConnection();
       return mail;
	}
	
	@Override
	public String downloadSelectedAttachmentDao(String fileName,int messagenum,HttpServletResponse response)
			throws Exception {
		MailContentAnalysis.fileName=fileName;
		
		IMAPFolder folder=null;
		MailConnection.getConnection();
        MailConnection.setIndoxFolder();
        if(MailConnection.getInboxFolder()==null){
       	 	return null;
        }
        else
       	 	folder=MailConnection.getInboxFolder();
        Message message = folder.getMessage(messagenum);
		MailContentAnalysis.saveAttachMent((Part) message,response);
		return null;
	}
}
