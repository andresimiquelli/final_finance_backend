package com.andresimiquelli.finalfinance.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.andresimiquelli.finalfinance.entities.User;
import com.andresimiquelli.finalfinance.entities.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Integer>{

	@Transactional(readOnly = true)
	Page<Wallet> findByUser(User user, Pageable pageRequest);
}
