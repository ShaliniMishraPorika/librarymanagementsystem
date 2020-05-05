package com.capgemini.librarymanagement.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagement.dao.AdminDAO;
import com.capgemini.librarymanagement.dto.AdminBean;
import com.capgemini.librarymanagement.dto.BookBean;
import com.capgemini.librarymanagement.dto.RequestBean;
import com.capgemini.librarymanagement.dto.StudentBean;
import com.capgemini.librarymanagement.factory.AdminFactory;

public class AdminServiceImp implements AdminService{
	
	private AdminDAO dao = AdminFactory.getAdminDAO();
	
	public boolean register(AdminBean info) {
		return dao.register(info) ;
	}

	public AdminBean login(String email, String password) {
		return dao.login(email, password);
	}
	public boolean addBook(BookBean book) {

		return dao.addBook(book);
	}

	public ArrayList<BookBean> searchBookByTitle(String bookTitle) {

		return dao.searchBookByTitle(bookTitle);
	}

	public ArrayList<BookBean> searchBookByAuthor(String bookAuthor) {

		return dao.searchBookByAuthor(bookAuthor);
	}

	public ArrayList<BookBean> searchBookByCategory(String bookCategory) {
		return dao.searchBookByCategory(bookCategory);
	}

	public boolean updateBook(BookBean book) {
		return dao.updateBook(book);
	}

	public boolean removeBook(int bookId) {

		return dao.removeBook(bookId);
	}

	public ArrayList<Integer> getBookIds() {

		return dao.getBookIds();
	}

	public ArrayList<BookBean> getBooksInfo() {

		return dao.getBooksInfo();
	}

	public List<StudentBean> showUsers() {
		
		return dao.showUsers();
	}

	public List<RequestBean> showRequests() {
		
		return dao.showRequests();
	}

	public boolean bookIssue(StudentBean student, BookBean book) {
		
		return dao.bookIssue(student, book);
	}

	public boolean isBookReceived(StudentBean student, BookBean book) {
		
		return dao.isBookReceived(student, book);
	}
}
