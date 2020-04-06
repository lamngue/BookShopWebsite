package com.bookstore.entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bookstore.entity.Users;

public class UsersTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Users user1 = new Users();
		user1.setEmail("tunglam1125@yahoo.com");
		user1.setFullName("Tung Lam");
		user1.setPassword("191997");
		
		EntityManagerFactory createEntityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager entityManager = createEntityManagerFactory.createEntityManager();
		
		//begin transaction
		entityManager.getTransaction().begin();
		entityManager.persist(user1);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		createEntityManagerFactory.close();
		
		System.out.println("An users object was persisted");
	}

}
