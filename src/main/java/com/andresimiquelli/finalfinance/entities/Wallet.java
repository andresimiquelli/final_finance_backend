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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "wallets")
public class Wallet implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	private Double leftover;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "wallet")
	private List<Group> groups = new ArrayList<>();
	
	@JsonManagedReference
	@OneToMany(mappedBy = "wallet")
	private List<Recurrency> recurrences = new ArrayList<>();
	
	public Wallet() {}

	public Wallet(Integer id, String name, String description, Double leftover, User user) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.leftover = leftover;
		this.user = user;
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
	
	public Double getLeftover() {
		return leftover;
	}
	
	public void setLeftover(Double leftover) {
		this.leftover = leftover;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Group> getGroups() {
		return groups;
	}
	
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	
	public List<Recurrency> getRecurrences() {
		return recurrences;
	}

	public void setRecurrences(List<Recurrency> recurrences) {
		this.recurrences = recurrences;
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
		Wallet other = (Wallet) obj;
		return Objects.equals(id, other.id);
	}

}
