package com.capgemini.librarymanagement.dao;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagement.database.DataBase;
import com.capgemini.librarymanagement.dto.AdminBean;
import com.capgemini.librarymanagement.dto.BookBean;
import com.capgemini.librarymanagement.dto.RequestBean;
import com.capgemini.librarymanagement.dto.StudentBean;
import com.capgemini.librarymanagement.exception.AdminException;

public class AdminDAOImp implements AdminDAO {

	public boolean register(AdminBean info) {
		for(AdminBean adminBean : DataBase.admin) {
			if(adminBean.getEmail().equals(info.getEmail())) {
				return false;
			}
		}
		DataBase.admin.add(info);
		return true;

	}

	public AdminBean auth(String email, String password) {
		for(AdminBean bean : DataBase.admin) {
			if(bean.getEmail().equals(email) && bean.getPassword().equals(password)) {
				System.out.println("Login Successful");
				return bean;
			} 
		}
		throw new AdminException("Invalid email and password");

	}

	public boolean delete(BookBean book) {
		for (BookBean bean : DataBase.book) {
			if (bean.getId()==book.getId()) {
				return false;
			}

		}
		DataBase.book.add(book);
		return true;
	}


	public boolean addBook(BookBean book) {
		for (BookBean bean : DataBase.book) {
			if (bean.getId()==book.getId()) {
				return false;
			}
		}
		DataBase.book.add(book);
		return true;
	}

	public LinkedList<BookBean> searchBookTitle(String bname) {
		LinkedList<BookBean> searchList=new LinkedList<BookBean>();
		for(int i=0;i<=DataBase.book.size()-1;i++)
		{
			BookBean retrievedBook=DataBase.book.get(i);
			String retrievedBname=retrievedBook.getName();
			if(bname.equals(retrievedBname))
			{
				searchList.add(retrievedBook);	
				return searchList;
			}
		}
		if(searchList.size()==0)
		{
			throw new AdminException("Book not found");
		}
		else
		{
			return searchList;
		}

	}

	public LinkedList<BookBean> searchBookAuthor(String bAuthor) {

		LinkedList<BookBean> searchList=new LinkedList<BookBean>();
		for(int i=0;i<=DataBase.book.size()-1;i++)
		{
			BookBean retrievedBook=DataBase.book.get(i);
			String retrievedBAuthor=retrievedBook.getAuthor();
			if(bAuthor.equals(retrievedBAuthor))
			{
				searchList.add(retrievedBook);	
				return searchList;
			}
		}
		if(searchList.size()==0)
		{
			throw new AdminException("Book not found");
		}
		else
		{
			return searchList;
		}		

	}

	public LinkedList<BookBean> searchBookType(String bookType) {
		LinkedList<BookBean> searchList=new LinkedList<BookBean>();
		for(int i=0;i<=DataBase.book.size()-1;i++)
		{
			BookBean retrievedBook=DataBase.book.get(i);
			String retrievedBookType=retrievedBook.getCategory();
			if(bookType.equals(retrievedBookType))
			{
				searchList.add(retrievedBook);	
			}
		}
		if(searchList.size()==0)
		{
			throw new AdminException("Book not found");
		}
		else
		{
			return searchList;
		}			
	}

	public int updateBook(int bid) {
		int status=0;
		for(int i=0;i<=DataBase.book.size()-1;i++)
		{
			BookBean retrievedBook=DataBase.book.get(i);
			int retrievedId=retrievedBook.getId();
			if(bid==retrievedId)
			{
				status++;
				break;
			}
		}
		throw new AdminException("Book not updated");
	}

	public boolean removeBook(int bid) {
		boolean status=false;
		for(int i=0;i<=DataBase.book.size()-1;i++)
		{
			BookBean retrievedBook=DataBase.book.get(i);
			int retrievedId=retrievedBook.getId();
			if(bid==retrievedId)
			{
				status=true;
				DataBase.book.remove(i);
				break;
			}
		}
		return status;

	}

	public LinkedList<Integer> getBookIds() {
		LinkedList<Integer> idList=new LinkedList<Integer>();
		for(int i=0;i<=DataBase.book.size()-1;i++)
		{
			BookBean retrievedBook=DataBase.book.get(i);
			int retrievedBookId=retrievedBook.getId();
			idList.add(retrievedBookId);
		}
		return idList;
	}

	public LinkedList<BookBean> getBooksInfo() {
		return DataBase.book;
	}

	public boolean issueBook(int bid) {
		for(BookBean bean : DataBase.book) {
			if(bean.getId()==bid) {
				DataBase.book.remove(bid);
				return true;
			} else {
				return false;
			}
		}
		return false;
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

		if (isValid)

		{


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

			if (noOfBooksBorrowed < 3) {

				boolean isRemoved = DataBase.book.remove(book);
				if (isRemoved) {

					noOfBooksBorrowed++;
					System.out.println(noOfBooksBorrowed);
					student.setBooksBorrowed(noOfBooksBorrowed);
					// DataBase.REQUESTDB.remove(requestInfo);
					requestInfo.setIssued(true);
					return true;
				} else {
					throw new AdminException("Book can't be borrowed");
				}

			} else {
				throw new AdminException("Student Exceeds maximum limit");
			}

		} else {
			throw new AdminException("Book data or Student data is incorrect");

		}
	}

	public boolean isBookReceived(StudentBean student, BookBean book) {
		boolean isValid = false;
		RequestBean requestInfo1 = new RequestBean();
		for (RequestBean requestInfo : DataBase.request) {

			if (requestInfo.getBookInfo().getId() == book.getId()
					&& requestInfo.getStudentInfo().getId() == student.getId() 
					&& requestInfo.isReturned() == true) {
				isValid = true;
				requestInfo1 = requestInfo;
			}
		}
		if (isValid) {

			book.setAuthor(requestInfo1.getBookInfo().getAuthor());
			book.setName(requestInfo1.getBookInfo().getName());
			DataBase.book.add(book);
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

