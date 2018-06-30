package com.asad.couponesController.exceptions;

public class CustomerPurchaseDataException extends Exception {

	public CustomerPurchaseDataException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	
	}

	public CustomerPurchaseDataException(String arg0) {
		super(arg0);
	}

	public CustomerPurchaseDataException(Throwable arg0) {
		super(arg0);
	}

	
}
