package com.andresimiquelli.finalfinance.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class GroupPostDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	@NotNull
	private String name;
	@NotEmpty
	@NotNull
	private String color;
	@NotNull
	private Integer walletId;
	
	public GroupPostDTO() {}

	public GroupPostDTO(@NotEmpty @NotNull String name, @NotEmpty @NotNull String color, @NotNull Integer walletId) {
		super();
		this.name = name;
		this.color = color;
		this.walletId = walletId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getWalletId() {
		return walletId;
	}

	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}
	
}
