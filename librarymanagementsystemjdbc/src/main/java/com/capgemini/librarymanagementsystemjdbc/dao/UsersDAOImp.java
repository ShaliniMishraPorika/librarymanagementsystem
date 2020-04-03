package com.capgemini.librarymanagementsystemjdbc.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import com.capgemini.librarymanagementsystemjdbc.dto.BookBean;
import com.capgemini.librarymanagementsystemjdbc.dto.BookIssueDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.BorrowedBooks;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestDetails;
import com.capgemini.librarymanagementsystemjdbc.dto.UsersBean;
import com.capgemini.librarymanagementsystemjdbc.exception.LMSException;

public class UsersDAOImp implements UsersDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Statement stmt = null;

	@Override
	public boolean register(UsersBean info) {
		try (FileInputStream fin = new FileInputStream("dburl.properties")) {

			Properties pro = new Properties();
			pro.load(fin);

			Class.forName("com.mysql.jdbc.Driver");
			String dburl = "jdbc:mysql://localhost:3306/library_management_system";
			try (Connection conn = DriverManager.getConnection(dburl, "root", "root")) {
				String query = "insert into users values(?,?,?,?,?,?)";
				try (PreparedStatement pstmt = conn.prepareStatement(query);) {
					pstmt.setInt(1, info.getId());
					pstmt.setString(2, info.getName());
					pstmt.setString(3, info.getMobile());
					pstmt.setString(4, info.getEmail());
					pstmt.setString(5, info.getPassword());
					pstmt.setString(6, info.getRole());
					int count = pstmt.executeUpdate();
					if(info.getEmail().isEmpty() && count==0) {
						return false;
					} else {
						return true;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public UsersBean auth(String email, String password) {
		try (FileInputStream fin = new FileInputStream("dburl.properties")) {

			Properties pro = new Properties();
			pro.load(fin);


			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro)) {
				String query = "select * from users where email=? and password=?";
				try (PreparedStatement pstmt = conn.prepareStatement(query)) {
					Class.forName(pro.getProperty("path"));
					pstmt.setString(1, email);
					pstmt.setString(2, password);
					try (ResultSet rs = pstmt.executeQuery()) {
						if (rs.next()) {
							UsersBean bean = new UsersBean();
							bean.setId(rs.getInt("uId"));
							bean.setName(rs.getString("name"));
							bean.setEmail(rs.getString("email"));
							bean.setPassword(rs.getString("password"));
							bean.setMobile(rs.getString("mobile"));
							bean.setRole(rs.getString("role"));
							return bean;
						} else {
							return null;
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public boolean addBook(BookBean book) {
		try(FileInputStream	fin = new FileInputStream("dburl.properties")){

			Properties pro = new Properties();
			pro.load(fin);
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro)){
				try(PreparedStatement pstmt = conn.prepareStatement("insert into book values (?,?,?,?,?)");){
					Class.forName(pro.getProperty("path"));
					pstmt.setInt(1, book.getBid());
					pstmt.setString(2, book.getBname());
					pstmt.setString(3, book.getAuthor());
					pstmt.setString(4, book.getCategory());
					pstmt.setString(5, book.getPublishername());
					int count = pstmt.executeUpdate();
					if (count != 0) {
						return true;
					} else {
						return false;
					}
				}
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public LinkedList<BookBean> searchBookByTitle(String bname) {
		BookBean bean = new BookBean();
		try(FileInputStream	fin = new FileInputStream("dburl.properties")){

			Properties pro = new Properties();
			pro.load(fin);


			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro)){

				try (PreparedStatement pstmt = conn.prepareStatement("select * from bookbean where bname=?");) {
					Class.forName(pro.getProperty("path"));
					pstmt.setString(1, bname);
					try (ResultSet rs = pstmt.executeQuery();) {
						LinkedList<BookBean> beans = new LinkedList<BookBean>();
						while (rs.next()) {
							BookBean book = new BookBean();
							book.setBid(rs.getInt("bId"));
							book.setBname(rs.getString("bname"));
							book.setAuthor(rs.getString("author"));
							book.setCategory(rs.getString("category"));
							book.setPublishername(rs.getString("publishername"));
							beans.add(book);
						}
						return beans;
					}
				}
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public LinkedList<BookBean> searchBookByAuthor(String bAuthor) {
		BookBean bean = new BookBean();
		try(FileInputStream	fin = new FileInputStream("dburl.properties")){

			Properties pro = new Properties();
			pro.load(fin);
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro)){
				try (PreparedStatement pstmt = conn.prepareStatement("select * from bookbean where author=?");) {
					Class.forName(pro.getProperty("path"));
					pstmt.setString(1, bAuthor);
					try (ResultSet rs = pstmt.executeQuery();) {
						LinkedList<BookBean> beans = new LinkedList<BookBean>();
						while (rs.next()) {
							BookBean book = new BookBean();
							book.setBid(rs.getInt("bId"));
							book.setBname(rs.getString("bname"));
							book.setAuthor(rs.getString("author"));
							book.setCategory(rs.getString("category"));
							book.setPublishername(rs.getString("publishername"));
							beans.add(book);
						}
						return beans;
					}
				}
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public LinkedList<BookBean> searchBookById(int bid) {
		BookBean bean = new BookBean();
		try(FileInputStream	fin = new FileInputStream("dburl.properties")){

			Properties pro = new Properties();
			pro.load(fin);

			try(Connection conn =
					DriverManager.getConnection(pro.getProperty("dburl"), pro)){
				try (PreparedStatement pstmt = conn.prepareStatement("select * from bookbean where bId=?");) {
					Class.forName(pro.getProperty("path"));
					pstmt.setInt(1, bid);
					try (ResultSet rs = pstmt.executeQuery();) {
						LinkedList<BookBean> beans = new LinkedList<BookBean>();
						while (rs.next()) {
							BookBean book = new BookBean();
							book.setBid(rs.getInt("bId"));
							book.setBname(rs.getString("bname"));
							book.setAuthor(rs.getString("author"));
							book.setCategory(rs.getString("category"));
							book.setPublishername(rs.getString("publishername"));
							beans.add(book);
						}
						return beans;
					}
				}
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateBook(BookBean bean) {

		try(FileInputStream info = new FileInputStream("dburl.properties");){
			Properties pro = new Properties();
			pro.load(info);
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);) {
				try (PreparedStatement pstmt = conn.prepareStatement("update book set bname=? where bId=?");) {
					Class.forName(pro.getProperty("path"));
					pstmt.setInt(1, bean.getBid());
					pstmt.setString(2, bean.getBname());
					pstmt.setString(3, bean.getAuthor());
					pstmt.setString(4, bean.getCategory());
					pstmt.setString(5, bean.getPublishername());
					int count = pstmt.executeUpdate();
					if (count != 0) {
						return true;
					} else {
						return false;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean removeBook(int bid) {
		try(FileInputStream	fin = new FileInputStream("dburl.properties")){

			Properties pro = new Properties();
			pro.load(fin);

			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro)){

				try(PreparedStatement pstmt = conn.prepareStatement("delete from book where bId=?")){
					Class.forName(pro.getProperty("path"));
					pstmt.setInt(1, bid);
					int rs = pstmt.executeUpdate();
					if (rs != 0) {
						return true;
					} else {
						return false;
					}
				}
			}

		}catch(Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public LinkedList<BookBean> getBookIds() {
		try(FileInputStream	fin = new FileInputStream("dburl.properties")){

			Properties pro = new Properties();
			pro.load(fin);

			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro)){

				try(PreparedStatement pstmt = conn.prepareStatement("select distinct bId from bookbean")){
					try (Statement stmt = (Statement)conn.createStatement();) {
						Class.forName(pro.getProperty("path"));

						try (ResultSet rs = stmt.executeQuery("select distinct bId from book");) {
							LinkedList<BookBean> beans = new LinkedList<BookBean>();
							while (rs.next()) {
								BookBean bean = new BookBean();
								bean.setBid(rs.getInt("bId"));

								beans.add(bean);
							}
							return beans;
						}
					}
				}
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public LinkedList<BookBean> getBooksInfo() {
		try(FileInputStream	fin = new FileInputStream("dburl.properties")){

			Properties pro = new Properties();
			pro.load(fin);

			Class.forName(pro.getProperty("path")).newInstance();
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro)){
				try (Statement stmt = (Statement)conn.createStatement();) {
					Class.forName(pro.getProperty("path"));

					try (ResultSet rs = stmt.executeQuery("select * from bookbean");) {
						LinkedList<BookBean> beans = new LinkedList<BookBean>();
						while (rs.next()) {
							BookBean bean = new BookBean();
							bean.setBid(rs.getInt("bId"));
							bean.setBname(rs.getString("bname"));
							bean.setAuthor(rs.getString("author"));
							bean.setCategory(rs.getString("category"));
							bean.setPublishername(rs.getString("publishername"));

							beans.add(bean);
						}
						return beans;
					}
				}
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<UsersBean> showUsers() {
		try(FileInputStream	fin = new FileInputStream("dburl.properties")){

			Properties pro = new Properties();
			pro.load(fin);

			Class.forName(pro.getProperty("path")).newInstance();
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"), pro)){
				String query = "select * from users";
				try (PreparedStatement pstmt = conn.prepareStatement(query);) {
					try (ResultSet rs = pstmt.executeQuery(query);) {
						List<UsersBean> usersList = new LinkedList<UsersBean>();
						while (rs.next()) {

							UsersBean user = new UsersBean();
							user.setId(rs.getInt("uId"));
							user.setName(rs.getString("name"));
							user.setEmail(rs.getString("email"));
							user.setMobile(rs.getString("mobile"));
							user.setRole(rs.getString("role"));
							usersList.add(user);
						}
						return usersList;
					}
				}
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean request(int uId, int bId) {
		try(FileInputStream info = new FileInputStream("dburl.properties");){
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
					PreparedStatement pstmt = conn.prepareStatement("select count(*) as uid from borrowed_books where uid=? and bid=? and email=(select email from users where uId=?)");) {
				pstmt.setInt(1, uId);
				pstmt.setInt(2, bId);
				pstmt.setInt(3, uId);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					int isBookExists = rs.getInt("uId");
					if(isBookExists==0) {
						try(PreparedStatement pstmt1 = conn.prepareStatement("select count(*) as userId from book_issue_details where userId=?");) {
							pstmt1.setInt(1, uId);
							rs=pstmt1.executeQuery();
							if(rs.next()) {
								int noOfBooksBorrowed = rs.getInt("userId");
								if(noOfBooksBorrowed<3) {
									try(PreparedStatement pstmt2 = conn.prepareStatement("insert into request_details values(?,(select name from users where uId=?)"
											+ ",?,(select bname from book where bId=?),(select email from users where uId=?))");){
										pstmt2.setInt(1,uId);
										pstmt2.setInt(2, uId);
										pstmt2.setInt(3, bId);
										pstmt2.setInt(4, bId);
										pstmt2.setInt(5, uId);
										int count = pstmt2.executeUpdate();
										if(count != 0) {
											return true;
										}else {
											return false;
										}
									}				 
								}else {
									throw new LMSException("no Of books limit has crossed");
								}
							}else {
								throw new LMSException("no of books limit has crossed");
							}		
						}				
					}else{
						throw new LMSException("You have already borrowed the requested book");
					}		
				}else {
					throw new LMSException("You have already borrowed the requested book");
				}			
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	@Override
	public List<RequestDetails> showRequest() {
		try (FileInputStream fin = new FileInputStream("dburl.properties")) {

			Properties pro = new Properties();
			pro.load(fin);

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String dburl = "jdbc:mysql://localhost:3306/library_management_system";
			try (Connection conn = DriverManager.getConnection(dburl, "root", "root")) {
				String query = "select * from request_details;";
				try (PreparedStatement pstmt = conn.prepareStatement(query);) {

					try (ResultSet rs = pstmt.executeQuery(query);) {
						List<RequestDetails> beans = new LinkedList<RequestDetails>();
						while (rs.next()) {
							RequestDetails book = new RequestDetails();
							book.setuId(rs.getInt("uId"));
							book.setFullName(rs.getString("userName"));
							book.setbId(rs.getInt("bId"));
							book.setBookName(rs.getString("bookName"));
							beans.add(book);
						}
						return beans;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}
	@Override
	public boolean issueBook(int bId, int uId) {
		try(FileInputStream info = new FileInputStream("dburl.properties");){
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
				PreparedStatement pstmt = conn.prepareStatement("select * from request_details where uId=? and bId=? and email=(select email from users where uId=?)")) {
				pstmt.setInt(1, uId);
				pstmt.setInt(2, bId);
				pstmt.setInt(3, uId);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					try(PreparedStatement pstmt1 = conn.prepareStatement("insert into book_issue_details values(?,?,?,?)");){
						pstmt1.setInt(1, bId);
						pstmt1.setInt(2, uId);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
						Calendar cal = Calendar.getInstance();
						String issueDate = sdf.format(cal.getTime());
						pstmt1.setDate(3, java.sql.Date.valueOf(issueDate));
						cal.add(Calendar.DAY_OF_MONTH, 7);
						String returnDate = sdf.format(cal.getTime());
						pstmt1.setDate(4, java.sql.Date.valueOf(returnDate));
						int count=pstmt1.executeUpdate();
						if(count != 0) {	
							try(PreparedStatement pstmt2 = conn.prepareStatement("Insert into borrowed_books values(?,?,(select email from users where uId=?))")){
								pstmt2.setInt(1, uId);
								pstmt2.setInt(2, bId);
								pstmt2.setInt(3, uId);
								int isBorrowed = pstmt2.executeUpdate();
								if(isBorrowed != 0) {
									return true;
								}else {
									return false;
								}
							}
						} else {
							throw new LMSException("Book Not issued");
						}					
					}
				} else {
					throw new LMSException("The respective user have not placed any request");
				}			
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}

	@Override
	public boolean returnBook(int bId, int uId) {
		try(FileInputStream info = new FileInputStream("dburl.properties");){
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);) {
				try (PreparedStatement pstmt = conn.prepareStatement("delete from book_issue_details where bookId=? and userId=?");) {
					pstmt.setInt(1,bId);
					pstmt.setInt(2,uId);
					int count =  pstmt.executeUpdate();
					if(count != 0) {
						try(PreparedStatement pstmt1 = conn.prepareStatement("delete from borrowed_books where bid=? and uid=?");) {
							pstmt1.setInt(1, bId);
							pstmt1.setInt(2, uId);
							int isReturned = pstmt1.executeUpdate();
							if(isReturned != 0 ) {
								return true;
							}else {
								return false;
							}
						}
					} else {
						return false;
					}
				}

			}
		} catch(Exception e) {
			e.printStackTrace();
		}		
		return false;
	}
	@Override
	public LinkedList<BookIssueDetails> bookHistoryDetails(int uId) {
		try(FileInputStream info = new FileInputStream("dburl.properties");){
			Properties pro = new Properties();
			pro.load(info);
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);) {
				try (PreparedStatement pstmt = conn.prepareStatement("select count(*) as userId from book_issue_details where userId=?");) {
					Class.forName(pro.getProperty("path"));
					pstmt.setInt(1, uId);
					rs=pstmt.executeQuery();
					LinkedList<BookIssueDetails> beans = new LinkedList<BookIssueDetails>();
					while(rs.next()) {
						BookIssueDetails issueDetails = new BookIssueDetails();
						issueDetails.setUserId(rs.getInt("userId"));
						beans.add(issueDetails);
					} 
					return beans;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<BorrowedBooks> borrowedBook(int uId) {

		try (FileInputStream info = new FileInputStream("dburl.properties");){
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try (Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);) {
				try (PreparedStatement pstmt = conn.prepareStatement("select * from borrowed_books where uid=?");) {
					pstmt.setInt(1, uId);
					rs=pstmt.executeQuery();
					LinkedList<BorrowedBooks> beans = new LinkedList<BorrowedBooks>();
					while(rs.next()) {
						BorrowedBooks listOfbooksBorrowed = new BorrowedBooks();
						listOfbooksBorrowed.setuId(rs.getInt("uid"));
						listOfbooksBorrowed.setbId(rs.getInt("bid"));
						listOfbooksBorrowed.setEmail(rs.getString("email"));
						beans.add(listOfbooksBorrowed);
					} 
					return beans;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}