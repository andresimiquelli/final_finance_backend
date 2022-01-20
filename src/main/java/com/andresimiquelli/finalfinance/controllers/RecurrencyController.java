package com.andresimiquelli.finalfinance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andresimiquelli.finalfinance.dto.RecurrencyDTO;
import com.andresimiquelli.finalfinance.services.RecurrencyService;

@RestController
@RequestMapping(value = "/recurrences")
public class RecurrencyController {

	@Autowired
	private RecurrencyService service;

	@GetMapping
	public Page<RecurrencyDTO> findAll(Pageable pageable){
		return service.findAll(pageable);
	}
	
	@GetMapping(value = "/{id}")
	public RecurrencyDTO findById(@PathVariable Integer id){
		return service.findById(id);
	}
}
