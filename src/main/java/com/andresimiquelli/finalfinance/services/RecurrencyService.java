package com.andresimiquelli.finalfinance.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andresimiquelli.finalfinance.dto.RecurrencyDTO;
import com.andresimiquelli.finalfinance.dto.RecurrencyPostDTO;
import com.andresimiquelli.finalfinance.dto.RecurrencyPutDTO;
import com.andresimiquelli.finalfinance.entities.Group;
import com.andresimiquelli.finalfinance.entities.Recurrency;
import com.andresimiquelli.finalfinance.entities.User;
import com.andresimiquelli.finalfinance.entities.Wallet;
import com.andresimiquelli.finalfinance.entities.enums.RecurrencyStatus;
import com.andresimiquelli.finalfinance.repositories.GroupRepository;
import com.andresimiquelli.finalfinance.repositories.RecurrencyRepository;
import com.andresimiquelli.finalfinance.repositories.UserRepository;
import com.andresimiquelli.finalfinance.repositories.WalletRepository;
import com.andresimiquelli.finalfinance.security.UserSpringSecurity;
import com.andresimiquelli.finalfinance.services.events.RecurrencyEventPublisher;
import com.andresimiquelli.finalfinance.services.exceptions.AuthorizationException;
import com.andresimiquelli.finalfinance.services.exceptions.DataIntegrityException;
import com.andresimiquelli.finalfinance.services.exceptions.ResourceNotFoundException;

@Service
public class RecurrencyService {

	@Autowired
	private RecurrencyRepository repository;
	
	@Autowired
	private WalletRepository walletRepository;
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RecurrencyEventPublisher reccurencyPublisher;
	
	@Transactional(readOnly = true)
	public Page<RecurrencyDTO> findAll(Integer walletId, Pageable pageable){
		
		Page<Recurrency> result = repository.findByWalletUserAndWalletId(getAuthenticatedUser(), walletId, pageable);
		Page<RecurrencyDTO> page = result.map(item -> new RecurrencyDTO(item));
		return page;
	}
	
	@Transactional(readOnly = true)
	public RecurrencyDTO findById(Integer id){
		Recurrency recurrency = getRecurrency(id);
		return new RecurrencyDTO(recurrency);
	}
	
	@Transactional
	public RecurrencyDTO insert(RecurrencyPostDTO recurrency) {
		Recurrency newRecurrency = fromDTO(recurrency);
		newRecurrency = repository.save(newRecurrency);
		reccurencyPublisher.publisherRecurrencyCreatedEvent(newRecurrency);
		return new RecurrencyDTO(newRecurrency);
	}
	
	@Transactional
	public RecurrencyDTO update(Integer id, RecurrencyPutDTO recurrency) { 
		Recurrency existing = getRecurrency(id);
		existing = updateData(existing, recurrency);
		existing = repository.save(existing);
		reccurencyPublisher.publisherRecurrencyUpdatedEvent(existing);
		return new RecurrencyDTO(existing);
	}
	
	@Transactional
	public void delete(Integer id) {
		Recurrency recurrency = getRecurrency(id);
		
		try {
			repository.deleteById(id);
			reccurencyPublisher.publisherRecurrencyDeletedEvent(recurrency);
		}
		catch(DataIntegrityViolationException e) {			
			throw new DataIntegrityException("Deletion is not possible. Recurrency has associated registers.");
		}
		
	}
	
	private Recurrency updateData(Recurrency existing, RecurrencyPutDTO newRecurrency) {
		if(newRecurrency.getType() != null)
			existing.setType(newRecurrency.getType());
			
		if(newRecurrency.getAmount() != null)
			existing.setAmount(newRecurrency.getAmount());
			
		if(newRecurrency.getTitle() != null)
			existing.setTitle(newRecurrency.getTitle());
			
		if(newRecurrency.getDescription() != null)
			existing.setDescription(newRecurrency.getDescription());
			
		if(newRecurrency.getStatus() != null)
			existing.setStatus(newRecurrency.getStatus());
			
		if(newRecurrency.getGroupId() != null) {
			Group group = groupRepository.getById(newRecurrency.getGroupId());
			existing.setGroup(group);
		}
			
		return existing;
	}
	
	private Recurrency fromDTO(RecurrencyPostDTO recurrency) {
		Wallet wallet = walletRepository.getById(recurrency.getWalletId());
		
		Group group = null;
		if(recurrency.getGroupId() != null)
			group = groupRepository.getById(recurrency.getGroupId());
		
		return new Recurrency(
				null, 
				recurrency.getType(), 
				recurrency.getAmount(),
				recurrency.getTitle(),
				recurrency.getDescription(),
				RecurrencyStatus.ACTIVE,
				wallet,
				group
			);
	}
	
	private Recurrency getRecurrency(Integer id) {
		Optional<Recurrency> optional = repository.findById(id);
		return optional.orElseThrow(
			() -> new ResourceNotFoundException("Resource not found. Id: "+id+" Tipo: "+Recurrency.class.getName())
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
