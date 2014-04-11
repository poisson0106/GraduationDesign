package com.sjw.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sjw.service.ContactService;

@Controller
public class ContactController {
	@Autowired
	ContactService contactService;
	
	@RequestMapping(value="initialContact",method=RequestMethod.GET)
	public String initialContact(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();
		String username=session.getAttribute("username").toString();
		username=username.substring(0,username.indexOf("@"));
		List<Map> result=contactService.initialContactService(username);
		int size=result.size();
		int page=size/10;
		if(page==0)
			page=1;
		else{
			if(size%10!=0)
				page++;
		}
		request.setAttribute("contacts", result);
		request.setAttribute("allpagenum", page);
		request.setAttribute("page", 1);
		return "contactmanage.definition";
	}
	
	@RequestMapping(value="deleteSelectedContact",method=RequestMethod.POST)
	public String deleteSelectedContact(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String nickname=request.getParameter("selected");
		nickname=nickname.substring(0, nickname.length()-1);
		contactService.deleteSelectedContactService(nickname);
		Thread.sleep(300);
		return null;
	}
}
