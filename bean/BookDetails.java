package com.capgemini.librarymanagementsystem.bean;

public class BookDetails {

	private int bookId;
	private String author1;
	private String author2;
	private String bookName;
	private String publisher;
	private String yop;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getAuthor1() {
		return author1;
	}

	public void setAuthor1(String author1) {
		this.author1 = author1;
	}

	public String getAuthor2() {
		return author2;
	}

	public void setAuthor2(String author2) {
		this.author2 = author2;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getYop() {
		return yop;
	}

	public void setYop(String yop) {
		this.yop = yop;
	}

	@Override
	public String toString() {
		return "bookId= B" + bookId + ", author1=" + author1 + ", author2=" + author2 + ", bookName="
				+ bookName + ", publisher=" + publisher + ", yop=" + yop + "\n ";
	}

	
}
