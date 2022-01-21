package com.andresimiquelli.finalfinance.dto;

import java.util.ArrayList;
import java.util.List;

import com.andresimiquelli.finalfinance.entities.Period;

public class PeriodDTO {

	private Integer id;
	private Integer year;
	private Integer month;
	private Double leftover;
	private Integer status;
	
	private List<EntryDTO> entries = new ArrayList<EntryDTO>();
	
	public PeriodDTO() {}

	public PeriodDTO(Integer id, Integer year, Integer month, Double leftover, Integer status) {
		super();
		this.id = id;
		this.year = year;
		this.month = month;
		this.leftover = leftover;
		this.status = status;
	}
	
	public PeriodDTO(Period period) {
		id = period.getId();
		year = period.getYear();
		month = period.getMonth();
		leftover = period.getLeftover();
		status = period.getStatus();
		entries = period.getEntries().stream().map(item -> new EntryDTO(item)).toList();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Double getLeftover() {
		return leftover;
	}

	public void setLeftover(Double leftover) {
		this.leftover = leftover;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<EntryDTO> getEntries() {
		return entries;
	}

	public void setEntries(List<EntryDTO> entries) {
		this.entries = entries;
	}
	
}
