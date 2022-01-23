package com.andresimiquelli.finalfinance.dto;

import java.io.Serializable;

import com.andresimiquelli.finalfinance.entities.enums.PeriodStatus;

public class PeriodPutDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Double leftover;
	private PeriodStatus status;
	
	public PeriodPutDTO() {}
	
	public PeriodPutDTO(Double leftover, PeriodStatus status) {
		super();
		this.leftover = leftover;
		this.status = status;
	}

	public Double getLeftover() {
		return leftover;
	}

	public void setLeftover(Double leftover) {
		this.leftover = leftover;
	}

	public PeriodStatus getStatus() {
		return status;
	}

	public void setStatus(PeriodStatus status) {
		this.status = status;
	}
}
