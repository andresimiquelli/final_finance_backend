package com.andresimiquelli.finalfinance.entities.enums;

public enum UserLevel {

	COMMON(0, "HOLE_COMMON"),
	DEVELOPER(1, "HOLE_DEVELOPER"),
	ADMIN(2, "HOLE_ADMIN");
	
	private Integer cod;
	private String label;
	
	private UserLevel(Integer cod, String label) {
		this.cod = cod;
		this.label = label;
	}
	
	public Integer getCod() {
		return cod;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static UserLevel toEnum(Integer cod) {
		if(cod == null)
			return null;
		
		for(UserLevel x : UserLevel.values()) {
			if(cod.equals(x.getCod()))
				return x;
		}
		
		throw new IllegalArgumentException("Cod "+cod+" not found.");
	}
}
