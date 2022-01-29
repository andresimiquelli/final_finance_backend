package com.andresimiquelli.finalfinance.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.andresimiquelli.finalfinance.entities.enums.EntryType;
import com.andresimiquelli.finalfinance.services.validation.EntryPostValidation;

@EntryPostValidation
public class EntryPostDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer periodId;
	private Integer periodYear;
	private Integer periodMonth;
	private Integer walletId;
	@NotNull
	private EntryType type;
	@NotNull
	private Double amount;
	@NotNull
	@Length(min = 1, max = 60)
	private String title;
	@Length(max = 255)
	private String description;
	private Integer recurrencyId;
	private Integer groupId;
	
	public EntryPostDTO() {}
	
	public EntryPostDTO(@NotNull Integer periodId, @NotNull EntryType type, @NotNull Double amount,
			@NotNull @Length(min = 1, max = 60) String title, @Length(max = 255) String description,
			Integer recurrencyId, Integer groupId) {
		super();
		this.periodId = periodId;
		this.type = type;
		this.amount = amount;
		this.title = title;
		this.description = description;
		this.recurrencyId = recurrencyId;
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
	public Integer getRecurrencyId() {
		return recurrencyId;
	}
	public void setRecurrencyId(Integer recurrencyId) {
		this.recurrencyId = recurrencyId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getPeriodId() {
		return periodId;
	}

	public void setPeriodId(Integer periodId) {
		this.periodId = periodId;
	}

	public Integer getPeriodMonth() {
		return periodMonth;
	}

	public void setPeriodMonth(Integer periodMonth) {
		this.periodMonth = periodMonth;
	}

	public Integer getPeriodYear() {
		return periodYear;
	}

	public void setPeriodYear(Integer periodYear) {
		this.periodYear = periodYear;
	}

	public Integer getWalletId() {
		return walletId;
	}

	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}
	
}
