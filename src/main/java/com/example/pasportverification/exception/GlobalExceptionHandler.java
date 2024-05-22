package com.example.pasportverification.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EmptyfileNumberrexception.class)
	public ResponseEntity<String> handleEmptyfileNumberrexception(EmptyfileNumberrexception ex) {
		// Create a custom response body
		String responseBody = "{\"error\": " + "{\"name\": \"error\", "
				+ "\"message\": \"fileNumber is not allowed to be empty string\", " + "\"status\": \"Bad Request\", "
				+ "\"statusCode\": 400}}";

		// Return the custom response with HTTP status code 400
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
	}

	@ExceptionHandler(EmptyDobException.class)
	public ResponseEntity<String> handleEmptyDobException(EmptyDobException ex) {
		// Create a custom response body
		String responseBody = "{\"error\": " + "{\"name\": \"error\", "
				+ "\"message\": \"dob is not allowed to be empty string\", " + "\"status\": \"Bad Request\", "
				+ "\"statusCode\": 400}}";

		// Return the custom response with HTTP status code 400
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
	}

	@ExceptionHandler(InvalidFilenumberException.class)
	public ResponseEntity<String> handleInvalidFilenumberException(InvalidFilenumberException ex) {
		// Create a custom response body
		String responseBody = "{\"error\": " + "{\"statusCode\": 400, " + "\"name\": \"error\", "
				+ "\"message\": \"Invalid File Number\"," + "\"status\": 400}}";

		// Return the custom response with HTTP status code 400
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
	}

	@ExceptionHandler(FilenumberRequiredException.class)
	public ResponseEntity<String> handleFilenumberRequiredException(FilenumberRequiredException ex) {
		// Create a custom response body
		String responseBody = "{\"error\": " + "{\"name\": \"error\", " + "\"message\": \"fileNumber is required\", "
				+ "\"status\": \"Bad Request\", " + "\"statusCode\": 400}}";

		// Return the custom response with HTTP status code 400
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
	}

	@ExceptionHandler(EmptyNameException.class)
	public ResponseEntity<String> handleEmptyNameException(EmptyNameException ex) {
		// Create a custom response body
		String responseBody = "{\"error\": " + "{\"name\": \"error\", "
				+ "\"message\": \"name is not allowed to be empty string\", " + "\"status\": \"Bad Request\", "
				+ "\"statusCode\": 400}}";

		// Return the custom response with HTTP status code 400
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
	}

	@ExceptionHandler(filenumbernotfoundexception.class)
	public ResponseEntity<String> handlefilenumbernotfoundexception(filenumbernotfoundexception ex) {
		// Create a custom response body
		String responseBody = "{\"error\": " + "{\"statusCode\": 404, " + "\"name\": \"error\", "
				+ "\"message\": \"fileNumber is not found\"," + "\"status\": 404}}";

		// Return the custom response with HTTP status code 400
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
	}

	@ExceptionHandler(InvalidNameException.class)
	public ResponseEntity<String> handleInvalidNameException(InvalidNameException ex) {
		// Create a custom response body
		String responseBody = "{\"error\": " + "{\"statusCode\": 400, " + "\"name\": \"error\", "
				+ "\"message\": \"Invalid Name\"," + "\"status\": 400}}";

		// Return the custom response with HTTP status code 400
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
	}

	@ExceptionHandler(InvalidDobException.class)
	public ResponseEntity<String> handleInvalidDobException(InvalidDobException ex) {
		// Create a custom response body
		String responseBody = "{\"error\": " + "{\"statusCode\": 400, " + "\"name\": \"error\", "
				+ "\"message\": \"Invalid dob\"," + "\"status\": 400}}";

		// Return the custom response with HTTP status code 400
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
	}

}
