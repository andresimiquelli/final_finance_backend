package com.andresimiquelli.finalfinance.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.andresimiquelli.finalfinance.entities.Wallet;

public class WalletDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String description;
	private Double leftover;
	
	private List<GroupDTO> groups = new ArrayList<>();
	private List<RecurrencyDTO> recurrences = new ArrayList<>();
	
	public WalletDTO() {}

	public WalletDTO(Integer id, String name, String description, Double leftover) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.leftover = leftover;
	}
	
	public WalletDTO(Wallet wallet) {
		id = wallet.getId();
		name = wallet.getName();
		description = wallet.getDescription();
		leftover= wallet.getLeftover();
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
	
	public Double getLeftover() {
		return leftover;
	}
	
	public void setLeftover(Double leftover) {
		this.leftover = leftover;
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
