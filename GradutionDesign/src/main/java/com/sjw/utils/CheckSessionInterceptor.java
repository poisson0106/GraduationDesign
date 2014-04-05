package com.sjw.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CheckSessionInterceptor implements HandlerInterceptor{
	
	private List<String> excludedUrls;  

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String requestUri = request.getRequestURI();
	    for (String url : excludedUrls) {  
	      if (requestUri.endsWith(url)) {  
	        return true;  
	      }  
	    }  
	   
	    // intercept  
	    HttpSession session = request.getSession();  
	    if (session.getAttribute("username") == null) {  
	      // see http://stackoverflow.com/questions/12713873/spring-3-1-how-do-you-send-all-exception-to-one-page  
	      throw new SessionOutException();  
	    } else {  
	      return true;  
	    }  
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void setExcludedUrls(List<String> excludedUrls) {  
	    this.excludedUrls = excludedUrls;  
	}  

}
