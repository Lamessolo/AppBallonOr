package com.joueurs.api.exception;

public class TitreErrorResponse {

	private int status;
	
	private String message;
	
	private long timeStamp;
	
	public TitreErrorResponse(int status, String message, long timeStamp) {
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
	}

	public int getStatus() {
		return status;
	}


	public String getMessage() {
		return message;
	}


	public long getTimeStamp() {
		return timeStamp;
	}
	
	
}
