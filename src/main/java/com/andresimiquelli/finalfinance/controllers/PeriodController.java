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

import com.andresimiquelli.finalfinance.dto.PeriodDTO;
import com.andresimiquelli.finalfinance.dto.PeriodPostDTO;
import com.andresimiquelli.finalfinance.dto.PeriodPutDTO;
import com.andresimiquelli.finalfinance.services.PeriodService;

@RestController
@RequestMapping(value = "/periods")
public class PeriodController {

	@Autowired
	private PeriodService service;

	@GetMapping
	public Page<PeriodDTO> findAll(@RequestParam Integer walletId, Pageable pageable){
		return service.findAll(walletId, pageable);
	}
	
	@GetMapping(value = "/{id}")
	public PeriodDTO findById(@PathVariable Integer id){
		return service.findById(id);
	}
	
	@GetMapping(value = "/{year}/{month}")
	public PeriodDTO findByDate(@PathVariable Integer year, @PathVariable Integer month) {
		return service.findByDate(year, month);
	}
	
	@PostMapping
	public ResponseEntity<PeriodDTO> insert(@Valid @RequestBody PeriodPostDTO period) {
		PeriodDTO newPeriod = service.insert(period);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(newPeriod.getId()).toUri();
		return ResponseEntity.created(uri).body(newPeriod);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id,@Valid @RequestBody PeriodPutDTO period) {
		service.update(id, period);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<Void> updatePatch(@PathVariable Integer id,@Valid @RequestBody PeriodPutDTO period) {
		service.update(id, period);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
