package com.capgemini.librarymanagementsystemspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.librarymanagementsystemspring.dao.AdminDAO;
import com.capgemini.librarymanagementsystemspring.dto.BookBean;

public class AdminServiceImp implements AdminService{
	@Autowired
	private AdminDAO dao;

	public boolean update(BookBean book) {

		return dao.update(book);
	}

	public boolean delete(int bookId) {

		return dao.delete(bookId);
	}

	public boolean addBook(BookBean info) {

		return dao.addBook(info);
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
