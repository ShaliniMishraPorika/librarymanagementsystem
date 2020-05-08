package com.capgemini.librarymanagementsystemhibernate;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;
import com.capgemini.librarymanagementsystemhibernate.service.StudentService;
import com.capgemini.librarymanagementsystemhibernate.service.StudentServiceImp;

public class StudentServiceTest {
	private StudentService dao = new StudentServiceImp();

	@Test
	public void testSearchBookById() {
		BookBean bean = dao.searchBookById(100001);
		Assertions.assertNotNull(bean);
	}

	@Test
	public void testSearchBookById1() {
		BookBean bean = dao.searchBookById(102);
		Assertions.assertNotNull(bean);
	}

	@Test
	public void testSearchByName() {
		BookBean bean = dao.searchBookByTitle("Java");
		Assertions.assertNotNull(bean);
	}

	@Test
	public void testSearchByName1() {
		BookBean bean = dao.searchBookByTitle("Java Programming");
		Assertions.assertNotNull(bean);
	}

	@Test
	public void testSearchByAuthor() {
		BookBean bean = dao.searchBookByAuthor("james");
		Assertions.assertNotNull(bean);
	}

	@Test
	public void testSearchByAuthor1() {
		BookBean bean = dao.searchBookByAuthor("james gosling");
		Assertions.assertNotNull(bean);
	}

	@Test
	public void testGetIds() {
		List<Integer> beans = dao.getBookIds();
		Assertions.assertNotNull(beans);
	}

	@Test
	public void testGetIds1() {
		List<Integer> beans = dao.getBookIds();
		Assertions.assertNull(beans);
	}

	@Test
	public void testGetAllBooks() {
		List<BookBean> beans = dao.getBooksInfo();
		Assertions.assertNotNull(beans);
	}

	@Test
	public void testGetAllBooks1() {
		List<BookBean> beans = dao.getBooksInfo();
		Assertions.assertNull(beans);
	}
	
	@Test
	public void testRequestBook() {
		boolean status = dao.request(1, 100001);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testRequestBook1() {
		boolean status = dao.request(1, 100001);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testRequestReturnBook() {
		boolean status = dao.reqReturnBook(100001, 1);
		Assertions.assertTrue(status);
	}
	
	@Test
	public void testRequestReturnBook1() {
		boolean status = dao.reqReturnBook(100001, 1);
		Assertions.assertTrue(status);
	}
}
