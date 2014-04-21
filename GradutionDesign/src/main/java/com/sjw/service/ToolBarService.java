package com.sjw.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.sjw.pojo.Mail;

public interface ToolBarService {
	public String setMailSeenService(String[] messagenum,HttpSession session,String from) throws Exception;
	
	public String setMailUnSeenService(String[] messagenum,HttpSession session,String from) throws Exception;
	
	public List<Mail> listOnePageEmailService(int end,int pagenum,String frompage,HttpSession session) throws Exception;
	
	public int getOnePageEmailCountService(String frompage,HttpSession session) throws Exception;
}
