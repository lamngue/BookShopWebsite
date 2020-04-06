package com.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookServices {
	
	private BookDAO bookDAO;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private CategoryDAO categoryDAO;
	private CommonUtility commonUtility;
	
	public BookServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		bookDAO = new BookDAO();
		commonUtility = new CommonUtility(request, response);
		categoryDAO = new CategoryDAO();
	}
	
	public void listBooks() throws ServletException, IOException {
		listBooks(null);
	}

	public void listBooks(String message) throws ServletException, IOException {
		List<Book> books = bookDAO.listAll();
		request.setAttribute("books", books);
		if(message != null) {
			request.setAttribute("message", message);
		}
		
		String listPage = "book_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
	}
	
	public void showBookNewForm() throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Category> categories = categoryDAO.listAll();
		request.setAttribute("categories", categories);
		
		String newPage = "book_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(newPage);
		requestDispatcher.forward(request, response);
	}

	public void createBook() throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		String title = request.getParameter("title");
		
		Book existBook = bookDAO.findByTitle(title);
		if(existBook != null) {
			String message = "Could not create new book because the title " + title + " already exists!";
			request.setAttribute("message", message);
			listBooks(message);
			return;
		}
		
		Book book = new Book();
		readBookFields(book);
		Book createdBook = bookDAO.create(book);
		
		
		if(createdBook.getBookId() > 0) {
			String message = "New book created successfully";
			request.setAttribute("message", message);
			listBooks(message);
		}
	}
	
	//update book info
	public void readBookFields(Book book) throws IOException, ServletException {
		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String description = request.getParameter("description");
		String isbn = request.getParameter("isbn");
		float price = Float.parseFloat(request.getParameter("price"));
		
		DateFormat dateFormat = new SimpleDateFormat("MM/DD/YYYY");
		Date publishDate = null;
		try {
			publishDate = dateFormat.parse(request.getParameter("publishDate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServletException("Error parsing publish date");
		}
		
		book.setTitle(title);
		book.setAuthor(author);
		book.setDescription(description);
		book.setIsbn(isbn);
		book.setPublishDate(publishDate);
		Category category = categoryDAO.get(categoryId);
		book.setCategory(category);
		
		book.setPrice(price);
		
		Part part = request.getPart("bookImage");
		if(part != null && part.getSize() > 0) {
			long size = part.getSize();
			byte[] data = new byte[(int) size];
			
			InputStream inputStream = part.getInputStream();
			inputStream.read(data);
			inputStream.close();
			
			book.setImage(data);
		}
	}

	public void editBook() throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer bookId = Integer.parseInt(request.getParameter("id"));
		
		Book editBook = bookDAO.get(bookId);
		if(editBook == null) {
			commonUtility.showMessageBackend("Could not find the book with id " + bookId);
		}
		List<Category> categories = categoryDAO.listAll();
		
		request.setAttribute("categories", categories);
		request.setAttribute("book", editBook);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("book_form.jsp");
		requestDispatcher.forward(request, response);
		
	}

	public void updateBook() throws IOException, ServletException {
		// TODO Auto-generated method stub
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		String title = request.getParameter("title");
		
		Book bookByTitle = bookDAO.findByTitle(title);
		Book existBook = bookDAO.get(bookId);
		
		//if title already exist in another book
		if(bookByTitle != null & !existBook.equals(bookByTitle)) {
			String message = "Could not update book because there's another book having same title";
			listBooks(message);
			return;
		}
		
		readBookFields(existBook);
		
		bookDAO.update(existBook);
		
		String message = "The book has been updated successfully";
		listBooks(message);
	}

	public void deleteBook() throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer bookId = Integer.parseInt(request.getParameter("id"));
		Book deletedBook = bookDAO.get(bookId);
		if(deletedBook == null) {
			commonUtility.showMessageBackend("Could not find the book with id " + bookId + " it might have been deleted.");
			return;
		}
		
		bookDAO.delete(bookId);
		String message = "The book has been deleted successfully";
		listBooks(message);
	}

	public void listBooksByCategory() throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer categoryId = Integer.parseInt(request.getParameter("id"));
		String catName = request.getParameter("name");
		Category catbyId = categoryDAO.get(categoryId);
		
		if(catbyId == null) {
			commonUtility.showMessageFrontend("Could not find the category " + catName);
			return;
		}
		
		List<Book> booksByCategory = bookDAO.listByCategory(categoryId);
		
		request.setAttribute("category", catbyId);
		request.setAttribute("listBooks", booksByCategory);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("frontend/books_list_by_category.jsp");
		requestDispatcher.forward(request, response);
	}

	public void viewBookDetail() throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer bookId = Integer.parseInt(request.getParameter("id"));
		String bookName = request.getParameter("name");
		Book book = bookDAO.get(bookId);
		if(book == null) {
			commonUtility.showMessageFrontend("Could not find the book " + bookName);
			return;
		}
		request.setAttribute("book", book);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("frontend/book_detail.jsp");
		requestDispatcher.forward(request, response);
	}

	public void search() throws ServletException, IOException {
		// TODO Auto-generated method stub
		String keyword = request.getParameter("keyword");
		List<Book> result = null;
		if(keyword.equals("")) {
			result = bookDAO.listAll();
		}else {
			result = bookDAO.search(keyword);
		}
		request.setAttribute("keyword", keyword);
		request.setAttribute("result", result);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("frontend/search_result.jsp");
		requestDispatcher.forward(request, response);
	}
}
