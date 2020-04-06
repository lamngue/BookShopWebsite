package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

public class CategoryDAOTest{

	private static CategoryDAO categoryDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		categoryDao = new CategoryDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		categoryDao.close();
	}

	@Test
	public void testCreateCategory() {
		Category newCat = new Category("Health");
		Category category = categoryDao.create(newCat);
		
		assertTrue(category != null && category.getCategoryId() > 0);
	}

	@Test
	public void testUpdateCategory() {
		Category cat = new Category("Java Core");
		int id = 11;
		cat.setCategoryId(id);
		Category category = categoryDao.update(cat);
		assertEquals(cat.getName(), category.getName());
	}

	@Test
	public void testGet() {
		Integer catId = 12;
		Category cat = categoryDao.get(catId);
		assertNotNull(cat);
	}

	@Test
	public void testDeleteCategory() {
		Integer catId = 23;
		categoryDao.delete(catId);
		assertNull(categoryDao.get(catId));
	}

	@Test
	public void testListAll() {
		List<Category> listCategory = categoryDao.listAll();
		listCategory.forEach(c -> System.out.println(c.getName()));
		assertTrue(listCategory.size() > 0);
	}

	@Test
	public void testCount() {
		long total = categoryDao.count();
		List<Category> listCategory = categoryDao.listAll();
		assertEquals(total, listCategory.size());
	}
	
	@Test
	public void testFindByName() {
		String name = "Java Core";
		Category cat = categoryDao.findByName(name);
		assertNotNull(cat);
	}
	
	@Test
	public void testFindByNameNotFound() {
		String name = "Java Core1";
		Category cat = categoryDao.findByName(name);
		assertNull(cat);
	}
}
