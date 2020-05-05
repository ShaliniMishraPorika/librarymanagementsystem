package com.capgemini.librarymanagementsystemhibernate.dao;

import java.util.List;

import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;

public interface StudentDAO {
	public BookBean searchBookByTitle(String name); 
	public BookBean searchBookByAuthor(String author);
	boolean request(int id, int bookId);
	public List<Integer> getBookIds();
	public List<BookBean> getBooksInfo();
	BookBean searchBookById(int bookId);
	boolean reqReturnBook(int id, int bookId);
}
