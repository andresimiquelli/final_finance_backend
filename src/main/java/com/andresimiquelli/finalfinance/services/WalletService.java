package com.andresimiquelli.finalfinance.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andresimiquelli.finalfinance.dto.WalletDTO;
import com.andresimiquelli.finalfinance.entities.Wallet;
import com.andresimiquelli.finalfinance.repositories.WalletRepository;
import com.andresimiquelli.finalfinance.services.exceptions.ResourceNotFoundException;

@Service
public class WalletService {
	
	@Autowired
	private WalletRepository repository;
	
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

}
