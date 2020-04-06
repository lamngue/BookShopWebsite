package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Category;

public class CategoryDAO extends JpaDAO<Category> implements GenericDAO<Category> {

	public CategoryDAO() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public Category create(Category t) {
		// TODO Auto-generated method stub
		return super.create(t);
	}

	@Override
	public Category update(Category entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Category get(Object id) {
		// TODO Auto-generated method stub
		return super.find(Category.class, id);
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		super.delete(Category.class, id);
	}

	@Override
	public List<Category> listAll() {
		// TODO Auto-generated method stub
		return super.findWithNamedQuery("Category.findAll");
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return super.countWithNamedQuery("Category.countAll");
	}

	public Category findByName(String categoryName) {
		List<Category> listResult = super.findWithNamedQuery("Category.findByName", "name", categoryName);
		if(listResult != null && listResult.size() >= 1) {
			return listResult.get(0);
		}
		return null;
	}
}
