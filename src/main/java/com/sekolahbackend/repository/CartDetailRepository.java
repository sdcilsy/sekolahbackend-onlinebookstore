package com.sekolahbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sekolahbackend.entity.CartDetail;

public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {

}
