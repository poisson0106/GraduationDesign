package com.sjw.daoImpl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sjw.dao.UserDao;
import com.sjw.pojo.User;
import com.sjw.utils.TelnetUtil;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

	@Override
	public String LoginOneUserDao(String username) {
		return this.getSqlSession().selectOne("loginOneUser", username);
	}

	@Override
	public Boolean registerOneUserDao(User user) {
		TelnetUtil telnet=new TelnetUtil("127.0.0.1","root","root");
		telnet.sendCommand("adduser "+user.getUsername()+" "+user.getPassword());
		telnet.disconnect();
		this.getSqlSession().update("registerOneUser", user);
		return true;
	}

}
