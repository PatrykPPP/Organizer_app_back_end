package com.organizer.organizerapp.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.organizer.organizerapp.repository.UserRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationRestController {
	
	@Autowired 
	UserRepository userRepository;

	@PostMapping("/registration")
	public void register() {
		
		System.out.println(userRepository.findByUsername("Peter"));
		
	}
	

}
