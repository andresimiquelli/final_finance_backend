package com.andresimiquelli.finalfinance.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.andresimiquelli.finalfinance.entities.Entry;
import com.andresimiquelli.finalfinance.entities.Period;
import com.andresimiquelli.finalfinance.entities.enums.EntryType;
import com.andresimiquelli.finalfinance.repositories.PeriodRepository;
import com.andresimiquelli.finalfinance.services.events.PeriodEntriesChangedEvent;

@Component
public class PeriodBalanceService implements ApplicationListener<PeriodEntriesChangedEvent>{

	@Autowired
	private PeriodRepository repository;
	
	@Override
	public void onApplicationEvent(PeriodEntriesChangedEvent event) {
		
		Double sum = 0.0;
		
		for(Entry entry : event.getPeriod().getEntries()) {
			sum += entry.getType().equals(EntryType.CREDIT)? entry.getAmount() : entry.getAmount()*-1;
		}
		
		Period period = event.getPeriod();
		period.setLeftover(sum);
		repository.save(period);
	}

}
