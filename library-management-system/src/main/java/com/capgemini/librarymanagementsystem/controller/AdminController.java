package com.capgemini.librarymanagementsystem.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.librarymanagementsystem.beans.BookInventory;
import com.capgemini.librarymanagementsystem.beans.LibraryResponse;
import com.capgemini.librarymanagementsystem.beans.UserBean;
import com.capgemini.librarymanagementsystem.service.AdminService;

@RestController
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	@PostMapping(path = "/registerStudent",
			produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
			consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public LibraryResponse registerStudent(@RequestBody UserBean bean) {
		LibraryResponse response = new LibraryResponse();
		if (service.registerStudent(bean)) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Student details has been added successfully");
		}else {
			response.setStatusCode(401);
			response.setMessage("Failure");
			response.setDescription("Student details are not added");
		}
		return response;
	}
	
	@PostMapping(path = "/updateStudent",
			produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
			consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public LibraryResponse updateStudent(@RequestBody UserBean bean) {
		LibraryResponse response  = new LibraryResponse();
		if (service.updateStudent(bean)) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Student details has updated");
			response.setBeans(Arrays.asList(bean));
		} else {
			response.setStatusCode(401);
			response.setMessage("Failure");
			response.setDescription("Student details is not updated");
		}
		return response;
	}
	
	@PostMapping(path = "/auth", 
			produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
			consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public LibraryResponse auth(@RequestBody UserBean bean) {
		UserBean infoBean = service.auth(bean.getUserEmail(), bean.getPassword());
		LibraryResponse response = new LibraryResponse();
		if (infoBean!=null) {
			response.setStatusCode(201);
			response.setMessage("SUCCESS");
			response.setDescription("you have entered valid Credentials");
			response.setBeans(Arrays.asList(infoBean));
		} else {
			response.setStatusCode(401);
			response.setMessage("FAILURE");
			response.setDescription("Invalid Credentials!!");
		}
		return response;
	}

	@DeleteMapping(path = "/deleteStudent/{userId}",
			produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public LibraryResponse deleteStudent(@PathVariable("userId") int userId) {
		LibraryResponse response = new LibraryResponse();
		if (service.deleteStudent(userId)) {
			response.setStatusCode(201);
			response.setMessage("SUCCESS");
			response.setDescription("Successfully deleted student details");
		} else {
			response.setStatusCode(401);
			response.setMessage("FAILURE");
			response.setDescription("Details not found to delete student");
		}
		return response;
	}
	
	@GetMapping(path = "/getStudentById/{userId}", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public LibraryResponse getStudentById(@PathVariable("userId")int userId) {
		LibraryResponse response = new LibraryResponse();
		List<UserBean> bean = service.getStudentById(userId);
		if (bean!=null && !bean.isEmpty()) {
			response.setStatusCode(201);
			response.setMessage("SUCCESS");
			response.setDescription("Data found from the database");
			response.setBeans(bean);
		} else {
			response.setStatusCode(401);
			response.setMessage("FAILURE");
			response.setDescription("Data not found from the database");

		}
		return response;
	}
	
	@GetMapping(path = "/showAllStudents",
			produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public LibraryResponse showAllStudents() {
		LibraryResponse response = new LibraryResponse();
		List<UserBean> bean = service.showAllStudents();
		if (bean!=null && !bean.isEmpty()) {
			response.setStatusCode(201);
			response.setMessage("SUCCESS");
			response.setDescription("Displayed all students");
			response.setBeans(bean);
		} else {
			response.setStatusCode(401);
			response.setMessage("FAILURE");
			response.setDescription("Unable to retrieve student details");
		}
		return response;
	}
	@PostMapping(path = "/addBook",
			produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
			consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public LibraryResponse addBook(@RequestBody BookInventory book) {
		LibraryResponse response = new LibraryResponse();
		if (service.addBook(book)) {
			response.setStatusCode(201);
			response.setMessage("SUCCESS");
			response.setDescription("BookDetails added to the database");
		} else {
			response.setStatusCode(401);
			response.setMessage("FALIURE");
			response.setDescription("Unable to add the BookDetails");
		}
		return response;
	}
	@PostMapping(path = "/updateBook",
			produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
			consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public LibraryResponse updateBook(@RequestBody BookInventory book) {
		LibraryResponse response = new LibraryResponse();
		if (service.updateBook(book)) {
			response.setStatusCode(201);
			response.setMessage("SUCCESS");
			response.setDescription("BookDetails has updated");
			response.setBook(Arrays.asList(book));
		} else {
			response.setStatusCode(401);
			response.setMessage("FAILURE");
			response.setDescription("BookDetails has not updated");
		}
		return response;
	}
	@DeleteMapping(path = "/deleteBook/{bookId}",
			produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public LibraryResponse deleteBook(@PathVariable("bookId") int bookId) {
		LibraryResponse response = new LibraryResponse();
		if (service.deleteBook(bookId)) {
			response.setStatusCode(201);
			response.setMessage("SUCCESS");
			response.setDescription("BookDetails has deleted from database");
		} else {
			response.setStatusCode(401);
			response.setMessage("FAILURE");
			response.setDescription("BookId not found to delete BookDetails");
		}
		return response;
	}
	
	@GetMapping(path = "/getBookByName",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public LibraryResponse getBookByName(@RequestParam("bookName")String bookName) {
		LibraryResponse response = new LibraryResponse();
		List<BookInventory> bean = service.getBookByName(bookName);
		if (bean!=null && !bean.isEmpty()) {
			response.setStatusCode(201);
			response.setMessage("SUCCESS");
			response.setDescription("BookDetails found by using BookName");
			response.setBook(bean);
		} else {
			response.setStatusCode(401);
			response.setMessage("FAILURE");
			response.setDescription("BookDetails not found");
		}
		return response;
	}
	@GetMapping(path = "/showAllBooks",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public LibraryResponse showAllBooks() {
		LibraryResponse response = new LibraryResponse();
		List<BookInventory> bean = service.showAllBooks();
		if (bean!=null && !bean.isEmpty()) {
			response.setStatusCode(201);
			response.setMessage("SUCCESS");
			response.setDescription("Viewed all the Books");
			response.setBook(bean);
		} else {
			response.setStatusCode(401);
			response.setMessage("FAILURE");
			response.setDescription("Unable to view the books");
		}
		return response;
	}
}
