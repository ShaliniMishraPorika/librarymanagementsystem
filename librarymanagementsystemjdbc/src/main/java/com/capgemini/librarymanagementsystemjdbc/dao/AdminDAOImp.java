package com.capgemini.librarymanagementsystemjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemjdbc.dto.UsersBean;
import com.capgemini.librarymanagementsystemjdbc.exception.LMSException;
import com.capgemini.librarymanagementsystemjdbc.utility.JdbcUtility;

public class AdminDAOImp implements AdminDAO {
	Connection connection;
	Statement statement;
	PreparedStatement preparedStatement;
	ResultSet rs;
	Properties properties = new Properties();
	BookBean bean = new BookBean();
	UsersBean user = new UsersBean();
	int count = 0;

	@Override
	public boolean addBook(BookBean book) throws LMSException {
		connection = JdbcUtility.getConnection();
		try { 
			preparedStatement = connection.prepareStatement(QueryMapper.addBook);
			preparedStatement.setInt(1, book.getBookId());
			preparedStatement.setString(2, book.getBookName());
			preparedStatement.setString(3, book.getBookAuthor());
			preparedStatement.setString(4, book.getBookCategory());
			preparedStatement.setString(5, book.getBookPublisherName());
			preparedStatement.setInt(6, book.getBookCopies());
			preparedStatement.setInt(7, book.getBookIsbn());
			preparedStatement.setInt(8, book.getBookCopyRight());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			Calendar cal = Calendar.getInstance();
			String dateAdded = sdf.format(cal.getTime());
			preparedStatement.setDate(9, java.sql.Date.valueOf(dateAdded));
			preparedStatement.setString(10, book.getStatus());
			int count = preparedStatement.executeUpdate();
			if (count != 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new LMSException("Cannot add the book/ book already exists");
		}

	}

	@Override
	public BookBean searchBookByTitle(String title) throws LMSException {
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.searchBookByName);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				bean.setBookId(rs.getInt("bId"));
				bean.setBookName(rs.getString("bname"));
				bean.setBookAuthor(rs.getString("author"));
				bean.setBookCopies(rs.getInt("copies"));
				bean.setBookCategory(rs.getString("category"));
				bean.setBookPublisherName(rs.getString("publishername"));
				bean.setBookCopies(rs.getInt("copies"));
				bean.setBookIsbn(rs.getInt("isbn"));
				//bean.setBookCopies(rs.getInt("copies"));
				bean.setBookCopyRight(rs.getInt("copyright"));
				bean.setDateAdded(rs.getString("dateAdded"));
				bean.setStatus(rs.getString("status"));
				return bean;
			} else {
				System.out.println("BookName with" +title+ "does not exist");
			}
		} catch (Exception e) {
			throw new LMSException("NO book found with title "+title);
		}
		return null;
	}

	@Override
	public BookBean searchBookByAuthor(String author) {
		try {
			System.out.println("------------------");
			preparedStatement = connection.prepareStatement(QueryMapper.searchBookByAuthor);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {		
				bean.setBookId(rs.getInt("bId"));
				bean.setBookName(rs.getString("bname"));
				bean.setBookAuthor(rs.getString("author"));
				bean.setBookCopies(rs.getInt("copies"));
				bean.setBookCategory(rs.getString("category"));
				bean.setBookPublisherName(rs.getString("publishername"));
				bean.setBookCopies(rs.getInt("copies"));
				bean.setBookIsbn(rs.getInt("isbn"));
				//bean.setBookCopies(rs.getInt("copies"));
				bean.setBookCopyRight(rs.getInt("copyright"));
				bean.setDateAdded(rs.getString("dateAdded"));
				bean.setStatus(rs.getString("status"));
				return bean;
			} else {
				System.out.println("Book Author with " +author.toUpperCase()+ " does not exist");
			}
		}
		catch(Exception e) {
			throw new LMSException("No book found with author " +author.toUpperCase());
		}
		return null;
	}

	@Override
	public BookBean searchBookById(int bookId) {
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.searchBookByAuthor);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				bean.setBookId(rs.getInt("bId"));
				bean.setBookName(rs.getString("bname"));
				bean.setBookAuthor(rs.getString("author"));
				bean.setBookCopies(rs.getInt("copies"));
				bean.setBookCategory(rs.getString("category"));
				bean.setBookPublisherName(rs.getString("publishername"));
				bean.setBookCopies(rs.getInt("copies"));
				bean.setBookIsbn(rs.getInt("isbn"));
				//bean.setBookCopies(rs.getInt("copies"));
				bean.setBookCopyRight(rs.getInt("copyright"));
				bean.setDateAdded(rs.getString("dateAdded"));
				bean.setStatus(rs.getString("status"));
				return bean;
			} else {
				System.out.println("Book Id with" +bookId+ "does not exist");
			}
		} catch(Exception e) {
			throw new LMSException("No book found with book-id" +bookId);
		}
		return null;
	}

	public boolean updateBook(BookBean bean) {
		boolean isUpdated = false;
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.bookUpdate);
			preparedStatement.setString(1, bean.getBookName());
			preparedStatement.setString(2, bean.getBookAuthor());
			preparedStatement.setInt(3, bean.getBookId());
			count = preparedStatement.executeUpdate();
			if(count != 0) {
				System.out.println("Book has been updated Sucessfully");
			} else {
				System.out.println("Book NOT updated");
			}
		} catch (Exception e) {
			throw new LMSException("Book NOT found");
		}
		return isUpdated;
	}

	@Override
	public boolean removeBook(int bookId) {
		boolean isRemoved = false;
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.deleteBook);
			preparedStatement.setInt(1, bookId);
			count = preparedStatement.executeUpdate();
			if(count != 0) {
				System.out.println("book has been removed Successfully");
			} else {
				System.out.println("Book with book-id " +bookId+ "does not exists");
			}
		} catch (Exception e) {
			throw new LMSException("NO book found with book-id "+bookId);
		}
		return isRemoved;
	}

	@Override
	public ArrayList<BookBean> getBookIds() {
		ArrayList<BookBean> beans = new ArrayList<BookBean>();
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.getBookIds);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				bean.setBookId(rs.getInt("bId"));
				beans.add(bean);
			}
			return beans;
		} catch (Exception e) {
			throw new LMSException("Cannot get book-ids");
		}
	}

	@Override
	public ArrayList<BookBean> getBooksInfo() {
		ArrayList<BookBean> beans = new ArrayList<BookBean>();
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.getAllBookInfo);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				bean.setBookId(rs.getInt("bId"));
				bean.setBookName(rs.getString("bname"));
				bean.setBookAuthor(rs.getString("author"));
				bean.setBookCopies(rs.getInt("copies"));
				bean.setBookCategory(rs.getString("category"));
				bean.setBookPublisherName(rs.getString("publishername"));
				bean.setBookCopies(rs.getInt("copies"));
				bean.setBookIsbn(rs.getInt("isbn"));
				bean.setBookCopyRight(rs.getInt("copyright"));
				bean.setDateAdded(rs.getString("dateadded"));
				bean.setStatus(rs.getString("status"));
				beans.add(bean);
			}
			return beans;
		} catch (Exception e) {
			throw new LMSException("Cannot get book-ids");
		}
	}

	@Override
	public List<UsersBean> showUsers() {
		List<UsersBean> beans = new ArrayList<UsersBean>();
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.getAllUserInfo);
			rs = preparedStatement.executeQuery();
			while(rs.next()) {
				user.setId(rs.getInt("uId"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setMobile(rs.getString("mobile"));
				user.setRole(rs.getString("role"));
				beans.add(user);
			}
			return beans;
		} catch (Exception e) {
			throw new LMSException("Cannot get Users-Info");
		}
	}

	@Override
	public boolean issueBook(int bookId, int userId) {
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.bookDetails);
			preparedStatement.setInt(1, bookId);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				PreparedStatement preparedStatement1 = connection.prepareStatement(QueryMapper.requestBook);
				preparedStatement1.setInt(1, bookId);
				preparedStatement1.setInt(2, userId);
				ResultSet rs1 = preparedStatement1.executeQuery();
				if(rs1.next()) {
					PreparedStatement preparedStatement2 = connection.prepareStatement(QueryMapper.borrowBook);
					preparedStatement2.setInt(1, userId);
					ResultSet rs2 = preparedStatement2.executeQuery();
					if(rs2.next()) {
						int noOfBooksBorrowed = rs2.getInt("uid");
						if(noOfBooksBorrowed < 3) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							LocalDate date = LocalDate.now();
							Calendar cal = Calendar.getInstance();
							cal.setTime(new java.util.Date());
							cal.add(Calendar.DATE, 15);
							String date1 =	sdf.format(cal.getTime());
							PreparedStatement preparedStatement3 = connection.prepareStatement(QueryMapper.bookIssue);
							preparedStatement3.setInt(1, userId);
							preparedStatement3.setInt(2, bookId);
							preparedStatement3.setObject(3, date);
							preparedStatement3.setObject(4, date1);
							count = preparedStatement3.executeUpdate();
							if(count != 0) {
								PreparedStatement preparedStatement4 = connection.prepareStatement(QueryMapper.borrowBookDetails);
								preparedStatement4.setInt(1, bookId);
								preparedStatement4.setInt(2, userId);
								preparedStatement4.setInt(3, userId);
								preparedStatement4.executeUpdate();
								PreparedStatement preparedStatement5 = connection.prepareStatement(QueryMapper.requestDelete);
								preparedStatement5.setInt(1, userId);
								preparedStatement5.setInt(2, bookId);
								preparedStatement5.executeUpdate();
								PreparedStatement preparedStatement6 = connection.prepareStatement(QueryMapper.updateBookDetails1);
								preparedStatement6.setInt(1, bookId);
								preparedStatement6.executeUpdate();
							} else {
								System.out.println("You have excedded the borrowing limit");
							}

						}
					}
				}
			}
		} catch (Exception e) {
			throw new LMSException("Cannot issue book ");
		}
		return false;
	}
	@Override
	public boolean returnBook(int bookId, int id) {
		try {
			preparedStatement =connection.prepareStatement(QueryMapper.issueBook);
			preparedStatement.setInt(1, bookId);
			preparedStatement.setInt(2, id);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				PreparedStatement preparedStatement1 = connection.prepareStatement(QueryMapper.requestBook);
				preparedStatement1.setInt(1, bookId);
				preparedStatement1.setInt(2, id);
				ResultSet rs1 = preparedStatement.executeQuery();
				if(rs1.next()) {
					PreparedStatement preparedStatement2 = connection.prepareStatement(QueryMapper.updateBookDetails2);
					preparedStatement2.setInt(1, bookId);
					preparedStatement2.executeUpdate();
					PreparedStatement preparedStatement3 = connection.prepareStatement(QueryMapper.deleteBookDetails);
					preparedStatement3.setInt(1, bookId);
					preparedStatement3.setInt(2, id);
					preparedStatement3.executeUpdate();
					PreparedStatement preparedStatement4 = connection.prepareStatement(QueryMapper.deleteBorrowBook);
					preparedStatement4.setInt(1, bookId);
					preparedStatement4.setInt(2, id);
					preparedStatement4.executeUpdate();
					PreparedStatement preparedStatement5 = connection.prepareStatement(QueryMapper.deleteRequestBook);
					preparedStatement5.setInt(1, bookId);
					preparedStatement5.setInt(2, id);
					preparedStatement5.executeUpdate();
					return true;
				}
			}
		} catch (Exception e) {
			throw new LMSException("Cannot return book");
		}
		return false;
	}

}
