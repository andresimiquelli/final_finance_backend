package com.andresimiquelli.finalfinance.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

public class WalletPutDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Length(min = 3, max = 191)
	private String name;
	
	@Length(max = 256)
	private String description;
	
	private Double leftover;
	
	public WalletPutDTO() {}
	
	public WalletPutDTO(@Length(min = 3, max = 191) String name, @Length(max = 256) String description, Double leftover) {
		super();
		this.name = name;
		this.description = description;
		this.leftover = leftover;
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

	public Double getLeftover() {
		return leftover;
	}

	public void setLeftover(Double leftover) {
		this.leftover = leftover;
	}
}
