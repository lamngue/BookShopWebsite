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

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Category;

public class CategoryServices {
	private CategoryDAO categoryDAO;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private CommonUtility commonUtility;

	public CategoryServices(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated constructor stub
		super();
		this.request = request;
		this.response = response;
		categoryDAO = new CategoryDAO();
		commonUtility = new CommonUtility(request, response);
	}
	
	public void listCategory() throws ServletException, IOException {
		listCategory(null);
	}
	
	public void listCategory(String message) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);
		if(message != null) {
			request.setAttribute("message", message);
		}
		String listPage = "category_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}
	
	public void createCategory() throws ServletException, IOException {
		String name = request.getParameter("name");
		Category cat = categoryDAO.findByName(name);
		if(cat != null) {
			commonUtility.showMessageBackend("Could not create category. " + "A category with name " + name + " already exists.");
		}
		else {
			Category newCategory = new Category(name);
			categoryDAO.create(newCategory);
			listCategory("New category created!");
		}
	}

	public void editCategory() throws ServletException, IOException {
		// TODO Auto-generated method stub
		int catID = Integer.parseInt(request.getParameter("id"));
		Category cat = categoryDAO.get(catID);
		if(cat == null) {
			commonUtility.showMessageBackend("Could not find category with id " + catID);
		}
		else {
	 		request.setAttribute("category", cat);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("category_form.jsp");
			requestDispatcher.forward(request, response);
		}	
	}

	public void updateCategory() throws ServletException, IOException {
		// TODO Auto-generated method stub
		int catId = Integer.parseInt(request.getParameter("categoryId"));
		String catName = request.getParameter("name");
		Category catById = categoryDAO.get(catId);
		Category catByName = categoryDAO.findByName(catName);
		
		//if the category by name is different that category by Id
		if(catByName != null && catById.getCategoryId() != catByName.getCategoryId()) {
			commonUtility.showMessageBackend("Could not update category." + "A category with name " + catName + " already exists");
		}
		else {
			catById.setName(catName);
			categoryDAO.update(catById);
			String message = "Category has been updated successfully!";
			listCategory(message);
		}
	}

	public void deleteCategory() throws ServletException, IOException {
		// TODO Auto-generated method stub
		int catId = Integer.parseInt(request.getParameter("id"));
		Category catById = categoryDAO.get(catId);
		BookDAO bookDAO = new BookDAO();
		long numberOfBooks = bookDAO.countByCategory(catId);
		String message; 
		if(catById == null) {
			commonUtility.showMessageBackend("Couldn't find category " + catId + " it might have been deleted!");
		}
		else {
			if(numberOfBooks > 0) {
				message = "Could not delete the category (ID: %d) because it contains %d books.";
				message = String.format(message, catId, numberOfBooks);
				listCategory(message);
			}
			else {
				categoryDAO.delete(catId);
				listCategory("Category with Id " + catId + " has been deleted successfully");
			}
		}
	}
	 
}
