package com.asad.couponesController.exceptions;

public class LogInDataIsNullException extends Exception {

	public LogInDataIsNullException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	public LogInDataIsNullException(String arg0) {
		super(arg0);
		}

	public LogInDataIsNullException(Throwable arg0) {
		super(arg0);
		}

	
}
