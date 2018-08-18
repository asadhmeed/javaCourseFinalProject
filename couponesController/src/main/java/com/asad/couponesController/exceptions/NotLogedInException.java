package com.asad.couponesController.exceptions;

public class NotLogedInException extends Exception {

	public NotLogedInException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public NotLogedInException(String arg0) {
		super(arg0);
	}

	public NotLogedInException(Throwable arg0) {
		super(arg0);
	}

}
