package com.capgemini.librarymanagement.dao;

import java.util.ArrayList;

import com.capgemini.librarymanagement.dto.BookBean;
import com.capgemini.librarymanagement.dto.RequestBean;
import com.capgemini.librarymanagement.dto.StudentBean;

public interface StudentDAO {
	boolean register(StudentBean info);

	StudentBean auth(String email, String password);

	ArrayList<BookBean> searchBookByTitle(String bookName);

	ArrayList<BookBean> searchBookByAuthor(String bookAuthor);

	ArrayList<BookBean> searchBookByCategory(String Category);

	ArrayList<Integer> getBookIds();

	ArrayList<BookBean> getBooksInfo();

	public RequestBean bookRequest(StudentBean student, BookBean book);

	public RequestBean bookReturn(StudentBean student, BookBean book);
}
