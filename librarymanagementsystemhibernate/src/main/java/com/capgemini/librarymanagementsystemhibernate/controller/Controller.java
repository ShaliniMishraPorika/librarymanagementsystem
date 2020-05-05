package com.capgemini.librarymanagementsystemhibernate.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.ValidationException;

import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;
import com.capgemini.librarymanagementsystemhibernate.dto.UserBean;
import com.capgemini.librarymanagementsystemhibernate.factory.AdminFactory;
import com.capgemini.librarymanagementsystemhibernate.factory.StudentFactory;
import com.capgemini.librarymanagementsystemhibernate.factory.UserFactory;
import com.capgemini.librarymanagementsystemhibernate.service.AdminService;
import com.capgemini.librarymanagementsystemhibernate.service.StudentService;
import com.capgemini.librarymanagementsystemhibernate.service.UserService;
import com.capgemini.librarymanagementsystemhibernate.validation.ValidationAdminStudent;

public class Controller {
	public static void main(String[] args) {
		doReg();
	}
	public static void doReg() {
		boolean flag = false;

		int userId = 0; 
		String userName = null; 
		String userMobile = null;
		String userEmail = null;
		String userPassword = null;
		String userRole = null;

		ValidationAdminStudent validation = new ValidationAdminStudent();
		Scanner scanner = new Scanner(System.in);
		do {
			UserService service = UserFactory.getUserService();
			System.out.println("press 1 for registration");
			System.out.println("press 2 for authentication");
			int a = scanner.nextInt();
			switch (a) {
			case 1:
				do {
					try {
						System.out.println("Enter ID :");
						userId = scanner.nextInt();
						validation.validatedId(userId);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Id should contains only digits");
					} catch (ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				do {
					try {
						System.out.println("Enter Name :");
						userName = scanner.next();
						validation.validatedName(userName);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Name should contains only Alphabates");
					} catch (ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				do {
					try {
						System.out.println("Enter Mobile :");
						userMobile = scanner.next();
						validation.validatedMobile(userMobile);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Mobile Number  should contains only numbers");
					} catch (ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				do {
					try {
						System.out.println("Enter Email :");
						userEmail = scanner.next();
						validation.validatedEmail(userEmail);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Email should be proper ");
					} catch (ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				do {
					try {
						System.out.println("Enter Password :");
						userPassword = scanner.next();
						validation.validatedPassword(userPassword);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Enter correct Password ");
					} catch (ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				do {
					try {
						System.out.println("Enter Role :");
						userRole = scanner.next();
						validation.validatedRole(userRole);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Role should contains only admin/student");
					} catch (ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				UserBean bean = new UserBean();
				bean.setName(userName);
				bean.setId(userId);
				bean.setMobile(userMobile);
				bean.setEmail(userEmail);
				bean.setPassword(userPassword);
				bean.setRole(userRole);
				boolean addUser = service.register(bean);
				//System.out.println(addUser);
				if(addUser) {
					System.out.println("Email already exist");
				} else {
					System.out.println("registered");
				}
				break;
			case 2:
				System.out.println("Enter Email");
				userEmail = scanner.next();
				System.out.println("Enter Password");
				userPassword = scanner.next();

				try {
					UserBean userBean = service.login(userEmail, userPassword);
					if(userBean!=null) {
						System.out.println("Login successful");
						String role = userBean.getRole();
						if(role.equalsIgnoreCase("admin")) {
							do{
								doAdmin();
							}while(true);
						}
						if(role.equalsIgnoreCase("student")) {
							doStudent();
						}
					} else {
						System.out.println("login failed");
					}

				} catch (Exception e) {
					System.out.println("Invalid Credentials");
				}
				break;
			default:
				break;
			}//end of switch case
		} while(true);
	}//end of doReg

	public static void doAdmin() {
		boolean flag = false;
		int userId = 0;
		int bookId = 0; 
		String bookAuthor = null; 
		String bookTitle = null;
		String bookCategory = null;
		String bookPublisherName = null;
		int bookCopies = 0;
		long bookISBN = 0;
		int bookCopyRightYear = 0;
		String bookStatus = null;
		BookBean bookBean = new BookBean();
		ValidationAdminStudent validation = new ValidationAdminStudent();

		AdminService service = AdminFactory.getAdminService();
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("press 1 to add book");
			System.out.println("Press 2 to update");
			System.out.println("Press 3 to Search the Book by title");
			System.out.println("Press 4 to Search the Book by author");
			System.out.println("Press 5 to Search the Book by Id");
			System.out.println("Press 6 to remove the Books");
			System.out.println("Press 7 to Get the Book Id's");
			System.out.println("Press 8 to Get the Book Information");
			System.out.println("Press 9 to Issue the Book");
			System.out.println("Press 10 to Return the Book");
			System.out.println("Press 11 to Main");

			int a = scanner.nextInt();
			switch (a) {
			case 1:
				do {
					try {
						System.out.println("Enter Book-ID :");
						bookId = scanner.nextInt();
						validation.validatedId(bookId);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Id should contains only digits");
					} catch (ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				do {
					try {
						System.out.println("Enter Book Title :");
						bookTitle = scanner.next();
						validation.validatedName(bookTitle);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Book-Title should contains only Alphabates");
					} catch (ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				do {
					try {
						System.out.println("Enter Author :");
						bookAuthor = scanner.next();
						validation.validatedName(bookAuthor);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Author Name should contains only Alphabates");
					} catch (ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);


				do {
					try {
						System.out.println("Enter Category :");
						bookCategory = scanner.next();
						validation.validatedName(bookCategory);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Book-Category should contains only Alphabates");
					} catch (ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				do {
					try {
						System.out.println("Enter No of Copies :");
						bookCopies = scanner.nextInt();
						validation.validatedNum(bookCopies);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Book-Copies should contain Atleast 1");
					} catch (ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				do {
					try {
						System.out.println("Enter PublisherName :");
						bookPublisherName = scanner.next();
						validation.validatedName(bookPublisherName);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Book-PublisherName should contains only Alphabates");
					} catch (ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				do {
					try {
						System.out.println("Enter ISBN Id :");
						bookISBN = scanner.nextLong();
						validation.validatedISBN(bookISBN);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Book-ISBN Number contains only 10 digits");
					} catch (ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				do {
					try {
						System.out.println("Enter CopyRightYear :");
						bookCopyRightYear = scanner.nextInt();
						validation.validatedYear(bookCopyRightYear);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Book-COpyRightYear contains only year");
					} catch (ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				do {
					try {
						System.out.println("Enter Book-Status :");
						bookStatus = scanner.next();
						validation.validatedName(bookStatus);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Book-Status contains either Old or New");
					} catch (ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				bookBean.setBookId(bookId);	
				bookBean.setTitle(bookTitle);
				bookBean.setCategory(bookCategory);
				bookBean.setAuthor(bookAuthor);
				bookBean.setCopies(bookCopies);
				bookBean.setPublisher_name(bookPublisherName);
				bookBean.setISBN(bookISBN);
				bookBean.setCategory(bookCategory);
				bookBean.setStatus(bookStatus);
				bookBean.setCopyright_year(bookCopyRightYear);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
				Calendar cal = Calendar.getInstance();
				String dateAdded = sdf.format(cal.getTime());
				bookBean.setDateAdded(dateAdded);
				boolean check2 = service.addBook(bookBean);
				if(check2) {
					System.out.println("Book Added");
				} else {
					System.out.println("Book already exist");
				}
				break;
			case 2:
				do {
					try {
						System.out.println("Enter Book-ID to be Updated:");
						bookId = scanner.nextInt();
						validation.validatedId(bookId);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Id should contains only digits");
					} catch (ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				do {
					try {
						System.out.println("Enter Book-Title :");
						bookTitle = scanner.next();
						validation.validatedName(bookTitle);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Book-Title should contains only Alphabates");
					} catch (ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);
				BookBean bean = new BookBean();
				bean.setBookId(bookId);
				bean.setTitle(bookTitle);
				boolean updated = service.update(bean);
				if (updated == true) {
					System.out.println("Book is updated");
				} else {
					System.out.println("Book is not updated");
				}
				break;
			case 3:
				do {
					try {
						System.out.println("Search the book by the Book_Title :");
						bookTitle = scanner.next();
						validation.validatedName(bookTitle);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Book-Author should contains only Alphabates");
					} catch (ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);
		
				bookBean.setTitle(bookTitle);
				BookBean bookBean1 =  service.searchBookByTitle(bookTitle);	
				
					if (bookBean1 != null) {
						System.out.println("-----------------------------------");
						System.out.println("Book_Id is-->"+bookBean1.getBookId());
						System.out.println("Book_Name is-->"+bookBean1.getTitle());
						System.out.println("Book_Author is-->"+bookBean1.getAuthor());
						System.out.println("Book_Category is-->"+bookBean1.getCategory());
						System.out.println("Book_Copies is-->"+bookBean1.getCopies());
						System.out.println("Book_PublisherName is-->"+bookBean1.getPublisher_name());
						System.out.println("Book_ISBN is-->"+bookBean1.getISBN());
						System.out.println("Book_CopyRightYear is-->"+bookBean1.getCopyright_year());
						System.out.println("Book_DateAdded is-->"+bookBean1.getDateAdded());
						System.out.println("Book_Status is-->"+bookBean1.getStatus());
					} else {
						System.out.println("No books are available with this title.");
					}
		
				break;
			case 4:
				do {
					try {
						System.out.println("Search the book by the Author Name:");
						bookAuthor = scanner.next();
						validation.validatedName(bookAuthor);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Book-Author should contains only Alphabates");
					} catch (ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);
				
				bookBean.setAuthor(bookAuthor);
				BookBean bookBean2 =  service.searchBookByAuthor(bookAuthor);

					if (bookBean2 != null) {
						System.out.println("-----------------------------------");
						System.out.println("Book_Id is-->"+bookBean2.getBookId());
						System.out.println("Book_Name is-->"+bookBean2.getTitle());
						System.out.println("Book_Author is-->"+bookBean2.getAuthor());
						System.out.println("Book_Category is-->"+bookBean2.getCategory());
						System.out.println("Book_Copies is-->"+bookBean2.getCopies());
						System.out.println("Book_PublisherName is-->"+bookBean2.getPublisher_name());
						System.out.println("Book_ISBN is-->"+bookBean2.getISBN());
						System.out.println("Book_CopyRightYear is-->"+bookBean2.getCopyright_year());
						System.out.println("Book_DateAdded is-->"+bookBean2.getDateAdded());
						System.out.println("Book_Status is-->"+bookBean2.getStatus());
					} else {
						System.out.println("No books are available written by this author");
					}
				break;
			case 5:
				do {
					try {
						System.out.println("Search the book by the Book-Id :");
						bookId = scanner.nextInt();
						validation.validatedId(bookId);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Book-Id should contains only digits");
					} catch (ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);
				bookBean.setBookId(bookId);
				BookBean bookBean3 = service.searchBookById(bookId);
					if (bookBean3 != null) {
						System.out.println("-----------------------------------");
						System.out.println("Book_Id is-->"+bookBean3.getBookId());
						System.out.println("Book_Name is-->"+bookBean3.getTitle());
						System.out.println("Book_Author is-->"+bookBean3.getAuthor());
						System.out.println("Book_Category is-->"+bookBean3.getCategory());
						System.out.println("Book_Copies is-->"+bookBean3.getCopies());
						System.out.println("Book_PublisherName is-->"+bookBean3.getPublisher_name());
						System.out.println("Book_ISBN is-->"+bookBean3.getISBN());
						System.out.println("Book_CopyRightYear is-->"+bookBean3.getCopyright_year());
						System.out.println("Book_DateAdded is-->"+bookBean3.getDateAdded());
						System.out.println("Book_Status is-->"+bookBean3.getStatus());
					} else {
						System.out.println("No books are available with this Id.");
					}
				break;
			case 6:
				do {
					try {
						System.out.println("Enter the book_Id to delete :");
						bookId = scanner.nextInt();
						validation.validatedId(bookId);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Id should contains only digits");
					} catch (ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				if (bookId == 0) {
					System.out.println("Enter the Valid Book_Id");
				} else {
					boolean remove = service.delete(bookId);
					if (remove) {
						System.out.println("The Book is removed");
					} else {
						System.out.println("The Book is not removed");
					}
				}
				break;
			case 7:
				List<Integer> ids = service.getBookIds();
				for (Integer integer : ids) {
					if (integer != null) {
						System.out.println(integer);
					} else {
						System.out.println("No Books Ids are available");
					}
				}
				break;
			case 8:
				List<BookBean> list =  service.getBooksInfo();
				for (BookBean bookBean4 : list) {

					if (bookBean4 != null) {
						System.out.println("-----------------------------------");
						System.out.println("Book_Id is-->"+bookBean4.getBookId());
						System.out.println("Book_Name is-->"+bookBean4.getTitle());
						System.out.println("Book_Author is-->"+bookBean4.getAuthor());
						System.out.println("Book_Category is-->"+bookBean4.getCategory());
						System.out.println("Book_Copies is-->"+bookBean4.getCopies());
						System.out.println("Book_PublisherName is-->"+bookBean4.getPublisher_name());
						System.out.println("Book_ISBN is-->"+bookBean4.getISBN());
						System.out.println("Book_CopyRightYear is-->"+bookBean4.getCopyright_year());
						System.out.println("Book_DateAdded is-->"+bookBean4.getDateAdded());
						System.out.println("Book_Status is-->"+bookBean4.getStatus());
					} else {
						System.out.println("Books info is not present");
					}
				}
				break;
			case 9:
				do {
					try {
						System.out.println("enter Book Id");
						bookId = scanner.nextInt();
						validation.validatedId(bookId);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Id should contains only digits");
					} catch (ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				do {
					try {
						System.out.println("enter User Id");
						userId = scanner.nextInt();
						validation.validatedId(userId);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Id should contains only digits");
					} catch (ValidationException e) {
						flag = false; 
						System.err.println(e.getMessage());
					}
				} while (!flag);

				try {
					boolean isIssued = service.issueBook(userId, bookId);
					if (isIssued) {
						System.out.println("Book Issued");
					} else {
						System.out.println("Book cannot be issued");
					}

				} catch (Exception e) {
					System.out.println("Invalid data request book cannot be issued");
				}
				break;
			case 10:
				do {
					try {
						System.out.println("enter Book Id");
						bookId = scanner.nextInt();
						validation.validatedId(bookId);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Id should contains only digits");
					} catch (ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				do {
					try {
						System.out.println("enter User Id");
						userId = scanner.nextInt();
						validation.validatedId(userId);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Id should contains only digits");
					} catch (ValidationException e) {
						flag = false; 
						System.err.println(e.getMessage());
					}
				} while (!flag);

				boolean isReturned = service.returnBook(userId, bookId);
				if(isReturned == true) {
					System.out.println("Book Returned");
				}else {
					System.out.println("Book cannot be returned");
				}
				break;
			case 11:
				doReg();
			default:
				System.out.println("Invalid Choice");
				break;
			}//end of admin methods
		} while(true); //end of do-while main
	}//end of admin

	public static void doStudent() {
		boolean flag = false;
		int userId = 0;
		int bookId = 0; 
		String bookAuthor = null; 
		String bookTitle = null;
		String bookCategory = null;
		String bookPublisherName = null;
		String bookIssuedate = null;
		String bookReturndate = null;
		int bookCopies = 0;
		long bookISBN = 0;
		int bookCopyRightYear = 0;
		Date bookDateAdded = null;
		String bookStatus = null;
		int returnedId = 0;
		int requestId = 0;
		ValidationAdminStudent validation = new ValidationAdminStudent();

		StudentService service = StudentFactory.getStudentService();
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("Press 1 to Search the Book by title");
			System.out.println("Press 2 to Search the Book by author");
			System.out.println("Press 3 to Search the Book by Id");
			System.out.println("Press 4 to Get the Book Id's");
			System.out.println("Press 5 to Get the Book Information");
			System.out.println("press 6 to request book");
			System.out.println("Press 8 to main");

			int a = scanner.nextInt();
			switch (a) {
			case 1:
				do {
					try {
						System.out.println("Search the book by the Book_Title :");
						bookTitle = scanner.next();
						validation.validatedName(bookTitle);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Book-Author should contains only Alphabates");
					} catch (ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				List<BookBean> booktitle = (List<BookBean>) service.searchBookByTitle(bookTitle);
				for (BookBean bookBean1 : booktitle) {	
					if (bookBean1 != null) {
						System.out.println("-----------------------------------");
						System.out.println("Book_Id is-->"+bookBean1.getBookId());
						System.out.println("Book_Name is-->"+bookBean1.getTitle());
						System.out.println("Book_Author is-->"+bookBean1.getAuthor());
						System.out.println("Book_Category is-->"+bookBean1.getCategory());
						System.out.println("Book_Copies is-->"+bookBean1.getCopies());
						System.out.println("Book_PublisherName is-->"+bookBean1.getPublisher_name());
						System.out.println("Book_ISBN is-->"+bookBean1.getISBN());
						System.out.println("Book_CopyRightYear is-->"+bookBean1.getCopyright_year());
						System.out.println("Book_DateAdded is-->"+bookBean1.getDateAdded());
						System.out.println("Book_Status is-->"+bookBean1.getStatus());
					} else {
						System.out.println("No books are available with this title.");
					}
				}
				break;
			case 2:
				do {
					try {
						System.out.println("Search the book by the Author Name:");
						bookAuthor = scanner.next();
						validation.validatedName(bookAuthor);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Book-Author should contains only Alphabates");
					} catch (ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				List<BookBean> bookauthor = (List<BookBean>) service.searchBookByAuthor(bookAuthor);
				for (BookBean bookBean1 : bookauthor) {

					if (bookBean1 != null) {
						System.out.println("-----------------------------------");
						System.out.println("Book_Id is-->"+bookBean1.getBookId());
						System.out.println("Book_Name is-->"+bookBean1.getTitle());
						System.out.println("Book_Author is-->"+bookBean1.getAuthor());
						System.out.println("Book_Category is-->"+bookBean1.getCategory());
						System.out.println("Book_Copies is-->"+bookBean1.getCopies());
						System.out.println("Book_PublisherName is-->"+bookBean1.getPublisher_name());
						System.out.println("Book_ISBN is-->"+bookBean1.getISBN());
						System.out.println("Book_CopyRightYear is-->"+bookBean1.getCopyright_year());
						System.out.println("Book_DateAdded is-->"+bookBean1.getDateAdded());
						System.out.println("Book_Status is-->"+bookBean1.getStatus());
					} else {
						System.out.println("No books are available written by this author");
					}
				}
				break;

			case 3:
				do {
					try {
						System.out.println("Search the book by the Book-Id :");
						bookId = scanner.nextInt();
						validation.validatedId(bookId);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Book-Id should contains only digits");
					} catch (ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);
				
				List<BookBean> bookIds = (List<BookBean>) service.searchBookById(bookId);
				for (BookBean bookBean1 : bookIds) {
					if (bookBean1 != null) {
						System.out.println("-----------------------------------");
						System.out.println("Book_Id is-->"+bookBean1.getBookId());
						System.out.println("Book_Name is-->"+bookBean1.getTitle());
						System.out.println("Book_Author is-->"+bookBean1.getAuthor());
						System.out.println("Book_Category is-->"+bookBean1.getCategory());
						System.out.println("Book_Copies is-->"+bookBean1.getCopies());
						System.out.println("Book_PublisherName is-->"+bookBean1.getPublisher_name());
						System.out.println("Book_ISBN is-->"+bookBean1.getISBN());
						System.out.println("Book_CopyRightYear is-->"+bookBean1.getCopyright_year());
						System.out.println("Book_DateAdded is-->"+bookBean1.getDateAdded());
						System.out.println("Book_Status is-->"+bookBean1.getStatus());
					} else {
						System.out.println("No books are available with this Id.");
					}
				}
				break;
			case 4:
				LinkedList<Integer> ids = (LinkedList<Integer>) service.getBookIds();
				for (Integer integer : ids) {

					if (integer != null) {
						System.out.println(integer);
					} else {
						System.out.println("No Books Ids are available");
					}
				}
				break;
			case 5:
				LinkedList<BookBean> list = (LinkedList<BookBean>) service.getBooksInfo();
				for (BookBean bookBean1 : list) {

					if (bookBean1 != null) {
						System.out.println("-----------------------------------");
						System.out.println("Book_Id is-->"+bookBean1.getBookId());
						System.out.println("Book_Name is-->"+bookBean1.getTitle());
						System.out.println("Book_Author is-->"+bookBean1.getAuthor());
						System.out.println("Book_Category is-->"+bookBean1.getCategory());
						System.out.println("Book_Copies is-->"+bookBean1.getCopies());
						System.out.println("Book_PublisherName is-->"+bookBean1.getPublisher_name());
						System.out.println("Book_ISBN is-->"+bookBean1.getISBN());
						System.out.println("Book_CopyRightYear is-->"+bookBean1.getCopyright_year());
						System.out.println("Book_DateAdded is-->"+bookBean1.getDateAdded());
						System.out.println("Book_Status is-->"+bookBean1.getStatus());
					} else {
						System.out.println("Books info is not present");
					}
				}
				break;

			case 6:
				do {
					try {
						System.out.println("enter Book Id");
						bookId = scanner.nextInt();
						validation.validatedId(bookId);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Id should contains only digits");
					} catch (ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				do {
					try {
						System.out.println("enter return Id");
						returnedId = scanner.nextInt();
						validation.validatedId(returnedId);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Id should contains only digits");
					} catch (ValidationException e) {
						flag = false; 
						System.err.println(e.getMessage());
					}
				} while (!flag);
				
				boolean returnBook = service.reqReturnBook(returnedId, bookId);
				if(returnBook) {
					System.out.println("Book returned");
				}else {
					System.out.println("book not returned");
				}
				break;
			case 7:
				do {
					try {
						System.out.println("enter Book Id");
						bookId = scanner.nextInt();
						validation.validatedId(bookId);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Id should contains only digits");
					} catch (ValidationException e) {
						flag = false;
						System.err.println(e.getMessage());
					}
				} while (!flag);

				do {
					try {
						System.out.println("enter req Id");
						requestId = scanner.nextInt();
						validation.validatedId(requestId);
						flag = true;
					} catch (InputMismatchException e) {
						flag = false;
						System.err.println("Id should contains only digits");
					} catch (ValidationException e) {
						flag = false; 
						System.err.println(e.getMessage());
					}
				} while (!flag);
				
				boolean book = service.request(requestId, bookId);
				if(book == true) {
					System.out.println("requested successfully");
				}else {
					System.out.println("book not found");
				}
				break;
			case 8:
				doReg();
			default:
				System.out.println("Invalid Choice");
				break;
			}//end of student methods
		} while(true); //end of do-while main

	}//end of student
}