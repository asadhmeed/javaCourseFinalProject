package com.asad.couponesController.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class ComponentNotFoundException extends Exception{

	public ComponentNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	
	
}
