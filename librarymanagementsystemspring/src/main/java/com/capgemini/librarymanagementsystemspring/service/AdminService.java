package com.capgemini.librarymanagementsystemspring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.librarymanagementsystemspring.dto.BookBean;

@Service
public interface AdminService {
	boolean update(BookBean book);

	boolean delete(int bookId);

	boolean addBook(BookBean info);

	List<Integer> getBookIds();

	List<BookBean> getBooksInfo();

	BookBean searchBookByTitle(String name);

	BookBean searchBookByAuthor(String author);

	BookBean searchBookById(int bookId);

	boolean issueBook(int id, int bookId);

	boolean returnBook(int id, int bookId);
}
