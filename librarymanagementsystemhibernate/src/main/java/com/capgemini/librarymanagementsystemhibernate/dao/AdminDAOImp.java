package com.capgemini.librarymanagementsystemhibernate.dao;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;
import com.capgemini.librarymanagementsystemhibernate.dto.BookIssueDetails;

public class AdminDAOImp implements AdminDAO {
	EntityManagerFactory factory = null;
	EntityManager manager = null;
	EntityTransaction transaction = null;
	
	public boolean update(BookBean book) {

		boolean isUpdated = false;
		
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			BookBean bean = manager.find(BookBean.class, book.getBookId());
			bean.setTitle(book.getTitle());
			manager.merge(book);
			isUpdated = true;
			System.out.println("updated");
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}	
		return isUpdated;
	}

	public boolean delete(int bookId) {

		boolean isDeleted = false;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			BookBean record = manager.find(BookBean.class, bookId);
			if(manager.contains(record)) {
				isDeleted = true;
				manager.remove(record);
				System.out.println("Record removed");
			} else {
				isDeleted = false;
				System.out.println("record not found");
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
		return isDeleted;
	}

	public boolean addBook(BookBean info) {
		boolean isBookAdded = false;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.merge(info);
			isBookAdded = true;
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
		return isBookAdded;
	}

	public List<Integer> getBookIds() {
		List<Integer> bookBeans = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager.createQuery("select b.bookId from BookBean b");
			bookBeans = query.getResultList();
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
		return bookBeans;
	}

	public List<BookBean> getBooksInfo() {
		List<BookBean> bookBeans = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager.createQuery("from BookBean");
			bookBeans = query.getResultList();
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
		return bookBeans;
	}

	public boolean issueBook(int id , int bookId) {
	
		BookIssueDetails book = new BookIssueDetails();
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager.createQuery("select b from BookBean b where b.bookId = :bbookId and b.copies>=1");
			Query query2 = query.setParameter("bbookId", bookId);
			List count = query2.getResultList();
			System.out.println(count);
			int S = count.size();
			if(S>=1) {
				Query query3 = manager.createQuery("select r from RequestBook r where r.id = :id and r.reqBookPK.bookId = :bookId");
				query3.setParameter("id", id);
				query3.setParameter("bookId", bookId);
				List count1 = query3.getResultList();
				int s = count1.size();
				System.out.println(s);
				if(s>=1) {
					Query q2 = manager.createQuery("select count(id) as idCount from BorrowedBook b where id=:id");
					q2.setParameter("id", id);
					List count2 = q2.getResultList();
					int s1 = count2.size();
					if(s1>=1) {
						int noOfBooksBorrowed =  count2.indexOf(0);
						if(noOfBooksBorrowed<3) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							LocalDate date = LocalDate.now();
							Calendar cal = Calendar.getInstance();
							cal.setTime(new java.util.Date());
							cal.add(Calendar.DATE, 15);
							String date1 =	sdf.format(cal.getTime());
							Query userEmail = manager.createQuery("select u.email from UserBean u  where id = :id");
							userEmail.setParameter("id", id);
							List userEmail1 = userEmail.getResultList();
							Query q3 = manager.createNativeQuery("insert into BookIssueDetails (id,bookId,email,issueDate,returnDate) values (? , ? , ? , ? , ?) ");
							q3.setParameter(1, id);
							q3.setParameter(2 , bookId);
							q3.setParameter(3, userEmail1.get(0));
							q3.setParameter(4, date);
							q3.setParameter(5, date1);
							int count3 = q3.executeUpdate();
							if(count3 != 0) {
								Query userEmail4 = manager.createQuery("select u.email from UserBean u where id = :id");
								userEmail4.setParameter("id", id);
								List userEmail44 = userEmail4.getResultList();
								Query q4 = manager.createNativeQuery("insert into BorrowedBooks (id,bookId,email) values (?,?,?)");
								q4.setParameter(1, id);
								q4.setParameter(2, bookId);
								q4.setParameter(3, userEmail44.get(0));
								q4.executeUpdate();

								Query q5 = manager.createQuery("delete from RequestBook r where r.id = :id and r.reqBookPK.bookId = :bookId");
								q5.setParameter("id", id);
								q5.setParameter("bookId", bookId);
								q5.executeUpdate();
								Query q6 = manager.createQuery("update BookBean b set b.copies = b.copies-1 where b.bookId = :bookId");
								q6.setParameter("bookId", bookId);
								q6.executeUpdate();
								transaction.commit();
								return true;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
		return false;
	}

	public BookBean searchBookByTitle(String name) {
		BookBean result = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			String jpql = "select m from BookBean m where m.title =:mtitle";
			TypedQuery<BookBean> query = manager.createQuery(jpql, BookBean.class);
			query.setParameter("mtitle", name);
			result = query.getSingleResult();
			
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
		return  result;
	}

	public BookBean searchBookByAuthor(String author) {
		BookBean result = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			String jpql = "select m from BookBean m where m.author =:mauthor";
			TypedQuery<BookBean> query = manager.createQuery(jpql, BookBean.class);
			query.setParameter("mauthor", author);
			result = query.getSingleResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
		return  result;
	}

	public BookBean searchBookById(int bookId) {
		BookBean result = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			result = manager.find(BookBean.class, bookId);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
		return result;
	}

	public boolean returnBook(int id, int bookId) {
		BookBean result = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager.createQuery("select b from BookIssueDetails b where b.id = :id and b.issuePK.bookId = :bookId");
			query.setParameter("id", id);
			query.setParameter("bookId", bookId);
			List count = query.getResultList();
			int i = count.size();
			if(i>=1) {
				Query query2 = manager.createQuery("select r from RequestBook r where r.id = :id and r.reqBookPK.bookId = :bookId and type = :type");
				query2.setParameter("id", id);
				query2.setParameter("bookId", bookId);
				query2.setParameter("type", "return");
				List count1 = query2.getResultList();
				int i1 = count1.size();
				if(i1>=1) {
					Query query3 = manager.createQuery("update BookBean b  set b.copies = b.copies+1 where b.bookId = :bookId");
					query3.setParameter("bookId", bookId);
					query3.executeUpdate();
					Query query4 = manager.createQuery("delete from BookIssueDetails b where b.issuePK.bookId = :bookId and id =:id");
					query4.setParameter("bookId", bookId);
					query4.setParameter("id", id);
					query4.executeUpdate();
					Query query5 = manager.createQuery("delete from BorrowedBook b where b.borrowBookPK.bookId = :bookId and id = :id");
					query5.setParameter("bookId", bookId);
					query5.setParameter("id", id);
					query5.executeUpdate();
					Query query6 = manager.createQuery("delete from RequestBook r where r.id = :id and r.reqBookPK.bookId = :bookId and type = :type");
					query6.setParameter("id", id);
					query6.setParameter("bookId", bookId);
					query6.setParameter("type", "return");
					query6.executeUpdate();
					transaction.commit();
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();
		return false;
	}

}
