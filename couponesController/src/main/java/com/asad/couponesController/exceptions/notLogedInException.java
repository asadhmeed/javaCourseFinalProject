package com.asad.couponesController.exceptions;

public class notLogedInException extends Exception {

	public notLogedInException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public notLogedInException(String arg0) {
		super(arg0);
	}

	public notLogedInException(Throwable arg0) {
		super(arg0);
	}

}
