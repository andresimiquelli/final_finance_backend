package com.andresimiquelli.finalfinance.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.andresimiquelli.finalfinance.entities.Entry;

public interface EntryRepository extends JpaRepository<Entry, Integer>{

	@Query("SELECT ent FROM Entry ent INNER JOIN Period per ON per.id = ent.period WHERE per.status= ?1 AND ent.recurrency.id = ?2")
	List<Entry> findByPeriodStatusAndRecurrencyId(Integer periodStatus, Integer recurrencyId );
}
