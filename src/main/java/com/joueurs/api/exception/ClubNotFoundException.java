package com.joueurs.api.exception;

public class ClubNotFoundException extends RuntimeException {

	public ClubNotFoundException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public ClubNotFoundException(String message) {
		super(message);
	
	}

	public ClubNotFoundException(Throwable cause) {
		super(cause);
	
	}
}
