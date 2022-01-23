package com.andresimiquelli.finalfinance.entities.enums;

public enum PeriodStatus {

	OPEN(1, "open"),
	CLOSE(0, "close");
	
	private Integer cod;
	private String label;
	
	private PeriodStatus(Integer cod, String label) {
		this.cod = cod;
		this.label = label;
	}
	
	public Integer getCod() {
		return cod;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static PeriodStatus toEnum(Integer cod) {
		if(cod == null)
			return null;
		
		for(PeriodStatus x : PeriodStatus.values()) {
			if(cod.equals(x.getCod()))
				return x;
		}
		
		throw new IllegalArgumentException("Cod "+cod+" not found.");
	}
}
