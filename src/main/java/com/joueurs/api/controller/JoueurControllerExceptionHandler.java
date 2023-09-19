package com.joueurs.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.joueurs.api.exception.JoueurErrorResponse;
import com.joueurs.api.exception.JoueurNotFoundException;

@ControllerAdvice
public class JoueurControllerExceptionHandler {

	// Add an exception handler using @ExceptionHandler
	
		@ExceptionHandler
		public ResponseEntity<JoueurErrorResponse> handlerException(JoueurNotFoundException exc){
			
			// create a JoueurErrorResponse		
			JoueurErrorResponse error = new JoueurErrorResponse();		
			error.setStatus(HttpStatus.NOT_FOUND.value());
			error.setMessage(exc.getMessage());
			error.setTimeStamp(System.currentTimeMillis());
			
			// return Response Entity				
			return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
		}
		
		
		@ExceptionHandler
		public ResponseEntity<JoueurErrorResponse> handlerException( Exception exc){
			
			// create a JoueurErrorResponse		
			JoueurErrorResponse error = new JoueurErrorResponse();		
			error.setStatus(HttpStatus.BAD_REQUEST.value());
			error.setMessage(exc.getMessage());
			error.setTimeStamp(System.currentTimeMillis());
			
			// return Response Entity				
			return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		}
}
