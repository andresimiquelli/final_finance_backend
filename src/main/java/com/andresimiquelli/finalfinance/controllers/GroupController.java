package com.andresimiquelli.finalfinance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andresimiquelli.finalfinance.dto.GroupDTO;
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
}
