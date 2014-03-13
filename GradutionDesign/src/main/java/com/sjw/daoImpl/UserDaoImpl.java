package com.sjw.daoImpl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sjw.dao.UserDao;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

	@Override
	public String LoginOneUserDao(String username) {
		return this.getSqlSession().selectOne("loginOneUser", username);
	}

}
