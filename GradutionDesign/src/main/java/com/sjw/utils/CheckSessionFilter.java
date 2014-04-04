package com.sjw.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class CheckSessionFilter
 */
public class CheckSessionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CheckSessionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hrequest=(HttpServletRequest) request;
		HttpServletResponse hresponse=(HttpServletResponse) response;
		HttpSession session=hrequest.getSession();
		String requestPath = hrequest.getServletPath();
		String username="";
		if(session.getAttribute("username")!=null)
			username=session.getAttribute("username").toString();
		if("".equals(username)||username==null){
			//if(!(requestPath.indexOf("initialUserRegister")>=0)||!(requestPath.indexOf("loginOneUser")>=0)||!(requestPath.indexOf("initialFindPassword")>=0)||!(requestPath.indexOf("registerOneUser")>=0)||!(requestPath.indexOf("getPwdQuestion")>=0)||!(requestPath.indexOf("findOnePassword")>=0)||!(requestPath.indexOf("initialLoginUser")>=0)){
			if(requestPath.indexOf("initialLoginUser")<0&&requestPath.indexOf("loginOneUser")<0&&requestPath.indexOf("initialUserRegister")<0&&requestPath.indexOf("initialFindPassword")<0&&requestPath.indexOf("registerOneUser")<0&&requestPath.indexOf("getPwdQuestion")<0&&requestPath.indexOf("findOnePassword")<0){
				/*PrintWriter out=response.getWriter();
				out.println("<html>");  
			    out.println("<script>");  
			    out.println("window.location.href='initialLoginUser'");  
			    out.println("</script>");  
			    out.println("</html>");*/
				hresponse.sendRedirect("initialLoginUser");
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
