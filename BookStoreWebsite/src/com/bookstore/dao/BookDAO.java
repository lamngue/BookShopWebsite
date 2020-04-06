package com.bookstore.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.bookstore.entity.Book;

public class BookDAO extends JpaDAO<Book> implements GenericDAO<Book> {
	public BookDAO() {
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public Book update(Book book) {
		// TODO Auto-generated method stub
		book.setLastUpdateTime(new Date());
		return super.update(book);
	}


	@Override
	public Book create(Book book) {
		// TODO Auto-generated method stub
		book.setLastUpdateTime(new Date());
		return super.create(book);
	}


	@Override
	public void delete(Class<Book> type, Object id) {
		// TODO Auto-generated method stub
		super.delete(type, id);
	}


	@Override
	public Book get(Object bookId) {
		// TODO Auto-generated method stub
		return super.find(Book.class, bookId);
	}

	@Override
	public void delete(Object bookId) {
		// TODO Auto-generated method stub
		super.delete(Book.class, bookId);
	}

	@Override
	public List<Book> listAll() {
		// TODO Auto-generated method stub
		return super.findWithNamedQuery("Book.findAll");
	}
	
	public Book findByTitle(String title) {
		List<Book> res = super.findWithNamedQuery("Book.findByTitle", "title", title);
		if(!res.isEmpty()) {
			return res.get(0);
		}
		return null;
	}
	
	public List<Book> listByCategory(int categoryId){
		List<Book> bookByCategory = super.findWithNamedQuery("Book.findByCategory", "catId", categoryId);
		return bookByCategory;
	}
	
	public List<Book> listNewBooks(){
		return super.findWithNamedQuery("Book.listNew", 0, 4);
	}
	
	public List<Book> search(String keyword){
		return super.findWithNamedQuery("Book.search", "keyword", keyword);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return super.countWithNamedQuery("Book.countAll");
	}
	
	public long countByCategory(int categoryId) {
		return super.countWithNamedQuery("Book.countByCategory", "catId", categoryId);
	}
}
