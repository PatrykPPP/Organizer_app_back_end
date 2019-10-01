package com.organizer.organizerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organizer.organizerapp.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role findByName(String name);
}
