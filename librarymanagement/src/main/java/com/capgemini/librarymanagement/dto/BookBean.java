package com.capgemini.librarymanagement.dto;

import java.io.Serializable;
import java.util.Date;

public class BookBean implements Serializable {
	private String name;
	private int id;
	private String author;
	private String category;
	private String publishername;
	private String issuedate;
	private String  returndate;
	
	
	public int getTotalBookAllowed() {
		return totalBookAllowed;
	}
	public void setTotalBookAllowed(int totalBookAllowed) {
		this.totalBookAllowed = totalBookAllowed;
	}
	public int getNoOfBookIssued() {
		return noOfBookIssued;
	}
	public void setNoOfBookIssued(int noOfBookIssued) {
		this.noOfBookIssued = noOfBookIssued;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	private int totalBookAllowed = 3;
	private int noOfBookIssued=0;
	private Date issueDate;
	private Date returnDate;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPublishername() {
		return publishername;
	}
	public void setPublishername(String publishername) {
		this.publishername = publishername;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIssuedate() {
		return issuedate;
	}
	public void setIssuedate(String issuedate) {
		this.issuedate = issuedate;
	}
	public String getReturndate() {
		return returndate;
	}
	public void setReturndate(String returndate) {
		this.returndate = returndate;
	}


}
