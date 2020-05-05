package com.capgemini.librarymanagementsystemhibernate.service;

import java.util.List;

import com.capgemini.librarymanagementsystemhibernate.dao.AdminDAO;
import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;
import com.capgemini.librarymanagementsystemhibernate.factory.AdminFactory;

public class AdminServiceImp implements AdminService {
	
	private AdminDAO dao = AdminFactory.getAdminDAO();
	
	public boolean update(BookBean book) {
		
		return dao.update(book);
	}

	public boolean delete(int bId) {
		
		return dao.delete(bId);
	}

	public boolean addBook(BookBean bean) {
		
		return dao.addBook(bean);
	}

	public List<Integer> getBookIds() {
		
		return dao.getBookIds();
	}

	public List<BookBean> getBooksInfo() {
		
		return dao.getBooksInfo();
	}

	public BookBean searchBookByTitle(String name) {
		
		return dao.searchBookByTitle(name);
	}

	public BookBean searchBookByAuthor(String author) {
		
		return dao.searchBookByAuthor(author);
	}

	public BookBean searchBookById(int bookId) {
		
		return dao.searchBookById(bookId);
	}

	public boolean issueBook(int id, int bookId) {
		
		return dao.issueBook(id, bookId);
	}

	public boolean returnBook(int id, int bookId) {
		
		return dao.returnBook(id, bookId);
	}

}
