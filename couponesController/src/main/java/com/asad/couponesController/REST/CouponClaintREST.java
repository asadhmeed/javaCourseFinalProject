package com.asad.couponesController.REST;

import com.asad.couponesController.LogIn;
import com.asad.couponesController.LogInResponse;
import com.asad.couponesController.Response;
import com.asad.couponesController.enums.LogInEnum;
import com.asad.couponesController.enums.ResponseMassageEnum;
import com.asad.couponesController.exceptions.IdIsNullException;
import com.asad.couponesController.exceptions.LogInDataIsNullException;

public interface CouponClaintREST {
	
	
	public Response logIn(LogIn logIn)throws LogInDataIsNullException ;
	
	public  Response logout(Long Id) throws IdIsNullException;
}
