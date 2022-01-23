package com.andresimiquelli.finalfinance.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.andresimiquelli.finalfinance.entities.enums.EntryType;

public class EntryPutDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private EntryType type;
	private Double amount;
	@Length(min = 1, max = 60)
	private String title;
	@Length(max = 255)
	private String description;
	private Integer groupId;
	
	public EntryPutDTO() {}

	public EntryPutDTO(EntryType type, Double amount, @Length(min = 1, max = 60) String title,
			@Length(max = 255) String description, Integer groupId) {
		super();
		this.type = type;
		this.amount = amount;
		this.title = title;
		this.description = description;
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

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
}
