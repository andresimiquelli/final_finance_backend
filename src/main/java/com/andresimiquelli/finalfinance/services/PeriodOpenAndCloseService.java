package com.andresimiquelli.finalfinance.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.andresimiquelli.finalfinance.entities.Period;
import com.andresimiquelli.finalfinance.entities.Wallet;
import com.andresimiquelli.finalfinance.entities.enums.PeriodStatus;
import com.andresimiquelli.finalfinance.repositories.WalletRepository;
import com.andresimiquelli.finalfinance.services.events.PeriodUpdatedEvent;

@Component
public class PeriodOpenAndCloseService implements ApplicationListener<PeriodUpdatedEvent>{

	@Autowired
	private WalletRepository walletRepository;
	
	@Override
	public void onApplicationEvent(PeriodUpdatedEvent event) {
		if(event.getPeriod().getStatus().equals(PeriodStatus.OPEN) && event.getPreviousPeriod().getStatus().equals(PeriodStatus.CLOSE)) {
			openedPeriod(event.getPeriod());
		}
		
		if(event.getPeriod().getStatus().equals(PeriodStatus.CLOSE) && event.getPreviousPeriod().getStatus().equals(PeriodStatus.OPEN)) {
			closedPeriod(event.getPeriod());
		}
	}
	
	private void openedPeriod(Period period) {
		Wallet wallet = period.getWallet();
		wallet.setLeftover(wallet.getLeftover()-period.getLeftover());
		walletRepository.save(wallet);
	}
	
	private void closedPeriod(Period period) {
		Wallet wallet = period.getWallet();
		wallet.setLeftover(wallet.getLeftover()+period.getLeftover());
		walletRepository.save(wallet);
	}

}
