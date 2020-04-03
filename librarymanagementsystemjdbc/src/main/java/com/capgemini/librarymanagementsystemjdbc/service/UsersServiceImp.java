package com.capgemini.librarymanagementsystemjdbc.service;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystemjdbc.dao.UsersDAO;
import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemjdbc.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.BorrowedBooks;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.UsersBean;
import com.capgemini.librarymanagementsystemjdbc.factory.UsersFactory;

public class UsersServiceImp  implements UsersService{
	UsersDAO dao = UsersFactory.getUsersDAO();
	@Override
	public boolean register(UsersBean info) {
		
		return dao.register(info);
	}

	@Override
	public UsersBean auth(String email, String password) {
		
		return dao.auth(email, password);
	}

	@Override
	public boolean addBook(BookBean book) {
		// TODO Auto-generated method stub
		return dao.addBook(book);
	}

	@Override
	public LinkedList<BookBean> searchBookByTitle(String bname) {
		
		return dao.searchBookByTitle(bname);
	}

	@Override
	public LinkedList<BookBean> searchBookByAuthor(String bAuthor) {
	
		return dao.searchBookByAuthor(bAuthor);
	}

	@Override
	public LinkedList<BookBean> searchBookById(int bid) {
		
		return dao.searchBookById(bid);
	}

	@Override
	public boolean updateBook(BookBean bean) {
		
		return dao.updateBook(bean);
	}

	@Override
	public boolean removeBook(int bid) {
		
		return dao.removeBook(bid);
	}

	@Override
	public LinkedList<BookBean> getBookIds() {
		
		return dao.getBookIds();
	}

	@Override
	public LinkedList<BookBean> getBooksInfo() {
		
		return dao.getBooksInfo();
	}

	@Override
	public List<UsersBean> showUsers() {
		
		return dao.showUsers();
	}

	@Override
	public boolean request(int uId, int bId) {
		
		return dao.request(uId, bId);
	}

	@Override
	public List<RequestDetails> showRequest() {
		
		return dao.showRequest();
	}

	@Override
	public boolean issueBook(int bId, int uId) {
		
		return dao.issueBook(bId, uId);
	}

	@Override
	public boolean returnBook(int bId, int uId) {
		
		return dao.returnBook(bId, uId);
	}

	@Override
	public LinkedList<BookIssueDetails> bookHistoryDetails(int uId) {
		
		return dao.bookHistoryDetails(uId);
	}

	@Override
	public List<BorrowedBooks> borrowedBook(int uId) {
		
		return dao.borrowedBook(uId);
	}

}
