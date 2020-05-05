package com.capgemini.librarymanagement.dao;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.librarymanagement.dto.AdminBean;
import com.capgemini.librarymanagement.dto.BookBean;
import com.capgemini.librarymanagement.dto.RequestBean;
import com.capgemini.librarymanagement.dto.StudentBean;

public interface AdminDAO {
	boolean register(AdminBean info);

	AdminBean login(String email, String password);

	boolean addBook(BookBean book);

	ArrayList<BookBean> searchBookByTitle(String bookName);

	ArrayList<BookBean> searchBookByAuthor(String bookAuthor);

	ArrayList<BookBean> searchBookByCategory(String bookCategory);

	boolean updateBook(BookBean book);

	boolean removeBook(int bookId);

	ArrayList<Integer> getBookIds();

	ArrayList<BookBean> getBooksInfo();

	List<StudentBean> showUsers();

	List<RequestBean> showRequests();

	boolean bookIssue(StudentBean student, BookBean book);

	boolean isBookReceived(StudentBean student, BookBean book);
}
