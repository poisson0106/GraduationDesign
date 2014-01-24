package com.sjw.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjw.dao.MailReceiveDao;
import com.sjw.pojo.Mail;
import com.sjw.service.MailReceiveService;

@Service
public class MailReceiveServiceImpl implements MailReceiveService {
	@Autowired
	private MailReceiveDao mailReceiveDao;
	
	@Override
	public List<Mail> initialMailReceiveService() throws Exception {
		return mailReceiveDao.initialMailReceiveDao();
	}

	@Override
	public int getTotalMailCountService() throws Exception {
		return mailReceiveDao.getTotalMailCountDao();
	}

	@Override
	public List<Mail> listOnePageEmailService(int end,int pagenum) throws Exception {
		return mailReceiveDao.listOnePageEmail(end,pagenum);
	}


	@Override
	public String deleteSelectedMailService(String[] messagenum) throws Exception {
		  return mailReceiveDao.deleteSelectedEmailDao(messagenum);
	}

}
