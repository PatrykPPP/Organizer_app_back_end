package com.organizer.organizerapp.service;

import org.springframework.security.core.Authentication;

public interface UserAuthenticationService {

	public Authentication getAuthentication();
}
