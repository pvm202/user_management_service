package com.usermanagement.models;


import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;



@Entity
@Table(name = "user_info")
@EntityListeners(AuditingEntityListener.class) // Enable auditing

public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	
	
	private String userName;
	
	@Column(unique = true,nullable = false)
	private String userEmail;
	
	private String userPassword;
	
	private String userPhone;
	
	@Column(updatable = false)
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime userCreatedAt;
	
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime userUpdatedAt;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public LocalDateTime getUserCreatedAt() {
		return userCreatedAt;
	}

	public void setUserCreatedAt(LocalDateTime userCreatedAt) {
		this.userCreatedAt = userCreatedAt;
	}

	public LocalDateTime getUserUpdatedAt() {
		return userUpdatedAt;
	}

	public void setUserUpdatedAt(LocalDateTime userUpdatedAt) {
		this.userUpdatedAt = userUpdatedAt;
	}

	public User(Integer userId, String userName, String userEmail, String userPassword, String userPhone,
			LocalDateTime userCreatedAt, LocalDateTime userUpdatedAt) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userPhone = userPhone;
		this.userCreatedAt = userCreatedAt;
		this.userUpdatedAt = userUpdatedAt;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	

}
