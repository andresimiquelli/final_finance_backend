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
		Optional<User> obj = repository.findById(id);
		User user = obj.orElseThrow(() -> new ResourceNotFoundException(
				"Resource not found. Id: "+id+" Tipo: "+User.class.getName()));
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
		User newUser = fromDTO(user);
		User existing = fromDTO(findById(id));
		newUser = updateData(existing, newUser);
		newUser = repository.save(newUser);
		return new UserDTO(newUser);
	}
	
	@Transactional
	public void delete(Integer id) {
		UserDTO obj = findById(id);
		obj.setStatus(UserStatus.INACTIVE);
		repository.save(fromDTO(obj));
	}
	
	public User fromDTO(UserDTO dto) {
		return new User(
				dto.getId(), 
				dto.getName(), 
				dto.getEmail(), 
				dto.getPassword(), 
				dto.getStatus()==null? null : dto.getStatus().getCod());
	}
	
	public User fromDTO(UserPostDTO dto) {
		return new User(null, dto.getName(), dto.getEmail(), dto.getPassword(), UserStatus.ACTIVE.getCod());
	}
	
	public User fromDTO(UserPutDTO dto) {
		User user = new User();
		user.setName(dto.getName());
		user.setPassword(dto.getPassword());
		user.setStatus(dto.getStatus());
		return user;
	}
	
	public User updateData(User existing, User user ) {
		if(user.getName() != null)
			existing.setName(user.getName());
		
		if(user.getEmail() != null)
			existing.setEmail(user.getEmail());
		
		if(user.getPassword() != null)
			existing.setPassword(user.getPassword());
		
		if(user.getStatus() != null)
			existing.setStatus(user.getStatus());
		
		return existing;
	}
}
