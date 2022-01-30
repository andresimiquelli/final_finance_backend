package com.andresimiquelli.finalfinance.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.andresimiquelli.finalfinance.entities.Recurrency;
import com.andresimiquelli.finalfinance.entities.User;

public interface RecurrencyRepository extends JpaRepository<Recurrency, Integer> {

	Page<Recurrency> findByWalletUserAndWalletId(User user, Integer walletId, Pageable requestPage);
}
