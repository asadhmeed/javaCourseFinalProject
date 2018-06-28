package com.asad.couponesController.customer;

import com.asad.couponesController.CustomerPurchaseData;
import com.asad.couponesController.LogIn;
import com.asad.couponesController.LogInResponse;
import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.enums.ResponseMassageEnum;

public interface CustomerServices {
	Coupon beyACoupon(CustomerPurchaseData customerData);
	public  LogInResponse logInCheck(LogIn logIn);
	public  ResponseMassageEnum logout(Long CustomerId);
}
