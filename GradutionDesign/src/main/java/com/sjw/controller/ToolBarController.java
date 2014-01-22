package com.sjw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sjw.service.ToolBarService;

@Controller
public class ToolBarController {
	@Autowired
	ToolBarService toolBarService;
	
	@RequestMapping(value="setMailSeen",method=RequestMethod.POST)
	public String SetMailSeen(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String[] messagenum=request.getParameter("selected").split(",");
		String result=toolBarService.setMailSeenService(messagenum);
		if(result=="success")
			return null;
		else
			return "message/error";
	}
	
	@RequestMapping(value="setMailUnSeen",method=RequestMethod.POST)
	public String SetMailUnSeen(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String[] messagenum=request.getParameter("selected").split(",");
		String result=toolBarService.setMailUnSeenService(messagenum);
		if(result=="success")
			return null;
		else
			return "message/error";
	}
}
