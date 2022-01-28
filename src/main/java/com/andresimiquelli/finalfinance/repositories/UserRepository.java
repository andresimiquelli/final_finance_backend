package com.andresimiquelli.finalfinance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.andresimiquelli.finalfinance.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Transactional(readOnly=true)
	User findByEmail(String email);
}
