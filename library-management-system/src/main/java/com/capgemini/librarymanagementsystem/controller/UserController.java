package com.capgemini.librarymanagementsystem.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.librarymanagementsystem.beans.LibraryResponse;
import com.capgemini.librarymanagementsystem.beans.UserBean;
import com.capgemini.librarymanagementsystem.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	public UserService service;
	
//	@GetMapping(path = "/searchBookByName",
//			produces = MediaType.APPLICATION_JSON_VALUE,
//			consumes = MediaType.APPLICATION_JSON_VALUE)
//	public LibraryResponse registerUser(@RequestBody UserBean user) {
//		LibraryResponse response = new LibraryResponse();
//		if (service.searchBookByName(bookName)) {
//			response.setStatusCode(201);
//			response.setMessage("SUCCESS");
//			response.setDescription("User added to database");
//			} else {
//				response.setStatusCode(401);
//				response.setMessage("FAILURE");
//				response.setDescription("User not added to the database");
//				
//		}
//		return response;
//	}

}
