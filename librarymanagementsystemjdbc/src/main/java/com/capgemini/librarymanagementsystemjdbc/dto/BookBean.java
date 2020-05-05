package com.capgemini.librarymanagementsystemjdbc.dto;

import lombok.Data;
@Data
public class BookBean {
	private String bookName;
	private int bookId;
	private String bookAuthor;
	private String bookCategory;
	private String bookPublisherName;
	private int bookCopies;
	private int bookIsbn;
	private int bookCopyRight;
	private String dateAdded;
	private String status;
}
