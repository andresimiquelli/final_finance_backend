package com.andresimiquelli.finalfinance.dto;

import java.util.Date;

import com.andresimiquelli.finalfinance.entities.Entry;
import com.andresimiquelli.finalfinance.entities.enums.EntryType;

public class EntryDTO {
	
	private Integer id;
	private EntryType type;
	private Double amount;
	private String title;
	private String description;
	private Date created_at;
	private Date updated_at;
	
	private GroupDTO group;
	private RecurrencyDTO recurrency;
	
	public EntryDTO() {}

	public EntryDTO(Integer id, EntryType type, Double amount, String title, String description, GroupDTO group, RecurrencyDTO recurrency) {
		this.id = id;
		this.type = type;
		this.amount = amount;
		this.title = title;
		this.description = description;
		this.group = group;
		this.setRecurrency(recurrency);
	}
	
	public EntryDTO(Entry entry) {
		id = entry.getId();
		type = entry.getType();
		amount = entry.getAmount();
		title = entry.getTitle();
		description = entry.getDescription();

		this.setCreated_at(entry.getCreated_at());
		this.setUpdated_at(entry.getUpdated_at());
		
		if(entry.getGroup() != null)
			group = new GroupDTO(entry.getGroup());
		
		if(entry.getRecurrency() != null)
			setRecurrency(new RecurrencyDTO(entry.getRecurrency()));
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

	public GroupDTO getGroup() {
		return group;
	}

	public void setGroup(GroupDTO group) {
		this.group = group;
	}

	public RecurrencyDTO getRecurrency() {
		return recurrency;
	}

	public void setRecurrency(RecurrencyDTO recurrency) {
		this.recurrency = recurrency;
	}

}
