package com.sjw.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjw.dao.ToolBarDao;
import com.sjw.pojo.Mail;
import com.sjw.service.ToolBarService;

@Service
public class ToolBarServiceImpl implements ToolBarService {
	@Autowired
	ToolBarDao toolBarDao;
	
	@Override
	public String setMailSeenService(String[] messagenum,HttpSession session,String from) throws Exception {
		return toolBarDao.setMailSeenDao(messagenum,session,from);
	}

	@Override
	public String setMailUnSeenService(String[] messagenum,HttpSession session,String from) throws Exception {
		return toolBarDao.setMailUnSeenDao(messagenum,session,from);
	}
	
	@Override
	public List<Mail> listOnePageEmailService(int end,int pagenum,String frompage,HttpSession session) throws Exception {
		return toolBarDao.listOnePageEmail(end,pagenum,frompage,session);
	}

	@Override
	public int getOnePageEmailCountService(String frompage,HttpSession session) throws Exception {
		return toolBarDao.getOnePageEmailCountDao(frompage,session);
	}
}
