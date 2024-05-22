package com.example.pasportverification.exception;

public class EmptyNameException extends RuntimeException {

	
	
	private static final long serialVersionUID = 1L;

	public EmptyNameException(String message) {
        super(message);
    }
}
