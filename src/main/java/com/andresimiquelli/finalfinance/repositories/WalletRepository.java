package com.andresimiquelli.finalfinance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andresimiquelli.finalfinance.entities.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Integer>{

}
