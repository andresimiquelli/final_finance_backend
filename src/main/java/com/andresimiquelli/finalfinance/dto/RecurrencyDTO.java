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
	
	private GroupDTO group;
	
	public RecurrencyDTO() {}

	public RecurrencyDTO(Integer id, EntryType type, Double amount, String title, String description, Integer status, GroupDTO group) {
		this.id = id;
		this.type = type;
		this.amount = amount;
		this.title = title;
		this.description = description;
		this.status = status;
		this.setGroup(group);
	}
	
	public RecurrencyDTO(Recurrency recurrency) {
		id = recurrency.getId();
		type = recurrency.getType();
		amount = recurrency.getAmount();
		title = recurrency.getTitle();
		description = recurrency.getDescription();
		status = recurrency.getStatus();
		
		if(recurrency.getGroup() != null)
			setGroup(new GroupDTO(recurrency.getGroup()));
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

	public GroupDTO getGroup() {
		return group;
	}

	public void setGroup(GroupDTO group) {
		this.group = group;
	}
	
}
