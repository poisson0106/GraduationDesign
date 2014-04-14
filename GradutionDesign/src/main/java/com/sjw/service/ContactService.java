package com.sjw.service;

import java.util.List;
import java.util.Map;

public interface ContactService {
	public List<Map> initialContactService(String username) throws Exception;
	
	public void deleteSelectedContactService(String nickname) throws Exception;
	
	public int getTotalContactByUserService(String username) throws Exception;
	
	public List<Map> getOnePageContactService(String username,int pagenum) throws Exception;
}
