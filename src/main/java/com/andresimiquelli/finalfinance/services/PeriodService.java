package com.andresimiquelli.finalfinance.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andresimiquelli.finalfinance.dto.PeriodDTO;
import com.andresimiquelli.finalfinance.dto.PeriodPostDTO;
import com.andresimiquelli.finalfinance.dto.PeriodPutDTO;
import com.andresimiquelli.finalfinance.entities.Period;
import com.andresimiquelli.finalfinance.entities.User;
import com.andresimiquelli.finalfinance.entities.Wallet;
import com.andresimiquelli.finalfinance.entities.enums.PeriodStatus;
import com.andresimiquelli.finalfinance.repositories.PeriodRepository;
import com.andresimiquelli.finalfinance.repositories.UserRepository;
import com.andresimiquelli.finalfinance.repositories.WalletRepository;
import com.andresimiquelli.finalfinance.security.UserSpringSecurity;
import com.andresimiquelli.finalfinance.services.events.PeriodEventPublisher;
import com.andresimiquelli.finalfinance.services.exceptions.AuthorizationException;
import com.andresimiquelli.finalfinance.services.exceptions.DataIntegrityException;
import com.andresimiquelli.finalfinance.services.exceptions.ResourceNotFoundException;

@Service
public class PeriodService {
	
	@Autowired
	private PeriodRepository repository;
	
	@Autowired
	private WalletRepository walletRepository;
	
	@Autowired
	private PeriodEventPublisher periodEvent;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public Page<PeriodDTO> findAll(Integer walletId, Pageable pageable){
		
		Page<Period> result = repository.findByWalletUserAndWalletId(getAuthenticatedUser(), walletId, pageable);
		Page<PeriodDTO> page = result.map(item -> new PeriodDTO(item));
		return page;
	}
	
	@Transactional(readOnly = true)
	public PeriodDTO findById(Integer id){
		Period period = getPeriod(id);
		return new PeriodDTO(period);
	}
	
	@Transactional
	public PeriodDTO insert(PeriodPostDTO period) {
		Period newPeriod = fromDTO(period);
		newPeriod = repository.save(newPeriod);
		periodEvent.publishPeriodCreatedEvent(newPeriod);
		return new PeriodDTO(newPeriod);
	}
	
	public void update(Integer id, PeriodPutDTO period) {
		Period existing = getPeriod(id);
		PeriodDTO previous = new PeriodDTO(existing);
		Period newPeriod = updateData(existing, period);
		repository.save(newPeriod);
		periodEvent.publisherPeriodUpdatedEvent(newPeriod, previous);
	}
	
	@Transactional
	public void delete(Integer id) {
		findById(id);
		
		try {
			repository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {			
			throw new DataIntegrityException("Deletion is not possible. Period has associated registers.");
		}
		
	}

	private Period updateData(Period existing, PeriodPutDTO newPeriod) {
		if(newPeriod.getLeftover() != null)
			existing.setLeftover(newPeriod.getLeftover());
		
		if(newPeriod.getStatus() != null)
			existing.setStatus(newPeriod.getStatus());
		
		return existing;
	}

	private Period fromDTO(PeriodPostDTO period) {
		Wallet wallet = getWallet(period.getWalletId());
		return new Period(null, period.getYear(), period.getMonth(), 0.0, PeriodStatus.OPEN, wallet); 
	}
	
	private Period getPeriod(Integer id) {
		Optional<Period> optional = repository.findById(id);
		return optional.orElseThrow(
			() -> new ResourceNotFoundException("Resource not found. Id: "+id+" Tipo: "+Period.class.getName())
		);
	}
	
	private Wallet getWallet(Integer id) {
		Optional<Wallet> optional = walletRepository.findById(id);
		return optional.orElseThrow(
				() -> new ResourceNotFoundException("Resource not found. Id: "+id+" Tipo: "+Wallet.class.getName())
		);
	}
	
	private User getAuthenticatedUser() {
		UserSpringSecurity auth = UserService.authenticated();
		if(auth == null) {
			throw new AuthorizationException("Forbidden");
		}
		
		return userRepository.getById(auth.getId());
	}

}
