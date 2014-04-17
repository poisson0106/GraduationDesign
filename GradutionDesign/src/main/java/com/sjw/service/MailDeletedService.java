package com.sjw.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.sjw.pojo.Mail;

public interface MailDeletedService {
	public int getTotalMailDeletedCountService(HttpSession session) throws Exception;
	
	public List<Mail> initialMailDeletedService(HttpSession session) throws Exception;
	
	public String deleteMailPavementlyService(String[] messagenum,HttpSession session) throws Exception;
	
	public Boolean redoMailByBoxService(String[] messagenum,String to,HttpSession session) throws Exception;
}
