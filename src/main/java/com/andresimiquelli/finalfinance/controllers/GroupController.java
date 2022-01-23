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

import com.andresimiquelli.finalfinance.dto.GroupDTO;
import com.andresimiquelli.finalfinance.dto.GroupPostDTO;
import com.andresimiquelli.finalfinance.dto.GroupPutDTO;
import com.andresimiquelli.finalfinance.services.GroupService;

@RestController
@RequestMapping(value = "/groups")
public class GroupController {

	@Autowired
	private GroupService service;

	@GetMapping
	public Page<GroupDTO> findAll(Pageable pageable){
		return service.findAll(pageable);
	}
	
	@GetMapping(value = "/{id}")
	public GroupDTO findById(@PathVariable Integer id){
		return service.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<GroupDTO> insert(@Valid @RequestBody GroupPostDTO group) {
		GroupDTO newGroup = service.insert(group);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(newGroup.getId()).toUri();
		return ResponseEntity.created(uri).body(newGroup);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<GroupDTO> update(@PathVariable Integer id,@Valid @RequestBody GroupPutDTO group) {
		GroupDTO newGroup = service.update(id, group);
		return ResponseEntity.accepted().body(newGroup);
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<GroupDTO> updatePatch(@PathVariable Integer id,@Valid @RequestBody GroupPutDTO group) {
		GroupDTO newGroup = service.update(id, group);
		return ResponseEntity.accepted().body(newGroup);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}	
}
