package com.sjw.dao;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sjw.pojo.Mail;

public interface MailReceiveDao {
	public List<Mail> initialMailReceiveDao(HttpSession session) throws Exception;
	
	public int getTotalMailCountDao(HttpSession session) throws Exception;
	
}
