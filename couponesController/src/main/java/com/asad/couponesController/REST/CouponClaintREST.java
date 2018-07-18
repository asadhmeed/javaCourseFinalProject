package com.asad.couponesController.REST;

import com.asad.couponesController.LogIn;
import com.asad.couponesController.RequestData;
import com.asad.couponesController.Response;
import com.asad.couponesController.exceptions.IdIsNullException;
import com.asad.couponesController.exceptions.LogInDataIsNullException;
import com.asad.couponesController.exceptions.NotLogedInException;
import com.asad.couponesController.exceptions.RequestDataIsNullException;

public interface CouponClaintREST {
	
	
	public Response logIn(LogIn logIn)throws LogInDataIsNullException, RequestDataIsNullException ;
	
	public  Response logout(RequestData Id) throws IdIsNullException, RequestDataIsNullException, NotLogedInException;
}
