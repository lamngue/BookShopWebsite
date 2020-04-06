package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDAOTest{
	
	private static UserDAO userDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		userDAO = new UserDAO();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		userDAO.close();
	}

	@Test
	public void testCreateUsers() {
		Users user1 = new Users();
		user1.setEmail("john3@gmail.com");
		user1.setFullName("John Smith 3");
		user1.setPassword("johnny3");
		user1 = userDAO.create(user1);
		assertTrue(user1.getUserId() > 0);
	}

	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {
		Users user1 = new Users();
		user1 = userDAO.create(user1);
		assertTrue(user1.getUserId() > 0);
	}
	
	@Test
	public void testUpdateUsers() {
		Users user = new Users();
		user.setUserId(1);
		user.setEmail("lnguye16@gustavus.edu");
		user.setFullName("Lam Nguyen");
		user.setPassword("lamdeptrai");
		user = userDAO.update(user);
		String expected = "lamdeptrai";
		String actual = user.getPassword();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetUsersFound() {
		Integer id = 1;
		Users user = userDAO.get(id);
		if (user != null){
			System.out.println(user.getEmail());
		}
		assertNotNull(user);
	}
	
	@Test
	public void testGetUsersNotFound() {
		Integer userId = 99;
		Users user = userDAO.get(userId);
		assertNull(user);
	}
	
	@Test 
	public void testDeleteUser() {
		Integer userId = 8;
		userDAO.delete(userId);
		Users user = userDAO.get(userId);
		assertNull(user);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteNotExistUser() {
		Integer userId = 55;
		userDAO.delete(userId);
	}
	
	@Test
	public void testListAll() {
		List<Users> listUsers = userDAO.listAll();
		for(Users user: listUsers) {
			System.out.println(user.getEmail());
		}
		assertTrue(listUsers.size() > 0);
	}
	
	@Test
	public void testCount() {
		long count = userDAO.count();
		assertEquals(count, userDAO.listAll().size());
	}
	
	@Test
	public void testFindByEmail() {
		String email = "lamnemchua@gmail.com";
		Users user = userDAO.findByEmail(email);
		assertNotNull(user);
	}
	
	@Test
	public void testCheckLoginSuccess() {
		String email = "lnguye16@gustavus.edu";
		String password = "lamdeptrai";
		boolean loginResult = userDAO.checkLogin(email, password);
		assertTrue(loginResult);
	}
	
	@Test
	public void testCheckLoginFail() {
		String email = "jeafdsa@gustavus.edu";
		String password = "lamdeptrai";
		boolean loginResult = userDAO.checkLogin(email, password);
		assertFalse(loginResult);
	}

}
