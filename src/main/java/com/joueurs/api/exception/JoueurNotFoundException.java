package com.joueurs.api.exception;


public class JoueurNotFoundException  extends RuntimeException{

	public JoueurNotFoundException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public JoueurNotFoundException(String message) {
		super(message);
	
	}

	public JoueurNotFoundException(Throwable cause) {
		super(cause);
	
	}

	
}
