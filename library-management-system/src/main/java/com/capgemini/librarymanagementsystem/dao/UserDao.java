package com.capgemini.librarymanagementsystem.dao;

import java.util.List;

import com.capgemini.librarymanagementsystem.beans.BookInventory;
import com.capgemini.librarymanagementsystem.beans.BookRegistration;
import com.capgemini.librarymanagementsystem.beans.BookTransaction;
import com.capgemini.librarymanagementsystem.beans.UserBean;

public interface UserDao {
	
	public UserBean auth(String email,String password);
	public boolean changePassword(int userId,String password);
	//public boolean forgotPassword(int user,String password);
	public List<BookInventory> searchBookByName(String bookName);
	public BookRegistration requestBook(BookInventory book, int userId);
	//public UserBean returnBook(BookInventory bookId, BookTransaction returnDate);
	public List<BookInventory> showAllBooks();

}
