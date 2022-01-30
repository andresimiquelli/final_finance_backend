package com.andresimiquelli.finalfinance.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andresimiquelli.finalfinance.dto.GroupDTO;
import com.andresimiquelli.finalfinance.dto.GroupPostDTO;
import com.andresimiquelli.finalfinance.dto.GroupPutDTO;
import com.andresimiquelli.finalfinance.entities.Group;
import com.andresimiquelli.finalfinance.entities.User;
import com.andresimiquelli.finalfinance.entities.Wallet;
import com.andresimiquelli.finalfinance.repositories.GroupRepository;
import com.andresimiquelli.finalfinance.repositories.UserRepository;
import com.andresimiquelli.finalfinance.repositories.WalletRepository;
import com.andresimiquelli.finalfinance.security.UserSpringSecurity;
import com.andresimiquelli.finalfinance.services.exceptions.AuthorizationException;
import com.andresimiquelli.finalfinance.services.exceptions.DataIntegrityException;
import com.andresimiquelli.finalfinance.services.exceptions.ResourceNotFoundException;

@Service
public class GroupService {

	@Autowired
	private GroupRepository repository;
	
	@Autowired
	private WalletRepository walletRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public Page<GroupDTO> findAll(Integer walletId, Pageable pageable){
		Page<Group> result = repository.findByWalletUserAndWalletId(getAuthenticatedUser(), walletId, pageable);
		Page<GroupDTO> page = result.map(item -> new GroupDTO(item));
		return page;
	}
	
	@Transactional(readOnly = true)
	public GroupDTO findById(Integer id){
		Group group = getGroup(id);
		return new GroupDTO(group);
	}
	
	@Transactional
	public GroupDTO insert(GroupPostDTO group) {
		Group newGroup = fromDTO(group);
		newGroup = repository.save(newGroup);
		return new GroupDTO(newGroup);
	}
	
	@Transactional
	public GroupDTO update(Integer id, GroupPutDTO group) {
		Group existing = getGroup(id);
		Group newGroup = updateData(existing, group);
		repository.save(newGroup);
		return new GroupDTO(newGroup);
	}
	
	@Transactional
	public void delete(Integer id) {
		findById(id);
		
		try {
			repository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {			
			throw new DataIntegrityException("Deletion is not possible. Group has associated registers.");
		}
		
	}
	
	private Group updateData(Group existing, GroupPutDTO newGroup) {
		if(newGroup.getName() != null)
			existing.setName(newGroup.getName());
		
		if(newGroup.getColor() != null)
			existing.setColor(newGroup.getColor());
		
		return existing;
	}
	
	private Group fromDTO(GroupPostDTO group) {
		Wallet wallet = getWallet(group.getWalletId());
		return new Group(null, group.getName(), group.getColor(), wallet);
	}
	
	private Group getGroup(Integer id) {
		Optional<Group> optional = repository.findById(id);
		return optional.orElseThrow(
				() -> new ResourceNotFoundException("Resource not found. Id: "+id+" Tipo: "+Group.class.getName())
		);
	}
	
	private Wallet getWallet(Integer id) {
		Optional<Wallet> optional = walletRepository.findById(id);
		return optional.orElseThrow(
				() -> new ResourceNotFoundException("Resource not found. Id: "+id+" Tipo: "+Wallet.class.getName())
		);
	}
	
	private User getAuthenticatedUser() {
		UserSpringSecurity auth = UserService.authenticated();
		if(auth == null) {
			throw new AuthorizationException("Forbidden");
		}
		
		return userRepository.getById(auth.getId());
	}

}
