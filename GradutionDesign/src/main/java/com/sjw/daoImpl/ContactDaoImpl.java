package com.sjw.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sjw.dao.ContactDao;

public class ContactDaoImpl extends SqlSessionDaoSupport implements ContactDao {

	@Override
	public List<Map> initialContactDao(String username) throws Exception {
		return this.getSqlSession().selectList("getAllContact", username);
	}

	@Override
	public void deleteSelectedContactDao(String nickname) throws Exception {
		this.getSqlSession().delete("deleteSelectedContact", nickname);
		
	}

	@Override
	public int getTotalContactByUserDao(String username) throws Exception {
		return this.getSqlSession().selectOne("getTotalContactNum", username);
	}

	@Override
	public List<Map> getOnePageContactDao(String username,int pagenum) throws Exception {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("username", username);
		map.put("begin", (pagenum-1)*10);
		map.put("end", (pagenum-1)*10+9);
		return this.getSqlSession().selectList("getOnePageContact", map);
	}

}
