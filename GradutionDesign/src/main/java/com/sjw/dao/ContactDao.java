package com.sjw.dao;

import java.util.List;
import java.util.Map;

public interface ContactDao {
	public List<Map> initialContactDao(String username) throws Exception;
	
	public void deleteSelectedContactDao(String nickname) throws Exception;
	
	public int getTotalContactByUserDao(String username) throws Exception;
	
	public List<Map> getOnePageContactDao(String username,int pagenum) throws Exception;
}
