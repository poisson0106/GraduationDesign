package com.sjw.service;

import java.util.List;

import com.sjw.pojo.Mail;

public interface ToolBarService {
	public String setMailSeenService(String[] messagenum) throws Exception;
	
	public String setMailUnSeenService(String[] messagenum) throws Exception;
	
	public List<Mail> listOnePageEmailService(int end,int pagenum,String frompage) throws Exception;
	
	public int getOnePageEmailCountService(String frompage) throws Exception;
}
