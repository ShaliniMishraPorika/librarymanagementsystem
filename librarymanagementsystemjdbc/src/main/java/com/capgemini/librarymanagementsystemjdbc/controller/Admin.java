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
import com.capgemini.librarymanagementsystemjdbc.validation.LMSValidations;

public class Admin {

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
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.println("1: Add Books");
			System.out.println("2: Update the Book");
			System.out.println("3: Remove the Book");
			System.out.println("4: Search the Book");
			System.out.println("5: Get the Book Id's");
			System.out.println("6: Get the Book Information");
			System.out.println("7: Issue  the Book");
			System.out.println("8: Return book");
			try {
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
				} // end of switch case
					// scanner.close(); //closing scanner object

			} catch (InputMismatchException e) {
				System.out.println("Enter digits");
			}
		} while (true);

	}
}
