package com.sjw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sjw.pojo.Mail;
import com.sjw.service.MailSendService;

@Controller
public class MailSendController {
	
	@Autowired
	MailSendService mailSendService;
	
	@RequestMapping(value="initialMailSend",method=RequestMethod.GET)
	public String InitialMailSend(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return "mailsend.definition";
	}
	
	@RequestMapping(value="sendOneEmail",method=RequestMethod.POST)
	public String SendOneEmail(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String receiver=request.getParameter("receiver");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String sender=request.getParameter("sender");
		String[] filenamelist=request.getParameter("filenamelist").split(",");
		String[] cclist=null;
		if(request.getParameter("cc")!=null&&request.getParameter("cc")==""){
			if(request.getParameter("cc").contains(","))
				cclist=request.getParameter("cc").split(",");
			else
				cclist[0]=request.getParameter("cc");
		}
		Boolean issend=false;
		Mail mail=new Mail();
		mail.setReceivers(receiver);
		if(cclist!=null)
			mail.setCc(cclist);
		mail.setSender(sender);
		mail.setSubject(subject);
		mail.setContent(content);
		if(!"".equals(filenamelist[0]))
			mail.setAttachnames(filenamelist);
		issend=mailSendService.SendOneEmailService(mail,request,request.getSession());
		if(issend)
			return "sendsuccess.definition";
		else
			return "senderror.definition";
	}
	
	@RequestMapping(value="uploadAttachment",method=RequestMethod.POST)
	public String UploadAttachment(HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(mailSendService.uploadAttachmentService(request,request.getSession()))
			return null;
		else
			return "senderror.definition";
	}
	
	@RequestMapping(value="saveOneEmail",method=RequestMethod.POST)
	public String SaveOneEmail(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Mail mail=new Mail();
		String receiver=request.getParameter("receiver");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String sender=request.getParameter("sender");
		String[] filenamelist=request.getParameter("filenamelist").split(",");
		Boolean issaved=false;
		mail.setReceivers(receiver);
		mail.setSender(sender);
		mail.setSubject(subject);
		mail.setContent(content);
		if(filenamelist!=null)
			mail.setAttachnames(filenamelist);
		issaved=mailSendService.saveOneEmailService(mail,request.getSession());
		if(issaved)
			return "sendsuccess.definition";
		else
			return "senderror.definition";
		
	}
	
	@RequestMapping(value="findReceivers",method=RequestMethod.POST)
	public String findReceivers(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String words=request.getParameter("words");
		String username=request.getParameter("username");
		username=username.substring(0, username.indexOf("@"));
		if(words.contains("@"))
			words=words.substring(0, words.indexOf("@"));
		if("".equals(words))
			words=words.toLowerCase();
		Map<String,String> keywords=new HashMap<String,String>();
		keywords.put("words", words);
		keywords.put("username", username);
		List<Map> receivers=mailSendService.findReceiversService(keywords);
		String json_max="";
		if(!receivers.isEmpty()){
			JSONArray ja_max=JSONArray.fromObject(receivers);
			json_max=ja_max.toString();
		}
		response.getWriter().write(json_max);
		return null;
	}
	
	@RequestMapping(value="saveDraftAuto",method=RequestMethod.POST)
	public String saveDraftAuto(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String receiver=request.getParameter("receiver");
		String[] cc=request.getParameter("cc").split(",");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String sender=request.getParameter("sender");
		String[] filenamelist=request.getParameter("filenamelist").split(",");
		int messagenum=Integer.parseInt(request.getParameter("messagenum"));
		Mail mail=new Mail();
		mail.setReceivers(receiver);
		mail.setSubject(subject);
		mail.setContent(content);
		mail.setSender(sender);
		mail.setMessagenum(messagenum);
		if(cc!=null)
			mail.setCc(cc);
		if(filenamelist!=null)
			mail.setAttachnames(filenamelist);
		Boolean issaved=mailSendService.saveDraftAutoService(mail,request.getSession());
		if(issaved){
			Map<String,String> map=new HashMap<String,String>();
			map.put("flag", "true");
			String json_max="";
			JSONArray ja_max=JSONArray.fromObject(map);
			json_max=ja_max.toString();
			response.getWriter().write(json_max);	
		}
		return null;
	}
}
