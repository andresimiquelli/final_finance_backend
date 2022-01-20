package com.andresimiquelli.finalfinance.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andresimiquelli.finalfinance.dto.RecurrencyDTO;
import com.andresimiquelli.finalfinance.entities.Recurrency;
import com.andresimiquelli.finalfinance.repositories.RecurrencyRepository;
import com.andresimiquelli.finalfinance.services.exceptions.ResourceNotFoundException;

@Service
public class RecurrencyService {

	@Autowired
	private RecurrencyRepository repository;
	
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
}
