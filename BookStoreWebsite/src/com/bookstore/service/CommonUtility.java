package com.bookstore.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonUtility {
	
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CommonUtility(HttpServletRequest request, HttpServletResponse response) {
		super();
		// TODO Auto-generated constructor stub
		this.request = request;
		this.response = response;
	}

	public void forwardToPage(String page) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
		requestDispatcher.forward(request, response);
	}
	
	public void showMessageFrontend(String message) throws ServletException, IOException {
		request.setAttribute("message", message);
		forwardToPage("message.jsp");
	}
	
	public void showMessageBackend(String message) throws ServletException, IOException {
		request.setAttribute("message", message);
		forwardToPage("messageAdmin.jsp");
	}
}


