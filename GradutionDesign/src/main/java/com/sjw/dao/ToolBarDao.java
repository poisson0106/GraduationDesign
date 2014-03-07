package com.sjw.dao;

import java.util.List;

import com.sjw.pojo.Mail;

public interface ToolBarDao {
	public String setMailSeenDao(String[] messagenum) throws Exception;
	
	public String setMailUnSeenDao(String[] messagenum) throws Exception;
	
	public List<Mail> listOnePageEmail(int end,int pagenum,String frompage) throws Exception;
	
	public int getOnePageEmailCountDao(String frompage) throws Exception ;
}
