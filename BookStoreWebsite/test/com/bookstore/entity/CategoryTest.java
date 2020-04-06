package com.bookstore.entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bookstore.entity.Users;

public class CategoryTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Category newCat = new Category("Advanced Java");
	
		
		EntityManagerFactory createEntityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager entityManager = createEntityManagerFactory.createEntityManager();
		
		//begin transaction
		entityManager.getTransaction().begin();
		entityManager.persist(newCat);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		createEntityManagerFactory.close();
		
		System.out.println("An category object was persisted");
	}

}
