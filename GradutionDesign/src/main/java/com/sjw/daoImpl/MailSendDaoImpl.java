package com.sjw.daoImpl;

import java.io.File;
import java.util.Map;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sjw.dao.MailSendDao;
import com.sjw.pojo.Mail;
import com.sjw.utils.MailConnection;
import com.sjw.utils.MailContentAnalysis;
import com.sun.mail.imap.IMAPFolder;

public class MailSendDaoImpl implements MailSendDao {

	@Override
	public Boolean SendOneEmailDao(Mail mail,HttpServletRequest request) throws Exception {
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.163.com");
		email.setAuthenticator(new DefaultAuthenticator("poisson0106@163.com", "19910106sjw"));
		email.setSSL(true);
		email.setFrom(mail.getSender());
		email.setSubject(mail.getSubject());
		email.setCharset("UTF-8");
		email.setHtmlMsg(mail.getContent());
		email.addTo(mail.getReceivers());
		String foldername=mail.getSender().substring(0,mail.getSender().indexOf("@"));
		String uploadDir=request.getSession().getServletContext().getRealPath("/")+File.separator+"tmp"+File.separator+foldername;
		for(int i=0;i<mail.getAttachnames().length;i++){
			EmailAttachment attr=new EmailAttachment();
			attr.setPath(uploadDir+File.separator+mail.getAttachnames()[i]);
			attr.setDescription(EmailAttachment.ATTACHMENT);
			attr.setName(MimeUtility.encodeText(mail.getAttachnames()[i]));
			email.attach(attr);
		}
		email.send();
		return true;
	}

	@Override
	public Boolean uploadAttachmentDao(HttpServletRequest request)
			throws Exception {
		String foldername=request.getParameter("foldername").substring(0,request.getParameter("foldername").indexOf("@"));
		String uploadDir=request.getSession().getServletContext().getRealPath("/")+File.separator+"tmp"+File.separator+foldername; //poisson֮��Ҫ�ӵ�½���������session����
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

	@Override
	public Boolean saveOneEmailDao(Mail mail) throws Exception {
		//邮件建立成功但无法转到草稿箱，待加
		MailConnection.getConnection();
		Session session=MailConnection.getSession();
		IMAPFolder folderDraft;
		MailConnection.setDraftFolder();
		if(MailConnection.getDraftFolder()==null)
			return false;
		else
			folderDraft=MailConnection.getDraftFolder();
		HtmlEmail email = new HtmlEmail();
		email.setCharset("UTF-8");
		email.setHostName("smtp.163.com");
		email.setSmtpPort(25);
		email.addTo(mail.getReceivers());
		email.setSubject(mail.getSubject());
		email.setFrom(mail.getSender());
		email.setHtmlMsg(mail.getContent());
		email.setMailSession(session);
		email.buildMimeMessage();
		MimeMessage message[]=new MimeMessage[1];
		message[0]=email.getMimeMessage();
		folderDraft.appendMessages(message);
		MailConnection.closeDraftFolder();
		MailConnection.closeConnection();
		//message.setFlag(Flags.Flag.DRAFT, true);
        return true;
	}

}
