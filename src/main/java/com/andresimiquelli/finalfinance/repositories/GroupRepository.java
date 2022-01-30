package com.andresimiquelli.finalfinance.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.andresimiquelli.finalfinance.entities.Group;
import com.andresimiquelli.finalfinance.entities.User;

public interface GroupRepository extends JpaRepository<Group, Integer> {

	Page<Group> findByWalletUserAndWalletId(User user, Integer walletId, Pageable requestPage);
}
