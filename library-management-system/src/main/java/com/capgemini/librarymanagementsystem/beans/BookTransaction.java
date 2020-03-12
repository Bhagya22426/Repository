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
@Table(name="Book_Transaction")
public class BookTransaction implements Serializable{
	
	@Id
	@Column
	private String transactionId;
	@Column
	private long fine;
	@Column
	private int registrationId;
	@Column
	private Date issueDate;
	@Column
	private Date returnDate;
	

}
