package com.capgemini.librarymanagementsystemhibernate.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode.Exclude;

@Data
@Entity
@Table(name = "BorrowedBooks")
public class BorrowedBook implements Serializable {
	@EmbeddedId
	private BorrowedBookPK borrowBookPK;
	@Column
	private int id;
	@Exclude
	@ManyToOne
	@JoinColumn(name = "email", insertable = false, updatable = false)
	private UserBean primary;
}
