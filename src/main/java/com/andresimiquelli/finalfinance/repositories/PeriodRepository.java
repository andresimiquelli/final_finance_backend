package com.andresimiquelli.finalfinance.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.andresimiquelli.finalfinance.entities.Period;
import com.andresimiquelli.finalfinance.entities.User;

public interface PeriodRepository extends JpaRepository<Period, Integer>{

	@Transactional(readOnly = true)
	@Query("SELECT per FROM Period per WHERE per.status = ?1")
	List<Period> findByStatus(Integer status);
	
	@Transactional(readOnly = true)
	Period findByYearAndMonthAndWalletId(Integer year, Integer month, Integer walletdId);
	
	@Transactional(readOnly = true)
	Page<Period> findByWalletUserAndWalletId(User user, Integer walletId, Pageable pageRequest);
}
