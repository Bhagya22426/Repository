package com.capgemini.librarymanagementsystem.bean;

public class BooksRegistration {

	private int registrationId;
	private int bookId;
	private int userId;
	private String registrationDate;

	public int getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public String toString() {
		return "RegistrationId= R" + registrationId + ", bookId=" + bookId + ", userId=" + userId
				+ ", registrationDate= "+registrationDate ;
	}

}
