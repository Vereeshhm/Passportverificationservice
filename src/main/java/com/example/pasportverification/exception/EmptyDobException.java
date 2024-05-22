package com.example.pasportverification.exception;

public class EmptyDobException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public EmptyDobException(String message) {
        super(message);
    }
}
