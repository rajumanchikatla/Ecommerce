package com.Ecom.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice	
public class EcomExceptions{
	
	 @ExceptionHandler(ProductNotFoundException.class)
	    public ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }

}
