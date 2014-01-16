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
	        	String tempcontent=(String) part.getContent();
	        	tempcontent=tempcontent.replaceAll("\n", "</br>");
	        	if(contentType.contains("charset=UTF-8")||contentType.contains("GBK")||contentType.contains("GB2312")||contentType.contains("utf-8")||contentType.contains("gb18030"))
	        		content.append(tempcontent);
	        	else{
	        		String changecode=new String(tempcontent.getBytes("ISO-8859-1"),"GBK");
	        		content.append(changecode);
	        	}
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
