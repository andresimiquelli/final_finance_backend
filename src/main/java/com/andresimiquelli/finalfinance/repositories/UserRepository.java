package com.andresimiquelli.finalfinance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andresimiquelli.finalfinance.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
