package com.capgemini.librarymanagementsystemjdbc.service;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.librarymanagementsystemjdbc.dao.AdminDAO;
import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemjdbc.dto.UsersBean;
import com.capgemini.librarymanagementsystemjdbc.factory.LMSFactory;

public class AdminServiceImp implements AdminService {
	
	AdminDAO dao = LMSFactory.getAdminDAO();
	
	@Override
	public boolean addBook(BookBean book) {
		
		return dao.addBook(book);
	}

	@Override
	public BookBean searchBookByTitle(String title) {
		
		return dao.searchBookByTitle(title);
	}

	@Override
	public BookBean searchBookByAuthor(String author) {
		
		return dao.searchBookByAuthor(author);
	}

	@Override
	public BookBean searchBookById(int bookId) {
		
		return dao.searchBookById(bookId);
	}

	@Override
	public boolean updateBook(BookBean bean) {
		
		return dao.updateBook(bean);
	}

	@Override
	public boolean removeBook(int bookId) {
		
		return dao.removeBook(bookId);
	}

	@Override
	public ArrayList<BookBean> getBookIds() {
		
		return dao.getBookIds();
	}

	@Override
	public ArrayList<BookBean> getBooksInfo() {
		
		return dao.getBookIds();
	}

	@Override
	public List<UsersBean> showUsers() {
		
		return dao.showUsers();
	}

	@Override
	public boolean issueBook(int bookId, int userId) {
		
		return dao.issueBook(bookId, userId);
	}

	@Override
	public boolean returnBook(int bookId, int id) {
		
		return dao.returnBook(bookId, id);
	}

}
