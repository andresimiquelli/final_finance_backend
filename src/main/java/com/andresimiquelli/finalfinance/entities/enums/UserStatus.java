package com.andresimiquelli.finalfinance.entities.enums;

public enum UserStatus {

	ACTIVE(1, "active"),
	INACTIVE(0, "inactive");
	
	private Integer cod;
	private String label;
	
	private UserStatus(Integer cod, String label) {
		this.cod = cod;
		this.label = label;
	}
	
	public Integer getCod() {
		return cod;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static UserStatus toEnum(Integer cod) {
		if(cod == null)
			return null;
		
		for(UserStatus x : UserStatus.values()) {
			if(cod.equals(x.getCod()))
				return x;
		}
		
		throw new IllegalArgumentException("Cod "+cod+" not found.");
	}
}
