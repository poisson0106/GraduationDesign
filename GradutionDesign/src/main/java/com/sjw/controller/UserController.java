package com.sjw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sjw.pojo.User;
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
	
	@RequestMapping(value="initialUserRegister",method=RequestMethod.GET)
	public String initalUserRegister(HttpServletRequest request,HttpServletResponse response){
		return "login/register";
	}

	@RequestMapping(value="registerOneUser",method=RequestMethod.POST)
	public String registerOneUser(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String question=request.getParameter("question");
		String answer=request.getParameter("answer");
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setQuestion(question);
		user.setAnswer(answer);
		//need add this user to the database through the server
		Boolean flag=userService.registerOneUserService(user);
		session.setAttribute("username", username+"@usstemail.com");
		session.setAttribute("password", password);
		if(flag)
			return "base.definition";
		else
			return "message/error";
	}
}
