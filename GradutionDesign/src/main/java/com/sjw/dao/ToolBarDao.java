package com.sjw.dao;

public interface ToolBarDao {
	public String setMailSeenDao(String[] messagenum) throws Exception;
	
	public String setMailUnSeenDao(String[] messagenum) throws Exception;
}
