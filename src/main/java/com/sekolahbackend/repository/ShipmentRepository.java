package com.sekolahbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sekolahbackend.entity.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {

	List<Shipment> findByUserId(Integer userId);
}
