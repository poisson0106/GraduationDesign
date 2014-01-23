package com.sjw.utils;

import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeUtility;

public class MailContentAnalysis {
	public static void getMailContent(Part part) throws Exception{
		 String contentType = part.getContentType();  
	        // ����ʼ���MimeType����  
	        System.out.println("�ʼ���MimeType����: " + contentType);  
	  
	        int nameIndex = contentType.indexOf("name");  
	  
	        boolean conName = false;  
	  
	        if (nameIndex != -1) {  
	            conName = true;  
	        }  
	  
	        /*System.out.println("�ʼ����ݵ�����:��" + contentType);  */
	  
	        if (part.isMimeType("text/plain") && conName == false) {  
	            // text/plain ����  
	        	String tempcontent=(String) part.getContent();
	        	tempcontent=tempcontent.replaceAll("\n", "</br>");
	        	if(contentType.contains("charset=UTF-8")||contentType.contains("GBK")||contentType.contains("GB2312")||contentType.contains("utf-8")||contentType.contains("gb18030"))
	        		content.append(tempcontent);
	        	else{
	        		String changecode=new String(tempcontent.getBytes("ISO-8859-1"),"GBK");
	        		content.append(changecode);
	        	}
	        } else if (part.isMimeType("text/html") && conName == false) {  
	            // text/html ����  
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
	
	public static boolean isContainAttach(Part part) throws Exception {  
        boolean attachFlag = false;  
        // String contentType = part.getContentType();  
        if (part.isMimeType("multipart/*")) {  
            Multipart mp = (Multipart) part.getContent();  
            for (int i = 0; i < mp.getCount(); i++) {  
                BodyPart mPart = mp.getBodyPart(i);  
                String disposition = mPart.getDisposition();  
                if ((disposition != null)  
                        && ((disposition.equals(Part.ATTACHMENT)) || (disposition  
                                .equals(Part.INLINE))))  
                    attachFlag = true;  
                else if (mPart.isMimeType("multipart/*")) {  
                    attachFlag = isContainAttach((Part) mPart);  
                } else {  
                    String conType = mPart.getContentType();  
  
                    if (conType.toLowerCase().indexOf("application") != -1)  
                        attachFlag = true;  
                    if (conType.toLowerCase().indexOf("name") != -1)  
                        attachFlag = true;  
                }  
            }  
        } else if (part.isMimeType("message/rfc822")) {  
            attachFlag = isContainAttach((Part) part.getContent());  
        }  
        return attachFlag;  
    }
	
	public static void listAttachMentName(Part part) throws Exception {
		fileName="";
        String fileNameTemp="";
        if (part.isMimeType("multipart/*")) {  
            Multipart mp = (Multipart) part.getContent();  
            for (int i = 0; i < mp.getCount(); i++) {  
                BodyPart mPart = mp.getBodyPart(i);  
                String disposition = mPart.getDisposition();  
                if ((disposition != null)  
                        && ((disposition.equals(Part.ATTACHMENT)) || (disposition  
                                .equals(Part.INLINE)))) {  
                    fileNameTemp = mPart.getFileName();
                    fileNameTemp = new String(fileNameTemp.getBytes("ISO-8859-1"),"UTF-8");
                    if (fileNameTemp.contains("charset=UTF-8")||fileNameTemp.contains("GBK")||fileNameTemp.contains("GB2312")||fileNameTemp.contains("utf-8")||fileNameTemp.contains("gb18030")) {
                    	fileName = fileName+MimeUtility.decodeText(fileNameTemp)+",";  
                    }  
                } else if (mPart.isMimeType("multipart/*")) {  
                    listAttachMentName(mPart);  
                } else {  
                    fileNameTemp = mPart.getFileName();
                    if(fileNameTemp!=null)
                    	fileNameTemp = new String(fileNameTemp.getBytes("ISO-8859-1"),"UTF-8");
                    if ((fileNameTemp != null)  
                            && (fileNameTemp.contains("charset=UTF-8")||fileNameTemp.contains("GBK")||fileNameTemp.contains("GB2312")||fileNameTemp.contains("utf-8")||fileNameTemp.contains("gb18030"))) {  
                        fileName = fileName+MimeUtility.decodeText(fileNameTemp)+",";  
                    }  
                }  
            }  
        } else if (part.isMimeType("message/rfc822")) {  
            listAttachMentName((Part) part.getContent());  
        }  
    }
	
	public static StringBuffer getContent(){
		return content;
	}
	
	public static void setContent(){
		content=new StringBuffer();
	}
	
	public static String getFileName() {
		return fileName;
	}

	public static void setFileName() {
		fileName="";
	}

	public static StringBuffer content;
	
	public static String fileName;
}
