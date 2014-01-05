package com.sjw.daoImpl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sjw.dao.LoginDao;

public class LoginDaoImpl extends SqlSessionDaoSupport implements LoginDao {

	@Override
	public String LoginOneUserDao(String username) {
		return this.getSqlSession().selectOne("loginOneUser", username);
	}

}
