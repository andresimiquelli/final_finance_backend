package com.andresimiquelli.finalfinance.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import com.andresimiquelli.finalfinance.services.validation.UserPostValidation;

@UserPostValidation
public class UserPostDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Name is required.")
	@Length(min = 5, max = 191)
	private String name;
	
	@NotEmpty(message = "Email is required")
	@Email
	private String email;
	
	@NotEmpty(message = "Email confirmation is required")
	@Email
	private String emailConfirmation;
	
	@NotEmpty(message = "Password is required")
	@Length(min = 6, max = 32)
	private String password;
	private String passwordConfirmation;
	
	public UserPostDTO() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailConfirmation() {
		return emailConfirmation;
	}

	public void setEmailConfirmation(String emailConfirmation) {
		this.emailConfirmation = emailConfirmation;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
