package com.capgemini.librarymanagementsystem.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="User_Details")
public class UserBean implements Serializable{
	
	@Id
	@Column
	@GeneratedValue
	private int userId;
	@Column
	private String userName;
	@Column
	private String userEmail;
	@Column
	private String password;
	@Column
	private String userType;
	@Column
	private long mobileNo;
	

}
