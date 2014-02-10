package com.sjw.dao;

import java.util.List;

import com.sjw.pojo.Mail;

public interface MailDraftDao {
	public int getMailDraftCountDao() throws Exception;
	
	public List<Mail> initialMailDraftDao() throws Exception;
}
