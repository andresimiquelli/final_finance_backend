package com.andresimiquelli.finalfinance.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.andresimiquelli.finalfinance.entities.enums.EntryType;

public class RecurrencyPostDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private EntryType type;
	@NotNull
	private Double amount;
	@NotEmpty
	@NotNull
	private String title;
	private String description;
	@NotNull
	private Integer walletId;
	private Integer groupId;
	
	public RecurrencyPostDTO() {}

	public RecurrencyPostDTO(@NotNull EntryType type, @NotNull Double amount, @NotEmpty @NotNull String title,
			String description, @NotNull Integer walletId, Integer groupId) {
		super();
		this.type = type;
		this.amount = amount;
		this.title = title;
		this.description = description;
		this.walletId = walletId;
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
	
	public Integer getWalletId() {
		return walletId;
	}

	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}
	
	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
}
