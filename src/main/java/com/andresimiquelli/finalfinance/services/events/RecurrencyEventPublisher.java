package com.andresimiquelli.finalfinance.services.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.andresimiquelli.finalfinance.entities.Recurrency;

@Component
public class RecurrencyEventPublisher {

	@Autowired
	private ApplicationEventPublisher publisher;
	
	public void publisherRecurrencyCreatedEvent(Recurrency recurrency) {
		RecurrencyCreatedeEvent event = new RecurrencyCreatedeEvent(this, recurrency);
		publisher.publishEvent(event);
	}
	
	public void publisherRecurrencyUpdatedEvent(Recurrency recurrency) {
		RecurrencyUpdatedeEvent event = new RecurrencyUpdatedeEvent(this, recurrency);
		publisher.publishEvent(event);
	}
	
	public void publisherRecurrencyDeletedEvent(Recurrency recurrency) {
		RecurrencyDeletedEvent event = new RecurrencyDeletedEvent(this, recurrency);
		publisher.publishEvent(event);
	}
}
