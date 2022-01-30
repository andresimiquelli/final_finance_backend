package com.andresimiquelli.finalfinance.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.andresimiquelli.finalfinance.dto.EntryDTO;
import com.andresimiquelli.finalfinance.dto.EntryPostDTO;
import com.andresimiquelli.finalfinance.dto.EntryPutDTO;
import com.andresimiquelli.finalfinance.services.EntryService;

@RestController
@RequestMapping(value = "/entries")
public class EntryController {

	@Autowired
	private EntryService service;

	@GetMapping
	public List<EntryDTO> findAll(@RequestParam Integer periodId){
		return service.findAll(periodId);
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
	
	@PostMapping(value = "/installments")
	public ResponseEntity<List<EntryDTO>> insertAll(
			@RequestBody 
			@NotEmpty(message = "Input entry list cannot be empty.")
			List<@Valid EntryPostDTO> entries) {
		
		List<EntryDTO> inserted = service.insertInstallments(entries);
		return ResponseEntity.created(null).body(inserted);
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
