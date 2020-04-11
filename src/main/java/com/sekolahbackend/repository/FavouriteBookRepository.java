package com.sekolahbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sekolahbackend.entity.FavouriteBook;

public interface FavouriteBookRepository extends JpaRepository<FavouriteBook, Integer> {

	FavouriteBook findByUserId(Integer userId);
	
	FavouriteBook findByUserUsername(String username);
}
