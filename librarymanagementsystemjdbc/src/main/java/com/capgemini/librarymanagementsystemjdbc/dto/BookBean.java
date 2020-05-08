package com.capgemini.librarymanagementsystemjdbc.dto;

import lombok.Data;
@Data
public class BookBean {
	private int bookId;
	private String bookName;
	private String bookAuthor;
	private String bookCategory;
	private String bookPublisherName;
	private int bookCopies;
	private int bookIsbn;
	private int bookCopyRight;
	private String status;
	
	@Override
	public String toString() {
		return "BookBean [bid=" + bookId + ", book_title=" + bookName + ", category=" + bookCategory + ", author=" + bookAuthor
				+ ", copies=" + bookCopies + ", publisher_name=" + bookPublisherName
				+ ", ISBN=" + bookIsbn + ", copyright_year=" + bookCopyRight + ", status= " + status + "]";
	}
}
