package com.capgemini.librarymanagementsystemjdbc.dao;

import java.util.ArrayList;

import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;

public interface StudentDAO {
	public BookBean searchBookByTitle(String title); 
	public BookBean searchBookByAuthor(String author);
	BookBean searchBookById(int bookId);
	public ArrayList<BookBean> getBookIds();
	public ArrayList<BookBean> getBooksInfo();
	boolean requestReturnBook(int bookId, int userId);	
	boolean requestBook(int userId, int bookId);
}
