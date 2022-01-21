package com.andresimiquelli.finalfinance.dto;

import java.util.ArrayList;
import java.util.List;

import com.andresimiquelli.finalfinance.entities.Wallet;

public class WalletDTO {

	private Integer id;
	private String name;
	private String description;
	
	private List<PeriodDTO> periods = new ArrayList<PeriodDTO>();
	
	public WalletDTO() {}

	public WalletDTO(Integer id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public WalletDTO(Wallet wallet) {
		id = wallet.getId();
		name = wallet.getName();
		description = wallet.getDescription();
		periods = wallet.getPeriods().stream().map(item -> new PeriodDTO(item)).toList();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<PeriodDTO> getPeriods() {
		return periods;
	}

	public void setPeriods(List<PeriodDTO> periods) {
		this.periods = periods;
	}
}
