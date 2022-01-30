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
import com.andresimiquelli.finalfinance.security.UserSpringSecurity;
import com.andresimiquelli.finalfinance.services.exceptions.AuthorizationException;
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
		
		Page<Wallet> result = repository.findByUser(getAuthenticatedUser(), pageable);
		Page<WalletDTO> page = result.map(item -> new WalletDTO(item));
		return page;
	}
	
	@Transactional(readOnly = true)
	public WalletDTO findById(Integer id){
		Wallet wallet = getWallet(id);
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
		Wallet existing = getWallet(id);
		Wallet newWallet = updateData(existing, wallet);
		newWallet = repository.save(newWallet);
		return new WalletDTO(newWallet);
	}
	
	public void delete(Integer id) {
		findById(id);
		
		try {
			repository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {			
			throw new DataIntegrityException("Deletion is not possible. Wallet has associated registers.");
		}
		
	}
	
	private Wallet fromDTO(WalletPostDTO wallet) {
		User user = getUser(wallet.getUserId());
		return new Wallet(
				null, 
				wallet.getName(), 
				wallet.getDescription(),
				0.0,
				user
			);
	}
	
	private Wallet updateData(Wallet existing, WalletPutDTO newWallet) {
		if(newWallet.getName() != null)
			existing.setName(newWallet.getName());
		
		if(newWallet.getDescription() != null)
			existing.setDescription(newWallet.getDescription());
		
		if(newWallet.getLeftover() != null)
			existing.setLeftover(newWallet.getLeftover());
		
		return existing;
	}
	
	private Wallet getWallet(Integer id) {
		Optional<Wallet> optional = repository.findById(id);
		return optional.orElseThrow(
			() -> new ResourceNotFoundException("Resource not found. Id: "+id+" Tipo: "+Wallet.class.getName())
		);
	}
	
	private User getUser(Integer id) {
		Optional<User> optional = userRepository.findById(id);
		return optional.orElseThrow(
			() -> new ResourceNotFoundException("Resource not found. Id: "+id+" Tipo: "+User.class.getName())
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
