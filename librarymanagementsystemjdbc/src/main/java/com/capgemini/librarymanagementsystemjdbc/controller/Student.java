package com.capgemini.librarymanagementsystemjdbc.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemjdbc.dto.UsersBean;
import com.capgemini.librarymanagementsystemjdbc.exception.LMSException;
import com.capgemini.librarymanagementsystemjdbc.factory.LMSFactory;
import com.capgemini.librarymanagementsystemjdbc.service.StudentService;
import com.capgemini.librarymanagementsystemjdbc.validation.LMSValidations;

public class Student {

	public static void doStudent() {
		boolean flag = false;
		int bookId = 0;
		int userId = 0;
		String bookAuthor = null;
		String bookName = null;

		BookBean bean = new BookBean();
		UsersBean usersBean = new UsersBean();
		LMSValidations validation = new LMSValidations();
		StudentService service = LMSFactory.getStudentService();
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("1: Search the Book");
			System.out.println("2: to Get the Book Id's");
			System.out.println("3: Get the Book Information");
			System.out.println("4: Request Return book");
			System.out.println("5: Request book");
			System.out.println("0: Exit");
			try {
				int choice = scanner.nextInt();
				switch (choice) {
				case 1:
					do {
						System.out.println("1: Search the Book by Author");
						System.out.println("2: Search the Book by Title");
						System.out.println("3: Search the Book by Id");
						System.out.println("0: Exit");

						int choice2 = scanner.nextInt();
						switch (choice2) {
						case 1:
							do {
								try {
									System.out.println("Search the book by the Book Name:");
									bookName = scanner.next();
									validation.validatedName(bookName);
									flag = true;
								} catch (InputMismatchException e) {
									flag = false;
									System.err.println("Name should contains only Alphabates");
								} catch (LMSException e) {
									flag = false;
									System.err.println(e.getMessage());
								}
							} while (!flag);

							bean.setBookName(bookName);
							BookBean bookBean = service.searchBookByAuthor(bookName);
							System.out.println(bookBean);
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
									System.err.println("Name should contains only Alphabates");
								} catch (LMSException e) {
									flag = false;
									System.err.println(e.getMessage());
								}
							} while (!flag);

							bean.setBookAuthor(bookAuthor);

							BookBean bookBean2 = service.searchBookByAuthor(bookAuthor);
							System.out.println(bookBean2);
							break;

						case 3:
							do {
								try {
									System.out.println("Search the book by the Book-Id :");
									bookId = scanner.nextInt();
									validation.validatedId(bookId);
									flag = true;
								} catch (Exception e) {
									flag = false;
									System.err.println("Book-Id should contains only digits");
								}
							} while (!flag);

							bean.setBookId(bookId);
							BookBean bookBean3 = service.searchBookById(bookId);
							System.out.println(bookBean3);
						case 0:
							doStudent();
						default:
							System.out.println("Enter Valid Choice");
							break;
						} // end of search switch case
					} while (true); // end of do-while
				case 2:
					ArrayList<BookBean> ids = service.getBookIds();
					for (BookBean integer : ids) {
						if (integer != null) {
							System.out.println(integer.getBookId());
						} else {
							System.out.println("No Books Ids are available");
						}
					}
					break;
				case 3:
					List<BookBean> info = service.getBooksInfo();
					for (BookBean bookBean : info) {
						if (bookBean != null) {
							System.out.println(bookBean);
						} else {
							System.out.println("Books info is not present");
						}
					}
					break;
				case 4:
					do {
						try {
							System.out.println("enter Book Id");
							bookId = scanner.nextInt();
							validation.validatedId(bookId);
							flag = true;
						} catch (Exception e) {
							flag = false;
							System.err.println("Id should contains only digits");
						}
					} while (!flag);

					do {
						try {
							System.out.println("enter User Id");
							userId = scanner.nextInt();
							validation.validatedId(userId);
							flag = true;
						} catch (Exception e) {
							flag = false;
							System.err.println("Id should contains only digits");
						}
					} while (!flag);

					bean.setBookId(bookId);
					usersBean.setId(userId);
					try {
						boolean isreturned = service.requestReturnBook(bookId, userId);
						if (isreturned) {
							System.out.println("Book Returned");
						} else {
							System.out.println("Book cannot be Returned");
						}

					} catch (Exception e) {
						System.out.println("Invalid data request book cannot be returned");
					}
					break;
				case 5:
					do {
						try {
							System.out.println("enter Book Id");
							bookId = scanner.nextInt();
							validation.validatedId(bookId);
							flag = true;
						} catch (Exception e) {
							flag = false;
							System.err.println("Id should contains only digits");
						}
					} while (!flag);

					do {
						try {
							System.out.println("enter User Id");
							userId = scanner.nextInt();
							validation.validatedId(userId);
							flag = true;
						} catch (Exception e) {
							flag = false;
							System.err.println("Id should contains only digits");
						}
					} while (!flag);

					bean.setBookId(bookId);
					usersBean.setId(userId);
					try {
						boolean request = service.requestReturnBook(bookId, userId);
						if (request) {
							System.out.println("Book Requested Successfully");
						} else {
							System.out.println("Book NOT requested");
						}

					} catch (Exception e) {
						System.out.println("Invalid data request book cannot be requested");
					}
					break;

				} // end of switch case
					// scanner.close(); //closing scanner object

			} catch (InputMismatchException e) {
				System.out.println("Enter digits");
				doStudent();
			}
		} while (true);
	}
}
