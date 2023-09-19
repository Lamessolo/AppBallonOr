package com.joueurs.api.exception;

import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException{

	
	private final HttpStatus status ;
	private final String message ;
	
	public BlogAPIException(HttpStatus status,String message) {
		this.message = message;
		this.status = status;
	}
	
	public BlogAPIException(HttpStatus status,String message,String message1) {
		super(message);
		this.message = message1;
		this.status = status;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
