package com.capgemini.librarymanagementsystemhibernate;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;
import com.capgemini.librarymanagementsystemhibernate.service.AdminService;
import com.capgemini.librarymanagementsystemhibernate.service.AdminServiceImp;

public class AdminServiceTest {
	private AdminService dao = new AdminServiceImp();

	@Test
	public void testAddBook() {
		BookBean bean = new BookBean();
		bean.setBookId(190);
		bean.setTitle("C");
		bean.setAuthor("Bjarne Stroustrup");
		bean.setCategory("language");
		bean.setPublisher_name("arihant");
		bean.setBookId(23);
		bean.setISBN(421412352);
		bean.setCopyright_year(1997);
		bean.setStatus("old");
		boolean status = dao.addBook(bean);
		Assertions.assertTrue(status);
	}

	@Test
	public void testAddBook1() {
		BookBean bean = new BookBean();
		bean.setBookId(190);
		bean.setTitle("C");
		bean.setAuthor("Bjarne Stroustrup");
		bean.setCategory("language");
		bean.setPublisher_name("arihant");
		bean.setBookId(23);
		bean.setISBN(421412352);
		bean.setCopyright_year(1997);
		bean.setStatus("old");
		boolean status = dao.addBook(bean);
		Assertions.assertTrue(status);
	}

	@Test
	public void testUpdateBook() {
		BookBean bean = new BookBean();
		bean.setBookId(11);
		bean.setTitle("C plus plus");
		boolean status = dao.update(bean);
		Assertions.assertTrue(status);
	}

	@Test
	public void testUpdateBook1() {
		BookBean bean = new BookBean();
		bean.setBookId(11);
		bean.setTitle("C plus plus");
		boolean status = dao.update(bean);
		Assertions.assertTrue(status);
	}

	@Test
	public void testDeleteBook() {
		boolean status = dao.delete(100);
		Assertions.assertTrue(status);
	}

	@Test
	public void testDeleteBook1() {
		boolean status = dao.delete(100);
		Assertions.assertTrue(status);
	}

	@Test
	public void testSearchBookById() {
		BookBean bean = dao.searchBookById(100001);
		Assertions.assertNotNull(bean);
	}

	@Test
	public void testSearchBookById1() {
		BookBean bean = dao.searchBookById(100002);
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
	public void testIssueBook() {
		boolean status = dao.issueBook(100001, 1);
		Assertions.assertTrue(status);
	}

	@Test
	public void testIssueBook1() {
		boolean status = dao.issueBook(100001, 1);
		Assertions.assertTrue(status);
	}

	@Test
	public void testReturnBook() {
		boolean status = dao.returnBook(1, 2);
		Assertions.assertFalse(status);
	}

	@Test
	public void testReturnBook1() {
		boolean status = dao.returnBook(2, 1);
		Assertions.assertTrue(status);
	}
}
