package com.asad.couponesController.customer;

import java.util.List;
import java.util.Set;
import com.asad.couponesController.RequestData;
import com.asad.couponesController.administrator.LogInService;
import com.asad.couponesController.entitys.Coupon;
import com.asad.couponesController.enums.ResponseMassageEnum;
import com.asad.couponesController.exceptions.ComponentNotFoundException;
import com.asad.couponesController.exceptions.CouponIsAlreadyPurchasedException;
import com.asad.couponesController.exceptions.CustomerPurchaseDataException;
import com.asad.couponesController.exceptions.IdIsNullException;
import com.asad.couponesController.exceptions.IncomeIsNullException;
import com.asad.couponesController.exceptions.RequestDataIsNullException;
import com.asad.couponesController.exceptions.NotLogedInException;

public interface CustomerServices extends LogInService {
	
	ResponseMassageEnum beyACoupon(RequestData couponData) throws CouponIsAlreadyPurchasedException, IdIsNullException,
			CustomerPurchaseDataException, IncomeIsNullException, RequestDataIsNullException, NotLogedInException;

	List<Coupon> getAllCoupon();

	Set<Coupon> getAllCouponsForCustomer(RequestData requestData)
			throws IdIsNullException, ComponentNotFoundException, NotLogedInException, RequestDataIsNullException;
   
	Set<Coupon> getSpecificCouponsForCustomer(RequestData requestData)
			throws IdIsNullException, ComponentNotFoundException, NotLogedInException, RequestDataIsNullException;

}
