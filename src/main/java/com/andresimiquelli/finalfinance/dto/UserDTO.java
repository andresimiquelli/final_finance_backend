package com.andresimiquelli.finalfinance.dto;

import java.util.ArrayList;
import java.util.List;

import com.andresimiquelli.finalfinance.entities.User;

public class UserDTO {

	private Integer id;
	private String name;
	private String email;
	private Integer status;
	
	private List<WalletDTO> wallets = new ArrayList<WalletDTO>();
	
	public UserDTO() {}

	public UserDTO(Integer id, String name, String email, Integer status) {

		this.id = id;
		this.name = name;
		this.email = email;
		this.status = status;
	}
	
	public UserDTO(User user) {	
		id = user.getId();
		name = user.getName();
		email = user.getEmail();
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<WalletDTO> getWallets() {
		return wallets;
	}

	public void setWallets(List<WalletDTO> wallets) {
		this.wallets = wallets;
	}

}
