package com.capgemini.librarymanagement.dao;

import java.util.ArrayList;

import com.capgemini.librarymanagement.database.DataBase;
import com.capgemini.librarymanagement.dto.BookBean;
import com.capgemini.librarymanagement.dto.RequestBean;
import com.capgemini.librarymanagement.dto.StudentBean;
import com.capgemini.librarymanagement.exception.LMSException;

public class StudentDAOImp implements StudentDAO {

	public boolean register(StudentBean info) {
		for (StudentBean studentBean : DataBase.student) {
			if (studentBean.getEmail().equals(info.getEmail())) {
				return false;
			}
		}
		DataBase.student.add(info);
		return true;
	}

	public StudentBean auth(String email, String password) {
		for (StudentBean bean : DataBase.student) {
			if (bean.getEmail().equals(email) && bean.getPassword().equals(password)) {
				System.out.println("Login Successful");
				return bean;
			}
		}
		throw new LMSException("Invalid email and password");

	}

	public ArrayList<BookBean> searchBookByTitle(String bookTitle) {
		ArrayList<BookBean> searchList = new ArrayList<BookBean>();
		for (int i = 0; i <= DataBase.book.size() - 1; i++) {
			BookBean retrievedBook = DataBase.book.get(i);
			String retrievedBname = retrievedBook.getTitle();
			if (bookTitle.equals(retrievedBname)) {
				searchList.add(retrievedBook);
				return searchList;
			}
		}
		if (searchList.size() == 0) {
			throw new LMSException("Book is not found");
		} else {
			return searchList;
		}
	}

	public ArrayList<BookBean> searchBookByAuthor(String bookAuthor) {
		ArrayList<BookBean> searchList = new ArrayList<BookBean>();
		for (int i = 0; i <= DataBase.book.size() - 1; i++) {
			BookBean retrievedBook = DataBase.book.get(i);
			String retrievedBAuthor = retrievedBook.getAuthor();
			if (bookAuthor.equals(retrievedBAuthor)) {
				searchList.add(retrievedBook);
			}
		}
		if (searchList.size() == 0) {
			throw new LMSException("Book is not found");
		} else {
			return searchList;
		}
	}

	public ArrayList<BookBean> searchBookByCategory(String bookCategory) {
		ArrayList<BookBean> searchList = new ArrayList<BookBean>();
		for (int i = 0; i <= DataBase.book.size() - 1; i++) {
			BookBean retrievedBook = DataBase.book.get(i);
			int retrievedBookType = retrievedBook.getId();
			if (bookCategory.equals(retrievedBookType)) {
				searchList.add(retrievedBook);
			}
		}
		if (searchList.size() == 0) {
			throw new LMSException("Book not found");
		} else {
			return searchList;
		}
	}

	public ArrayList<Integer> getBookIds() {
		ArrayList<Integer> idList = new ArrayList<Integer>();
		for (int i = 0; i <= DataBase.book.size() - 1; i++) {
			BookBean retrievedBook = DataBase.book.get(i);
			int retrievedBookId = retrievedBook.getId();
			idList.add(retrievedBookId);
		}
		return idList;

	}

	public ArrayList<BookBean> getBooksInfo() {
		return DataBase.book;
	}

	public RequestBean bookRequest(StudentBean student, BookBean book) {
		boolean flag = false, isRequestExists = false;
		RequestBean requestInfo = new RequestBean();
		StudentBean userInfo2 = new StudentBean();
		BookBean bookInfo2 = new BookBean();

		for (RequestBean requestInfo2 : DataBase.request) {
			if (book.getId() == requestInfo2.getBookInfo().getId()) {
				isRequestExists = true;

			}

		}

		if (!isRequestExists) {
			for (StudentBean user : DataBase.student) {
				if (user.getId() == student.getId()) {
					for (BookBean book1 : DataBase.book) {
						if (book1.getId() == book1.getId()) {
							userInfo2 = user;
							bookInfo2 = book1;
							flag = true;
						}
					}
				}
			}
			if (flag == true) {
				requestInfo.setBookInfo(bookInfo2);
				requestInfo.setStudentInfo(userInfo2);
				DataBase.request.add(requestInfo);
				return requestInfo;
			}

		}

		throw new LMSException("Invalid request or you cannot request that book");
	}

	public RequestBean bookReturn(StudentBean student, BookBean book) {
		for (RequestBean requestInfo : DataBase.request) {

			if (requestInfo.getBookInfo().getId() == book.getId()
					&& requestInfo.getStudentInfo().getId() == student.getId() && requestInfo.isIssued() == true) {

				System.out.println("Returning Issued book only");
				requestInfo.setReturned(true);

				return requestInfo;
			}

		}

		throw new LMSException("Invalid return ");
	}

}
