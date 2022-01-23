package com.andresimiquelli.finalfinance.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andresimiquelli.finalfinance.dto.EntryDTO;
import com.andresimiquelli.finalfinance.dto.EntryPostDTO;
import com.andresimiquelli.finalfinance.dto.EntryPutDTO;
import com.andresimiquelli.finalfinance.entities.Entry;
import com.andresimiquelli.finalfinance.entities.Group;
import com.andresimiquelli.finalfinance.entities.Period;
import com.andresimiquelli.finalfinance.entities.Recurrency;
import com.andresimiquelli.finalfinance.repositories.EntryRepository;
import com.andresimiquelli.finalfinance.repositories.GroupRepository;
import com.andresimiquelli.finalfinance.repositories.PeriodRepository;
import com.andresimiquelli.finalfinance.repositories.RecurrencyRepository;
import com.andresimiquelli.finalfinance.services.events.PeriodEventPublisher;
import com.andresimiquelli.finalfinance.services.exceptions.DataIntegrityException;
import com.andresimiquelli.finalfinance.services.exceptions.ResourceNotFoundException;

@Service
public class EntryService {

	@Autowired
	private EntryRepository repository;
	
	@Autowired
	private PeriodRepository periodRepository;
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private RecurrencyRepository recurrencyRepository;
	
	@Autowired
	private PeriodEventPublisher periodPublisher;	
	
	@Transactional(readOnly = true)
	public Page<EntryDTO> findAll(Pageable pageable){
		Page<Entry> result = repository.findAll(pageable);
		Page<EntryDTO> page = result.map(item -> new EntryDTO(item));
		return page;
	}
	
	@Transactional(readOnly = true)
	public EntryDTO findById(Integer id){
		Entry entry = getEntry(id);
		return new EntryDTO(entry);
	}
	
	@Transactional
	public EntryDTO insert(EntryPostDTO entry) {
		Entry newEntry = fromDTO(entry);
		newEntry = repository.save(newEntry);
		periodPublisher.publisherPeriodEntriesChangedEvent(newEntry.getPeriod());
		return new EntryDTO(newEntry);
	}
	
	@Transactional
	public EntryDTO update(Integer id, EntryPutDTO entry) {
		Entry existing = getEntry(id);
		Entry newEntry = updateData(existing, entry);
		newEntry = repository.save(newEntry);
		periodPublisher.publisherPeriodEntriesChangedEvent(newEntry.getPeriod());
		return new EntryDTO(newEntry);
	}
	
	@Transactional
	public void delete(Integer id) {
		Entry entry = getEntry(id);
		
		try {
			repository.deleteById(id);
			periodPublisher.publisherPeriodEntriesChangedEvent(entry.getPeriod());
		}
		catch(DataIntegrityViolationException e) {			
			throw new DataIntegrityException("Deletion is not possible. Entry has associated registers.");
		}
		
	}
	
	private Entry updateData(Entry existing, EntryPutDTO newEntry) {
		
		if(newEntry.getType() != null)
			existing.setType(newEntry.getType());
		
		if(newEntry.getAmount() != null)
			existing.setAmount(newEntry.getAmount());
		
		if(newEntry.getTitle() != null)
			existing.setTitle(newEntry.getTitle());
		
		if(newEntry.getDescription() != null)
			existing.setDescription(newEntry.getDescription());
		
		if(newEntry.getGroupId() != null) {
			Group group = getGroup(newEntry.getGroupId());
			existing.setGroup(group);
		}
		
		return existing;
	}
	
	private Entry fromDTO(EntryPostDTO entry) {
		Period period = getPeriod(entry.getPeriodId());
		
		Group group = null;
		if(entry.getGroupId() != null)
			group = getGroup(entry.getGroupId());
		
		Recurrency recurrency = null;
		if(entry.getRecurrencyId() != null)
			recurrency = getRecurrency(entry.getRecurrencyId());
		
		return new Entry(
				null, 
				entry.getType(), 
				entry.getAmount(), 
				entry.getTitle(), 
				entry.getDescription(), 
				period, 
				group, 
				recurrency
			);
	}
	
	private Entry getEntry(Integer id) {
		Optional<Entry> optional = repository.findById(id);
		return optional.orElseThrow(
			() -> new ResourceNotFoundException("Resource not found. Id: "+id+" Tipo: "+Entry.class.getName())
		);
	}
	
	private Period getPeriod(Integer id) {
		Optional<Period> optional = periodRepository.findById(id);
		return optional.orElseThrow(
			() -> new ResourceNotFoundException("Resource not found. Id: "+id+" Tipo: "+Period.class.getName())
		);
	}
	
	private Group getGroup(Integer id) {
		Optional<Group> optional = groupRepository.findById(id);
		return optional.orElseThrow(
			() -> new ResourceNotFoundException("Resource not found. Id: "+id+" Tipo: "+Group.class.getName())
		);
	}
	
	private Recurrency getRecurrency(Integer id) {
		Optional<Recurrency> optional = recurrencyRepository.findById(id);
		return optional.orElseThrow(
			() -> new ResourceNotFoundException("Resource not found. Id: "+id+" Tipo: "+Recurrency.class.getName())
		);
	}
}
