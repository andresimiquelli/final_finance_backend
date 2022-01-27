package com.andresimiquelli.finalfinance.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.andresimiquelli.finalfinance.entities.enums.UserLevel;
import com.andresimiquelli.finalfinance.entities.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String password;
	private Integer status;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "USER_LEVELS")
	private Set<Integer> levels = new HashSet<>();
	
	@JsonManagedReference
	@OneToMany(mappedBy = "user")
	private List<Wallet> wallets = new ArrayList<Wallet>();
	
	public User() {
		this.levels.add(UserLevel.COMMON.getCod());
	}

	public User(Integer id, String name, String email, String password, Integer status) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.status = status;
		this.levels.add(UserLevel.COMMON.getCod());
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserStatus getStatus() {
		return UserStatus.toEnum(status);
	}

	public void setStatus(UserStatus status) {
		if(status != null)
			this.status = status.getCod();
	}
	
	public Set<UserLevel> getLevels() {
		return levels.stream().map(x -> UserLevel.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addLevel(UserLevel level) {
		levels.add(level.getCod());
	}
	
	public List<Wallet> getWallets() {
		return wallets;
	}

	public void setWallets(List<Wallet> wallets) {
		this.wallets = wallets;
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
	
}
