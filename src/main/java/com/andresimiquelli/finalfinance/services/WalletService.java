package com.andresimiquelli.finalfinance.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andresimiquelli.finalfinance.dto.WalletDTO;
import com.andresimiquelli.finalfinance.dto.WalletPostDTO;
import com.andresimiquelli.finalfinance.dto.WalletPutDTO;
import com.andresimiquelli.finalfinance.entities.User;
import com.andresimiquelli.finalfinance.entities.Wallet;
import com.andresimiquelli.finalfinance.repositories.UserRepository;
import com.andresimiquelli.finalfinance.repositories.WalletRepository;
import com.andresimiquelli.finalfinance.services.exceptions.DataIntegrityException;
import com.andresimiquelli.finalfinance.services.exceptions.ResourceNotFoundException;

@Service
public class WalletService {
	
	@Autowired
	private WalletRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public Page<WalletDTO> findAll(Pageable pageable){
		Page<Wallet> result = repository.findAll(pageable);
		Page<WalletDTO> page = result.map(item -> new WalletDTO(item));
		return page;
	}
	
	@Transactional(readOnly = true)
	public WalletDTO findById(Integer id){
		Optional<Wallet> obj = repository.findById(id);
		Wallet wallet = obj.orElseThrow(() -> new ResourceNotFoundException("Resource not found. Id: "+id+" Tipo: "+Wallet.class.getName()));
		return new WalletDTO(wallet);
	}
	
	@Transactional
	public WalletDTO insert(WalletPostDTO wallet) {
		Wallet newWallet = fromDTO(wallet);
		newWallet = repository.save(newWallet);
		return new WalletDTO(newWallet);
	}
	
	@Transactional
	public WalletDTO update(Integer id, WalletPutDTO wallet) {
		Wallet newWallet = fromDTO(wallet);
		Optional<Wallet> optional = repository.findById(id);
		Wallet existing = optional.get();
		newWallet = updateData(existing, newWallet);
		newWallet = repository.save(newWallet);
		return new WalletDTO(newWallet);
	}
	
	public void delete(Integer id) {
		findById(id);
		
		try {
			repository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {			
			throw new DataIntegrityException("Deletion not possible. Wallet has registers associateds.");
		}
		
	}
	
	public Wallet fromDTO(WalletDTO wallet) {
		return new Wallet(wallet.getId(), wallet.getName(), wallet.getDescription(),null);
	}
	
	public Wallet fromDTO(WalletPostDTO wallet) {
		User user = userRepository.getById(wallet.getUserId());
		return new Wallet(
				null, 
				wallet.getName(), 
				wallet.getDescription(),
				user
			);
	}
	
	public Wallet fromDTO(WalletPutDTO wallet) {
		return new Wallet(null, wallet.getName(), wallet.getDescription(), null);
	}
	
	private Wallet updateData(Wallet existing, Wallet newWallet) {
		if(newWallet.getName() != null)
			existing.setName(newWallet.getName());
		
		if(newWallet.getDescription() != null)
			existing.setDescription(newWallet.getDescription());
		
		return existing;
	}

}
