package com.andresimiquelli.finalfinance.dto;


import java.io.Serializable;

import com.andresimiquelli.finalfinance.entities.enums.EntryType;
import com.andresimiquelli.finalfinance.entities.enums.RecurrencyStatus;

public class RecurrencyPutDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private EntryType type;
	private Double amount;
	private String title;
	private String description;
	private RecurrencyStatus status;
	private Integer groupId;
	
	public RecurrencyPutDTO() {}

	public RecurrencyPutDTO(EntryType type, Double amount, String title, String description, RecurrencyStatus status, Integer groupId) {
		super();
		this.type = type;
		this.amount = amount;
		this.title = title;
		this.description = description;
		this.status = status;
		this.groupId = groupId;
	}

	public EntryType getType() {
		return type;
	}

	public void setType(EntryType type) {
		this.type = type;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RecurrencyStatus getStatus() {
		return status;
	}

	public void setStatus(RecurrencyStatus status) {
		this.status = status;
	}
	
	public Integer getGroupId() {
		return groupId;
	}
	
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

}
