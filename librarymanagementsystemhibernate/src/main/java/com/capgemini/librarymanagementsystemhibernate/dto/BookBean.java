package com.capgemini.librarymanagementsystemhibernate.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "BookBean")
public class BookBean implements Serializable {
	@Id
	@Column(unique = true, updatable = false)
	private int bookId;
	@Column
	private String title;
	@Column(updatable = false)
	private String author;
	@Column(updatable = false)
	private String category;
	@Column(updatable = false)
	private String publisher_name;
	@Column(updatable = false)
	private int copies;
	@Column(updatable = false)
	private long ISBN;
	@Column(updatable = false)
	private int copyright_year;
	@Column(updatable = false)
	private String status;
	@Column(updatable = false)
	private String dateAdded;

}
