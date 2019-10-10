package com.organizer.organizerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.organizer.organizerapp.entity.User;
import com.organizer.organizerapp.repository.UserRepository;
import com.organizer.organizerapp.userdetails.UserDetailsImpl;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);
		
		if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
		
		return new UserDetailsImpl(user);
	}

}
