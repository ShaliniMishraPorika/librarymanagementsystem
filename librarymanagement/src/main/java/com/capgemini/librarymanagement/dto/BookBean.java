package com.capgemini.librarymanagement.dto;

import java.io.Serializable;
import java.util.Date;

public class BookBean implements Serializable {
	private String title;
	private int id;
	private String author;
	private String category;
	private String publisherName;
	private String issueDate;
	private String returnDate;
	private int totalBookAllowed = 3;
	private int noOfBookIssued = 0;
	private String dateAdded;
	private long ISBN;
	private int copies;
	private int copyRightYear;
	private String status;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

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

	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public long getISBN() {
		return ISBN;
	}

	public void setISBN(long iSBN) {
		ISBN = iSBN;
	}

	public int getCopies() {
		return copies;
	}

	public void setCopies(int copies) {
		this.copies = copies;
	}

	public int getCopyRightYear() {
		return copyRightYear;
	}

	public void setCopyRightYear(int copyRightYear) {
		this.copyRightYear = copyRightYear;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
