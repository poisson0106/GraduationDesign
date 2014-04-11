package com.sjw.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjw.dao.ContactDao;
import com.sjw.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	ContactDao contactDao;

	@Override
	public List<Map> initialContactService(String username) throws Exception {
		return contactDao.initialContactDao(username);
	}

	@Override
	public void deleteSelectedContactService(String nickname) throws Exception {
		this.contactDao.deleteSelectedContactDao(nickname);
	}

}
