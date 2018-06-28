package com.asad.couponesController.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class NameIsUsedException extends Exception{

	public NameIsUsedException(String massage,Exception e ) {
		super(massage, e);
		
	}

	
}
