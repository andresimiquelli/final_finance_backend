package com.andresimiquelli.finalfinance.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.andresimiquelli.finalfinance.entities.enums.PeriodStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "periods")
public class Period implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer year;
	private Integer month;
	private Double leftover;
	private Integer status;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "wallet_id")
	private Wallet wallet;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "period")
	private List<Entry> entries = new ArrayList<Entry>();
	
	public Period() {}

	public Period(Integer id, Integer year, Integer month, Double leftover, PeriodStatus status, Wallet wallet) {
		this.id = id;
		this.year = year;
		this.month = month;
		this.leftover = leftover;
		
		if(status != null)
			this.status = status.getCod();
		
		this.wallet = wallet;
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

	public PeriodStatus getStatus() {
		return PeriodStatus.toEnum(status);
	}

	public void setStatus(PeriodStatus status) {
		if(status != null)
			this.status = status.getCod();
	}
	
	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	
	public List<Entry> getEntries() {
		return entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Period other = (Period) obj;
		return Objects.equals(id, other.id);
	}

}
