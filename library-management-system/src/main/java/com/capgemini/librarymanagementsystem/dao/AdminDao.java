package com.capgemini.librarymanagementsystem.dao;

import java.util.Date;
import java.util.List;

import com.capgemini.librarymanagementsystem.beans.BookInventory;
import com.capgemini.librarymanagementsystem.beans.BookRegistration;
import com.capgemini.librarymanagementsystem.beans.BookTransaction;
import com.capgemini.librarymanagementsystem.beans.UserBean;

public interface AdminDao {
	
	public boolean registerStudent(UserBean user);
	
	public boolean updateStudent(UserBean userInfoBean);
	
	public boolean deleteStudent(int userId);
	
	//public List<UserBean> searchStudent(String userName);
	
	public List<UserBean> getStudentById(int userId); 
	
	public List<UserBean> showAllStudents();
	
	public List<BookTransaction> showAllIssuedBooks();
	
	public List<BookRegistration> requestedBookInfo();
	
	public BookTransaction acceptRequest(String registrationId);
	
	public BookTransaction addFine(String registrationId, Date returnDate);
	
	public UserBean auth(String email,String password);
	
	public boolean addBook(BookInventory book);
	
	public boolean updateBook(BookInventory book);
	
	public boolean deleteBook(int bookId);	
	
	public List<BookInventory> getBookByName(String bookName);
	
	public List<BookInventory> showAllBooks();
	
	
}
