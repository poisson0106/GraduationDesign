package com.sjw.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.sjw.pojo.Mail;

public interface ToolBarDao {
	public String setMailSeenDao(String[] messagenum,HttpSession session) throws Exception;
	
	public String setMailUnSeenDao(String[] messagenum,HttpSession session) throws Exception;
	
	public List<Mail> listOnePageEmail(int end,int pagenum,String frompage,HttpSession session) throws Exception;
	
	public int getOnePageEmailCountDao(String frompage,HttpSession session) throws Exception ;
}
