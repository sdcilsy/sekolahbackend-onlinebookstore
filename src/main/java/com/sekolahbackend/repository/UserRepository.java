package com.sekolahbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sekolahbackend.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
	
	User findByEmail(String email);
	
}
