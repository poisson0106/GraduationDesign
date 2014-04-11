package com.sjw.daoImpl;

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

}
