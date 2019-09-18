package com.organizer.organizerapp.controller.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationRestController {

	@GetMapping("/authentication")
	public AuthenticationMessage getAuthentication() {
		return new AuthenticationMessage("Authenticated");
	}
}
