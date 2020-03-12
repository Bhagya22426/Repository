package com.capgemini.librarymanagementsystem.dao;

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
import com.capgemini.librarymanagementsystem.beans.UserBean;
import com.capgemini.librarymanagementsystem.exception.BookException;

import lombok.extern.java.Log;
@Log
@Repository
public class UserDaoImpl implements UserDao{
	
	@PersistenceUnit
	private EntityManagerFactory factory;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public UserBean auth(String email, String password) {
		String jpql = "select a from UserBean a where a.email=:email";
		EntityManager manager = factory.createEntityManager();
		TypedQuery<UserBean> query = manager.createQuery(jpql, UserBean.class);
		query.setParameter("email", email);
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
		//EntityTransaction transaction = manager.getTransaction();
	}

	@Override
	public boolean changePassword(int userId, String password) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		UserBean bean = manager.find(UserBean.class, userId);
		bean.setPassword(encoder.encode(password));
		manager.persist(bean);
		transaction.commit();
		return true;
	}

//	@Override
//	public boolean forgotPassword(String email, String password) {
//		return false;
//	}

	@Override
	public List<BookInventory> searchBookByName(String bookName) {
		String jpql = "select b from BookInventory b where b.bookName=:bookName";
		EntityManager manager = factory.createEntityManager();
		TypedQuery<BookInventory> query = manager.createQuery(jpql, BookInventory.class);
		query.setParameter("bookName", bookName);
		return query.getResultList();
	}

	@Override
	public BookRegistration requestBook(BookInventory book, int userId) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		String viewDetails = "select b from BookInventory where bookId=:bookId";
		TypedQuery<BookInventory> query = manager.createQuery(viewDetails, BookInventory.class);
		query.setParameter("bookId", book.getBookId());
		BookInventory books = query.getSingleResult();
		BookRegistration reg = new BookRegistration();
		
		reg.setBookId(books.getBookId());
		Date date = new Date();
		reg.setRegistrationDate(date);
		Random random = new Random();
		int registrationId = random.nextInt();
		reg.setRegistrationId(registrationId);
		reg.setUserId(userId);
		manager.persist(reg);
		transaction.commit();
		return reg;
}
//	@Override
//	public UserBean returnBook(String bookName) {
//		
//		return null;
//	}

	@Override
	public List<BookInventory> showAllBooks() {
		String jpql = "select b from BookInventory b";
		EntityManager manager = factory.createEntityManager();
		TypedQuery<BookInventory> query = manager.createQuery(jpql, BookInventory.class);
		return query.getResultList();
	}
	
	
}
