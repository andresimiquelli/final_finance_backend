package com.andresimiquelli.finalfinance.dto;

import com.andresimiquelli.finalfinance.entities.Group;

public class GroupDTO {

	private Integer id;
	private String name;
	private String color;
	
	public GroupDTO() {}

	public GroupDTO(Integer id, String name, String color) {

		this.id = id;
		this.name = name;
		this.color = color;
	}
	
	public GroupDTO(Group group) {
		this.id = group.getId();
		this.name = group.getName();
		this.color = group.getColor();
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
