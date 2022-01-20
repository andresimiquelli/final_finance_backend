package com.andresimiquelli.finalfinance.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andresimiquelli.finalfinance.dto.WalletDTO;
import com.andresimiquelli.finalfinance.entities.Wallet;
import com.andresimiquelli.finalfinance.repositories.WalletRepository;

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
		Wallet result = repository.findById(id).get();
		WalletDTO dto = new WalletDTO(result);
		return dto;
	}

}
