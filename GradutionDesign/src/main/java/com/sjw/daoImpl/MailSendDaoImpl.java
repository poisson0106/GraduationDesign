package com.sjw.daoImpl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sjw.dao.MailSendDao;
import com.sjw.pojo.Mail;
import com.sjw.utils.MD5Util;
import com.sjw.utils.MailConnection;
import com.sjw.utils.MailContentAnalysis;
import com.sun.mail.imap.IMAPFolder;

public class MailSendDaoImpl extends SqlSessionDaoSupport implements MailSendDao {

	@Override
	public Boolean SendOneEmailDao(Mail mail,HttpServletRequest request,HttpSession session) throws Exception {
		//发送邮件部分
		String username=session.getAttribute("username").toString();
		username=username.substring(0, username.indexOf("@"));
		String password=session.getAttribute("password").toString();
		HtmlEmail email = new HtmlEmail();
		email.setHostName("127.0.0.1");
		email.setSmtpPort(25);
		email.setAuthenticator(new DefaultAuthenticator(username,password));
		email.setFrom(mail.getSender());
		email.setSubject(mail.getSubject());
		email.setCharset("UTF-8");
		email.setHtmlMsg(mail.getContent());
		email.addTo(mail.getReceivers());
		if(mail.getCc()!=null){
			for(int i=0;i<mail.getCc().length;i++){
				email.addCc(mail.getCc()[i]);
			}
		}
		String foldername=mail.getSender().substring(0,mail.getSender().indexOf("@"));
		String uploadDir=request.getSession().getServletContext().getRealPath("/")+File.separator+"tmp"+File.separator+foldername;
		if(mail.getAttachnames()!=null){
			for(int i=0;i<mail.getAttachnames().length;i++){
				EmailAttachment attr=new EmailAttachment();
				attr.setPath(uploadDir+File.separator+mail.getAttachnames()[i]);
				attr.setDescription(EmailAttachment.ATTACHMENT);
				attr.setName(MimeUtility.encodeText(mail.getAttachnames()[i]));
				email.attach(attr);
			}
		}
		email.send();
		
		//存入发件箱部分
		MailConnection.getConnection(username+"@usstemail.com", password);
		IMAPFolder folderSent;
		MailConnection.setSentFolder();
		if(MailConnection.getSentFolder()==null)
			return false;
		else
			folderSent=MailConnection.getSentFolder();
		email.buildMimeMessage();
		MimeMessage message[]=new MimeMessage[1];
		message[0]=email.getMimeMessage();
		folderSent.appendMessages(message);
		MailConnection.closeSentFolder();
		MailConnection.closeConnection();
		
		//检查联系人，没有就添加联系人,cc里的不判断
		Map<String,String> info=new HashMap<String,String>();
		String friendname=mail.getReceivers();
		friendname=friendname.substring(0,friendname.indexOf("@"));
		String nickname=null;
		
		info.put("username", username);
		info.put("friendname", friendname);
		String result=this.getSqlSession().selectOne("friendIsExist", info);
		if(result==null){
			nickname=this.getSqlSession().selectOne("getNickName", friendname);
			info.clear();
			info.put("username", username);
			info.put("nickname", nickname);
			this.getSqlSession().insert("addRelations", info);
		}
		
		//如果草稿箱不为空的话，删除草稿箱文件（拟考虑将此放在最后，待测试）
		if(mail.getMessagenum()!=0){
			MailConnection.getConnection(username+"@usstemail.com", password);
			IMAPFolder folderDraft;
			MailConnection.setDraftFolder();
			if(MailConnection.getDraftFolder()==null)
				return false;
			else 
				folderDraft=MailConnection.getDraftFolder();
			Message messagedraft=folderDraft.getMessage(mail.getMessagenum());
			messagedraft.setFlag(Flags.Flag.DELETED, true);
			MailConnection.closeDraftFolder();
			MailConnection.closeConnection();
		}
		return true;
	}

	@Override
	public Boolean uploadAttachmentDao(HttpServletRequest request,HttpSession session)
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
	public Boolean saveOneEmailDao(Mail mail,HttpSession httpsession) throws Exception {
		MailConnection.getConnection(httpsession.getAttribute("username").toString(),httpsession.getAttribute("password").toString());
		Session session=MailConnection.getSession();
		IMAPFolder folderDraft;
		MailConnection.setDraftFolder();
		if(MailConnection.getDraftFolder()==null)
			return false;
		else
			folderDraft=MailConnection.getDraftFolder();
		HtmlEmail email = new HtmlEmail();
		email.setCharset("UTF-8");
		email.setHostName("mail.usstemail.com");
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

	@Override
	public List<Map> findReceiversDao(Map keywords) throws Exception {
		return this.getSqlSession().selectList("findReceivers", keywords);
	}

	@Override
	public int saveDraftAutoDao(Mail mail,HttpSession session) throws Exception {
		int result=0;
		String username=session.getAttribute("username").toString();
		String password=session.getAttribute("password").toString();
		MailConnection.getConnection(username, password);
		Session msession=MailConnection.getSession();
		IMAPFolder folderDraft;
		MailConnection.setDraftFolder();
		if(MailConnection.getDraftFolder()==null)
			return -1;
		else
			folderDraft=MailConnection.getDraftFolder();
		if(mail.getMessagenum()!=0){
			Message message=folderDraft.getMessage(mail.getMessagenum());
			message.setFlag(Flags.Flag.DELETED, true);
			MailConnection.closeDraftFolder();
			MailConnection.setDraftFolder();
			if(MailConnection.getDraftFolder()==null)
				return -1;
			else
				folderDraft=MailConnection.getDraftFolder();
		}
		HtmlEmail email = new HtmlEmail();
		email.setCharset("UTF-8");
		email.setHostName("mail.usstemail.com");
		email.setSmtpPort(25);
		email.addTo(mail.getReceivers());
		email.setSubject(mail.getSubject());
		email.setFrom(mail.getSender());
		if(mail.getCc()!=null){
			for(int i=0;i<mail.getCc().length;i++){
				email.addCc(mail.getCc()[i]);
			}
		}
		email.setHtmlMsg(" ");
		email.setHtmlMsg(mail.getContent());
		email.setMailSession(msession);
		email.buildMimeMessage();
		MimeMessage message[]=new MimeMessage[1];
		message[0]=email.getMimeMessage();
		folderDraft.appendMessages(message);
		Message getmessage=folderDraft.getMessage(folderDraft.getMessageCount());
		result=getmessage.getMessageNumber();
		MailConnection.closeDraftFolder();
		MailConnection.closeConnection();
		return result;
	}

}
