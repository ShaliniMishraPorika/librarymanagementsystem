package com.capgemini.librarymanagement.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.capgemini.librarymanagement.dto.AdminBean;
import com.capgemini.librarymanagement.dto.BookBean;
import com.capgemini.librarymanagement.dto.RequestBean;
import com.capgemini.librarymanagement.dto.StudentBean;
import com.capgemini.librarymanagement.factory.AdminFactory;
import com.capgemini.librarymanagement.factory.StudentFactory;
import com.capgemini.librarymanagement.factory.ValidationException;
import com.capgemini.librarymanagement.service.AdminService;
import com.capgemini.librarymanagement.service.StudentService;
import com.capgemini.librarymanagement.validation.ValidationAdminStudent;

public class Controller {
	public static void main(String[] args) {
		doReg();
	}

	public static void doReg() {

		boolean flag = false;

		int regId = 0;
		String regName = null;
		String regMobile = null;
		String regEmail = null;
		String regPassword = null;

		int regId1 = 0;
		String regName1 = null;
		String regMobile1 = null;
		String regEmail1 = null;
		String regPassword1 = null;

		int bookId = 0;
		String bookAuthor = null;
		String bookTitle = null;
		String bookCategory = null;
		String bookPublisherName = null;
		// String bookIssuedate = null;
		// String bookReturndate = null;
		int bookCopies = 0;
		long bookISBN = 0;
		int bookCopyRightYear = 0;
		// Date bookDateAdded = null;
		String bookStatus = null;

		ValidationAdminStudent validation = new ValidationAdminStudent();

		Scanner scanner = new Scanner(System.in);

		do {
			System.out.println("----------WELCOME TO LIBRARY-----------");
			System.out.println("Press 1 to Admin Page");
			System.out.println("Press 2 to Student Page");
			System.out.println("-----------------------------------");

			int i = scanner.nextInt();
			switch (i) {
			case 1:
				AdminService service = AdminFactory.getAdminService();
				do {
					System.out.println("-----------------------------------");
					System.out.println("Press 1 to Admin Register");
					System.out.println("Press 2 to Login");
					System.out.println("Press 3 to exit");
					System.out.println("-----------------------------------");
					int choice = scanner.nextInt();
					switch (choice) {
					case 1:
						do {
							try {
								System.out.println("Enter ID :");
								regId = scanner.nextInt();
								validation.validatedId(regId);
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
								regName = scanner.next();
								validation.validatedName(regName);
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
								regMobile = scanner.next();
								validation.validatedMobile(regMobile);
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
								regEmail = scanner.next();
								validation.validatedEmail(regEmail);
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
								regPassword = scanner.next();
								validation.validatedPassword(regPassword);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Enter correct Password ");
							} catch (ValidationException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);

						AdminBean bean = new AdminBean();
						bean.setAid(regId);
						bean.setAname(regName);
						bean.setEmail(regEmail);
						bean.setPassword(regPassword);

						boolean check = service.register(bean);
						if (check) {
							System.out.println("Registered");
						} else {
							System.out.println("Email already exist");
						}

						break;

					case 2:
						System.out.println("Enter email :");
						String email = scanner.next();
						System.out.println("Enter Password :");
						String password = scanner.next();
						try {
							AdminBean authBean = service.login(email, password);
							System.out.println("Logged in");

							do {
								System.out.println("-----------------------------------");
								System.out.println("Press 1 to Add Books");
								System.out.println("Press 2 to update");
								System.out.println("Press 3 to Search the Book by Author");
								System.out.println("Press 4 to Search the Book by Title");
								System.out.println("Press 5 to Search the Book by Category");
								System.out.println("Press 6 to remove the Books");
								System.out.println("Press 7 to Get the Book Id's");
								System.out.println("Press 8 to Get the All The Books ");
								System.out.println("Press 9 to Book Issue");
								System.out.println("Press 10 to Show Users");
								System.out.println("Press 11 to Show Requests");
								System.out.println("Press 12 Receive Returned Book");
								System.out.println("Press 13 to main");
								System.out.println("-----------------------------------");
								int choice1 = scanner.nextInt();
								switch (choice1) {
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
											validation.validatedStatus(bookStatus);
											flag = true;
										} catch (InputMismatchException e) {
											flag = false;
											System.err.println("Book-Status contains either Old or New");
										} catch (ValidationException e) {
											flag = false;
											System.err.println(e.getMessage());
										}
									} while (!flag);

									BookBean bookBean = new BookBean();
									bookBean.setId(bookId);
									bookBean.setTitle(bookTitle);
									bookBean.setCategory(bookCategory);
									bookBean.setAuthor(bookAuthor);
									bookBean.setCopies(bookCopies);
									bookBean.setPublisherName(bookPublisherName);
									bookBean.setISBN(bookISBN);
									bookBean.setCategory(bookCategory);
									bookBean.setStatus(bookStatus);
									bookBean.setCopyRightYear(bookCopyRightYear);
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
									Calendar cal = Calendar.getInstance();
									String dateAdded = sdf.format(cal.getTime());
									bookBean.setDateAdded(dateAdded);
									boolean check2 = service.addBook(bookBean);
									if (check2) {
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

									BookBean bean2 = new BookBean();
									bean2.setId(bookId);
									bean2.setTitle(bookTitle);
									boolean updated = service.updateBook(bean2);
									if (updated == true) {
										System.out.println("Book is updated");
									} else {
										System.out.println("Book is not updated");
									}
									break;
								case 3:

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

									// BookBean bean3 = new BookBean();
									// bean3.setAuthor(bookAuthor);
									List<BookBean> bookauthor = service.searchBookByAuthor(bookAuthor);
									for (BookBean bookBean1 : bookauthor) {

										if (bookBean1 != null) {
											System.out.println("-----------------------------------");
											System.out.println("Book_Id is-->" + bookBean1.getId());
											System.out.println("Book_Name is-->" + bookBean1.getTitle());
											System.out.println("Book_Author is-->" + bookBean1.getAuthor());
											System.out.println("Book_Category is-->" + bookBean1.getCategory());
											System.out.println("Book_Copies is-->" + bookBean1.getCopies());
											System.out
													.println("Book_PublisherName is-->" + bookBean1.getPublisherName());
											System.out.println("Book_ISBN is-->" + bookBean1.getISBN());
											System.out
													.println("Book_CopyRightYear is-->" + bookBean1.getCopyRightYear());
											System.out.println("Book_DateAdded is-->" + bookBean1.getDateAdded());
											System.out.println("Book_Status is-->" + bookBean1.getStatus());
										} else {
											System.out.println("No books are available written by this author");
										}
									}
									break;
								case 4:

									do {
										try {
											System.out.println("  Search the book by the Book_Title :");
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

									// BookBean bean4 = new BookBean();
									// bean4.setAuthor(bookTitle);
									List<BookBean> booktitle = service.searchBookByTitle(bookTitle);
									for (BookBean bookBean1 : booktitle) {
										if (bookBean1 != null) {
											System.out.println("-----------------------------------");
											System.out.println("Book_Id is-->" + bookBean1.getId());
											System.out.println("Book_Name is-->" + bookBean1.getTitle());
											System.out.println("Book_Author is-->" + bookBean1.getAuthor());
											System.out.println("Book_Category is-->" + bookBean1.getCategory());
											System.out.println("Book_Copies is-->" + bookBean1.getCopies());
											System.out
													.println("Book_PublisherName is-->" + bookBean1.getPublisherName());
											System.out.println("Book_ISBN is-->" + bookBean1.getISBN());
											System.out
													.println("Book_CopyRightYear is-->" + bookBean1.getCopyRightYear());
											System.out.println("Book_DateAdded is-->" + bookBean1.getDateAdded());
											System.out.println("Book_Status is-->" + bookBean1.getStatus());
										} else {
											System.out.println("No books are available with this title.");
										}
									}
									break;
								case 5:

									do {
										try {
											System.out.println("Search the book by the Book_Category :");
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
									//
									// BookBean bean5 = new BookBean();
									// bean5.setAuthor(bookCategory);
									List<BookBean> bookIds = service.searchBookByCategory(bookCategory);
									for (BookBean bookBean1 : bookIds) {
										if (bookBean1 != null) {
											System.out.println("-----------------------------------");
											System.out.println("Book_Id is-->" + bookBean1.getId());
											System.out.println("Book_Name is-->" + bookBean1.getTitle());
											System.out.println("Book_Author is-->" + bookBean1.getAuthor());
											System.out.println("Book_Category is-->" + bookBean1.getCategory());
											System.out.println("Book_Copies is-->" + bookBean1.getCopies());
											System.out
													.println("Book_PublisherName is-->" + bookBean1.getPublisherName());
											System.out.println("Book_ISBN is-->" + bookBean1.getISBN());
											System.out
													.println("Book_CopyRightYear is-->" + bookBean1.getCopyRightYear());
											System.out.println("Book_DateAdded is-->" + bookBean1.getDateAdded());
											System.out.println("Book_Status is-->" + bookBean1.getStatus());
										} else {
											System.out.println("No books are available with this Id.");
										}
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
										// BookBean bean6 = new BookBean();
										// bean6.setId(bookId);
										boolean remove = service.removeBook(bookId);
										if (remove) {
											System.out.println("The Book is removed");
										} else {
											System.out.println("The Book is not removed");
										}
									}
									break;
								case 7:
									ArrayList<Integer> ids = service.getBookIds();
									for (Integer integer : ids) {

										if (integer != null) {
											System.out.println(integer);
										} else {
											System.out.println("No Books Ids are available");
										}
									}
									break;
								case 8:
									ArrayList<BookBean> info = service.getBooksInfo();
									for (BookBean bookBean1 : info) {

										if (bookBean1 != null) {
											System.out.println("-----------------------------------");
											System.out.println("Book_Id is-->" + bookBean1.getId());
											System.out.println("Book_Name is-->" + bookBean1.getTitle());
											System.out.println("Book_Author is-->" + bookBean1.getAuthor());
											System.out.println("Book_Category is-->" + bookBean1.getCategory());
											System.out.println("Book_Copies is-->" + bookBean1.getCopies());
											System.out
													.println("Book_PublisherName is-->" + bookBean1.getPublisherName());
											System.out.println("Book_ISBN is-->" + bookBean1.getISBN());
											System.out
													.println("Book_CopyRightYear is-->" + bookBean1.getCopyRightYear());
											System.out.println("Book_DateAdded is-->" + bookBean1.getDateAdded());
											System.out.println("Book_Status is-->" + bookBean1.getStatus());
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
											regId = scanner.nextInt();
											validation.validatedId(regId);
											flag = true;
										} catch (InputMismatchException e) {
											flag = false;
											System.err.println("Id should contains only digits");
										} catch (ValidationException e) {
											flag = false;
											System.err.println(e.getMessage());
										}
									} while (!flag);

									StudentBean userBean2 = new StudentBean();
									BookBean bookBean2 = new BookBean();
									bookBean2.setId(bookId);
									userBean2.setId(regId);

									try {
										boolean isIssued = service.bookIssue(userBean2, bookBean2);
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
									try {
										System.out.println("Users of Library are :");
										System.out.println("-------------------------------");

										List<StudentBean> userInfos = service.showUsers();
										for (StudentBean infos : userInfos) {

											System.out.println("User id ---------- " + infos.getId());
											System.out.println("User Name -------- " + infos.getName());
											System.out.println("User Email------ " + infos.getEmail());
											System.out.println(
													"User No Of Books Borrowed ------- " + infos.getBooksBorrowed());

										}
									} catch (Exception e) {
										System.out.println("no books present in library");
									}
									break;
								case 11:
									try {
										System.out.println("Requests for Books are :");
										System.out.println("-------------------------------");

										List<RequestBean> requestInfos = service.showRequests();
										for (RequestBean info1 : requestInfos) {

											System.out.println("Book id ---------- " + info1.getBookInfo().getId());
											System.out.println("Book Name -------- " + info1.getBookInfo().getAuthor());
											System.out.println("User id----------- " + info1.getStudentInfo().getId());
											System.out
													.println("User Name -------- " + info1.getStudentInfo().getName());
											System.out.println("Book Issued ------" + info1.isIssued());
											System.out.println("Book Returned------" + info1.isReturned());

										}
									} catch (Exception e) {
										System.out.println("no books present in library");
									}
									break;
								case 12:
									System.out.println("Receive Returned Book");
									System.out.println("-----------------------");

									do {
										try {
											System.out.println("Enter Student Id");
											regId = scanner.nextInt();
											validation.validatedId(regId);
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
											System.out.println("Enter Book Id");
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

									StudentBean student = new StudentBean();
									BookBean book = new BookBean();
									student.setId(regId);
									book.setId(bookId);
									boolean isReceive = service.isBookReceived(student, book);
									if (isReceive) {
										System.out.println(" Received Returned book");
									} else {
										System.out.println("Invalid ! Admin unable to receive");
									}
									break;
								case 13:
									doReg();
								default:
									System.out.println("Invalid Choice");
									break;
								}
							} while (true);
						} catch (Exception e) {
							System.out.println("Invalid Credentials");
						}

						break;
					case 3:
						doReg();
						break;
					default:
						System.out.println("Invalid Choice");
						break;
					}
				} while (true);

			case 2:
				StudentService service1 = StudentFactory.getStudentService();
				do {
					System.out.println("-----------------------------------");
					System.out.println("Press 1 to Student Register");
					System.out.println("Press 2 to Student Login");
					System.out.println("3 to main");
					System.out.println("-----------------------------------");
					int choice = scanner.nextInt();
					switch (choice) {
					case 1:
						do {
							try {
								System.out.println("Enter ID :");
								regId1 = scanner.nextInt();
								validation.validatedId(regId1);
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
								regName1 = scanner.next();
								validation.validatedName(regName1);
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
								regMobile1 = scanner.next();
								validation.validatedMobile(regMobile1);
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
								regEmail1 = scanner.next();
								validation.validatedEmail(regEmail1);
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
								regPassword1 = scanner.next();
								validation.validatedPassword(regPassword1);
								flag = true;
							} catch (InputMismatchException e) {
								flag = false;
								System.err.println("Enter correct Password ");
							} catch (ValidationException e) {
								flag = false;
								System.err.println(e.getMessage());
							}
						} while (!flag);

						StudentBean bean1 = new StudentBean();
						bean1.setId(regId1);
						bean1.setName(regName1);
						bean1.setPhone(regMobile1);
						bean1.setEmail(regEmail1);
						bean1.setPassword(regPassword1);

						boolean check = service1.register(bean1);
						if (check) {
							System.out.println("Registered");
						} else {
							System.out.println("Email already exist");
						}
						break;
					case 2:
						System.out.println("Enter email :");
						String email = scanner.next();
						System.out.println("Enter Password :");
						String password = scanner.next();
						try {
							StudentBean studentBean = service1.auth(email, password);
							System.out.println("Logged in");
							do {
								System.out.println("--------------------------------------------");
								System.out.println("Press 1 to Search the Book by Author");
								System.out.println("Press 2 to Search the Book by Title");
								System.out.println("Press 3 to Search the Book by Id");
								System.out.println("Press 4 to Get the Book Id's");
								System.out.println("Press 5 to Get the Books Information");
								System.out.println("Press 6 to Request the Book");
								System.out.println("Press 7 to Return the Book");
								System.out.println("Press 8 to main");
								System.out.println("--------------------------------------------");
								int choice2 = scanner.nextInt();
								switch (choice2) {

								case 1:
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

									// BookBean bean3 = new BookBean();
									// bean3.setAuthor(bookAuthor);
									List<BookBean> bookauthor = service1.searchBookByAuthor(bookAuthor);
									for (BookBean bookBean1 : bookauthor) {

										if (bookBean1 != null) {
											System.out.println("-----------------------------------");
											System.out.println("Book_Id is-->" + bookBean1.getId());
											System.out.println("Book_Name is-->" + bookBean1.getTitle());
											System.out.println("Book_Author is-->" + bookBean1.getAuthor());
											System.out.println("Book_Category is-->" + bookBean1.getCategory());
											System.out.println("Book_Copies is-->" + bookBean1.getCopies());
											System.out
													.println("Book_PublisherName is-->" + bookBean1.getPublisherName());
											System.out.println("Book_ISBN is-->" + bookBean1.getISBN());
											System.out
													.println("Book_CopyRightYear is-->" + bookBean1.getCopyRightYear());
											System.out.println("Book_DateAdded is-->" + bookBean1.getDateAdded());
											System.out.println("Book_Status is-->" + bookBean1.getStatus());
										} else {
											System.out.println("No books are available written by this author");
										}
									}
									break;
								case 2:
									do {
										try {
											System.out.println("  Search the book by the Book_Title :");
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

									// BookBean bean4 = new BookBean();
									// bean4.setAuthor(bookTitle);
									List<BookBean> booktitle = service1.searchBookByTitle(bookTitle);
									for (BookBean bookBean1 : booktitle) {
										if (bookBean1 != null) {
											System.out.println("-----------------------------------");
											System.out.println("Book_Id is-->" + bookBean1.getId());
											System.out.println("Book_Name is-->" + bookBean1.getTitle());
											System.out.println("Book_Author is-->" + bookBean1.getAuthor());
											System.out.println("Book_Category is-->" + bookBean1.getCategory());
											System.out.println("Book_Copies is-->" + bookBean1.getCopies());
											System.out
													.println("Book_PublisherName is-->" + bookBean1.getPublisherName());
											System.out.println("Book_ISBN is-->" + bookBean1.getISBN());
											System.out
													.println("Book_CopyRightYear is-->" + bookBean1.getCopyRightYear());
											System.out.println("Book_DateAdded is-->" + bookBean1.getDateAdded());
											System.out.println("Book_Status is-->" + bookBean1.getStatus());
										} else {
											System.out.println("No books are available with this title.");
										}
									}
									break;
								case 3:
									System.out.println(" Search the book by the Book_Id's :");
									String bids = scanner.next();

									BookBean bean4 = new BookBean();
									bean4.setAuthor(bids);
									List<BookBean> bookIds = service1.searchBookByAuthor(bids);
									for (BookBean bookBean : bookIds) {
										if (bookBean != null) {
											System.out.println("-----------------------------------");
											System.out.println("Book_Id is-->" + bookBean.getId());
											System.out.println("Book_Name is-->" + bookBean.getTitle());
											System.out.println("Book_Author is-->" + bookBean.getAuthor());
											System.out.println("Book_Category is-->" + bookBean.getCategory());
											System.out
													.println("Book_PublisherName is-->" + bookBean.getPublisherName());
										} else {
											System.out.println("No books are available with this Id.");
										}
									}
									break;

								case 9:
									do {
										try {
											System.out.println("Search the book by the Book_Category :");
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
									//
									// BookBean bean5 = new BookBean();
									// bean5.setAuthor(bookCategory);
									List<BookBean> bookcategory = service1.searchBookByCategory(bookCategory);
									for (BookBean bookBean1 : bookcategory) {
										if (bookBean1 != null) {
											System.out.println("-----------------------------------");
											System.out.println("Book_Id is-->" + bookBean1.getId());
											System.out.println("Book_Name is-->" + bookBean1.getTitle());
											System.out.println("Book_Author is-->" + bookBean1.getAuthor());
											System.out.println("Book_Category is-->" + bookBean1.getCategory());
											System.out.println("Book_Copies is-->" + bookBean1.getCopies());
											System.out
													.println("Book_PublisherName is-->" + bookBean1.getPublisherName());
											System.out.println("Book_ISBN is-->" + bookBean1.getISBN());
											System.out
													.println("Book_CopyRightYear is-->" + bookBean1.getCopyRightYear());
											System.out.println("Book_DateAdded is-->" + bookBean1.getDateAdded());
											System.out.println("Book_Status is-->" + bookBean1.getStatus());
										} else {
											System.out.println("No books are available with this Id.");
										}
									}
									break;

								case 4:
									ArrayList<Integer> ids = service1.getBookIds();
									for (Integer integer : ids) {

										if (integer != null) {
											System.out.println(integer);
										} else {
											System.out.println("No Books Ids are available");
										}
									}
									break;
								case 5:
									ArrayList<BookBean> info = service1.getBooksInfo();

									for (BookBean bookBean : info) {

										if (bookBean != null) {
											System.out.println("-----------------------------------");
											System.out.println("Book_Id is-->" + bookBean.getId());
											System.out.println("Book_Name is-->" + bookBean.getTitle());
											System.out.println("Book_Author is-->" + bookBean.getAuthor());
											System.out.println("Book_Category is-->" + bookBean.getCategory());
											System.out
													.println("Book_PublisherName is-->" + bookBean.getPublisherName());
										} else {
											System.out.println("Books info is not present");
										}
									}
									break;
								case 6:

									do {
										try {
											System.out.println("Enter Book ID :");
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

									BookBean bookBean = new BookBean();
									bookBean.setId(bookId);

									do {
										try {
											System.out.println("Enter User id");
											regId1 = scanner.nextInt();
											validation.validatedId(regId1);
											flag = true;
										} catch (InputMismatchException e) {
											flag = false;
											System.err.println("Id should contains only digits");
										} catch (ValidationException e) {
											flag = false;
											System.err.println(e.getMessage());
										}
									} while (!flag);
									StudentBean userBean = new StudentBean();
									userBean.setId(regId1);

									try {
										RequestBean request = service1.bookRequest(userBean, bookBean);
										System.out.println("Request placed to admin");
										System.out.println("-----------------------------------");
										System.out.println("User Id-----" + request.getStudentInfo().getId());
										System.out.println("User name---" + request.getStudentInfo().getName());
										System.out.println("Book Id-----" + request.getBookInfo().getId());
										System.out.println("Book Name---" + request.getBookInfo().getTitle());

									} catch (Exception e) {

										System.out.println("Enter valid data or Invalid Request");
									}
									break;
								case 7:

									System.out.println("Returning a book:");
									System.out.println("------------------");
									do {
										try {
											System.out.println("Enter User id");
											regId1 = scanner.nextInt();
											validation.validatedId(regId1);
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
											System.out.println("Enter Book Id");
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
									StudentBean userBean7 = new StudentBean();
									userBean7.setId(regId1);
									BookBean bookBean7 = new BookBean();
									bookBean7.setId(bookId);

									try {
										RequestBean requestInfo = service1.bookReturn(userBean7, bookBean7);
										System.out.println("Book is Returning to Admin");
										System.out.println("-----------------------------------");
										System.out.println("User Id ------" + requestInfo.getStudentInfo().getId());
										System.out.println("Book Id ------" + requestInfo.getBookInfo().getId());
										System.out.println("Is Returning --" + requestInfo.isReturned());

									} catch (Exception e) {
										System.out.println("Invalid Return");
									}

									break;
								case 8:
									doReg();
								default:
									break;
								}
							} while (true);
						} catch (Exception e) {
							System.out.println("Invalid Credentials");
						}
						break;

					case 3:
						doReg();
					default:
						System.out.println("Invalid Choice");
						break;
					}
				} while (true);
			}
		} while (true);

	}
}
