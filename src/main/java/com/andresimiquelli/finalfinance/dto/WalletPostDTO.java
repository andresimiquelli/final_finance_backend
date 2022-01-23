package com.andresimiquelli.finalfinance.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class WalletPostDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Length(min = 3, max = 191)
	private String name;
	
	@Length(max = 256)
	private String description;
	
	@NotNull
	@Min(0)
	private Integer userId;
	
	public WalletPostDTO() {}

	public WalletPostDTO(@NotEmpty @Length(min = 3, max = 191) String name, @Length(max = 256) String description,
			Integer userId) {
		super();
		this.name = name;
		this.description = description;
		this.userId = userId;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
