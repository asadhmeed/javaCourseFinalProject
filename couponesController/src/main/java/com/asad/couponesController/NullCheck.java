package com.asad.couponesController;

import com.asad.couponesController.exceptions.RequestDataIsNullException;

public class NullCheck {

	
	
	
	public static void checkIfItIsNull(Object object, String massage) throws RequestDataIsNullException {
		if (object == null) {
			throw new RequestDataIsNullException(massage);
		}
	}
}
