package com.andresimiquelli.finalfinance.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.andresimiquelli.finalfinance.entities.enums.EntryType;
import com.andresimiquelli.finalfinance.entities.enums.RecurrencyStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "recurrences")
public class Recurrency implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer type;
	private Double amount;
	private String title;
	private String description;
	private Integer status;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "wallet_id")
	private Wallet wallet;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "group_id")
	private Group group;
	
	public Recurrency() {}

	public Recurrency(Integer id, EntryType type, Double amount, String title, String description, RecurrencyStatus status, Wallet wallet, Group group) {
		this.id = id;
		this.type = type.getCod();
		this.amount = amount;
		this.title = title;
		this.description = description;
		
		if(status != null)
			this.status = status.getCod();
		
		this.wallet = wallet;
		this.setGroup(group);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EntryType getType() {
		return EntryType.toEnum(type);
	}

	public void setType(EntryType type) {
		this.type = type.getCod();
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

	public RecurrencyStatus getStatus() {
		return RecurrencyStatus.toEnum(status);
	}

	public void setStatus(RecurrencyStatus status) {
		this.status = status.getCod();
	}
	
	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
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
		Recurrency other = (Recurrency) obj;
		return Objects.equals(id, other.id);
	}
}
