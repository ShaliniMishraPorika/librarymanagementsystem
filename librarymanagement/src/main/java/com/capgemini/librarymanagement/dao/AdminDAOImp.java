package com.capgemini.librarymanagement.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagement.database.DataBase;
import com.capgemini.librarymanagement.dto.AdminBean;
import com.capgemini.librarymanagement.dto.BookBean;
import com.capgemini.librarymanagement.dto.RequestBean;
import com.capgemini.librarymanagement.dto.StudentBean;
import com.capgemini.librarymanagement.exception.LMSException;

public class AdminDAOImp implements AdminDAO {

	public boolean register(AdminBean info) {
		for (AdminBean adminBean : DataBase.admin) {
			if (adminBean.getEmail().equals(info.getEmail())) {
				return false;
			}
		}
		DataBase.admin.add(info);
		return true;

	}

	public AdminBean login(String email, String password) {
		for (AdminBean bean : DataBase.admin) {
			if (bean.getEmail().equals(email) && bean.getPassword().equals(password)) {
				System.out.println("Login Successful");
				return bean;
			}
		}
		throw new LMSException("Invalid email and password");

	}

	public boolean delete(BookBean book) {
		for (BookBean bean : DataBase.book) {
			if (bean.getId() == book.getId()) {
				return false;
			}

		}
		DataBase.book.add(book);
		return true;
	}

	public boolean addBook(BookBean book) {
		for (BookBean bean : DataBase.book) {
			if (bean.getId() == book.getId()) {
				return false;
			}
		}
		DataBase.book.add(book);
		return true;
	}

	public ArrayList<BookBean> searchBookByTitle(String bookName) {
		ArrayList<BookBean> searchList = new ArrayList<BookBean>();
		for (int i = 0; i <= DataBase.book.size() - 1; i++) {
			BookBean retrievedBook = DataBase.book.get(i);
			String retrievedBname = retrievedBook.getTitle();
			if (bookName.equals(retrievedBname)) {
				searchList.add(retrievedBook);
				return searchList;
			}
		}
		if (searchList.size() == 0) {
			throw new LMSException("Book not found");
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
				return searchList;
			}
		}
		if (searchList.size() == 0) {
			throw new LMSException("Book not found");
		} else {
			return searchList;
		}
	}

	public ArrayList<BookBean> searchBookByCategory(String bookCategory) {
		ArrayList<BookBean> searchList = new ArrayList<BookBean>();
		for (int i = 0; i <= DataBase.book.size() - 1; i++) {
			BookBean retrievedBook = DataBase.book.get(i);
			String retrievedBookType = retrievedBook.getCategory();
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

	public boolean updateBook(BookBean book) {

		for (int i = 0; i <= DataBase.book.size() - 1; i++) {
			BookBean retrievedBook = DataBase.book.get(i);
			if (retrievedBook.getId() == book.getId()) {
				retrievedBook.setTitle(book.getTitle());
				return true;
			}

			else {
				throw new LMSException("Invalid Book");
			}
		}
		throw new LMSException("Book not updated");
	}

	public boolean removeBook(int bookId) {
		boolean status = false;
		for (int i = 0; i <= DataBase.book.size() - 1; i++) {
			BookBean retrievedBook = DataBase.book.get(i);
			int retrievedId = retrievedBook.getId();
			if (bookId == retrievedId) {
				status = true;
				DataBase.book.remove(i);
				break;
			} else {
				throw new LMSException("Invalid BookId");
			}
		}
		return status;

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

	public List<StudentBean> showUsers() {
		List<StudentBean> usersList = new LinkedList<StudentBean>();
		for (StudentBean studentBean : DataBase.student) {

			studentBean.getId();
			studentBean.getName();
			studentBean.getEmail();
			studentBean.getBooksBorrowed();
			usersList.add(studentBean);

		}
		return usersList;
	}

	public List<RequestBean> showRequests() {
		List<RequestBean> infos = new LinkedList<RequestBean>();
		for (RequestBean requestInfo : DataBase.request) {
			requestInfo.getBookInfo();
			requestInfo.getStudentInfo();
			requestInfo.isIssued();
			requestInfo.isReturned();
			infos.add(requestInfo);
		}
		return infos;
	}

	public boolean bookIssue(StudentBean student, BookBean book) {
		boolean isValid = false;

		RequestBean requestInfo = new RequestBean();

		int noOfBooksBorrowed = student.getBooksBorrowed();
		for (RequestBean info : DataBase.request) {
			if (info.getStudentInfo().getId() == student.getId()) {
				if (info.getBookInfo().getId() == book.getId()) {
					requestInfo = info;
					isValid = true;
				}
			}
		}
		if (isValid) {
			for (BookBean info2 : DataBase.book) {
				if (info2.getId() == book.getId()) {
					book = info2;
				}
			}

			for (StudentBean studentInfo : DataBase.student) {
				if (studentInfo.getId() == student.getId()) {
					student = studentInfo;
					noOfBooksBorrowed = student.getBooksBorrowed();
				}
			}

			if (noOfBooksBorrowed <= 3) {
				// boolean isRemoved = DataBase.book.remove(book);
				int noOfBooksAvailble = book.getCopies();
				if (noOfBooksAvailble > 0) {
					noOfBooksAvailble--;
					book.setCopies(noOfBooksAvailble);
					noOfBooksBorrowed++;
					System.out.println(noOfBooksBorrowed);
					student.setBooksBorrowed(noOfBooksBorrowed);
					requestInfo.setIssued(true);
					return true;
				} else {
					throw new LMSException("Book can't be borrowed");
				}

			} else {
				throw new LMSException("Student Exceeds maximum limit");
			}

		} else {
			throw new LMSException("Book data or Student data is incorrect");
		}
	}

	public boolean isBookReceived(StudentBean student, BookBean book) {
		boolean isValid = false;
		RequestBean requestInfo1 = new RequestBean();
		for (RequestBean requestInfo : DataBase.request) {

			if (requestInfo.getBookInfo().getId() == book.getId()
					&& requestInfo.getStudentInfo().getId() == student.getId() && requestInfo.isReturned() == true) {
				isValid = true;
				requestInfo1 = requestInfo;
			}
		}
		if (isValid) {

			book.setAuthor(requestInfo1.getBookInfo().getAuthor());
			book.setTitle(requestInfo1.getBookInfo().getTitle());
			// DataBase.book.add(book);
			int noOfBooksAvailble = book.getCopies();
			noOfBooksAvailble++;
			book.setCopies(noOfBooksAvailble);

			DataBase.request.remove(requestInfo1);

			for (StudentBean userInfo2 : DataBase.student) {
				if (userInfo2.getId() == student.getId()) {
					student = userInfo2;
				}
			}

			int noOfBooksBorrowed = student.getBooksBorrowed();
			noOfBooksBorrowed--;
			student.setBooksBorrowed(noOfBooksBorrowed);
			return true;
		}

		return false;
	}
}
