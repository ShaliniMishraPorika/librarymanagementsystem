package com.capgemini.librarymanagementsystemspring.dto;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class BookIssueDetailsPK implements Serializable{
	private int bookId;
	private String email;
}
