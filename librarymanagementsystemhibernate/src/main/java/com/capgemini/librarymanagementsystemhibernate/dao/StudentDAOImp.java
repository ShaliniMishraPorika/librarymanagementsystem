package com.capgemini.librarymanagementsystemhibernate.dao;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.capgemini.librarymanagementsystemhibernate.dto.BookBean;
import com.capgemini.librarymanagementsystemhibernate.dto.RequestBook;
import com.capgemini.librarymanagementsystemhibernate.dto.UserBean;

public class StudentDAOImp implements StudentDAO {
	EntityManagerFactory factory = null;
	EntityManager manager = null;
	EntityTransaction transaction = null;

	public BookBean searchBookByTitle(String name) {
		BookBean result = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			String jpql = "select m from BookBean m where m.mtitle =:mtitle";
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
		return result;
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
		return result;
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

	public List<Integer> getBookIds() {

		List<Integer> bookBeans = null;
		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.getTransaction();
			Query q = manager.createQuery("select bookId from BookBean b");
			bookBeans = q.getResultList();
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
			manager.getTransaction();
			Query query = manager.createQuery("select * from BookBean");
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

	public boolean request(int id, int bookId) {

		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager.createQuery("select b from BookBean b where b.bookId = :bbookId");
			Query query2 = query.setParameter("bbookId", bookId);
			List count = query2.getResultList();
			int c = count.size();
			if (c != 0) {

				Query query3 = manager.createQuery("select u.name from UserBean u where id=:id");
				query3.setParameter("id", id);
				List list = query3.getResultList();
				Query query4 = manager.createQuery("select b.title from BookBean b where bookId=:bookId");
				query4.setParameter("bookId", bookId);
				List list2 = query4.getResultList();
				Query query5 = manager.createQuery("select e.email from UserBean e where id=:id");
				query5.setParameter("id", id);
				List list3 = query5.getResultList();
				Query req = manager.createNativeQuery(
						"insert into RequestBook (bookId,email,title,id,name,type) values (?,?,?,?,?,?)");
				req.setParameter(1, bookId);
				req.setParameter(2, list3.get(0));
				req.setParameter(3, list2.get(0));
				req.setParameter(4, id);
				req.setParameter(5, list.get(0));
				req.setParameter(6, "request");

				req.executeUpdate();
				transaction.commit();
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		manager.close();
		factory.close();

		return false;
	}

	public boolean reqReturnBook(int id, int bookId) {

		try {
			factory = Persistence.createEntityManagerFactory("TestPersistence");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager.createQuery("select b from BorrowedBookBean b where b.borrowBookPK.bookId = :bookId");
			query.setParameter("bookId", bookId);
			List count = query.getResultList();
			int s = count.size();
			if (s >= 0) {
				Query query2 = manager.createQuery("select u.name from UserBean u where id=:id");
				query2.setParameter("id", id);
				List list = query2.getResultList();
				Query query3 = manager.createQuery("select b.title from BookBean b where bookId=:bookId");
				query3.setParameter("bookId", bookId);
				List list2 = query3.getResultList();
				Query query4 = manager.createQuery("select e.email from UserBean e where id=:id");
				query4.setParameter("id", id);
				List list3 = query4.getResultList();
				Query requestQuery = manager.createNativeQuery(
						"insert into RequestBook (bookId,email,title,id,name,type) values (?,?,?,?,?,?)");
				requestQuery.setParameter(1, bookId);
				requestQuery.setParameter(2, list3.get(0));
				requestQuery.setParameter(3, list2.get(0));
				requestQuery.setParameter(4, id);
				requestQuery.setParameter(5, list.get(0));
				requestQuery.setParameter(6, "return");
				int count1 = requestQuery.executeUpdate();
				transaction.commit();
				return true;
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
