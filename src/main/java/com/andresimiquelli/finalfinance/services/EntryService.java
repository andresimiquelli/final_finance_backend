package com.andresimiquelli.finalfinance.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andresimiquelli.finalfinance.dto.EntryDTO;
import com.andresimiquelli.finalfinance.entities.Entry;
import com.andresimiquelli.finalfinance.repositories.EntryRepository;
import com.andresimiquelli.finalfinance.services.exceptions.ResourceNotFoundException;

@Service
public class EntryService {

	@Autowired
	private EntryRepository repository;
	
	@Transactional(readOnly = true)
	public Page<EntryDTO> findAll(Pageable pageable){
		Page<Entry> result = repository.findAll(pageable);
		Page<EntryDTO> page = result.map(item -> new EntryDTO(item));
		return page;
	}
	
	@Transactional(readOnly = true)
	public EntryDTO findById(Integer id){
		Optional<Entry> obj = repository.findById(id);
		Entry entry = obj.orElseThrow(() -> new ResourceNotFoundException("Resource not found. Id: "+id+" Tipo: "+Entry.class.getName()));
		return new EntryDTO(entry);
	}
}
