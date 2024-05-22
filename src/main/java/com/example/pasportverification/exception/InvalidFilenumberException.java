package com.example.pasportverification.exception;

public class InvalidFilenumberException extends RuntimeException{

	
	
	private static final long serialVersionUID = 1L;

	public InvalidFilenumberException(String message) {
        super(message);
    }
}
