package com.sjw.utils;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		try {
			MailConnection.closeInboxFolder();
			MailConnection.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
