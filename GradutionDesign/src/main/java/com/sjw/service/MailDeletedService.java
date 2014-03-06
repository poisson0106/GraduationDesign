package com.sjw.service;

import java.util.List;

import com.sjw.pojo.Mail;

public interface MailDeletedService {
	public int getTotalMailDeletedCountService() throws Exception;
	
	public List<Mail> initialMailDeletedService() throws Exception;
	
	public String deleteMailPavemently(String[] messagenum) throws Exception;
}
