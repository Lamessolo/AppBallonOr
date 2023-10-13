package com.joueurs.api.exception;


public class TitreNotFoundException extends RuntimeException {

	public TitreNotFoundException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public TitreNotFoundException(String message) {
		super(message);
	
	}

	public TitreNotFoundException(Throwable cause) {
		super(cause);
	
	}
}
