package com.andresimiquelli.finalfinance.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andresimiquelli.finalfinance.dto.UserDTO;
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
	public UserDTO insert(User user) {
		user.setStatus(UserStatus.ACTIVE);
		User obj = repository.save(user);
		return new UserDTO(obj);
	}
	
	@Transactional
	public UserDTO update(Integer id, User user) {
		user = repository.save(user);
		return new UserDTO(user);
	}
	
	@Transactional
	public void delete(Integer id) {
		UserDTO obj = findById(id);
		obj.setStatus(UserStatus.INACTIVE);
		repository.save(fromDTO(obj));
	}
	
	public User fromDTO(UserDTO dto) {
		return new User(dto.getId(), dto.getName(), dto.getEmail(), dto.getPassword(), dto.getStatus().getCod());
	}
	
	public User fromDTO(UserDTO dto, UserDTO user ) {
		if(dto.getName() != null)
			user.setName(dto.getName());
		if(dto.getEmail() != null)
			user.setEmail(dto.getEmail());
		if(dto.getPassword() != null)
			user.setPassword(dto.getPassword());
		if(dto.getStatus() != null)
			user.setStatus(dto.getStatus());
		
		return fromDTO(user);
	}
}
