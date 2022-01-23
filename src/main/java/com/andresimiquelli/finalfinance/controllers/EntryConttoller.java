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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.andresimiquelli.finalfinance.dto.EntryDTO;
import com.andresimiquelli.finalfinance.dto.EntryPostDTO;
import com.andresimiquelli.finalfinance.dto.EntryPutDTO;
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
	
	@PostMapping
	public ResponseEntity<EntryDTO> insert(@Valid @RequestBody EntryPostDTO entry) {
		EntryDTO newEntry = service.insert(entry);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(newEntry.getId()).toUri();
		return ResponseEntity.created(uri).body(newEntry);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<EntryDTO> update(@PathVariable Integer id,@Valid @RequestBody EntryPutDTO entry) {
		EntryDTO newEntry = service.update(id, entry);
		return ResponseEntity.accepted().body(newEntry);
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<EntryDTO> updatePatch(@PathVariable Integer id,@Valid @RequestBody EntryPutDTO entry) {
		EntryDTO newEntry = service.update(id, entry);
		return ResponseEntity.accepted().body(newEntry);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
