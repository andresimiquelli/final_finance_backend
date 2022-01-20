package com.andresimiquelli.finalfinance.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andresimiquelli.finalfinance.dto.PeriodDTO;
import com.andresimiquelli.finalfinance.entities.Period;
import com.andresimiquelli.finalfinance.repositories.PeriodRepository;
import com.andresimiquelli.finalfinance.services.exceptions.ResourceNotFoundException;

@Service
public class PeriodService {
	
	@Autowired
	private PeriodRepository repository;
	
	@Transactional(readOnly = true)
	public Page<PeriodDTO> findAll(Pageable pageable){
		Page<Period> result = repository.findAll(pageable);
		Page<PeriodDTO> page = result.map(item -> new PeriodDTO(item));
		return page;
	}
	
	@Transactional(readOnly = true)
	public PeriodDTO findById(Integer id){
		Optional<Period> obj = repository.findById(id);
		Period period = obj.orElseThrow(() -> new ResourceNotFoundException("Resource not found. Id: "+id+" Tipo: "+Period.class.getName()));
		return new PeriodDTO(period);
	}

}
