package com.capgemini.librarymanagementsystem.bean;

public class BooksTransaction {

	private int transactionId;
	private int fine;
	private String issueDate;
	private int registrationId;
	private String returnDate;

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getFine() {
		return fine;
	}

	public void setFine(int fine) {
		this.fine = fine;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public int getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	@Override
	public String toString() {
		return "TransactionId= T" + transactionId + ", fine=" + fine + ", issueDate=" + issueDate
				+ ", registrationId=" + registrationId + ", returnDate=" + returnDate + "\n";
	}

	
}
