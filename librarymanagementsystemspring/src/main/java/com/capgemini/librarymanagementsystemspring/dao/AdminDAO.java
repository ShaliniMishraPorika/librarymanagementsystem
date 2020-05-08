package com.capgemini.librarymanagementsystemspring.dao;

import java.util.List;

import com.capgemini.librarymanagementsystemspring.dto.BookBean;

public interface AdminDAO {
	boolean update(BookBean book);

	boolean delete(int bookId);

	boolean addBook(BookBean info);

	List<Integer> getBookIds();

	List<BookBean> getBooksInfo();

	BookBean searchBookByTitle(String name);

	BookBean searchBookByAuthor(String author);

	BookBean searchBookById(int bookId);

	boolean issueBook(int id, int bookId);

	boolean returnBook(int id, int bookId);
}
