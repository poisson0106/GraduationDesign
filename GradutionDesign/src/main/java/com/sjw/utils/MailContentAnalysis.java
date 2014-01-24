package com.sjw.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeUtility;
import javax.swing.JFileChooser;

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
	
	//���ģ��ķ��μ�����ķ���
	public static void saveAttachMent(Part part) throws Exception {  
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
                    if (fileNameTemp.contains("charset=UTF-8")||fileNameTemp.contains("GBK")||fileNameTemp.contains("GB2312")||fileNameTemp.contains("utf-8")||fileNameTemp.contains("gb18030")) {  
                        fileNameTemp = MimeUtility.decodeText(fileNameTemp);
                        if(fileNameTemp.equals(fileName))
                        	saveFile(fileName, mPart.getInputStream());
                    }  
                } else if (mPart.isMimeType("multipart/*")) {  
                    saveAttachMent(mPart);  
                } else {  
                    fileNameTemp = mPart.getFileName();
                    if(fileNameTemp!=null)
                    	fileNameTemp = new String(fileNameTemp.getBytes("ISO-8859-1"),"UTF-8");
                    if ((fileNameTemp != null)  
                            && (fileNameTemp.contains("charset=UTF-8")||fileNameTemp.contains("GBK")||fileNameTemp.contains("GB2312")||fileNameTemp.contains("utf-8")||fileNameTemp.contains("gb18030"))) {  
                        fileNameTemp = MimeUtility.decodeText(fileNameTemp);
                        if(fileNameTemp.equals(fileName))
                        	saveFile(fileName, mPart.getInputStream());  
                    }  
                }  
            }  
        } else if (part.isMimeType("message/rfc822")) {  
            saveAttachMent((Part) part.getContent());  
        }  
    }
  
    /**  
     * ��*�������ı��渽����ָ��Ŀ¼�� ��  
     */  
    private static void saveFile(String fileName, InputStream in) throws Exception {  
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(null);
        String path = chooser.getSelectedFile().getPath();
        
        File storeFile = new File(path+'\\'+fileName);  
        System.out.println("�����ı����ַ:��" + storeFile.toString());
        BufferedOutputStream bos = null;  
        BufferedInputStream bis = null;  
  
        try {  
            bos = new BufferedOutputStream(new FileOutputStream(storeFile));  
            bis = new BufferedInputStream(in);  
            int c;  
            while ((c = bis.read()) != -1) {  
                bos.write(c);  
                bos.flush();  
            }  
        } catch (Exception exception) {  
            exception.printStackTrace();  
            throw new Exception("�ļ�����ʧ��!");  
        } finally {  
            bos.close();  
            bis.close();  
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
