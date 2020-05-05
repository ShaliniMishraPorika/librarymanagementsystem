package com.capgemini.librarymanagementsystemjdbc.dao;

public interface QueryMapper {
	String register = "insert into userInfo values(?,?,?,?,?,?);";
	String email = "select * from userInfo where email=?;";
	String login = "select * from userInfo where email=? and password=?;";
	String addBook = "insert into bookInfo values (?,?,?,?,?,?,?,?,?,?)";
	String searchBookByName = "select * from bookinfo where bname=?;";
	String searchBookByAuthor = "select * from booinfo where author=?;";
	String searchBookById = "select * from bookinfo where bId=?;";
	String getBookIds = "select bId from bookinfo;";
	String bookUpdate = "update bookinfo set bname=? where bId=?;";
	String deleteBook = "delete from bookinfo where bId=?";
	String getAllBookInfo = "select * from bookInfo;";
	String getAllUserInfo = "select * from userInfo;";
	String searchRequest = "select count(*) as uid from borrowed_books where uid=? and bid=? and email=(select email from userInfo where uId=?);";
	String searchRequest2 = "select count(*) as userId from book_issue_details where userId=?;";
	//String requestInsert = "insert into requestdetails values(?,(select name from userInfo where uId=?),?,(select bname from bookInfo where bId=?),(select email from userInfo where uId=?));";
	String issueRequest = "select * from requestdetails where uId=? and bId=? and email=(select email from userInfo where uId=?);";
	String returnBook = "delete from book_issue_details where bookId=? and userId=?;";
	String returnBook2 = "delete from borrowed_books where bid=? and uid=?;";
	String copiesInc = "update bookInfo set copies= copies + 1 where bId=?;";
	String bookHistoryDetails = "select count(*) as userId from book_issue_details where userId=?;";
	
	/*Issue Book Queries*/
	String bookDetails = "select * from bookinfo where bid=? and copies>1";
	String requestBook = "select * from requestdetails where bid=? and uid=?;";
	String borrowBook = "select count(uid) as uid from borrowed_books where uid=?";
	String bookIssue = "insert into book_issue_details values(?,?,?,?);";
	String borrowBookDetails = "insert into borrowed_books values(?,?,(select email from userInfo where uId=?));";
	String requestDelete = "delete from requestdetails where uid=? and bid=?;";
	String updateBookDetails1 = "update bookInfo set copies= copies-1 where bid=?;";
	
	/*Return Book Queries*/
	String issueBook = "select * book_issue_details where bookId=? and userId=?;";
	//String requestBook = "select * from requestdetails where bid=? and id=?;";
	String updateBookDetails2 = "update bookInfo set copies = copies+1 where bid=?;";
	String deleteBookDetails = "delete from book_issue_details where bookId=? and userId=?;";
	String deleteBorrowBook = "delete from borrowed_books where bid=? and uid=?;";
	String deleteRequestBook = "delete from requestdetails where bId=? and uId=?;";
	
	/*Request Book*/
	String requestBookDetails = "select * from bookInfo where bid=?;";
	String requestInsert = "insert into requestdetails values(?,(select name from userInfo where uId=?),?,(select bname from bookInfo where bId=?),(select email from userInfo where uId=?),?);";
	
	/*Return Request Book*/
	String returnRequest = "insert into requestdetails values(?,(select name from userInfo where uId=?),?,(select bname from bookInfo where bId=?),(select email from userInfo where uId=?),?);";
}
