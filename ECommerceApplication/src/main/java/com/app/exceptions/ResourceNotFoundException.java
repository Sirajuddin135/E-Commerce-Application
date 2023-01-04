package com.app.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	String resourceName;
	String field;
	String fieldName;
	
	public ResourceNotFoundException() {}
	
	public ResourceNotFoundException(String resourceName, String field, String fieldName) {
		super(String.format("%s not found with %s: %s", resourceName, field, fieldName));
		this.resourceName = resourceName;
		this.field = field;
		this.fieldName = fieldName;
	}
}
