package com.capgemini.librarymanagement.service;

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

	public AdminBean auth(String email, String password) {
		return dao.auth(email, password);
	}
	public boolean addBook(BookBean book) {

		return dao.addBook(book);
	}

	public LinkedList<BookBean> searchBookTitle(String bTitle) {

		return dao.searchBookTitle(bTitle);
	}

	public LinkedList<BookBean> searchBookAuthor(String bAuthor) {

		return dao.searchBookAuthor(bAuthor);
	}

	public LinkedList<BookBean> searchBookType(String bookType) {
		return dao.searchBookType(bookType);
	}

	public int updateBook(int bid) {
		return dao.updateBook(bid);
	}

	public boolean removeBook(int bid) {

		return dao.removeBook(bid);
	}

	public LinkedList<Integer> getBookIds() {

		return dao.getBookIds();
	}

	public LinkedList<BookBean> getBooksInfo() {

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
