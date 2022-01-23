package com.andresimiquelli.finalfinance.services.events;

import org.springframework.context.ApplicationEvent;

import com.andresimiquelli.finalfinance.entities.Period;

public class PeriodCreatedEvent extends ApplicationEvent{
	private static final long serialVersionUID = 1L;
	
	private Period period;
	
	public PeriodCreatedEvent(Object source, Period period) {
		super(source);
		this.period = period;
	}
	
	public Period getPeriod() {
		return period;
	}
}
