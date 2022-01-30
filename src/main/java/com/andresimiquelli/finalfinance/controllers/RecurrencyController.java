package com.andresimiquelli.finalfinance.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.andresimiquelli.finalfinance.dto.RecurrencyDTO;
import com.andresimiquelli.finalfinance.dto.RecurrencyPostDTO;
import com.andresimiquelli.finalfinance.dto.RecurrencyPutDTO;
import com.andresimiquelli.finalfinance.services.RecurrencyService;

@RestController
@RequestMapping(value = "/recurrences")
public class RecurrencyController {

	@Autowired
	private RecurrencyService service;

	@GetMapping
	public Page<RecurrencyDTO> findAll(@RequestParam Integer walletId, Pageable pageable){
		return service.findAll(walletId, pageable);
	}
	
	@GetMapping(value = "/{id}")
	public RecurrencyDTO findById(@PathVariable Integer id){
		return service.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<RecurrencyDTO> insert(@Valid @RequestBody RecurrencyPostDTO recurrency) {
		RecurrencyDTO newRecurrency = service.insert(recurrency);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(newRecurrency.getId()).toUri();
		return ResponseEntity.created(uri).body(newRecurrency);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<RecurrencyDTO> update(@PathVariable Integer id,@Valid @RequestBody RecurrencyPutDTO recurrency) {
		RecurrencyDTO newRecurrency = service.update(id, recurrency);
		return ResponseEntity.accepted().body(newRecurrency);
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<RecurrencyDTO> updatePatch(@PathVariable Integer id,@Valid @RequestBody RecurrencyPutDTO recurrency) {
		RecurrencyDTO newRecurrency = service.update(id, recurrency);
		return ResponseEntity.accepted().body(newRecurrency);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
