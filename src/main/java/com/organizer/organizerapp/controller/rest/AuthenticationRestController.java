package com.organizer.organizerapp.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.organizer.organizerapp.entity.User;
import com.organizer.organizerapp.repository.TaskRepository;
import com.organizer.organizerapp.repository.UserRepository;
import com.organizer.organizerapp.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationRestController {
	
	@Autowired
	UserService s;

	@GetMapping("/authentication")
	public AuthenticationMessage getAuthentication() {
		
		User user = new User();
		
		user.setUsername("John");
		user.setPassword("123");
		
		s.save(user);
		
		return new AuthenticationMessage("Authenticated");
	}
}
