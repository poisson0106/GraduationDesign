package com.sjw.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjw.dao.ToolBarDao;
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
}
