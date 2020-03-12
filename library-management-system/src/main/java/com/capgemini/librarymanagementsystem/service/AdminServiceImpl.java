package com.capgemini.librarymanagementsystem.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.librarymanagementsystem.beans.BookInventory;
import com.capgemini.librarymanagementsystem.beans.BookRegistration;
import com.capgemini.librarymanagementsystem.beans.BookTransaction;
import com.capgemini.librarymanagementsystem.beans.UserBean;
import com.capgemini.librarymanagementsystem.dao.AdminDao;
@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminDao dao;

	@Override
	public boolean registerStudent(UserBean user) {
		return dao.registerStudent(user);
	}

	@Override
	public boolean updateStudent(UserBean userBean) {
		return dao.updateStudent(userBean);
	}

	@Override
	public boolean deleteStudent(int userId) {
		return dao.deleteStudent(userId);
	}

	@Override
	public List<UserBean> getStudentById(int userId) {
		return dao.getStudentById(userId);
	}

	@Override
	public List<UserBean> showAllStudents() {
		return dao.showAllStudents();
	}

	@Override
	public List<BookTransaction> showAllIssuedBooks() {
		return dao.showAllIssuedBooks();
	}

	@Override
	public List<BookRegistration> requestedBookInfo() {
		return dao.requestedBookInfo();
	}

	@Override
	public BookTransaction acceptRequest(String registrationId) {
		return dao.acceptRequest(registrationId);
	}

	@Override
	public BookTransaction addFine(String registrationId, Date returnDate) {
		return dao.addFine(registrationId, returnDate);
	}

	@Override
	public UserBean auth(String email, String password) {
		return dao.auth(email, password);
	}

	@Override
	public boolean addBook(BookInventory book) {
		return dao.addBook(book);
	}

	@Override
	public boolean updateBook(BookInventory book) {
		return dao.updateBook(book);
	}

	@Override
	public boolean deleteBook(int bookId) {
		return dao.deleteBook(bookId);
	}

	@Override
	public List<BookInventory> getBookByName(String bookName) {
		return dao.getBookByName(bookName);
	}

	@Override
	public List<BookInventory> showAllBooks() {
		return dao.showAllBooks();
	}

	
}
