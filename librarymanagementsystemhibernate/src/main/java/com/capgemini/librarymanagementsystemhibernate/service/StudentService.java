package com.capgemini.librarymanagementsystemhibernate.service;

import java.util.List;

import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;

public interface StudentService {
	public BookBean searchBookByTitle(String name); 
	public BookBean searchBookByAuthor(String author);
	BookBean searchBookById(int bookId);
	public List<Integer> getBookIds();
	public List<BookBean> getBooksInfo();
	boolean request(int id, int bookId);
	boolean reqReturnBook(int id , int bookId);
}
