package com.andresimiquelli.finalfinance.entities.enums;

public enum EntryType {

	CREDIT(1, "credit"),
	DEBIT(2, "debit");
	
	private int cod;
	private String label;
	
	private EntryType(int cod, String label) {
		this.cod = cod;
		this.label = label;
	}
	
	public int getCod() {
		return this.cod;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public static EntryType toEnum(Integer cod) {
		
		if(cod == null)
			return null;
		
		for(EntryType x : EntryType.values()) {
			if(cod.equals(x.getCod()))
				return x;
		}
		
		throw new IllegalArgumentException("Cod "+cod+" not found.");
	}
}
