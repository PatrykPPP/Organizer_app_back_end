package com.organizer.organizerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organizer.organizerapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
