package com.asad.couponesController.administrator;

import com.asad.couponesController.LogIn;
import com.asad.couponesController.LogInResponse;
import com.asad.couponesController.enums.ResponseMassageEnum;
import com.asad.couponesController.exceptions.IdIsNullException;
import com.asad.couponesController.exceptions.LogInDataIsNullException;

public interface LogInService  {

	
	public LogInResponse logInCheck(LogIn logIn)  throws LogInDataIsNullException;
	public  ResponseMassageEnum logout(Long Id) throws IdIsNullException;
}
