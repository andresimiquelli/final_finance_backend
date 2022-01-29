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
	private Integer installment;
	private Integer totalInstallments;
	private Boolean paid;
	
	public EntryPutDTO() {}

	public EntryPutDTO(
			EntryType type, 
			Double amount, 
			@Length(min = 1, max = 60) String title,
			@Length(max = 255) String description, 
			Integer groupId,
			Integer installment,
			Integer totalInstallments) {
		
		this.type = type;
		this.amount = amount;
		this.title = title;
		this.description = description;
		this.groupId = groupId;
		this.installment = installment;
		this.totalInstallments = totalInstallments;
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

	public Integer getInstallment() {
		return installment;
	}

	public void setInstallment(Integer installment) {
		this.installment = installment;
	}

	public Integer getTotalInstallments() {
		return totalInstallments;
	}

	public void setTotalInstallments(Integer totalInstallments) {
		this.totalInstallments = totalInstallments;
	}

	public Boolean getPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}
	
}
