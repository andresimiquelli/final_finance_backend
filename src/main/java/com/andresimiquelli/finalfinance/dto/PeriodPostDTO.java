package com.andresimiquelli.finalfinance.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PeriodPostDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull
	private Integer year;
	@NotNull
	@Min(1)
	@Max(12)
	private Integer month;
	@NotNull
	private Integer walletId;
	
	public PeriodPostDTO() {}

	public PeriodPostDTO(@NotNull Integer year, @NotNull @Min(1) @Max(12) Integer month, @NotNull Integer walletId) {
		super();
		this.year = year;
		this.month = month;
		this.walletId = walletId;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getWalletId() {
		return walletId;
	}

	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}
}
