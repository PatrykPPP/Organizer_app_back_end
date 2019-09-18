package com.organizer.organizerapp.controller.rest;

public class AuthenticationMessage {

	private String message;

	public AuthenticationMessage() {
	}

	public AuthenticationMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	};	
}
