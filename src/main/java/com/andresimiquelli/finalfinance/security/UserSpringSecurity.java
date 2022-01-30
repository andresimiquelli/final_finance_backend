package com.andresimiquelli.finalfinance.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.andresimiquelli.finalfinance.entities.enums.UserLevel;

public class UserSpringSecurity implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String email;
	private String password;
	private  Collection<? extends GrantedAuthority> authorities;
	
	public UserSpringSecurity() {}
	
	public UserSpringSecurity(Integer id, String email, String password, Set<UserLevel> levels) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.authorities = levels.stream().map(x -> new SimpleGrantedAuthority(x.getLabel())).collect(Collectors.toList());
	}

	public Integer getId(){
		return id;
	}
	
	public boolean hasRole(UserLevel level) {
		
		return getAuthorities().contains(new SimpleGrantedAuthority(level.getLabel()));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
