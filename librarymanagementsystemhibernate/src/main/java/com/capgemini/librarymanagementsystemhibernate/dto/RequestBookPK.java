package com.capgemini.librarymanagementsystemhibernate.dto;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class RequestBookPK implements Serializable {

	private int bookId;
	private String email;

	@Override
	public String toString() {
		return "RequestBookPK [bid=" + bookId + ", email=" + email + "]";
	}
}
