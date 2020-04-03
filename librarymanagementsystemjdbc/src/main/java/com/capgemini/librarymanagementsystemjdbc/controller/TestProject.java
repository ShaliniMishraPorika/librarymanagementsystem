package com.capgemini.librarymanagementsystemjdbc.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;
//import com.capgemini.librarymanagementsystemjdbc.dto.StudentBean;
//import com.capgemini.librarymanagementsystemjdbc.dto.UsersBean;
//import com.capgemini.librarymanagementsystemjdbc.dto.AdminBean;
//import com.capgemini.librarymanagementsystemjdbc.factory.AdminFactory;
//import com.capgemini.librarymanagementsystemjdbc.factory.StudentFactory;
//import com.capgemini.librarymanagementsystemjdbc.service.AdminService;
//import com.capgemini.librarymanagementsystemjdbc.service.StudentService;
//
//public class TestProject {
//	public static void main(String[] args) {
//		doReg();
//	}
//	public static void doReg() {
//
//		Scanner scanner = new Scanner(System.in);
//		do {
//			System.out.println("Press 1 for Admin");
//			System.out.println("Press 2 for Student");
//			System.out.println("Jdbc");
//			int i = scanner.nextInt();
//			switch (i) {
//			case 1:
//				int a = scanner.nextInt();
//				do {
//					AdminService service = AdminFactory.getAdminService();
//					System.out.println("press 1 for admin register");
//					System.out.println("press 2 for login");
//					System.out.println("admin main");
//					int choice = scanner.nextInt();
//					switch (choice) {
//					case 1:
//
//						System.out.println("Enter Name");
//						String regName = scanner.next();
//						System.out.println("Enter ID");
//						int regID = scanner.nextInt();
//						System.out.println("Enter Mobile");
//						long regMobile = scanner.nextLong();
//						System.out.println("Enter Email");
//						String regEmail = scanner.next();
//						System.out.println("Enter Password");
//						String regPassword = scanner.next();
//						AdminBean bean = new AdminBean();
//						bean.setAid(regID);
//
//						bean.setAname(regName);
//						bean.setMobile(regMobile);
//						bean.setEmail(regEmail);
//						bean.setPassword(regPassword);
//						boolean check = service.register(bean);
//						if(check) {
//							System.out.println("Email already exist");
//						} else {
//							System.out.println("Registered");
//						}	
//
//						break;
//					case 2:
//						System.out.println("Enter Email");
//						String regEmail1 = scanner.next();
//						System.out.println("Enter Password");
//						String regPassword1 = scanner.next();
//						try {
//							AdminBean check1 = service.auth(regEmail1, regPassword1);
//							System.out.println("login successful");
//							do {
//								System.out.println("Press 1 to Add Books");
//								System.out.println("Press 2 to update");
//								System.out.println("Press 3 to Search the Book by bookName");
//								System.out.println("Press 4 to Search the Book by author");
//								System.out.println("Press 5 to Search the Book by Id");
//								System.out.println("Press 6 to remove the Books");
//								System.out.println("Press 7 to Get the Book Id's");
//								System.out.println("Press 8 to Get the Book Information");
//								System.out.println("Press 9 to main");
//								int choice1 = scanner.nextInt();
//								switch (choice1) {
//								case 1:
//									System.out.println("Enter ID");
//									int bookId = scanner.nextInt();
//									System.out.println("Enter Book Name");
//									String bookName = scanner.next();
//									System.out.println("Enter Author");
//									String bookAuthor = scanner.next();
//									System.out.println("Enter Category");
//									String bookCategory = scanner.next();
//									System.out.println("Enter Publisher Name");
//									String bookPubName = scanner.next();
//
//									BookBean bean2 = new BookBean();	
//									bean2.setBname(bookName);
//									bean2.setBid(bookId);
//									bean2.setAuthor(bookAuthor);
//									bean2.setCategory(bookCategory);
//									bean2.setPublishername(bookPubName);
//									boolean check2 = service.addBook(bean2);
//									if(check2) {
//										System.out.println("Book already exist");
//									} else {
//										System.out.println("Book Added");
//									}
//									break;
//								case 2:
//									System.out.println("No implementation");
//									break;
//								case 3:
//									System.out.println("Enter book Name");
//									String bookName1 = scanner.next();
//									BookBean bean3 = new BookBean();
//									bean3.setBname(bookName1);
//									BookBean list = service.searchBookTitle(bookName1);
//									if(list!=null) {
//										System.out.println(list.getBid());
//										System.out.println(list.getBname());
//										System.out.println(list.getAuthor());
//										System.out.println(list.getCategory());
//										System.out.println(list.getPublishername());
//										System.out.println(list);
//									} else {
//										System.out.println("Book Not Found");
//									}
//									break;
//								case 4:
//									System.out.println("Enter Book author");
//									String author = scanner.next();
//									BookBean bean4 = new BookBean();
//									bean4.setAuthor(author);
//									BookBean list1 = service.searchBookAuthor(author);
//									if(list1!=null) {
//										System.out.println(list1.getBid());
//										System.out.println(list1.getBname());
//										System.out.println(list1.getAuthor());
//										System.out.println(list1.getCategory());
//										System.out.println(list1.getPublishername());
//										System.out.println(list1);
//									} else {
//										System.out.println("Book Not Found");
//									}
//									break;
//								case 5:
//									System.out.println("Enter Book id");
//									int id = scanner.nextInt();
//									BookBean bean5 = new BookBean();
//									bean5.setBid(id);
//									BookBean list2 = service.searchBookType(id);
//									if(list2!=null) {
//										System.out.println(list2.getBid());
//										System.out.println(list2.getBname());
//										System.out.println(list2.getAuthor());
//										System.out.println(list2.getCategory());
//										System.out.println(list2.getPublishername());
//										System.out.println(list2);
//									} else {
//										System.out.println("Book Not Found");
//									}
//									break;
//								case 6:
//									System.out.println("Enter New Book Name to Update");
//									String bname1 = scanner.next();
//									System.out.println("Enter Book Name to Update");
//									String bname2 = scanner.next();
//									BookBean bean6 = new BookBean();
//									bean6.setBname(bname1);
//									bean6.setBname(bname2);
//									boolean b = service.updateBook(bean6);
//									if(b) {
//										System.out.println("Book Updated");
//									} else {
//										System.out.println("Book Not Update");
//									}
//								case 9:
//									doReg();
//								default:
//									System.out.println("Invalid choice");
//									break;
//								}
//							}while(true);
//						} catch (Exception e) {
//							System.out.println("Invalid Credentials");
//						}
//
//						break;
//					case 3:
//						doReg();
//					default:
//						System.out.println("Invalid Choice");
//						break;
//					}
//
//				} while(true);
//			case 2:
//				StudentService service1 = StudentFactory.getStudentService();
//				do {
//					System.out.println("press 1 for student register");
//					System.out.println("press 2 for login");
//					System.out.println("admin main");
//					int choice = scanner.nextInt();
//					switch (choice) {
//					case 1:
//
//						System.out.println("Enter Name");
//						String regName = scanner.next();
//						System.out.println("Enter ID");
//						int regID = scanner.nextInt();
//						System.out.println("Enter Mobile");
//						long regMobile = scanner.nextLong();
//						System.out.println("Enter Email");
//						String regEmail = scanner.next();
//						System.out.println("Enter Password");
//						String regPassword = scanner.next();
//						StudentBean bean = new StudentBean();
//						bean.setSid(regID);
//
//						bean.setSname(regName);
//						bean.setMobile(regMobile);
//						bean.setSemail(regEmail);
//						bean.setPassword(regPassword);
//						boolean check = service1.register(bean);
//						if(check) {
//							System.out.println("Email already exist");
//						} else {
//							System.out.println("Registered");
//						}	
//
//						break;
//					case 2:
//						System.out.println("Enter Email");
//						String regEmail1 = scanner.next();
//						System.out.println("Enter Password");
//						String regPassword1 = scanner.next();
//						AdminBean bean1 = new AdminBean();
//						bean1.setEmail(regEmail1);
//						bean1.setPassword(regPassword1);
//						try {
//							StudentBean check1 = service1.auth(regEmail1, regPassword1);
//							do {
//								System.out.println("Press 1 to Search the Book by bookName");
//								System.out.println("Press 2 to Search the Book by author");
//								System.out.println("Press 3 to Search the Book by Id");
//								System.out.println("Press 4 book ids");
//								System.out.println("Press 7 to Get the Book Id's");
//								System.out.println("Press 5 to Get the Book Information");
//								System.out.println("Press 9 to main");
//								int choice1 = scanner.nextInt();
//								switch (choice1) {
//								case 1:
//									System.out.println("Enter book Name");
//									String bookName1 = scanner.next();
//									BookBean bean2 = new BookBean();
//									bean2.setBname(bookName1);
//									BookBean list = service1.searchBookTitle(bookName1);
//									if(list!=null) {
//										System.out.println(list.getBid());
//										System.out.println(list.getBname());
//										System.out.println(list.getAuthor());
//										System.out.println(list.getCategory());
//										System.out.println(list.getPublishername());
//										System.out.println(list);
//									} else {
//										System.out.println("Book Not Found");
//									}
//									break;
//								case 2:
//									System.out.println("Enter Book author");
//									String author = scanner.next();
//									BookBean bean4 = new BookBean();
//									bean4.setAuthor(author);
//									BookBean list11 = service1.searchBookAuthor(author);
//									if(list11!=null) {
//										System.out.println(list11.getBid());
//										System.out.println(list11.getBname());
//										System.out.println(list11.getAuthor());
//										System.out.println(list11.getCategory());
//										System.out.println(list11.getPublishername());
//										System.out.println(list11);
//									} else {
//										System.out.println("Book Not Found");
//									}
//									break;
//								case 3:
//									System.out.println("Enter Book id");
//									int id = scanner.nextInt();
//									BookBean bean5 = new BookBean();
//									bean5.setBid(id);
//									BookBean list2 = service1.searchBookType(id);
//									if(list2!=null) {
//										System.out.println(list2.getBid());
//										System.out.println(list2.getBname());
//										System.out.println(list2.getAuthor());
//										System.out.println(list2.getCategory());
//										System.out.println(list2.getPublishername());
//										System.out.println(list2);
//									} else {
//										System.out.println("Book Not Found");
//									}
//									break;
//								case 4:
//									List<BookBean> list3 = service1.getBookIds();
//									for(BookBean bean3 : list3) {
//										System.out.println(bean3.getBid());
//										System.out.println("----------");
//									}
//									break;
//								case 5:
//									List<BookBean> list4 = service1.getBooksInfo();
//									for(BookBean bean3 : list4) {
//										System.out.println(bean3.getBid());
//										System.out.println(bean3.getBname());
//										System.out.println(bean3.getAuthor());
//										System.out.println(bean3.getCategory());
//										System.out.println(bean3.getPublishername());
//										System.out.println("--------------------");
//									}
//									break;
//								case 9:
//									doReg();
//								default:
//									System.out.println("Invalid choice");
//									break;
//								}
//							}while(true);
//						} catch(Exception e) {
//							e.printStackTrace();
//						}
//
//						break;
//					case 3:
//						doReg();
//					default:
//						System.out.println("Invalid Choice");
//						break;
//					}
//
//				} while(true);
//
//			default:
//				System.out.println("Invalid Choice");
//				break;
//			}
//
//		} while(true);
//
//	}
//}
//
//
//
//
//
//
//
//
//
//
//try {
//	String check1 = service.auth(regEmail1, regPassword1);
//	System.out.println("Login successful");
//	UsersBean beanUsersBean = new UsersBean(regEmail1, regPassword1);
//	
//		do {
//			AdminService service2 = AdminFactory.getAdminService();
//			System.out.println("Press 1 to Add Books");
//			System.out.println("Press 2 to update");
//			System.out.println("Press 3 to Search the Book by bookName");
//			System.out.println("Press 4 to Search the Book by author");
//			System.out.println("Press 5 to Search the Book by Id");
//			System.out.println("Press 6 to remove the Books");
//			System.out.println("Press 7 to Get the Book Id's");
//			System.out.println("Press 8 to Get the Book Information");
//			System.out.println("Press 9 to main");
//			int choice1 = scanner.nextInt();
//			switch (choice1) {
//			case 1:
//				System.out.println("Enter ID");
//				int bookId = scanner.nextInt();
//				System.out.println("Enter Book Name");
//				String bookName = scanner.next();
//				System.out.println("Enter Author");
//				String bookAuthor = scanner.next();
//				System.out.println("Enter Category");
//				String bookCategory = scanner.next();
//				System.out.println("Enter Publisher Name");
//				String bookPubName = scanner.next();
//
//				BookBean bean2 = new BookBean();	
//				bean2.setBname(bookName);
//				bean2.setBid(bookId);
//				bean2.setAuthor(bookAuthor);
//				bean2.setCategory(bookCategory);
//				bean2.setPublishername(bookPubName);
//				boolean check2 = service2.addBook(bean2);
//				if(check2) {
//					System.out.println("Book already exist");
//				} else {
//					System.out.println("Book Added");
//				}
//				break;
//			case 2:
//				System.out.println("No implementation");
//				break;
//			case 3:
//				System.out.println("Enter book Name");
//				String bookName1 = scanner.next();
//				BookBean bean3 = new BookBean();
//				bean3.setBname(bookName1);
//				BookBean list = service2.searchBookTitle(bookName1);
//				if(list!=null) {
//					System.out.println(list.getBid());
//					System.out.println(list.getBname());
//					System.out.println(list.getAuthor());
//					System.out.println(list.getCategory());
//					System.out.println(list.getPublishername());
//					System.out.println(list);
//				} else {
//					System.out.println("Book Not Found");
//				}
//				break;
//			case 4:
//				System.out.println("Enter Book author");
//				String author = scanner.next();
//				BookBean bean4 = new BookBean();
//				bean4.setAuthor(author);
//				BookBean list1 = service2.searchBookAuthor(author);
//				if(list1!=null) {
//					System.out.println(list1.getBid());
//					System.out.println(list1.getBname());
//					System.out.println(list1.getAuthor());
//					System.out.println(list1.getCategory());
//					System.out.println(list1.getPublishername());
//					System.out.println(list1);
//				} else {
//					System.out.println("Book Not Found");
//				}
//				break;
//			case 5:
//				System.out.println("Enter Book id");
//				int id = scanner.nextInt();
//				BookBean bean5 = new BookBean();
//				bean5.setBid(id);
//				BookBean list2 = service2.searchBookType(id);
//				if(list2!=null) {
//					System.out.println(list2.getBid());
//					System.out.println(list2.getBname());
//					System.out.println(list2.getAuthor());
//					System.out.println(list2.getCategory());
//					System.out.println(list2.getPublishername());
//					System.out.println(list2);
//				} else {
//					System.out.println("Book Not Found");
//				}
//				break;
//			case 6:
//				System.out.println("Enter New Book Name to Update");
//				String bname1 = scanner.next();
//				System.out.println("Enter Book Name to Update");
//				String bname2 = scanner.next();
//				BookBean bean6 = new BookBean();
//				bean6.setBname(bname1);
//				bean6.setBname(bname2);
//				boolean b = service2.updateBook(bean6);
//				if(b) {
//					System.out.println("Book Updated");
//				} else {
//					System.out.println("Book Not Update");
//				}
//			case 9:
//				doReg();
//			default:
//				System.out.println("Invalid choice");
//				break;
//			} 
//		}while(true);
//
//	
//		StudentService service1 = StudentFactory.getStudentService();
//		do {
//			System.out.println("Press 1 to Search the Book by bookName");
//			System.out.println("Press 2 to Search the Book by author");
//			System.out.println("Press 3 to Search the Book by Id");
//			System.out.println("Press 4 book ids");
//			System.out.println("Press 7 to Get the Book Id's");
//			System.out.println("Press 5 to Get the Book Information");
//			System.out.println("Press 9 to main");
//			int choice1 = scanner.nextInt();
//			switch (choice1) {
//			case 1:
//				System.out.println("Enter book Name");
//				String bookName1 = scanner.next();
//				BookBean bean2 = new BookBean();
//				bean2.setBname(bookName1);
//				BookBean list = service1.searchBookTitle(bookName1);
//				if(list!=null) {
//					System.out.println(list.getBid());
//					System.out.println(list.getBname());
//					System.out.println(list.getAuthor());
//					System.out.println(list.getCategory());
//					System.out.println(list.getPublishername());
//					System.out.println(list);
//				} else {
//					System.out.println("Book Not Found");
//				}
//				break;
//			case 2:
//				System.out.println("Enter Book author");
//				String author = scanner.next();
//				BookBean bean4 = new BookBean();
//				bean4.setAuthor(author);
//				BookBean list11 = service1.searchBookAuthor(author);
//				if(list11!=null) {
//					System.out.println(list11.getBid());
//					System.out.println(list11.getBname());
//					System.out.println(list11.getAuthor());
//					System.out.println(list11.getCategory());
//					System.out.println(list11.getPublishername());
//					System.out.println(list11);
//				} else {
//					System.out.println("Book Not Found");
//				}
//				break;
//			case 3:
//				System.out.println("Enter Book id");
//				int id = scanner.nextInt();
//				BookBean bean5 = new BookBean();
//				bean5.setBid(id);
//				BookBean list2 = service1.searchBookType(id);
//				if(list2!=null) {
//					System.out.println(list2.getBid());
//					System.out.println(list2.getBname());
//					System.out.println(list2.getAuthor());
//					System.out.println(list2.getCategory());
//					System.out.println(list2.getPublishername());
//					System.out.println(list2);
//				} else {
//					System.out.println("Book Not Found");
//				}
//	
//				break;
//			case 4:
//				List<BookBean> list3 = service1.getBookIds();
//				for(BookBean bean3 : list3) {
//					System.out.println(bean3.getBid());
//					System.out.println("----------");
//				}
//				break;
//			case 5:
//				List<BookBean> list4 = service1.getBooksInfo();
//				for(BookBean bean3 : list4) {
//					System.out.println(bean3.getBid());
//					System.out.println(bean3.getBname());
//					System.out.println(bean3.getAuthor());
//					System.out.println(bean3.getCategory());
//					System.out.println(bean3.getPublishername());
//					System.out.println("--------------------");
//				}
//				break;
//			case 9:
//				doReg();
//			default:
//				System.out.println("Invalid choice");
//				break;
//			}
//		}while(true);
//	
//} catch (Exception e) {
//	System.out.println("Invalid Credentials");
//}

