package com.sjw.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sjw.pojo.Mail;

public interface MailReceiveService {
	public List<Mail> initialMailReceiveService(HttpSession session) throws Exception;
	
	public int getTotalMailCountService(HttpSession session) throws Exception;
	
	public String deleteSelectedMailService(String[] messagenum,HttpSession session) throws Exception;
	
}
