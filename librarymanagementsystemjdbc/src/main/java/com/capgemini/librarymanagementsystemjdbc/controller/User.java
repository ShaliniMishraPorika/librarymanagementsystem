package com.capgemini.librarymanagementsystemjdbc.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemjdbc.dto.UsersBean;
import com.capgemini.librarymanagementsystemjdbc.exception.LMSException;
import com.capgemini.librarymanagementsystemjdbc.factory.LMSFactory;
import com.capgemini.librarymanagementsystemjdbc.service.AdminService;
import com.capgemini.librarymanagementsystemjdbc.service.StudentService;
import com.capgemini.librarymanagementsystemjdbc.service.UserService;
import com.capgemini.librarymanagementsystemjdbc.validation.LMSValidations;

public class User {
	static Scanner scanner = new Scanner(System.in);

	public static void doUser() {
		boolean flag = false;
		int regId = 0;
		String regName = null;
		String regMobile = null;
		String regEmail = null;
		String regPassword = null;
		String regRole = null;

		UserService service = LMSFactory.getUserService();
		UsersBean usersBean = new UsersBean();
		LMSValidations validation = new LMSValidations();
		do {
			System.out.println("--------------------------------------");
			System.out.println("Welcome to Library");
			System.out.println("Press 1 to Register");
			System.out.println("Press 2 to Login");
			System.out.println("Press 3 to EXIT");
			System.out.println("---------------------------------------");
			try {
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
						} catch (LMSException e) {
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
						} catch (LMSException e) {
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
						} catch (LMSException e) {
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
						} catch (LMSException e) {
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
						} catch (LMSException e) {
							flag = false;
							System.err.println(e.getMessage());
						}
					} while (!flag);

					do {
						try {
							System.out.println("Enter Role :");
							regRole = scanner.next();
							validation.validatedRole(regRole);
							flag = true;
						} catch (InputMismatchException e) {
							flag = false;
							System.err.println("Role should contains only Alphabates (Student/Admin)");
						} catch (LMSException e) {
							flag = false;
							System.err.println(e.getMessage());
						}
					} while (!flag);

					usersBean.setId(regId);
					usersBean.setName(regName);
					usersBean.setMobile(regMobile);
					usersBean.setEmail(regEmail);
					usersBean.setPassword(regPassword);
					usersBean.setRole(regRole);

					boolean check = service.register(usersBean);
					if (check) {
						System.out.println("Registered Successfully");
					} else {
						System.out.println("User already Exists");
					}
					break;
				case 2:
					System.out.println("Enter email for Login");
					String email = scanner.next();
					System.out.println("Enter Password");
					String password = scanner.next();
					try {
						UsersBean loginInfo = service.login(email, password);
						String role = loginInfo.getRole();
						if (role.equalsIgnoreCase("admin")) {
							do {
								doAdmin();
							} while (true);
						} else if (role.equalsIgnoreCase("student")) {
							do {
								doStudent();
							} while (true);
						} else {
							System.out.println("Invalid user and password");
						}
					} catch (Exception e) {
						System.out.println("Invalid user credentials");
					}
					break;
				case 3:
					System.exit(0);
				default:
					System.out.println("enter valid choice");
					break;

				} // end of switch case

			} catch (InputMismatchException e) {
				System.out.println("Enter digits");
				doUser();
			}
		} while (true); // end of do-While
	} // end of doUser() method

	public static void doAdmin() {
		boolean flag = false;
		int bookId = 0;
		int userId = 0;
		String bookAuthor = null;
		String bookName = null;
		String bookCategory = null;
		String bookPublisherName = null;
		String bookPublisher = null;
		int ISBN = 0;
		String status = null;
		int copies = 0;
		int year = 0;

		BookBean bean = new BookBean();
		UsersBean usersBean = new UsersBean();
		LMSValidations validation = new LMSValidations();
		AdminService service = LMSFactory.getAdminService();
		// Scanner scanner = new Scanner(System.in);

		do {
			try {
				System.out.println("1: Add Books");
				System.out.println("2: Update the Book");
				System.out.println("3: Remove the Book");
				System.out.println("4: Search the Book");
				System.out.println("5: Get the Book Id's");
				System.out.println("6: Get the Book Information");
				System.out.println("7: Issue the Book");
				System.out.println("8: Return book");
				System.out.println("9: Main");
				System.out.println("0: Exit");

				int choice1 = scanner.nextInt();
				switch (choice1) {
				case 1:
					do {
						System.out.println("Enter ID :");
						String regId = scanner.next();
						try {
							bookId = Integer.parseInt(regId);
							flag = true;
						} catch (Exception e) {
							flag = false;
							System.err.println("Id should contains only digits");
						}

					} while (!flag);

					do {
						try {
							System.out.println("Enter Book Name :");
							bookName = scanner.next();
							validation.validatedName(bookName);
							flag = true;
						} catch (Exception e) {
							flag = false;
							System.err.println("Book-Name should contains only Alphabates");
						}
					} while (!flag);

					do {
						try {
							System.out.println("Enter Author :");
							bookAuthor = scanner.next();
							validation.validatedName(bookAuthor);
							flag = true;
						} catch (Exception e) {
							flag = false;
							System.err.println("Author Name should contains only Alphabates");
						}
					} while (!flag);

					do {
						try {
							System.out.println("Enter Category :");
							bookCategory = scanner.next();
							validation.validatedName(bookCategory);
							flag = true;
						} catch (Exception e) {
							flag = false;
							System.err.println("Book-Category should contains only Alphabates");
						}
					} while (!flag);

					do {
						try {
							System.out.println("Enter BookPublisher :");
							bookPublisher = scanner.next();
							validation.validatedName(bookPublisher);
							flag = true;
						} catch (Exception e) {
							flag = false;
							System.err.println("Book-Publisher should contains only Alphabates");
						}
					} while (!flag);

					do {
						try {
							System.out.println("Enter Number Of Copies :");
							String copies1 = scanner.next();
							try {
								copies = Integer.parseInt(copies1);

							} catch (Exception e) {
								e.printStackTrace();
							}

						} catch (Exception e) {
							flag = false;
							System.err.println("Copies should contains only digits");
						}
					} while (!flag);

					do {
						try {
							System.out.println("Enter ISBN :");
							String isbn = scanner.next();
							try {
								ISBN = Integer.parseInt(isbn);

							} catch (Exception e) {
								e.printStackTrace();
							}

						} catch (Exception e) {
							flag = false;
							System.err.println("ISBN should contains only digits");
						}
					} while (!flag);

					do {
						try {
							System.out.println("Enter status :");
							status = scanner.next();
							validation.validatedStatus(status);
							flag = true;
						} catch (Exception e) {
							flag = false;
							System.err.println("Status can be only new or old");
						}
					} while (!flag);

					do {
						System.out.println("Enter Copyright Year :");
						String copyrightYear = scanner.next();
						try {
							year = Integer.parseInt(copyrightYear);
							flag = true;
						} catch (Exception e) {
							flag = false;
							System.err.println("Id should contains only digits");
						}

					} while (!flag);

					bean.setBookId(bookId);
					bean.setBookName(bookName);
					bean.setBookCategory(bookCategory);
					bean.setBookAuthor(bookAuthor);
					bean.setBookCopies(copies);
					bean.setBookPublisherName(bookPublisherName);
					bean.setBookIsbn(ISBN);
					bean.setBookCopyRight(year);
					bean.setStatus(status);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					String dateAdded = sdf.format(cal.getTime());
					bean.setDateAdded(dateAdded);
					boolean bookAdded = service.addBook(bean);
					if (bookAdded == false) {
						System.out.println("Book already exists");
					} else {
						System.out.println("Book added");
					}
					break;
				case 2:
					do {
						try {
							System.out.println("Enter the updated id :");
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
							System.out.println("Enter BookName to be Updated :");
							bookName = scanner.next();
							validation.validatedName(bookName);
							flag = true;
						} catch (Exception e) {
							flag = false;
							System.err.println("Name should contains only Alphabates");
						}
					} while (!flag);

					bean.setBookId(bookId);
					bean.setBookName(bookName);
					boolean updated = service.updateBook(bean);
					if (updated == false) {
						System.out.println("book is not updated");
					} else {
						System.out.println("book  updated");
					}
					break;
				case 3:
					do {
						try {
							System.out.println("Enter the Book-Id to Delete :");
							bookId = scanner.nextInt();
							validation.validatedId(bookId);
							flag = true;
						} catch (Exception e) {
							flag = false;
							System.err.println("Book-Id should contains only digits");
						}
					} while (!flag);

					bean.setBookId(bookId);
					if (bookId == 0) {
						System.out.println("Enter the Valid Book_Id");
					} else {
						BookBean bean6 = new BookBean();
						bean6.setBookId(bookId);
						boolean isRemoved = service.removeBook(bookId);
						if (isRemoved == false) {
							System.out.println("The Book is not removed");
						} else {
							System.out.println("The Book is removed Successfully");
						}
					}

					break;
				case 4:
					// search the book

					System.out.println("1: Search by Book Title");
					System.out.println("2: Search by Book Author");
					System.out.println("3: Search by Book Id");
					System.out.println("0: exit");
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
						doAdmin();
					default:
						System.out.println("Enter Valid Choice");
						break;
					}

				case 5:
					ArrayList<BookBean> ids = service.getBookIds();
					for (BookBean integer : ids) {
						if (integer != null) {
							System.out.println(integer.getBookId());
						} else {
							System.out.println("No Books Ids are available");
						}
					}
					break;
				case 6:
					List<BookBean> info = service.getBooksInfo();
					for (BookBean bookBean : info) {

						if (bookBean != null) {
							System.out.println(bookBean);
						} else {
							System.out.println("Books info is not present");
						}
					}
					break;
				case 7:
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

				case 8:
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
					boolean bookReturn = service.returnBook(bookId, userId);
					if (bookReturn) {
						System.out.println("Book is returned");
					} else {
						System.out.println("Book not returned");
					}
					break;
				case 9:
					doUser();
					break;
				case 0:
					System.exit(0);
				default:
					System.out.println("Enter valid Choice");
				} // end of switch case
					// scanner.close(); //closing scanner object

			} catch (InputMismatchException e) {
				System.out.println("Enter digits");
			}
		} while (true);
	} // end of doAdmin()

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
		// Scanner scanner = new Scanner(System.in);
		do {
			try {
				System.out.println("1: Search the Book");
				System.out.println("2: to Get the Book Id's");
				System.out.println("3: Get the Book Information");
				System.out.println("4: Request Return book");
				System.out.println("5: Request book");
				System.out.println("6: Main");
				System.out.println("0: Exit");
				// try {
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
				case 6:
					doUser();
					break;
				case 0:
					System.exit(0);
				} // end of switch case
					// scanner.close(); //closing scanner object

			} catch (InputMismatchException e) {
				System.out.println("Enter digits");
				doStudent();
			}
		} while (true);
	}
} // end of class
