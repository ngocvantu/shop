package com.tunguyen.shop.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Table(name = "user")
@Entity(name = "user") // error : Table name and entity name must be the same
public class User {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Length(min = 3)
	// @Pattern(regexp = "^[a-zA-Z0-9_.-]+$", message="Chỉ được nhập số, chữ số
	// và dấu gạch dưới")
	@Column(name = "username")
	private String username; 
	
	@NotEmpty
	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;

	@Column
	private Date dateCreated;

	@Column
	private Date dateOfBirth;

	@Column
	private String image;

	@Column
	private String userType;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
}
