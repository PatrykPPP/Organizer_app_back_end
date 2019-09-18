package com.organizer.organizerapp.controller.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

	@GetMapping("/authenticaion")
	public String getAuthentication() {
		return "You are authenticated!";
	}
}
