package com.asad.couponesController.exceptions;

import com.asad.couponesController.enums.ResponseMassageEnum;

public class RequestDataIsNullException extends Exception {

	public RequestDataIsNullException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	
	}

	public RequestDataIsNullException(String arg0) {
		super(arg0);
	
	}

	public RequestDataIsNullException(Throwable arg0) {
		super(arg0);
	
	}

	
	
}
