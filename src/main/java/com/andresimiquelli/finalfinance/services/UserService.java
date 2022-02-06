package com.andresimiquelli.finalfinance.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andresimiquelli.finalfinance.dto.UserDTO;
import com.andresimiquelli.finalfinance.dto.UserPostDTO;
import com.andresimiquelli.finalfinance.dto.UserPutDTO;
import com.andresimiquelli.finalfinance.entities.User;
import com.andresimiquelli.finalfinance.entities.enums.UserLevel;
import com.andresimiquelli.finalfinance.entities.enums.UserStatus;
import com.andresimiquelli.finalfinance.repositories.UserRepository;
import com.andresimiquelli.finalfinance.security.UserSpringSecurity;
import com.andresimiquelli.finalfinance.services.exceptions.AuthorizationException;
import com.andresimiquelli.finalfinance.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public static UserSpringSecurity authenticated() {
		try {
			return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional(readOnly = true)
	public Page<UserDTO> findAll(Pageable pageable){		
		Page<User> result = repository.findAll(pageable);
		Page<UserDTO> page = result.map(item -> new UserDTO(item));
		return page;
	}
	
	@Transactional(readOnly = true)
	public UserDTO findById(Integer id){
		
		hasAuthorization(id);
			
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
		
		hasAuthorization(id);
		
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
	
	public UserDTO getAuthenticatedUser() {
		UserSpringSecurity auth = UserService.authenticated();
		if(auth == null) {
			throw new AuthorizationException("Forbidden");
		}
		
		return new UserDTO(repository.getById(auth.getId()));
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
		return new User(
				null, 
				dto.getName(), 
				dto.getEmail(), 
				passwordEncoder.encode(dto.getPassword()),
				UserStatus.ACTIVE.getCod()
			);
	}
	
	private User updateData(User existing, UserPutDTO user ) {
		if(user.getName() != null)
			existing.setName(user.getName());
		
		if(user.getPassword() != null)
			existing.setPassword(passwordEncoder.encode(user.getPassword()));
		
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
	
	private void hasAuthorization(Integer id) {
		UserSpringSecurity auth = authenticated();
		if(auth == null || !auth.hasRole(UserLevel.ADMIN) && !id.equals(auth.getId())) {
			throw new AuthorizationException("Access denied");
		}
	}
}
