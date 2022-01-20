package com.andresimiquelli.finalfinance.dto;

import java.util.Date;

import com.andresimiquelli.finalfinance.entities.Entry;

public class EntryDTO {
	
	private Integer id;
	private Character type;
	private Double amount;
	private String title;
	private String description;
	private Integer status;
	private Date created_at;
	private Date updated_at;
	
	public EntryDTO() {}

	public EntryDTO(Integer id, Character type, Double amount, String title, String description, Integer status) {
		this.id = id;
		this.type = type;
		this.amount = amount;
		this.title = title;
		this.description = description;
		this.status = status;
	}
	
	public EntryDTO(Entry entry) {
		this.id = entry.getId();
		this.type = entry.getType();
		this.amount = entry.getAmount();
		this.title = entry.getTitle();
		this.description = entry.getDescription();
		this.status = entry.getStatus();
		this.setCreated_at(entry.getCreated_at());
		this.setUpdated_at(entry.getUpdated_at());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Character getType() {
		return type;
	}

	public void setType(Character type) {
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
}
