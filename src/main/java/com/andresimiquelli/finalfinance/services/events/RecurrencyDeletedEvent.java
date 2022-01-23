package com.andresimiquelli.finalfinance.services.events;

import org.springframework.context.ApplicationEvent;

import com.andresimiquelli.finalfinance.entities.Recurrency;

public class RecurrencyDeletedEvent extends ApplicationEvent{
	private static final long serialVersionUID = 1L;
	
	private Recurrency recurrency;

	public RecurrencyDeletedEvent(Object source, Recurrency recurrency) {
		super(source);
		
		this.recurrency = recurrency;
	}
	
	public Recurrency getRecurrency() {
		return recurrency;
	}
}
