package com.sekolahbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sekolahbackend.entity.BookCategory;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer> {

	@Query(value = "SELECT * FROM book_category bc WHERE bc.status = 'NOT_ACTIVE'", nativeQuery = true)
	List<BookCategory> findByStatusNotActive();
	
}
