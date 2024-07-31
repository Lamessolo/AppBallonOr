package com.joueurs.api.exception;


public class CompteNotFoundException extends RuntimeException {

	
	private String postName;
	private String fieldName;
	private long fieldValue;
	
	public CompteNotFoundException(String postName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : '%s'",postName, fieldName,fieldValue));
		this.postName = postName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public String getPostName() {
		return postName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public long getFieldValue() {
		return fieldValue;
	}

}
