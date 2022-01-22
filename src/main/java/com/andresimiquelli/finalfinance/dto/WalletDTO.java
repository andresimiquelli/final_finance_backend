package com.andresimiquelli.finalfinance.dto;

import java.util.ArrayList;
import java.util.List;

import com.andresimiquelli.finalfinance.entities.Wallet;

public class WalletDTO {

	private Integer id;
	private String name;
	private String description;
	
	private List<GroupDTO> groups = new ArrayList<>();
	private List<RecurrencyDTO> recurrences = new ArrayList<>();
	
	public WalletDTO() {}

	public WalletDTO(Integer id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public WalletDTO(Wallet wallet) {
		id = wallet.getId();
		name = wallet.getName();
		description = wallet.getDescription();
		groups = wallet.getGroups().stream().map(item -> new GroupDTO(item)).toList();
		recurrences = wallet.getRecurrences().stream().map(item -> new RecurrencyDTO(item)).toList();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<GroupDTO> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupDTO> groups) {
		this.groups = groups;
	}

	public List<RecurrencyDTO> getRecurrences() {
		return recurrences;
	}

	public void setRecurrences(List<RecurrencyDTO> recurrences) {
		this.recurrences = recurrences;
	}
}
