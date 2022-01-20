package com.andresimiquelli.finalfinance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andresimiquelli.finalfinance.entities.Period;

public interface PeriodRepository extends JpaRepository<Period, Integer>{

}
