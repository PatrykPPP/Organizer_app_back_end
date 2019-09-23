package com.organizer.organizerapp.controller.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationRestController {

	@GetMapping("/authentication")
	public AuthenticationMessage getAuthentication() {
		return new AuthenticationMessage("Authenticated");
	}
}
