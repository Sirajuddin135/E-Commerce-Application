package com.app.exception;

import java.time.LocalDateTime;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
		
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetail> myEhandler(Exception e, WebRequest req) {
		MyErrorDetail err = new MyErrorDetail();
		
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetail>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<MyErrorDetail> myNFEhandler(NotFoundException nfe, WebRequest req) {
		MyErrorDetail err = new MyErrorDetail();
		
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(nfe.getMessage());
		err.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetail>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetail> myMVNEhandler(MethodArgumentNotValidException me) {
		MyErrorDetail err = new MyErrorDetail();
		
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage("Validation Error");
		err.setDescription(me.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<MyErrorDetail>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<MyErrorDetail> myEhandlerCustomer(CustomerException e, WebRequest req) {
		
		MyErrorDetail err = new MyErrorDetail(LocalDateTime.now(),e.getMessage(),req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetail>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyErrorDetail> loginExceptionHandler(LoginException se, WebRequest req){
		
		
		MyErrorDetail err= new MyErrorDetail();
			err.setTimeStamp(LocalDateTime.now());
			err.setMessage(se.getMessage());
			err.setDescription(req.getDescription(false));
				
		return new ResponseEntity<MyErrorDetail>(err, HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(OrderException.class)
	public ResponseEntity<MyErrorDetail> myOEHandler(OrderException oe, WebRequest req) {
		MyErrorDetail err = new MyErrorDetail(LocalDateTime.now(), oe.getMessage(),req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetail>(err, HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
