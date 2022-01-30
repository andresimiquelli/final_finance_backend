package com.andresimiquelli.finalfinance.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.andresimiquelli.finalfinance.dto.UserDTO;
import com.andresimiquelli.finalfinance.dto.UserPostDTO;
import com.andresimiquelli.finalfinance.dto.UserPutDTO;
import com.andresimiquelli.finalfinance.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserService service;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public Page<UserDTO> findAll(Pageable pageable){
		return service.findAll(pageable);
	}
	
	@GetMapping(value = "/{id}")
	public UserDTO findById(@PathVariable Integer id){
		return service.findById(id);
	}
	
	@PreAuthorize("hasAnyRole('DEVELOPER')")
	@PostMapping
	public ResponseEntity<UserDTO> insert(@Valid @RequestBody UserPostDTO user) {
		UserDTO newUSer = service.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(newUSer.getId()).toUri();
		return ResponseEntity.created(uri).body(newUSer);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable Integer id,@Valid @RequestBody UserPutDTO user) {
		UserDTO newUser = service.update(id, user);
		return ResponseEntity.accepted().body(newUser);
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<UserDTO> updatePatch(@PathVariable Integer id,@Valid @RequestBody UserPutDTO user) {
		UserDTO newUser = service.update(id, user);
		return ResponseEntity.accepted().body(newUser);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
