package com.andresimiquelli.finalfinance.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import com.andresimiquelli.finalfinance.entities.Entry;
import com.andresimiquelli.finalfinance.entities.Period;
import com.andresimiquelli.finalfinance.entities.enums.PeriodStatus;
import com.andresimiquelli.finalfinance.repositories.EntryRepository;
import com.andresimiquelli.finalfinance.services.events.PeriodEventPublisher;
import com.andresimiquelli.finalfinance.services.events.RecurrencyUpdatedeEvent;

public class EntryUpdatedRecurrencyService implements ApplicationListener<RecurrencyUpdatedeEvent> {
	
	@Autowired
	private EntryRepository entryRepository;
	
	@Autowired
	private PeriodEventPublisher periodPublisher;
	
	private Set<Period> periods = new HashSet<>();

	@Override
	public void onApplicationEvent(RecurrencyUpdatedeEvent event) {
		List<Entry> entries = entryRepository.findByPeriodStatusAndRecurrencyId(PeriodStatus.OPEN.getCod(), event.getRecurrency().getId());
		
		for(Entry x : entries) {
			periods.add(x.getPeriod());
			
			x.setType(event.getRecurrency().getType());
			x.setAmount(event.getRecurrency().getAmount());
			x.setTitle(event.getRecurrency().getTitle());
			x.setDescription(event.getRecurrency().getDescription());
			x.setGroup(event.getRecurrency().getGroup());
			
			entryRepository.save(x);
		}
		
		for(Period x : periods) {
			periodPublisher.publisherPeriodEntriesChangedEvent(x);
		}
		
	}
	
	
}
