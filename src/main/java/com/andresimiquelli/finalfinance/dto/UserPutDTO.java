package com.andresimiquelli.finalfinance.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.andresimiquelli.finalfinance.entities.enums.UserStatus;
import com.andresimiquelli.finalfinance.services.validation.UserPutValidation;

@UserPutValidation
public class UserPutDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Length(min = 5, max = 191)
	private String name;
	
	@Length(min = 6, max = 32)
	private String password;
	private String passwordConfirmation;
	private UserStatus status;
	
	public UserPutDTO() {}

	public UserPutDTO(String name, String password, String passwordConfirmation, UserStatus status) {
		super();
		this.name = name;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}
}
