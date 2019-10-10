package com.organizer.organizerapp.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.organizer.organizerapp.entity.User;

public interface UserService extends UserDetailsService {

	User findByUsername(String username);
	
	void save(User user);
}