package com.andresimiquelli.finalfinance.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class GroupPutDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	private String name;
	private String color;
	
	public GroupPutDTO() {}
	
	public GroupPutDTO(@NotEmpty String name, String color) {
		super();
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
