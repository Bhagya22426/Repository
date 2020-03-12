package com.capgemini.librarymanagementsystem.beans;

import java.util.List;

import lombok.Data;

@Data
public class LibraryResponse {
	
	private int statusCode;
	private String message;
	private String description;
	
	private List<UserBean> beans;
	private List<BookInventory> book;

	

}
