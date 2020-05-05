package com.capgemini.librarymanagementsystemjdbc.dto;

import java.util.Date;

import lombok.Data;
@Data
public class BookIssueDetails {
	private int bookId;
	private String bookName;
	private int userId;
	private String userName;
	private Date issueDate;
	private Date returnDate;
	private float fine;
	private Date actualReturnDate;
	
}
