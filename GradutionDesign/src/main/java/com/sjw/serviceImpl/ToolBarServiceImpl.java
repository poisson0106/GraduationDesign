package com.sjw.serviceImpl;

import java.util.List;

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
	public String setMailSeenService(String[] messagenum) throws Exception {
		return toolBarDao.setMailSeenDao(messagenum);
	}

	@Override
	public String setMailUnSeenService(String[] messagenum) throws Exception {
		return toolBarDao.setMailUnSeenDao(messagenum);
	}
	
	@Override
	public List<Mail> listOnePageEmailService(int end,int pagenum,String frompage) throws Exception {
		return toolBarDao.listOnePageEmail(end,pagenum,frompage);
	}

	@Override
	public int getOnePageEmailCountService(String frompage) throws Exception {
		return toolBarDao.getOnePageEmailCountDao(frompage);
	}
}
