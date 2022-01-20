package com.andresimiquelli.finalfinance.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andresimiquelli.finalfinance.dto.GroupDTO;
import com.andresimiquelli.finalfinance.entities.Group;
import com.andresimiquelli.finalfinance.repositories.GroupRepository;
import com.andresimiquelli.finalfinance.services.exceptions.ResourceNotFoundException;

@Service
public class GroupService {

	@Autowired
	private GroupRepository repository;
	
	@Transactional(readOnly = true)
	public Page<GroupDTO> findAll(Pageable pageable){
		Page<Group> result = repository.findAll(pageable);
		Page<GroupDTO> page = result.map(item -> new GroupDTO(item));
		return page;
	}
	
	@Transactional(readOnly = true)
	public GroupDTO findById(Integer id){
		Optional<Group> obj = repository.findById(id);
		Group group = obj.orElseThrow(() -> new ResourceNotFoundException("Resource not found. Id: "+id+" Tipo: "+Group.class.getName()));
		return new GroupDTO(group);
	}

}
