package com.andresimiquelli.finalfinance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andresimiquelli.finalfinance.entities.Entry;

public interface EntryRepository extends JpaRepository<Entry, Integer>{

}
