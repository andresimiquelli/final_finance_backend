package com.andresimiquelli.finalfinance.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.andresimiquelli.finalfinance.entities.Entry;
import com.andresimiquelli.finalfinance.entities.Period;
import com.andresimiquelli.finalfinance.entities.enums.PeriodStatus;
import com.andresimiquelli.finalfinance.repositories.EntryRepository;
import com.andresimiquelli.finalfinance.repositories.PeriodRepository;
import com.andresimiquelli.finalfinance.services.events.PeriodEventPublisher;
import com.andresimiquelli.finalfinance.services.events.RecurrencyCreatedeEvent;

@Component
public class EntryCreateByRecurrencyService implements ApplicationListener<RecurrencyCreatedeEvent>{
	
	@Autowired
	private PeriodRepository periodRepository;
	
	@Autowired
	private EntryRepository entryRepository;
	
	@Autowired
	private PeriodEventPublisher periodPublisher;
	
	private List<Period> periods = new ArrayList<>();
	
	@Override
	public void onApplicationEvent(RecurrencyCreatedeEvent event) {
		periods = periodRepository.findByStatus(PeriodStatus.OPEN.getCod());
		List<Entry> entries = new ArrayList<>();
		for(Period x : periods) {
			entries.add(new Entry(
					null,
					event.getRecurrency().getType(),
					event.getRecurrency().getAmount(),
					event.getRecurrency().getTitle(),
					event.getRecurrency().getDescription(),
					x,
					event.getRecurrency().getGroup(),
					event.getRecurrency(),
					1,
					1,
					false
			));
		}
		
		if(entries.size() > 0)
			entryRepository.saveAll(entries);
		
		for(Period x : periods) {
			periodPublisher.publisherPeriodEntriesChangedEvent(x);
		}
	}

}
