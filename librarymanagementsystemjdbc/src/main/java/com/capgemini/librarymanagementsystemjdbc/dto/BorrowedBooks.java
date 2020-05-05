package com.capgemini.librarymanagementsystemjdbc.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class BorrowedBooks implements Serializable{
	private int borrowedBookId;
	private int borrowedUserId;
	private String email;
	private Date issueDate;

}
