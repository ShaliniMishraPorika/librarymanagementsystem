package com.capgemini.librarymanagementsystemjdbc.dao;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemjdbc.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.BorrowedBooks;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.UsersBean;

public interface UsersDAO {
	boolean register(UsersBean info);
	UsersBean auth(String email, String password);
	boolean addBook(BookBean book);
	LinkedList<BookBean> searchBookByTitle(String bname);
	LinkedList<BookBean> searchBookByAuthor(String bAuthor);
	LinkedList<BookBean> searchBookById(int bid);
	boolean updateBook(BookBean bean);
	boolean removeBook(int bid);
	LinkedList<BookBean> getBookIds();
	LinkedList<BookBean> getBooksInfo();
	List<UsersBean> showUsers();	
	boolean request(int uId, int bId);
	List<RequestDetails> showRequest();
	boolean issueBook(int bId,int uId);
	//BookBean borrowBook(int bId);
	boolean returnBook(int bId, int uId);
	List<BorrowedBooks> borrowedBook(int uId);
	LinkedList<BookIssueDetails> bookHistoryDetails(int uId);
}
