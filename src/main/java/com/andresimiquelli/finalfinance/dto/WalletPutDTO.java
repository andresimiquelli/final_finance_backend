package com.andresimiquelli.finalfinance.dto;

import org.hibernate.validator.constraints.Length;

public class WalletPutDTO {

	@Length(min = 3, max = 191)
	private String name;
	
	@Length(max = 256)
	private String description;
	
	public WalletPutDTO() {}
	
	public WalletPutDTO(@Length(min = 3, max = 191) String name, @Length(max = 256) String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
