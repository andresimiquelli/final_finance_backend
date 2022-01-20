package com.andresimiquelli.finalfinance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andresimiquelli.finalfinance.dto.WalletDTO;
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
}
