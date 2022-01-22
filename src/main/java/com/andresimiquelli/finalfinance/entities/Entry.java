package com.andresimiquelli.finalfinance.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.andresimiquelli.finalfinance.entities.enums.EntryType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "entries")
public class Entry implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer type;
	private Double amount;
	private String title;
	private String description;
	private Integer status;
	
	@CreationTimestamp
	private Date created_at;
	@UpdateTimestamp
	private Date updated_at;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "period_id")
	private Period period;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "group_id")
	private Group group;
	
	@ManyToOne
	@JoinColumn(name = "recurrency_id")
	private Recurrency recurrency;
	
	public Entry() {}
	
	public Entry(Integer id, EntryType type, Double amount, String title, String description, Integer status, Period period, Group group, Recurrency recurrency) {
		super();
		this.id = id;
		this.type = type.getCod();
		this.amount = amount;
		this.title = title;
		this.description = description;
		this.status = status;
		this.period = period;
		this.group = group;
		this.setRecurrency(recurrency);
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	
	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
	public Recurrency getRecurrency() {
		return recurrency;
	}

	public void setRecurrency(Recurrency recurrency) {
		this.recurrency = recurrency;
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
		Entry other = (Entry) obj;
		return Objects.equals(id, other.id);
	}

}
