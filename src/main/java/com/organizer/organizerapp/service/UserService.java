package com.organizer.organizerapp.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.organizer.organizerapp.entity.User;

public interface UserService extends UserDetailsService {
	
	User findByUserName(String userName);
	
	void save(User user);
}
