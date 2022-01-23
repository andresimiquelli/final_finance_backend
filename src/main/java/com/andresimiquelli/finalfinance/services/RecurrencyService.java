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
import com.andresimiquelli.finalfinance.entities.Wallet;
import com.andresimiquelli.finalfinance.entities.enums.RecurrencyStatus;
import com.andresimiquelli.finalfinance.repositories.GroupRepository;
import com.andresimiquelli.finalfinance.repositories.RecurrencyRepository;
import com.andresimiquelli.finalfinance.repositories.WalletRepository;
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
	
	@Transactional(readOnly = true)
	public Page<RecurrencyDTO> findAll(Pageable pageable){
		Page<Recurrency> result = repository.findAll(pageable);
		Page<RecurrencyDTO> page = result.map(item -> new RecurrencyDTO(item));
		return page;
	}
	
	@Transactional(readOnly = true)
	public RecurrencyDTO findById(Integer id){
		Optional<Recurrency> obj = repository.findById(id);
		Recurrency recurrency = obj.orElseThrow(() -> new ResourceNotFoundException("Resource not found. Id: "+id+" Tipo: "+Recurrency.class.getName()));
		return new RecurrencyDTO(recurrency);
	}
	
	@Transactional
	public RecurrencyDTO insert(RecurrencyPostDTO recurrency) {
		Recurrency newRecurrency = fromDTO(recurrency);
		newRecurrency = repository.save(newRecurrency);
		return new RecurrencyDTO(newRecurrency);
	}
	
	@Transactional
	public RecurrencyDTO update(Integer id, RecurrencyPutDTO recurrency) { 
		Optional<Recurrency> optional = repository.findById(id);
		Recurrency existing = optional.orElseThrow(() -> new ResourceNotFoundException("Resource not found. Id: "+id+" Tipo: "+Recurrency.class.getName()));
		existing = updateData(existing, recurrency);
		existing = repository.save(existing);
		return new RecurrencyDTO(existing);
	}
	
	@Transactional
	public void delete(Integer id) {
		findById(id);
		
		try {
			repository.deleteById(id);
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
}
