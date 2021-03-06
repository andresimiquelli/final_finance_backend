package com.andresimiquelli.finalfinance.services.events;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.andresimiquelli.finalfinance.dto.PeriodDTO;
import com.andresimiquelli.finalfinance.entities.Period;

@Component
public class PeriodEventPublisher {
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	public void publishPeriodCreatedEvent(Period period) {
		PeriodCreatedEvent event = new PeriodCreatedEvent(this, period);
		publisher.publishEvent(event);
	}
	
	public void publishPeriodCreatedEvent(Set<Period> periods) {
		for(Period period : periods) {
			PeriodCreatedEvent event = new PeriodCreatedEvent(this, period);
			publisher.publishEvent(event);
		}
	}
	
	public void publisherPeriodUpdatedEvent(Period period, PeriodDTO previousPeriod) {
		PeriodUpdatedEvent event = new PeriodUpdatedEvent(this, period, previousPeriod);
		publisher.publishEvent(event);
	}
	
	public void publisherPeriodEntriesChangedEvent(Period period) {
		PeriodEntriesChangedEvent event = new PeriodEntriesChangedEvent(this, period);
		publisher.publishEvent(event);
	}
	
	public void publisherPeriodEntriesChangedEvent(Set<Period> periods) {
		
		for(Period period : periods) {
			PeriodEntriesChangedEvent event = new PeriodEntriesChangedEvent(this, period);
			publisher.publishEvent(event);
		}
		
	}

}
