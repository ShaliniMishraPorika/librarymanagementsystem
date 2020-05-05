package com.capgemini.librarymanagementsystemhibernate.service;

import java.util.List;

import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;

public interface AdminService {
	boolean update(BookBean book);
	boolean delete(int bId);
	boolean addBook(BookBean info);
	List<Integer> getBookIds();
	List<BookBean> getBooksInfo();
	BookBean searchBookByTitle(String name);
	BookBean searchBookByAuthor(String author);
	BookBean searchBookById(int bookId);
	boolean issueBook(int id, int bookId);
	boolean returnBook(int id, int bookId);
}
