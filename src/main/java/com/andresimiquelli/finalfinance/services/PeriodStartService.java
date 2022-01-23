package com.andresimiquelli.finalfinance.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.andresimiquelli.finalfinance.entities.Entry;
import com.andresimiquelli.finalfinance.entities.Period;
import com.andresimiquelli.finalfinance.entities.Recurrency;
import com.andresimiquelli.finalfinance.entities.Wallet;
import com.andresimiquelli.finalfinance.repositories.EntryRepository;
import com.andresimiquelli.finalfinance.services.events.PeriodCreatedEvent;
import com.andresimiquelli.finalfinance.services.events.PeriodEventPublisher;

@Component
public class PeriodStartService implements ApplicationListener<PeriodCreatedEvent>{
	
	@Autowired
	private EntryRepository entryRepository;
	
	@Autowired
	private PeriodEventPublisher periodPublisher;
	
	@Override
	public void onApplicationEvent(PeriodCreatedEvent event) {
		Wallet wallet = event.getPeriod().getWallet();			
		entryRepository.saveAll(
				wallet.getRecurrences().stream().map(x -> fromRecurrency(x, event.getPeriod())).toList()
		);
		
		periodPublisher.publisherPeriodEntriesChangedEvent(event.getPeriod());
	}
	
	private Entry fromRecurrency(Recurrency recurrency, Period period) {
		return new Entry(
			null,
			recurrency.getType(),
			recurrency.getAmount(),
			recurrency.getTitle(),
			recurrency.getDescription(),
			period,
			recurrency.getGroup(),
			recurrency
		);
	}

}
