package com.andresimiquelli.finalfinance.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andresimiquelli.finalfinance.dto.UserDTO;
import com.andresimiquelli.finalfinance.dto.UserPostDTO;
import com.andresimiquelli.finalfinance.dto.UserPutDTO;
import com.andresimiquelli.finalfinance.entities.User;
import com.andresimiquelli.finalfinance.entities.enums.UserStatus;
import com.andresimiquelli.finalfinance.repositories.UserRepository;
import com.andresimiquelli.finalfinance.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;

	@Transactional(readOnly = true)
	public Page<UserDTO> findAll(Pageable pageable){
		Page<User> result = repository.findAll(pageable);
		Page<UserDTO> page = result.map(item -> new UserDTO(item));
		return page;
	}
	
	@Transactional(readOnly = true)
	public UserDTO findById(Integer id){
		User user = getUser(id);
		return new UserDTO(user);
	}
	
	@Transactional
	public UserDTO insert(UserPostDTO user) {
		User newUser = fromDTO(user);
		newUser = repository.save(newUser);
		return new UserDTO(newUser);
	}
	
	@Transactional
	public UserDTO update(Integer id, UserPutDTO user) {
		User existing = getUser(id);
		User newUser = updateData(existing, user);
		newUser = repository.save(newUser);
		return new UserDTO(newUser);
	}
	
	@Transactional
	public void delete(Integer id) {
		UserDTO obj = findById(id);
		obj.setStatus(UserStatus.INACTIVE);
		repository.save(fromDTO(obj));
	}
	
	private User fromDTO(UserDTO dto) {
		return new User(
				dto.getId(), 
				dto.getName(), 
				dto.getEmail(), 
				dto.getPassword(), 
				dto.getStatus()==null? null : dto.getStatus().getCod());
	}
	
	private User fromDTO(UserPostDTO dto) {
		return new User(null, dto.getName(), dto.getEmail(), dto.getPassword(), UserStatus.ACTIVE.getCod());
	}
	
	private User updateData(User existing, UserPutDTO user ) {
		if(user.getName() != null)
			existing.setName(user.getName());
		
		if(user.getPassword() != null)
			existing.setPassword(user.getPassword());
		
		if(user.getStatus() != null)
			existing.setStatus(user.getStatus());
		
		return existing;
	}
	
	private User getUser(Integer id) {
		Optional<User> optional = repository.findById(id);
		return optional.orElseThrow(
			() -> new ResourceNotFoundException("Resource not found. Id: "+id+" Tipo: "+User.class.getName())
		);
	}
}
