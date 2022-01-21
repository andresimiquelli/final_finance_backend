package com.andresimiquelli.finalfinance.entities.enums;

public enum EntryType {

	CREDIT(1, "credit"),
	DEBIT(2, "debit");
	
	private int cod;
	private String name;
	
	private EntryType(int cod, String name) {
		this.cod = cod;
		this.name = name;
	}
	
	public int getCode() {
		return this.cod;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static EntryType toEnum(Integer cod) {
		
		if(cod == null)
			return null;
		
		for(EntryType x : EntryType.values()) {
			if(cod.equals(x.getCode()))
				return x;
		}
		
		throw new IllegalArgumentException("Cod "+cod+" not found.");
	}
}
