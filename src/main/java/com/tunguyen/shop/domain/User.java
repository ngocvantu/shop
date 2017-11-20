package com.tunguyen.shop.domain;

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
	@NotEmpty
	@Column(name = "username")
	private String username; 
	
	@NotEmpty
	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;

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
	
	
}
