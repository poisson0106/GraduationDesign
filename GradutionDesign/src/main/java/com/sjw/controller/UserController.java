package com.sjw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sjw.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
		
	@RequestMapping(value="loginOneUser",method=RequestMethod.POST)
	public String LoginOneUser(HttpServletRequest request,HttpServletResponse response){
		/*String username=request.getParameter("username");
		if(request.getParameter("password").equals(loginService.LoginOneUserService(username))){
			mv.setViewName("home");
		}
		else{
			mv.setViewName("/login/login");
		}*/
		return "base.definition";
		
	}
}
