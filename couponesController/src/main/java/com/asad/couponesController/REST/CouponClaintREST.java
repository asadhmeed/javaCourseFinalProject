package com.asad.couponesController.REST;

import com.asad.couponesController.LogIn;
import com.asad.couponesController.LogInResponse;
import com.asad.couponesController.Response;
import com.asad.couponesController.enums.LogInEnum;
import com.asad.couponesController.enums.ResponseMassageEnum;
import com.asad.couponesController.exceptions.IdIsNullException;
import com.asad.couponesController.exceptions.LogInDataIsNullException;
import com.asad.couponesController.exceptions.RequestDataIsNullException;

public interface CouponClaintREST {
	
	
	public Response logIn(LogIn logIn)throws LogInDataIsNullException, RequestDataIsNullException ;
	
	public  Response logout(Long Id) throws IdIsNullException, RequestDataIsNullException;
}
