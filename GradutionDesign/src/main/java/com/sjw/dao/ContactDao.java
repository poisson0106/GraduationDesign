package com.sjw.dao;

import java.util.List;
import java.util.Map;

public interface ContactDao {
	public List<Map> initialContactDao(String username) throws Exception;
	
	public void deleteSelectedContactDao(String nickname) throws Exception;
}
