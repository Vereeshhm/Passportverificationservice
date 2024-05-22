package com.example.pasportverification.exception1;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.pasportverification.Entity.pasportdto;

@ControllerAdvice
public class GlobalExceptionHandler1 {

	@ExceptionHandler(Emptyfilenumberexception.class)
	public ResponseEntity<String> handleEmptyfilenumberexception(Emptyfilenumberexception ex) {
		// Create a custom response body
		String responseBody = "{\"error\": " + "{\"name\": \"error\", "
				+ "\"message\": \"fileNumber is not allowed to be empty string\", " + "\"status\": \"Bad Request\", "
				+ "\"statusCode\": 400}}";

		// Return the custom response with HTTP status code 400
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
	}

	@ExceptionHandler(Emptydobexception.class)
	public ResponseEntity<String> handleEmptydobexception(Emptydobexception ex) {
		String responseBody = "{\"error\": " + "{\"name\": \"error\", "
				+ "\"message\": \"dob is not allowed to be empty string\", " + "\"status\": \"Bad Request\", "
				+ "\"statusCode\": 400}}";

		// Return the custom response with HTTP status code 400
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
	}

	@ExceptionHandler(Filenumberrequiredexception.class)
	public ResponseEntity<String> handleFilenumberrequiredexception(Filenumberrequiredexception ex) {
		String responseBody = "{\"error\": " + "{\"statusCode\": 400, " + "\"name\": \"error\", "
				+ "\"message\": \"Invalid File Number\"," + "\"status\": 400}}";

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
	}

	@ExceptionHandler(InvalidDOBexception.class)
	public ResponseEntity<String> handleInvalidDobException(InvalidDOBexception ex) {
		// Create a custom response body
		String responseBody = "{\"error\": " + "{\"statusCode\": 400, " + "\"name\": \"error\", "
				+ "\"message\": \"Invalid dob\"," + "\"status\": 400}}";

		// Return the custom response with HTTP status code 400
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
	}

	@ExceptionHandler(filenotfoundexception.class)
	public ResponseEntity<String> handlefilenotfoundexception(filenotfoundexception ex) {

		String responseBody = "{\"error\": " + "{\"statusCode\": 404, " + "\"name\": \"error\", "
				+ "\"message\": \"fileNumber is not found\"," + "\"status\": 404}}";

		// Return the custom response with HTTP status code 400
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Map<String, Object>> handleBadRequestException(BadRequestException ex,
			@RequestBody pasportdto pasportdto) {
		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put("name", "error");
		errorResponse.put("message", "fileNumber is required");
		errorResponse.put("status", "Bad Request");
		errorResponse.put("statusCode", 400);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
