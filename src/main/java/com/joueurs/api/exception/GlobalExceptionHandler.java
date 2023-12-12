package com.joueurs.api.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;



@ControllerAdvice
public class GlobalExceptionHandler {

	// Handle specific Joueur Not Found exception 
		@ExceptionHandler(JoueurNotFoundException.class)
		public ResponseEntity<ErrorDetails> handleJoueurNotFoundException(
				JoueurNotFoundException exception, WebRequest webRequest){
			ErrorDetails errorDetails = new ErrorDetails (new Date(), exception.getMessage(),
					webRequest.getDescription(false));	
			return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);	
		}
		
		// Handle specific Club Not Found exception 
		@ExceptionHandler(ClubNotFoundException.class)
		public ResponseEntity<ErrorDetails> handleClubNotFoundException(
						ClubNotFoundException exception, WebRequest webRequest){
					ErrorDetails errorDetails = new ErrorDetails (new Date(), exception.getMessage(),
							webRequest.getDescription(false));	
					return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);	
		}
		
		// Handle specific Poste Not Found exception 
		@ExceptionHandler(PosteNotFoundException.class)
		public ResponseEntity<ErrorDetails> handlePosteNotFoundException(
			PosteNotFoundException exception, WebRequest webRequest){
			ErrorDetails errorDetails = new ErrorDetails (new Date(), exception.getMessage(),
				webRequest.getDescription(false));	
			return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);	
		}
		
		// Handle specific Selection Not Found exception 
		@ExceptionHandler(SelectionNotFoundException.class)
		public ResponseEntity<ErrorDetails> handleSelectionNotFoundException(
			SelectionNotFoundException exception, WebRequest webRequest){
				ErrorDetails errorDetails = new ErrorDetails (new Date(), exception.getMessage(),
					webRequest.getDescription(false));	
			return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);	
				}
	
		// Handle specific Titre Not Found exception 
		@ExceptionHandler(TitreNotFoundException.class)
		public ResponseEntity<ErrorDetails> handleTitreNotFoundException(
			TitreNotFoundException exception, WebRequest webRequest){
			ErrorDetails errorDetails = new ErrorDetails (new Date(), exception.getMessage(),
				webRequest.getDescription(false));	
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);	
						}
		
}
