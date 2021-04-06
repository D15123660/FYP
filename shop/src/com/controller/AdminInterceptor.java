package com.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Background login verification interceptor
 */
public class AdminInterceptor extends HandlerInterceptorAdapter{

	/**
	 * Check login status
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		if(uri.contains("css/") || uri.contains("js/") || uri.contains("img/") 
				|| uri.contains("login") || uri.contains("logout")) {
			return true; // Do not intercept the path
		}
		Object username = request.getSession().getAttribute("username");
		if (Objects.nonNull(username) && !username.toString().trim().isEmpty()) {
			return true; // Login verification passed
		}
		response.sendRedirect("login.jsp");
		return false; // All other cases will be intercepted
	}

}
