package com.capgemini.librarymanagementsystemspring.dto;

import java.util.List;

import lombok.Data;
@Data
public class LMSResponse {
	private boolean error;
	private String message;
	private UserBean user;
	private List<UserBean> userBean;
	private BookBean book;
	private List<Integer> bookBeanId;
	private List<BookBean> bookBeanList;
	private BookIssueDetails bookIssue;
}
