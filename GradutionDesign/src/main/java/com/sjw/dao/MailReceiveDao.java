package com.sjw.dao;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.sjw.pojo.Mail;

public interface MailReceiveDao {
	public List<Mail> initialMailReceiveDao() throws Exception;
	
	public int getTotalMailCountDao() throws Exception;
	
	public List<Mail> listOnePageEmail(int end,int pagenum) throws Exception;
	
	public String deleteSelectedEmailDao(String[] messagenum) throws Exception;
}
