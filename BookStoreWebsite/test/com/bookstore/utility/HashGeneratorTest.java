package com.bookstore.utility;

import static org.junit.Assert.*;

import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HashGeneratorTest {
	
	private static HashGeneratorUtils hash;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
		hash = new HashGeneratorUtils();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		hash.close();
	}

	@Test
	public void hashGeneratorTest() throws HashGenerationException {
		String password = "lamdeptrai";
		String encrypted = hash.generateMD5(password);
		System.out.println(encrypted);
		assertNotEquals(password, encrypted);
	}
}
