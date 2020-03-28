package com.sekolahbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sekolahbackend.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByRoleName(String roleName);
}
