package com.sjw.daoImpl;

import javax.mail.Folder;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sjw.dao.UserDao;
import com.sjw.pojo.User;
import com.sjw.utils.MailConnection;
import com.sjw.utils.TelnetUtil;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

	@Override
	public String LoginOneUserDao(String username) {
		return this.getSqlSession().selectOne("loginOneUser", username);
	}

	@Override
	public Boolean registerOneUserDao(User user) throws Exception {
		//添加用户
		TelnetUtil telnet=new TelnetUtil("127.0.0.1","root","root");
		telnet.sendCommand("adduser "+user.getUsername()+" "+user.getPassword());
		telnet.disconnect();
		//添加验证问题与答案
		this.getSqlSession().update("registerOneUser", user);
		//添加文件夹
		MailConnection.getConnection(user.getUsername()+"@usstemail.com", user.getPassword());
		IMAPStore store=MailConnection.getStore();
		Folder folder=(IMAPFolder)store.getFolder("DRAFT");
		if(!folder.exists())
			folder.create(Folder.HOLDS_MESSAGES);
		folder=(IMAPFolder)store.getFolder("DELETED");
		if(!folder.exists())
			folder.create(Folder.HOLDS_MESSAGES);
		folder=(IMAPFolder)store.getFolder("SENT");
		if(!folder.exists())
			folder.create(Folder.HOLDS_MESSAGES);
		MailConnection.closeConnection();
		return true;
	}

	@Override
	public User checkUsernameRepeatDao(String username) throws Exception {
		return this.getSqlSession().selectOne("checkUsernameRepeat", username);
	}

	@Override
	public User getPwdQuestionDao(String username) throws Exception {
		return this.getSqlSession().selectOne("getPwdQuestion", username);
	}

	@Override
	public Boolean findOnePasswordDao(User user) throws Exception {
		String answer=this.getSqlSession().selectOne("getPwdAnswer", user);
		if(answer.equals(user.getAnswer())){
			this.getSqlSession().update("changePwd", user);
			return true;
		}
		else
			return false;
	}

	@Override
	public String getNicknameDao(String username) throws Exception {
		return this.getSqlSession().selectOne("getNickname",username);
	}

	@Override
	public User initialUserInfoChangeDao(String username) throws Exception {
		return this.getSqlSession().selectOne("getWholeUserInfo", username);
	}

	@Override
	public Boolean changeUserInfoDao(User user) throws Exception {
		Boolean flag=false;
		if(user.getPassword()==""||user.getPassword()==null){
			this.getSqlSession().update("updateUserWithoutPwd",user);
			flag=true;
		}
		else{
			this.getSqlSession().update("updateUserWithPwd",user);
			flag=true;
		}
		return flag;
	}
}
