package com.example.pasportverification.exception;

public class FilenumberRequiredException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FilenumberRequiredException(String message) {
        super(message);
    }
}
