package com.capgemini.librarymanagementsystem.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Book_Registration")
public class BookRegistration implements Serializable{
	
	@Id
	@Column
	private int registrationId;
	@Column
	private int bookId;
	@Column
	private int userId;
	@Column
	private Date registrationDate;

}
