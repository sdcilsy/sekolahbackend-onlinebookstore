package com.sekolahbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sekolahbackend.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	List<Transaction> findByUserId(Integer userId);
	
}
