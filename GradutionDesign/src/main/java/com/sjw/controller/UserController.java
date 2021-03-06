package com.sjw.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sjw.pojo.User;
import com.sjw.service.UserService;
import com.sjw.utils.MD5Util;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="loginOneUser",method=RequestMethod.POST)
	public String LoginOneUser(HttpServletRequest request,HttpServletResponse response) throws Exception{
		/*String username=request.getParameter("username");
		if(request.getParameter("password").equals(loginService.LoginOneUserService(username))){
			mv.setViewName("home");
		}
		else{
			mv.setViewName("/login/login");
		}*/
		String username=request.getParameter("username");
		if(MD5Util.MD5(request.getParameter("password")).equals(userService.LoginOneUserService(username))){
			request.getSession().setAttribute("username", username+"@usstemail.com");
			request.getSession().setAttribute("password", request.getParameter("password"));
			request.getSession().setAttribute("nickname", userService.getNicknameService(username));
			return "base.definition";
		}
		else
			return "message/error";
		
	}
	
	@RequestMapping(value="initialUserRegister",method=RequestMethod.GET)
	public String initalUserRegister(HttpServletRequest request,HttpServletResponse response){
		return "registeruser.definition";
	}
	
	@RequestMapping(value="initialFindPassword",method=RequestMethod.GET)
	public String initialFindPassword(HttpServletRequest request,HttpServletResponse response){
		return "findpwd.definition";
	}
	
	@RequestMapping(value="initialLoginUser",method=RequestMethod.GET)
	public String initialLoginUser(HttpServletRequest request,HttpServletResponse response){
		return "login/login";
	}
	
	@RequestMapping(value="initialUserInfoChange",method=RequestMethod.GET)
	public String initialUserInfoChange(HttpServletRequest request,HttpServletResponse response) throws Exception{
		/*String username=request.getSession().getAttribute("username").toString();
		username=username.substring(0,username.indexOf("@"));
		User user=userService.initialUserInfoChangeService(username);
		request.setAttribute("question", user.getQuestion());
		request.setAttribute("answer", user.getAnswer());*/
		return "userinfo.definition";
	}
	
	@RequestMapping(value="registerOneUser",method=RequestMethod.POST)
	public String registerOneUser(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String question=request.getParameter("question");
		String answer=request.getParameter("answer");
		String nickname=request.getParameter("nickname");
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setQuestion(question);
		user.setAnswer(answer);
		user.setNickname(nickname);
		//need add this user to the database through the server
		Boolean flag=userService.registerOneUserService(user);
		session.setAttribute("username", username+"@usstemail.com");
		session.setAttribute("password", password);
		session.setAttribute("nickname", nickname);
		if(flag)
			return "base.definition";
		else
			return "message/error";
	}
	
	@RequestMapping(value="logoutOneUser",method=RequestMethod.GET)
	public String logoutOneUser(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.getSession().removeAttribute("username");
		request.getSession().removeAttribute("password");
		request.getSession().removeAttribute("nickname");
		return "login/login";
	}
	
	@RequestMapping(value="checkUsernameRepeat",method=RequestMethod.POST)
	public String checkUsernameRepeat(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String username=request.getParameter("username");
		User result=userService.checkUsernameRepeatService(username);
		String json_max="";
		if(result!=null){
			JSONArray ja_max=JSONArray.fromObject(result);
			json_max=ja_max.toString();
		}
		response.getWriter().write(json_max);
		return null;
	}
	
	@RequestMapping(value="getPwdQuestion",method=RequestMethod.POST)
	public String getPwdQuestion(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String username=request.getParameter("username");
		User result=userService.getPwdQuestionDaoService(username);
		String json_max="";
		if(result!=null){
			JSONArray ja_max=JSONArray.fromObject(result);
			json_max=ja_max.toString();
		}
		response.getWriter().write(json_max);
		return null;
	}
	
	@RequestMapping(value="findOnePassword",method=RequestMethod.POST)
	public String findOnePassword(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String answer=request.getParameter("answer");
		String password=request.getParameter("password");
		String username=request.getParameter("username");
		password=MD5Util.MD5(password);
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setAnswer(answer);
		Boolean flag=userService.findOnePasswordService(user);
		if(flag)
			return "message/pwdsuccess";
		else
			return "message/error";
	}
	
	@RequestMapping(value="checkPasswordOnChange",method=RequestMethod.POST)
	public String checkPasswordOnChange(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception{
		String opassword=request.getParameter("opassword");
		if(session.getAttribute("password").equals(opassword)){
			Map<String,String> map=new HashMap<String,String>();
			map.put("flag", "true");
			String json_max="";
			JSONArray ja_max=JSONArray.fromObject(map);
			json_max=ja_max.toString();
			response.getWriter().write(json_max);
		}
		return null;
		
	}
	
	@RequestMapping(value="changeUserInfo",method=RequestMethod.POST)
	public String changeUserInfo(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception{
		String nickname=request.getParameter("nickname");
		String question=request.getParameter("nquestion");
		String answer =request.getParameter("nanswer");
		String ischangepwd=request.getParameter("ischangepwd");
		String username=request.getSession().getAttribute("username").toString();
		username=username.substring(0,username.indexOf("@"));
		User user=new User();
		if("no".equals(ischangepwd)){
			if(question==null||question==""||answer==""||answer==null){
				user.setNickname(nickname);
				user.setUsername(username);
			}
			else{
				user.setNickname(nickname);
				user.setQuestion(question);
				user.setAnswer(answer);
				user.setUsername(username);
			}
		}
		else
		{
			user.setNickname(nickname);
			user.setQuestion(question);
			user.setAnswer(answer);
			String password=MD5Util.MD5(request.getParameter("npassword"));
			user.setPassword(password);
			user.setUsername(username);
		}
		Boolean isupdate=userService.changeUserInfoService(user);
		if(user.getPassword()!=null&&user.getPassword()!=""){
			session.removeAttribute("password");
			session.setAttribute("password",request.getParameter("npassword"));
		}
		session.removeAttribute("nickname");
		session.setAttribute("nickname", nickname);
		if(isupdate)
			return "infosuccess.definition";
		else
			return "infoerror.definition";
	}
}
