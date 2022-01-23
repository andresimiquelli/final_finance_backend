package com.andresimiquelli.finalfinance.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.andresimiquelli.finalfinance.dto.WalletDTO;
import com.andresimiquelli.finalfinance.dto.WalletPostDTO;
import com.andresimiquelli.finalfinance.dto.WalletPutDTO;
import com.andresimiquelli.finalfinance.services.WalletService;

@RestController
@RequestMapping(value = "/wallets")
public class WalletController {

	@Autowired
	private WalletService service;

	@GetMapping
	public Page<WalletDTO> findAll(Pageable pageable){
		return service.findAll(pageable);
	}
	
	@GetMapping(value = "/{id}")
	public WalletDTO findById(@PathVariable Integer id){
		return service.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<WalletDTO> insert(@Valid @RequestBody WalletPostDTO wallet) {
		WalletDTO newWallet = service.insert(wallet);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(newWallet.getId()).toUri();
		return ResponseEntity.created(uri).body(newWallet);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<WalletDTO> update(@PathVariable Integer id,@Valid @RequestBody WalletPutDTO wallet) {
		WalletDTO newWallet = service.update(id, wallet);
		return ResponseEntity.accepted().body(newWallet);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
