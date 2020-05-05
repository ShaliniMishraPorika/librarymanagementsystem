package com.capgemini.librarymanagementsystemhibernate.service;

import java.util.List;

import com.capgemini.librarymanagementsystemhibernate.dao.StudentDAO;
import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;
import com.capgemini.librarymanagementsystemhibernate.factory.StudentFactory;

public class StudentServiceImp implements StudentService{
	
	private StudentDAO dao = StudentFactory.getStudentDAO();
	
	public BookBean searchBookByTitle(String name) {
		
		return dao.searchBookByTitle(name);
	}

	public BookBean searchBookByAuthor(String author) {
		
		return dao.searchBookByAuthor(author);
	}

	public BookBean searchBookById(int bookId) {
		
		return dao.searchBookById(bookId);
	}

	public List<Integer> getBookIds() {
		
		return dao.getBookIds();
	}

	public List<BookBean> getBooksInfo() {
		
		return dao.getBooksInfo();
	}

	public boolean request(int id, int bookId) {
		
		return dao.request(id, bookId);
	}

	public boolean reqReturnBook(int id, int bookId) {
		
		return dao.reqReturnBook(id, bookId);
	}

}
