package com.sjw.daoImpl;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sjw.dao.MailSendDao;
import com.sjw.pojo.Mail;
import com.sjw.utils.MailContentAnalysis;

public class MailSendDaoImpl implements MailSendDao {

	@Override
	public Boolean SendOneEmailDao(Mail mail) throws Exception {
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.163.com");
		email.setAuthenticator(new DefaultAuthenticator("poisson0106@163.com", "19910106sjw"));
		email.setSSL(true);
		email.setFrom(mail.getSender());
		email.setSubject(mail.getSubject());
		email.setCharset("UTF-8");
		email.setHtmlMsg(mail.getContent());
		email.addTo(mail.getReceivers());
		email.send();
		return true;
	}

	@Override
	public Boolean uploadAttachmentDao(HttpServletRequest request)
			throws Exception {
		String foldername=request.getParameter("foldername").substring(0,request.getParameter("foldername").indexOf("@"));
		String uploadDir=request.getSession().getServletContext().getRealPath("/")+File.separator+"tmp"+File.separator+foldername; //poisson之后要从登陆界面过来的session中拿
    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
    	Map<String,MultipartFile> filemap=multipartRequest.getFileMap();
    	File dirPath = new File(uploadDir);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
        for (Map.Entry<String, MultipartFile> entity : filemap.entrySet()) {
        	MultipartFile attach = entity.getValue();
        	//String attachname=new String(attach.getOriginalFilename().getBytes("ISO-8859-1"),"UTF-8");
        	File uploadFile = new File(uploadDir+File.separator+attach.getOriginalFilename());
        	FileUtils.writeByteArrayToFile(uploadFile, attach.getBytes());
        }
    	return true;
	}

}
