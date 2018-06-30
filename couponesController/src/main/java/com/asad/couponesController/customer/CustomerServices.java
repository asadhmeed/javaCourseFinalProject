package com.asad.couponesController.customer;

import java.util.Set;

import com.asad.couponesController.CustomerPurchaseData;
import com.asad.couponesController.LogIn;
import com.asad.couponesController.LogInResponse;
import com.asad.couponesController.administrator.LogInService;
import com.asad.couponesController.IncomeData;
import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.enums.ResponseMassageEnum;
import com.asad.couponesController.exceptions.ComponentNotFoundException;
import com.asad.couponesController.exceptions.CouponIsAlreadyPurchasedException;
import com.asad.couponesController.exceptions.CustomerPurchaseDataException;
import com.asad.couponesController.exceptions.IdIsNullException;
import com.asad.couponesController.exceptions.IncomeIsNullException;

public interface CustomerServices extends LogInService {
	Coupon beyACoupon(CustomerPurchaseData customerData)throws CouponIsAlreadyPurchasedException, IdIsNullException,CustomerPurchaseDataException, IncomeIsNullException;
	
	public  ResponseMassageEnum logout(Long CustomerId);
	Set<Coupon> getAllCoupon(Long customerId) throws IdIsNullException, ComponentNotFoundException;
}
