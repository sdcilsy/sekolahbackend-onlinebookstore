package com.sekolahbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sekolahbackend.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	Cart findByUserId(Integer userId);
}
