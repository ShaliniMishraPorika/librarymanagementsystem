package com.capgemini.librarymanagementsystemspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.librarymanagementsystemspring.dao.StudentDAO;
import com.capgemini.librarymanagementsystemspring.dto.BookBean;
@Service
public class StudentServiceImp implements StudentService{
	@Autowired
	private StudentDAO dao;
	@Override
	public BookBean searchBookByTitle(String name) {
		
		return dao.searchBookByTitle(name);
	}

	@Override
	public BookBean searchBookByAuthor(String author) {
		
		return dao.searchBookByAuthor(author);
	}

	@Override
	public boolean request(int id, int bookId) {
		
		return dao.request(id, bookId);
	}

	@Override
	public List<Integer> getBookIds() {
		
		return dao.getBookIds();
	}

	@Override
	public List<BookBean> getBooksInfo() {
		
		return dao.getBooksInfo();
	}

	@Override
	public BookBean searchBookById(int bookId) {
		
		return dao.searchBookById(bookId);
	}

	@Override
	public boolean reqReturnBook(int id, int bookId) {
		
		return dao.reqReturnBook(id, bookId);
	}
	
}
