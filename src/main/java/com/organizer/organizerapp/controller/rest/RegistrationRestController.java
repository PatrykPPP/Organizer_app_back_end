package com.organizer.organizerapp.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.organizer.organizerapp.entity.User;
import com.organizer.organizerapp.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationRestController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/registration")
	public void register(@RequestBody User user  ) {
		
		User existing = userService.findByUserName(user.getUsername());
		
		if ( existing != null) {
			System.out.println("Exist");
		}
		
		userService.save(user);
	}

}
