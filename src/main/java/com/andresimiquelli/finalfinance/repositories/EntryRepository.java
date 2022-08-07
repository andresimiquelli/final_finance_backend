package com.andresimiquelli.finalfinance.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.andresimiquelli.finalfinance.entities.Entry;
import com.andresimiquelli.finalfinance.entities.User;

public interface EntryRepository extends JpaRepository<Entry, Integer>{

	@Query("SELECT ent FROM Entry ent INNER JOIN Period per ON per.id = ent.period.id WHERE per.status= ?1 AND ent.recurrency.id = ?2")
	List<Entry> findByPeriodStatusAndRecurrencyId(Integer periodStatus, Integer recurrencyId );
	
	@Query("SELECT ent FROM Entry ent WHERE ent.period.wallet.user = ?1 AND ent.period.id = ?2 ORDER BY ent.group.name ASC, ent.created_at ASC")
	List<Entry> findByPeriodWalletUserAndPeriodId(User user, Integer periodId);
}
