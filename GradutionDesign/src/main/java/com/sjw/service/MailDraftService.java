package com.sjw.service;

import java.util.List;

import com.sjw.pojo.Mail;

public interface MailDraftService {
	public int getTotalMailDraftCountService() throws Exception;
	
	public List<Mail> initialMailDraftService() throws Exception;

}
