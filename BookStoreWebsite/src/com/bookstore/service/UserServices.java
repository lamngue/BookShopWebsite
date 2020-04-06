package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

public class UserServices {
	private UserDAO userDAO;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private CommonUtility commonUtility;

	public UserServices(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated constructor stub
		this.request = request;
		this.response = response;
		userDAO = new UserDAO();
		commonUtility = new CommonUtility(request, response);
	}
	
	public void listUser() throws ServletException, IOException {
		listUser(null);
	}
	
	public void listUser(String message) throws ServletException, IOException {
		List<Users> listUsers = userDAO.listAll();
		request.setAttribute("listUsers", listUsers);
		if(message != null) {
			request.setAttribute("message", message);
		}
		
		String listPage = "user_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}
	
	public void createUser() throws ServletException, IOException {
		String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		Users foundUser = userDAO.findByEmail(email);
		if(foundUser != null) {
			commonUtility.showMessageBackend("Email " + email + " is already in use!");
		}
		else {
			Users user = new Users(email, fullname, password);
			userDAO.create(user);
			String message = "New user created successfully";
			listUser(message);
		}
	}

	public void editUser() throws ServletException, IOException {
		Integer userId = Integer.parseInt(request.getParameter("id"));
		// TODO Auto-generated method stub
		Users user = userDAO.get(userId);
		
		//if user is deleted by other admin
		if(user == null) {
			commonUtility.showMessageBackend("Could not find user with id " + userId);
		}
		else {
			request.setAttribute("user", user);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("user_form.jsp");
			requestDispatcher.forward(request, response);
		}	
	}

	public void updateUser() throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userId = Integer.parseInt(request.getParameter("userId"));
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullname");
		String password = request.getParameter("password");
		
		
		Users userById = userDAO.get(userId);
		
		if(password.equals("")) {
			password = userById.getPassword();
		}
		
		Users userByEmail = userDAO.findByEmail(email);
		
		
		//if there's another user already use the email
		if(userByEmail != null && userByEmail.getUserId() != userById.getUserId()) {
			commonUtility.showMessageBackend("Could not update user. Email " + email + " already exists");
		}
		else {
			Users user = new Users(userId, email, fullName, password);
			userDAO.update(user);
			String message1 = "User has been updated successfully";
			listUser(message1);
		}
	}

	public void deleteUser() throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userId = Integer.parseInt(request.getParameter("id"));
		if(userId == 1) {
			commonUtility.showMessageBackend("Could not delete the default user");
		}
		else {
			userDAO.delete(userId);
			listUser("User has been deleted successfully");
		}
	}
	
	public void login() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		boolean loginResult = userDAO.checkLogin(email, password);
		
		if(loginResult) {
			System.out.println("User is authenticated");
			request.getSession().setAttribute("useremail", email);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin");
			dispatcher.forward(request, response);
			
		}else {
			String message = "Login failed";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}
}
