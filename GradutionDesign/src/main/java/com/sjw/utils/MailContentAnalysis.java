package com.sjw.utils;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletResponse;

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
	        	if(contentType.contains("charset=UTF-8")||contentType.contains("GBK")||contentType.contains("GB2312")||contentType.contains("utf-8")||contentType.contains("gb18030")||contentType.contains("gb2312")){
	        		content.append("纯文本部分:");
	        		content.append("</br>");
	        		content.append(tempcontent);
	        	}
	        	else{
	        		String changecode=new String(tempcontent.getBytes("ISO-8859-1"),"GBK");
	        		content.append("纯文本部分:");
	        		content.append("</br>");
	        		content.append(changecode);
	        	}
	        } else if (part.isMimeType("text/html") && conName == false) {  
	            // text/html 类型
	        	if(content.length()>0){
	        		content.append("</br>");
	        		content.append("---------------------------------------------------------");
		        	content.append("</br>");
		        	content.append("HTML富文本部分：");
		        	content.append("</br>");
	        	}
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
                    if(fileNameTemp!=null){
                    	fileNameTemp = new String(fileNameTemp.getBytes("ISO-8859-1"),"UTF-8");
                    	//if (fileNameTemp.contains("charset=UTF-8")||fileNameTemp.contains("GBK")||fileNameTemp.contains("GB2312")||fileNameTemp.contains("utf-8")||fileNameTemp.contains("gb18030")) {
                    		fileName = fileName+MimeUtility.decodeText(fileNameTemp)+",";  
                    	//}
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
	
	public static void saveAttachMent(Part part,HttpServletResponse response) throws Exception {  
        String fileNameTemp = "";  
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
                    //if (fileNameTemp.contains("charset=UTF-8")||fileNameTemp.contains("GBK")||fileNameTemp.contains("GB2312")||fileNameTemp.contains("utf-8")||fileNameTemp.contains("gb18030")) {  
                        fileNameTemp = MimeUtility.decodeText(fileNameTemp);
                        if(fileNameTemp.equals(fileName))
                        	saveFile(fileName, mPart.getInputStream(),response);
                    //}  
                } else if (mPart.isMimeType("multipart/*")) {  
                    saveAttachMent(mPart,response);  
                } else {  
                    fileNameTemp = mPart.getFileName();
                    if(fileNameTemp!=null)
                    	fileNameTemp = new String(fileNameTemp.getBytes("ISO-8859-1"),"UTF-8");
                    /*if ((fileNameTemp != null)  
                            && (fileNameTemp.contains("charset=UTF-8")||fileNameTemp.contains("GBK")||fileNameTemp.contains("GB2312")||fileNameTemp.contains("utf-8")||fileNameTemp.contains("gb18030"))) {  
                        fileNameTemp = MimeUtility.decodeText(fileNameTemp);
                        if(fileNameTemp.equals(fileName))
                        	saveFile(fileName, mPart.getInputStream(),response);  
                    }  */
                    if (fileNameTemp != null){
                    	fileNameTemp = MimeUtility.decodeText(fileNameTemp);
                        if(fileNameTemp.equals(fileName))
                        	saveFile(fileName, mPart.getInputStream(),response);  
                    }
                }  
            }  
        } else if (part.isMimeType("message/rfc822")) {  
            saveAttachMent((Part) part.getContent(),response);  
        }  
    }
  
	 /**  
	  	* 　*　真正的保存附件到指定目录里 　  
     */  
    private static void saveFile(String fileName, InputStream in,HttpServletResponse response) throws Exception {
    	PrintWriter out = response.getWriter();  
    	//response.setContentType("text/html;charset=UTF-8");
    	response.setHeader("Content-Disposition","attachment;filename=" + new String(fileName.getBytes("UTF-8"),"iso8859-1"));
    	int temp = 0;  
        while((temp = in.read()) != -1)  
        {  
            out.write(temp);  
        }  
    	
    }
    
    public static void deleteTempAttachment(String filepath){
    	File file=new File(filepath);
    	if(file.isFile()&&file.exists())
    		file.delete();
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
