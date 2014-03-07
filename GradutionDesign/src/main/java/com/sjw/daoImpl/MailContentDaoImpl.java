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
		else if("sentboxmenu".equals(frompage)){
			MailConnection.setSentFolder();
			if(MailConnection.getSentFolder()==null){
       	 		return null;
			}
			else
       	 		folder=MailConnection.getSentFolder();
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
        
        //取得发件人以及姓名
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
        
        //取得主题
        String subject = "";  
        subject = MimeUtility.decodeText(message.getSubject());  
        if (subject == null) {  
            subject = "No Subject";  
        }
        mail.setSubject(subject);
        
		//取得收件人地址列表
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
            
       //取得收件日期
       if(!"draftboxmenu".equals(frompage))
    	   mail.setDate(DateFormat.getDateInstance(DateFormat.MEDIUM).format(message.getSentDate()));
       
       //取得附件是否有附件
       mail.setWithattach(MailContentAnalysis.isContainAttach((Part) message));
       
       //取得具体附件内容
       if(mail.isWithattach()){
    	   MailContentAnalysis.listAttachMentName((Part) message);
    	   String filenamelist=MailContentAnalysis.getFileName();
    	   mail.setAttachnames(filenamelist.split(","));
       }
       
       //取得messagenum
       mail.setMessagenum(messagenum);
       
       //取得从哪个页面跳转过来
       mail.setFrompage(frompage);
       
       if("inboxmenu".equals(frompage))
    	   MailConnection.closeInboxFolder();
       else if("delboxmenu".equals(frompage))
    	   MailConnection.closeDelFolder();
       else if("draftboxmenu".equals(frompage))
    	   MailConnection.closeDraftFolder();
       else if("sentboxmenu".equals(frompage))
    	   MailConnection.closeSentFolder();
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
