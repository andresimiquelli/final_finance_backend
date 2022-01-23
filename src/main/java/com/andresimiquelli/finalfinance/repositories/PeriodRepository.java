package com.andresimiquelli.finalfinance.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.andresimiquelli.finalfinance.entities.Period;

public interface PeriodRepository extends JpaRepository<Period, Integer>{

	@Query("SELECT per FROM Period per WHERE per.status = ?1")
	List<Period> findByStatus(Integer status);
}
