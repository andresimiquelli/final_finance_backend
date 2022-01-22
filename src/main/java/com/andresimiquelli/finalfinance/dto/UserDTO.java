package com.andresimiquelli.finalfinance.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.andresimiquelli.finalfinance.entities.User;
import com.andresimiquelli.finalfinance.entities.enums.UserStatus;

public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String email;
	private String password;
	private UserStatus status;
	
	private List<WalletDTO> wallets = new ArrayList<WalletDTO>();
	
	public UserDTO() {}

	public UserDTO(Integer id, String name, String email, String password, UserStatus status) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.status = status;
	}
	
	public UserDTO(User user) {	
		id = user.getId();
		name = user.getName();
		email = user.getEmail();
		password = user.getPassword();
		status = user.getStatus();
		wallets = user.getWallets().stream().map(item -> new WalletDTO(item)).toList();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public List<WalletDTO> getWallets() {
		return wallets;
	}

	public void setWallets(List<WalletDTO> wallets) {
		this.wallets = wallets;
	}

}
