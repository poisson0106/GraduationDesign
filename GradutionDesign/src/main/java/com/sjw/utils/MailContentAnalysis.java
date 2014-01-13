package com.sjw.utils;

import javax.mail.Multipart;
import javax.mail.Part;

public class MailContentAnalysis {
	public static void getMailContent(Part part) throws Exception{
		 String contentType = part.getContentType();  
	        // 获得邮件的MimeType类型  
	        System.out.println("邮件的MimeType类型: " + contentType);  
	  
	        int nameIndex = contentType.indexOf("name");  
	  
	        boolean conName = false;  
	  
	        if (nameIndex != -1) {  
	            conName = true;  
	        }  
	  
	        /*System.out.println("邮件内容的类型:　" + contentType);  */
	  
	        if (part.isMimeType("text/plain") && conName == false) {  
	            // text/plain 类型  
	            content.append((String) part.getContent());  
	        } else if (part.isMimeType("text/html") && conName == false) {  
	            // text/html 类型  
	        	content.append((String) part.getContent());  
	        } else if (part.isMimeType("multipart/*")) {  
	            // multipart/*  
	            Multipart multipart = (Multipart) part.getContent();  
	            int counts = multipart.getCount();  
	            for (int i = 0; i < counts; i++) {  
	                getMailContent(multipart.getBodyPart(i));  
	            }  
	        } else if (part.isMimeType("message/rfc822")) {  
	            // message/rfc822  
	            getMailContent((Part) part.getContent());  
	        } 
	}
	
	public static StringBuffer getContent(){
		return content;
	}
	
	public static void setContent(){
		content=new StringBuffer();
	}
	
	public static StringBuffer content;
}
