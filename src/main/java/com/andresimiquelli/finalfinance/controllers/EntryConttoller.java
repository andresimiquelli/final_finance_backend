package com.andresimiquelli.finalfinance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andresimiquelli.finalfinance.dto.EntryDTO;
import com.andresimiquelli.finalfinance.services.EntryService;

@RestController
@RequestMapping(value = "/entries")
public class EntryConttoller {

	@Autowired
	private EntryService service;

	@GetMapping
	public Page<EntryDTO> findAll(Pageable pageable){
		return service.findAll(pageable);
	}
	
	@GetMapping(value = "/{id}")
	public EntryDTO findById(@PathVariable Integer id){
		return service.findById(id);
	}
}
