package com.bookstore.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDAOTest{
	private static BookDAO bookDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bookDao = new BookDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		bookDao.close();
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteBookFailed() {
		Integer bookId = 100;
		bookDao.delete(bookId);
	}
	
	@Test
	public void testDeleteBookSuccess() {
		Integer bookId = 5;
		bookDao.delete(bookId);
		assertTrue(true);	
	}
	
	@Test
	public void testGetBookFail() {
		Integer bookId = 99;
		Book book = bookDao.get(bookId);
		assertNull(book);
	}
	
	@Test
	public void testGetBookSuccess() {
		Integer bookId = 2;
		Book book = bookDao.get(bookId);
		assertNotNull(book);
	}
	
	@Test
	public void testListAll() {
		List<Book> listBooks = bookDao.listAll();
		for(Book aBook : listBooks) {
			System.out.println(aBook.getTitle() + ", " + aBook.getAuthor());
		}
		
		assertTrue(listBooks.size() > 0);
	}
	
	@Test
	public void testFindByTitleNotExist() {
		String title = "Thinkin in Java";
		Book book = bookDao.findByTitle(title);
		assertNull(book);
	}
	
	@Test
	public void testFindByTitleSuccess() {
		String title = "Web Design For Dummies";
		Book book = bookDao.findByTitle(title);
		assertNotNull(book);
	}
	
	@Test 
	public void testCount() {
		long totalBooks = bookDao.count();
		assertEquals(4, totalBooks);
	}
	
	@Test
	public void testListNewBooks() {
		List<Book> listNewBooks = bookDao.listNewBooks();
		for(Book abook: listNewBooks) {
			System.out.println(abook.getTitle() + " - " + abook.getPublishDate());
		}
		assertTrue(listNewBooks.size() == 4);
	}
	
	@Test
	public void testListByCategory() {
		int catId = 18;
		List<Book> bookByCategory = bookDao.listByCategory(catId);
		System.out.println(bookByCategory.size());
		assertTrue(bookByCategory.size() > 0);
	}
	
	@Test
	public void testSearchBookTitle() {
		String keyword = "Java";
		List<Book> matchedBook = bookDao.search(keyword);
		for(Book abook : matchedBook) {
			System.out.println(abook.getTitle());
		}
		assertEquals(4, matchedBook.size());
	}
	
	@Test
	public void testSearchBookAuthor() {
		String keyword = "Kathy";
		List<Book> matchedBook = bookDao.search(keyword);
		for(Book abook : matchedBook) {
			System.out.println(abook.getTitle());
		}
		assertEquals(2, matchedBook.size());
	}
	
	@Test
	public void testSearchBookDescription() {
		String keyword = "Spring in Action";
		List<Book> matchedBook = bookDao.search(keyword);
		for(Book abook : matchedBook) {
			System.out.println(abook.getTitle());
		}
		assertEquals(1, matchedBook.size());
	}
	
	@Test
	public void testUpdateBook() throws ParseException, IOException {
		Book existingBook = new Book();
		existingBook.setBookId(6);
		Category category = new Category("Web Design");
		category.setCategoryId(12);
		existingBook.setCategory(category);
		existingBook.setTitle("Web Design For Dummies");
		existingBook.setAuthor("Raoul-Gabriel Urma, Mario Fusco, Alan Mycroft");
		existingBook.setDescription("Java 8 in Action is a clearly written guide to the new features of Java 8. The book covers lambdas, streams, and functional-style programming.");
		existingBook.setPrice(36.72f);
		existingBook.setIsbn("1617291994");
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("08/28/2014");
		existingBook.setPublishDate(publishDate);
		
		String imagePath = "D:\\dummy-data\\books\\Java 8 in Action.jpg";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		
		existingBook.setImage(imageBytes);

		Book updatedBook = bookDao.update(existingBook);
		assertEquals(updatedBook.getTitle(), "Web Design For Dummies");
	}

	@Test
	public void testCreateBook() throws ParseException, IOException {
		Book newBook = new Book();
		Category category = new Category("Java");
		category.setCategoryId(18);
		newBook.setCategory(category);
		newBook.setTitle("Java 8 in Action: Lambdas, Streams, and functional-style programming");
		newBook.setAuthor("Raoul-Gabriel Urma, Mario Fusco, Alan Mycroft");
		newBook.setDescription("Java 8 in Action is a clearly written guide to the new features of Java 8. The book covers lambdas, streams, and functional-style programming.");
		newBook.setPrice(36.72f);
		newBook.setIsbn("1617291994");
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("08/28/2014");
		newBook.setPublishDate(publishDate);
		
		String imagePath = "D:\\dummy-data\\books\\Java 8 in Action.jpg";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		
		newBook.setImage(imageBytes);

		Book createdBook = bookDao.create(newBook);
		assertTrue(createdBook.getBookId() > 0);
	}
	
	@Test
	public void testCountByCategory() {
		int catId = 18;
		long res = bookDao.countByCategory(catId);
		assertEquals(res, 6);
	}

}
