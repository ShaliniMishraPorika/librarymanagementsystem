package com.capgemini.librarymanagementsystemjdbc.service;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemjdbc.dto.UsersBean;

public interface AdminService {
	boolean addBook(BookBean book);
	BookBean searchBookByTitle(String title);
	BookBean searchBookByAuthor(String author);
	BookBean searchBookById(int bookId);
	boolean updateBook(BookBean bean);
	boolean removeBook(int bookId);
	ArrayList<BookBean> getBookIds();
	ArrayList<BookBean> getBooksInfo();
	List<UsersBean> showUsers();
	boolean issueBook(int bookId,int userId);
	boolean returnBook(int bookId, int id);
}
