package com.app.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.payloads.APIResponse;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class MyGlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<APIResponse> myResourceNotFoundException(ResourceNotFoundException e) {
		String message = e.getMessage();

		APIResponse res = new APIResponse(message, false);

		return new ResponseEntity<APIResponse>(res, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(APIException.class)
	public ResponseEntity<APIResponse> myAPIException(APIException e) {
		String message = e.getMessage();

		APIResponse res = new APIResponse(message, false);

		return new ResponseEntity<APIResponse>(res, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> myMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		Map<String, String> res = new HashMap<>();

		e.getBindingResult().getAllErrors().forEach(err -> {
			String fieldName = ((FieldError) err).getField();
			String message = err.getDefaultMessage();

			res.put(fieldName, message);
		});

		return new ResponseEntity<Map<String, String>>(res, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String, String>> myConstraintsVoilationException(ConstraintViolationException e) {
		Map<String, String> res = new HashMap<>();

		e.getConstraintViolations().forEach(voilation -> {
			String fieldName = voilation.getPropertyPath().toString();
			String message = voilation.getMessage();

			res.put(fieldName, message);
		});

		return new ResponseEntity<Map<String, String>>(res, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<String> myAuthenticationException(AuthenticationException e) {

		String res = e.getMessage();
		
		return new ResponseEntity<String>(res, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingPathVariableException.class)
	public ResponseEntity<APIResponse> myMissingPathVariableException(MissingPathVariableException e) {
		APIResponse res = new APIResponse(e.getMessage(), false);

		return new ResponseEntity<APIResponse>(res, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<APIResponse> myDataIntegrityException(DataIntegrityViolationException e) {
		APIResponse res = new APIResponse(e.getMessage(), false);

		return new ResponseEntity<APIResponse>(res, HttpStatus.BAD_REQUEST);
	}
}
