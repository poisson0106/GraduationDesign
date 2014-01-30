package com.sjw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sjw.service.MailDraftService;

@Controller
public class MailDraftController {
	@Autowired
	MailDraftService mailDraftService;
	
	@RequestMapping(value="initialDraftBox",method=RequestMethod.GET)
	public String InitialDraftBox(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return "maildraft.definition";
	}
}
