package com.capgemini.librarymanagementsystem.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.capgemini.librarymanagementsystem.beans.BookInventory;
import com.capgemini.librarymanagementsystem.beans.BookRegistration;
import com.capgemini.librarymanagementsystem.beans.BookTransaction;
import com.capgemini.librarymanagementsystem.beans.UserBean;
import com.capgemini.librarymanagementsystem.exception.BookException;
import com.capgemini.librarymanagementsystem.exception.UniqueEmailException;

import lombok.extern.java.Log;
@Log
@Repository
public class AdminDaoImpl implements AdminDao{

	@PersistenceUnit
	private EntityManagerFactory factory;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public boolean registerStudent(UserBean user) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		
		transaction.begin();
		try {
			user.setPassword(encoder.encode(user.getPassword()));
			manager.persist(user);
			transaction.commit();
			return true;
		} catch (Exception e) {
			log.info(e.getMessage());
			for (StackTraceElement element : e.getStackTrace()) {
				log.info(element.toString());
			}
			throw new BookException("BookId already exists!!");
		}
	}

	@Override
	public boolean updateStudent(UserBean userBean) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		UserBean bean = manager.find(UserBean.class, userBean.getUserId());
		transaction.begin();
		try {
			//bean.setPassword(bean.getPassword());
			bean.setUserName(userBean.getUserName());
			manager.persist(bean);
			transaction.commit();
		} catch (Exception e) {
			log.info(e.getMessage());
			for (StackTraceElement element : e.getStackTrace()) {
				log.info(element.toString());
			}
		}
		return true;
	}

	@Override
	public boolean deleteStudent(int userId) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		UserBean bean = manager.find(UserBean.class, userId);
		manager.remove(bean);
		transaction.commit();
		return true;
	}

	@Override
	public List<UserBean> getStudentById(int userId) {
		String jpql = "select i from UserBean i where i.userId=:userId";
		EntityManager manager = factory.createEntityManager();
		TypedQuery<UserBean> query = manager.createQuery(jpql, UserBean.class);
		query.setParameter("userId", userId);
		return query.getResultList();
	}

	@Override
	public List<UserBean> showAllStudents() {
		String jpql = "select i from UserBean i";
		EntityManager manager = factory.createEntityManager();
		TypedQuery<UserBean> query = manager.createQuery(jpql, UserBean.class);
		List<UserBean> beans = query.getResultList();
		return beans;
	}

	@Override
	public UserBean auth(String userEmail, String password) {
		String jpql = "select u from UserBean u where u.userEmail=:userEmail";
		EntityManager manager = factory.createEntityManager();
		TypedQuery<UserBean> query = manager.createQuery(jpql, UserBean.class);
		query.setParameter("userEmail", userEmail);
		try {
			UserBean bean = query.getSingleResult();
			if (encoder.matches(password, bean.getPassword())) {
				return bean;

			} else {
				return null;
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			for (StackTraceElement element : e.getStackTrace()) {
				log.info(element.toString());
			}
			return null;
		}

	}

	@Override
	public boolean addBook(BookInventory book) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			manager.persist(book);
			transaction.commit();
			return true;
		} catch (Exception e) {
			log.info(e.getMessage());
			for (StackTraceElement element : e.getStackTrace()) {
				log.info(element.toString());
			}
		}

		throw new UniqueEmailException("Email already exists");
	}

	@Override
	public boolean updateBook(BookInventory book) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		BookInventory bean = manager.find(BookInventory.class, book.getBookId());
		transaction.begin();
		try {
			bean.setBookName(book.getBookName());
			manager.persist(bean);
			transaction.commit();
		} catch (Exception e) {
			log.info(e.getMessage());
			for (StackTraceElement element : e.getStackTrace()) {
				log.info(element.toString());
			}
		}
		return true;
	}

	@Override
	public boolean deleteBook(int bookId) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		
		BookInventory bean = manager.find(BookInventory.class, bookId);
		manager.remove(bean);
		transaction.commit();
		return true;
	}

	@Override
	public List<BookInventory> getBookByName(String bookName) {
		String jpql = "select b from BookInventory b where b.bookName=:bookName";
		EntityManager manager = factory.createEntityManager();
		TypedQuery<BookInventory> query = manager.createQuery(jpql, BookInventory.class);
		query.setParameter("bookName", bookName);
		return query.getResultList();
	}

	@Override
	public List<BookInventory> showAllBooks() {
		String jpql = "select b from BookInventory b";
		EntityManager manager = factory.createEntityManager();
		TypedQuery<BookInventory> query = manager.createQuery(jpql, BookInventory.class);
		return query.getResultList();
	}

	@Override
	public List<BookTransaction> showAllIssuedBooks() {
		EntityManager manager = factory.createEntityManager();
		@SuppressWarnings("unused")
		EntityTransaction transaction = manager.getTransaction();
		List<BookTransaction> list = new ArrayList<BookTransaction>();
		try {
		String jpql = "select t from BookTransaction t";
		TypedQuery<BookTransaction> query = manager.createQuery(jpql, BookTransaction.class);
		@SuppressWarnings("unused")
		List<BookTransaction> list1 = query.getResultList();
				
		for (BookTransaction book : list) {
			list.add(book);
		}
	}catch(Exception e){
		return list;
	}
		return list;
	}

	@Override
	public List<BookRegistration> requestedBookInfo() {
		EntityManager manager=factory.createEntityManager();
		EntityTransaction transaction=manager.getTransaction();
		List<BookRegistration> arrayList = new ArrayList<BookRegistration>();
		try {
			String jpql = "select r from BookRegistration r";
			TypedQuery<BookRegistration> query=manager.createQuery(jpql, BookRegistration.class);
			List<BookRegistration> list = query.getResultList();
			for (BookRegistration bookRegistration : list) {
				arrayList.add(bookRegistration);
				
			}
		} catch (Exception e) {
			return arrayList;
		}
		return arrayList;
	}

	@Override
	public BookTransaction acceptRequest(String registrationId) {
		
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		String viewDetails = "select r from BookRegistration where registrationId=:registrationId";
		TypedQuery<BookRegistration> query = manager.createQuery(viewDetails, BookRegistration.class);
		query.setParameter("registrationId", registrationId);
		BookRegistration books = query.getSingleResult();
		
		Random random = new Random();
		int transactionId = random.nextInt();
		
		if (transactionId < 0) {
			transactionId = transactionId*(-1);
		}
		BookTransaction trans = new BookTransaction();
		trans.setRegistrationId(books.getRegistrationId());
		trans.setTransactionId(Integer.toString(transactionId));
		trans.setIssueDate(books.getRegistrationDate());
		trans.setFine(0);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(books.getRegistrationDate());
		calendar.add(Calendar.DATE, 15);
		
		trans.setReturnDate(calendar.getTime());
		manager.persist(trans);
		transaction.commit();
		return trans;
	}

	@Override
	public BookTransaction addFine(String registrationId, Date returnDate) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		String viewDetails = "select t from BookTransaction where registrationId=:registrationId";
		TypedQuery<BookTransaction> query = manager.createQuery(viewDetails, BookTransaction.class);
		query.setParameter("registrationId", registrationId);
		BookTransaction book = query.getSingleResult();
		Date ret = book.getReturnDate();
		BookTransaction present = manager.find(BookTransaction.class, book.getRegistrationId());
		long days = (returnDate.getTime()-ret.getTime())/(1000*60*60*24);
		if ((days - 15) > 0) {
			present.setFine((days - 15) *1);
		} else {
			present.setFine(book.getFine());
		}
		present.setIssueDate(book.getIssueDate());
		present.setRegistrationId(book.getRegistrationId());
		present.setReturnDate(book.getReturnDate());
		present.setTransactionId(book.getTransactionId());
		transaction.commit();
		System.out.println(present.getFine());
		return book;
	}



	//	@Override
	//	public List<UserBean> searchStudent(String userName) {
	//		String jpql = "select u from UserBean u where u.userName=:uName";
	//		EntityManager manager = factory.createEntityManager();
	//		TypedQuery<UserBean> query = manager.createQuery(jpql,UserBean.class);
	//		query.setParameter("userName", userName);
	//		return query.getResultList();
	//	}


}
