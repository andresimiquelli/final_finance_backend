package com.andresimiquelli.finalfinance.dto;

import com.andresimiquelli.finalfinance.entities.Recurrency;
import com.andresimiquelli.finalfinance.entities.enums.EntryType;

public class RecurrencyDTO {
	
	private Integer id;
	private EntryType type;
	private Double amount;
	private String title;
	private String description;
	private Integer status;
	
	public RecurrencyDTO() {}

	public RecurrencyDTO(Integer id, EntryType type, Double amount, String title, String description, Integer status) {
		this.id = id;
		this.type = type;
		this.amount = amount;
		this.title = title;
		this.description = description;
		this.status = status;
	}
	
	public RecurrencyDTO(Recurrency recurrency) {
		this.id = recurrency.getId();
		this.type = recurrency.getType();
		this.amount = recurrency.getAmount();
		this.title = recurrency.getTitle();
		this.description = recurrency.getDescription();
		this.status = recurrency.getStatus();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
