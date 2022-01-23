package com.andresimiquelli.finalfinance.services.events;

import org.springframework.context.ApplicationEvent;

import com.andresimiquelli.finalfinance.dto.PeriodDTO;
import com.andresimiquelli.finalfinance.entities.Period;

public class PeriodUpdatedEvent extends ApplicationEvent{
	private static final long serialVersionUID = 1L;
	
	private Period period;
	private PeriodDTO previousPeriod;
	
	public PeriodUpdatedEvent(Object source, Period period, PeriodDTO previousPeriod ) {
		super(source);
		this.period = period;
		this.previousPeriod = previousPeriod;
	}
	
	public Period getPeriod() {
		return period;
	}
	
	public PeriodDTO getPreviousPeriod() {
		return previousPeriod;
	}
}
