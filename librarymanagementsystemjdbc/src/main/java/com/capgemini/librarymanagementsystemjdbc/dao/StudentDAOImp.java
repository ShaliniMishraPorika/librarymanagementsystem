package com.capgemini.librarymanagementsystemjdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemjdbc.exception.LMSException;

public class StudentDAOImp implements StudentDAO{
	Connection connection;
	Statement statement;
	PreparedStatement preparedStatement;
	ResultSet rs;
	int rs1;
	BookBean bean = new BookBean();
	@Override
	public BookBean searchBookByTitle(String title) {
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
				bean.setBookIsbn(rs.getInt("isbn"));
				bean.setBookCopies(rs.getInt("copies"));
				bean.setBookCopyRight(rs.getInt("copyright"));
				bean.setDateAdded(rs.getString("dateadded"));
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
			preparedStatement = connection.prepareStatement(QueryMapper.searchBookByAuthor);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {		
				bean.setBookId(rs.getInt("bId"));
				bean.setBookName(rs.getString("bname"));
				bean.setBookAuthor(rs.getString("author"));
				bean.setBookCopies(rs.getInt("copies"));
				bean.setBookCategory(rs.getString("category"));
				bean.setBookPublisherName(rs.getString("publishername"));
				bean.setBookIsbn(rs.getInt("isbn"));
				bean.setBookCopies(rs.getInt("copies"));
				bean.setBookCopyRight(rs.getInt("copyright"));
				bean.setDateAdded(rs.getString("dateadded"));
				bean.setStatus(rs.getString("status"));
				return bean;
			} else {
				System.out.println("Book Author with" +author+ "does not exist");
			}
		}
		catch(Exception e) {
			throw new LMSException("No book found with author" +author);
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
				bean.setBookIsbn(rs.getInt("isbn"));
				bean.setBookCopies(rs.getInt("copies"));
				bean.setBookCopyRight(rs.getInt("copyright"));
				bean.setDateAdded(rs.getString("dateadded"));
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


	@Override
	public ArrayList<BookBean> getBookIds() {
		ArrayList<BookBean> beans = new ArrayList<BookBean>();
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.getBookIds);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				bean.setBookId(rs.getInt("bid"));
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
				bean.setBookIsbn(rs.getInt("isbn"));
				bean.setBookCopies(rs.getInt("copies"));
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
	public boolean requestReturnBook(int bookId, int userId) {
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.requestBookDetails);
			preparedStatement.setInt(1, bookId);
			rs = preparedStatement.executeQuery();
			while(rs.next()) {
				PreparedStatement preparedStatement1 = connection.prepareStatement(QueryMapper.requestInsert);
				preparedStatement1.setInt(1, bookId);
				preparedStatement1.setInt(2, userId);
				preparedStatement1.setInt(3, userId);
				preparedStatement1.setInt(4, bookId);
				preparedStatement1.setInt(5, userId);
				preparedStatement1.setString(6, "request");
				rs1 = preparedStatement1.executeUpdate();
				return true;
			}
		} catch (Exception e) {
			throw new LMSException("Cannot insert book");
		}

		return false;
	}

	@Override
	public boolean requestBook(int userId, int bookId) {
		try {
			preparedStatement = connection.prepareStatement(QueryMapper.requestBookDetails);
			preparedStatement.setInt(1, bookId);
			rs = preparedStatement.executeQuery();
			while(rs.next()) {
				PreparedStatement preparedStatement1 = connection.prepareStatement(QueryMapper.returnRequest);
				preparedStatement1.setInt(1, bookId);
				preparedStatement1.setInt(2, userId);
				preparedStatement1.setInt(3, userId);
				preparedStatement1.setInt(4, bookId);
				preparedStatement1.setInt(5, userId);
				preparedStatement1.setString(6, "return");
				rs1 = preparedStatement1.executeUpdate();
				return true;

			}
		} catch (Exception e) {
			throw new LMSException("Cannot place the request for the book-id" + bookId);
		}
		return false;
	}
}
