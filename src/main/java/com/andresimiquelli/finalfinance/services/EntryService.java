package com.andresimiquelli.finalfinance.services;

import java.util.Optional;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
import com.andresimiquelli.finalfinance.entities.Wallet;
import com.andresimiquelli.finalfinance.entities.enums.PeriodStatus;
import com.andresimiquelli.finalfinance.repositories.EntryRepository;
import com.andresimiquelli.finalfinance.repositories.GroupRepository;
import com.andresimiquelli.finalfinance.repositories.PeriodRepository;
import com.andresimiquelli.finalfinance.repositories.RecurrencyRepository;
import com.andresimiquelli.finalfinance.repositories.WalletRepository;
import com.andresimiquelli.finalfinance.services.events.PeriodEventPublisher;
import com.andresimiquelli.finalfinance.services.exceptions.DataIntegrityException;
import com.andresimiquelli.finalfinance.services.exceptions.ResourceNotFoundException;

@Service
public class EntryService {

	@Autowired
	private EntryRepository repository;
	
	@Autowired
	private WalletRepository walletRepository;
	
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
	
	public EntryDTO insert(EntryPostDTO entry) {
		Entry newEntry = fromDTO(entry, 1, 1);
		newEntry = repository.save(newEntry);
		periodPublisher.publisherPeriodEntriesChangedEvent(newEntry.getPeriod());
		return new EntryDTO(newEntry);
	}
	
	public List<EntryDTO> insertInstallments(List<EntryPostDTO> entries) {
		List<EntryDTO> inserted = new ArrayList<>();
		Set<Period> periods = new HashSet<>();
		
		Integer installment = 1;
		Integer totalInstallments = entries.size();
		for(EntryPostDTO entry: entries) {
			Entry newEntry = fromDTO(entry, installment, totalInstallments);
			newEntry = repository.save(newEntry);
			inserted.add(new EntryDTO(newEntry));
			periods.add(newEntry.getPeriod());
		}
		
		periodPublisher.publisherPeriodEntriesChangedEvent(periods);
		
		return inserted;
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
		
		if(newEntry.getInstallment() != null) {
			existing.setInstallment(newEntry.getInstallment());
		}
		
		if(newEntry.getTotalInstallments() != null) {
			existing.setTotalInstallments(newEntry.getTotalInstallments());
		}
		
		if(newEntry.getPaid() != null) {
			existing.setPaid(newEntry.getPaid());
		}
		
		return existing;
	}
	
	private Entry fromDTO(EntryPostDTO entry, Integer installment, Integer totalInstallments) {
		
		Period period = null;
		if(entry.getPeriodId() == null) {
			period = getPeriod(entry.getPeriodYear(), entry.getPeriodMonth(), entry.getWalletId());
		} else {
			period = getPeriod(entry.getPeriodId());
		}
			
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
				recurrency,
				installment,
				totalInstallments,
				false
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
	
	private Period getPeriod(Integer year, Integer month, Integer walletId) {
		Period period = periodRepository.findByYearAndMonthAndWalletId(year, month, walletId);
		 if(period == null) {
			 Wallet wallet = walletRepository.getById(walletId);
			 if(wallet != null) {
				 Period newPeriod = new Period(null, year, month, 0.0, PeriodStatus.OPEN, wallet);
				 newPeriod = periodRepository.save(newPeriod);
				 return newPeriod;
			 }

			throw new ResourceNotFoundException("Resource not found Id: "+walletId+" Tipo: "+Wallet.class.getName());
		 }
		 
		return period;
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
