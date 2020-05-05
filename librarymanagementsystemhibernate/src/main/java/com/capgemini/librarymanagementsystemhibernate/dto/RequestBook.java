package com.capgemini.librarymanagementsystemhibernate.dto;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode.Exclude;

@Inheritance
@Entity
@Table(name = "RequestBook")
public class RequestBook implements Serializable {
	@EmbeddedId
	private RequestBookPK reqBookPK;
	@Column
	private String name;
	@Column
	private String title;
	@Column
	private String type;
	@Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "email", insertable = false, updatable = false)
	private UserBean primary;
	@Column
	private int id;

	@Override
	public String toString() {
		return "RequestBook [reqBookPK=" + reqBookPK + ", id=" + id + ", name=" + name + ", title=" + title + ", type="
				+ type + ", primary=" + primary + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
}