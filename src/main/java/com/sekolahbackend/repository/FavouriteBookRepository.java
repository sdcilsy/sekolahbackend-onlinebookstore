package com.sekolahbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sekolahbackend.entity.FavouriteBook;

public interface FavouriteBookRepository extends JpaRepository<FavouriteBook, Integer> {

	List<FavouriteBook> findByUserId(Integer userId);
	
}
