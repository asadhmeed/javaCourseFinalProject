package com.asad.couponesController.administrator;

import com.asad.couponesController.LogIn;
import com.asad.couponesController.LogInResponse;
import com.asad.couponesController.RequestData;
import com.asad.couponesController.enums.ResponseMassageEnum;
import com.asad.couponesController.exceptions.IdIsNullException;
import com.asad.couponesController.exceptions.LogInDataIsNullException;
import com.asad.couponesController.exceptions.RequestDataIsNullException;
import com.asad.couponesController.exceptions.notLogedInException;

public interface LogInService  {

	
	public LogInResponse logIn(LogIn logIn)  throws LogInDataIsNullException, RequestDataIsNullException;
	public  ResponseMassageEnum logout(Long Id) throws  RequestDataIsNullException, IdIsNullException;
	
}
